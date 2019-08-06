package com.lx.spider.processor;

import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.lx.spider.entity.Movie;
import com.lx.spider.entity.WebPage;
import com.lx.spider.pipeline.FilePipeline;
import com.lx.util.JdbcUtil;
import com.lx.util.UploadData;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: lx
 * @Date: 7/31/2019 8:20 PM
 */
public class DoubanProcessor implements Processor {
    @Override
    public List<String> process(String data) {


        ArrayList<String> list = new ArrayList<>();

        Document document = Jsoup.parse(data);

        Elements elements = document.getElementsByTag("a");

        for (Element e : elements) {
            String link = e.attr("abs:href");
            if (!list.contains(link) && !"".equals(link)) {
                list.add(link);
            }

        }
        System.out.println(list.size());
        return list;
    }

    @Override
    public void parseItem(WebPage webPage) {
        System.out.println("aaaaaaaaaaaaa");
        String data = webPage.getContent();
        String url = webPage.getUrl();
        String id = url.replace("https://movie.douban.com/subject/", "");
        id = id.split("/")[0];


        Movie movie = new Movie();
        Gson gson = new Gson();
        FilePipeline filePipeline = new FilePipeline("D:\\work\\spider\\douban.txt");
        double scores = 0.0;

        String anotherName = "";
        String country = "";
        String language = "";
        Document document = Jsoup.parse(data);


        Element title = document.selectFirst("#content > h1 > span:nth-child(1)");
        if (null != title) { 
            String title1 = title.text();
            movie.setTitle(title1);
        }

        Element year = document.selectFirst("#content > h1 > span.year");
        if (null != year) {
            String text = year.text();
            movie.setYear(text);
        }

        Element director = document.selectFirst("#info > span:nth-child(1) > span.attrs");
        if (null != director) {
            String text = director.text();
            movie.setDirector(text);
        }

        Element screenWriter = document.selectFirst("#info > span:nth-child(3)");
        if (null != screenWriter) {
            String text = screenWriter.text();
            if (!Strings.isNullOrEmpty(text)) {
                text = text.replace("编剧: ", "");
            }
            movie.setScreenWriter(text);
        }
        Element actorE = document.selectFirst("#info > span.actor > span.attrs");
        if (null != actorE) {
            String actor = actorE.text();
            if (!Strings.isNullOrEmpty(actor) && actor.contains("更多...")) {
                actor = actor.replace("更多...", "");
            }
            movie.setToStar(actor);
        }
        Element type = document.selectFirst("#info > span[property='v:genre']");
        if (null != type) {
            String text = type.text();
            movie.setType(text);
        }
        String info = document.select("#info").text();
        if (Strings.isNullOrEmpty(info)){
            return;
        }

        Element showTime = document.selectFirst("#info > span:nth-child(19)");
        if (null != showTime) {
            String text = showTime.text();
            movie.setShowTime(text);
        }

        Element fileTime = document.selectFirst("#info > span:nth-child(22)");
        if (null != fileTime) {
            String text = fileTime.text();
            movie.setFileTime(text);
        }

        Element IMDLink = document.selectFirst("#info > a");
        if (null != IMDLink) {
            String text = IMDLink.text();
            String ImdLink = "http://www.imdb.com/title/" + text;
            movie.setIMDLink(ImdLink);
        }
        //#interest_sectl > div.rating_wrap.clearbox > div.rating_self.clearfix > strong
        Element score = document.selectFirst("#interest_sectl > div.rating_wrap.clearbox > div.rating_self.clearfix > strong");
        if (null != score) {
            String text = score.text();
            movie.setScore(text);
        }



        if (info.indexOf("制片国家/地区:") !=-1 && info.indexOf("语言:") !=-1) {
            country = info.substring(info.indexOf("制片国家/地区:") + 9, info.indexOf("语言:"));
        }

        if (!Strings.isNullOrEmpty(country)) {
            movie.setCountry(country);
        }

        //String language = info.substring(info.indexOf("语言:") + 4, info.indexOf("上映日期"));

        if (info.indexOf("语言:") !=-1 && info.indexOf("上映日期") !=-1) {
            language = info.substring(info.indexOf("语言:") + 4, info.indexOf("上映日期"));
        }

        if (!Strings.isNullOrEmpty(language)) {
            movie.setLanguage(language);
        }


        if (info.indexOf("IMDb链接:") != -1 && info.indexOf("又名:")+3 != 2) {
            anotherName = info.substring(info.indexOf("又名:") + 3, info.indexOf("IMDb链接:"));
        } else {
            anotherName = info.substring(info.indexOf("又名:") + 3);
        }
        if (!Strings.isNullOrEmpty(anotherName)) {
            anotherName = anotherName.trim();
            movie.setAnotherName(anotherName);
        }
        //anotherName =info.substring(info.indexOf("又名:")+3);


        movie.setId(id);
        System.out.println(id);
        System.out.println(year);

//        System.out.println(content);
//        System.out.println(director);
//        System.out.println(screenWriter);
//        System.out.println(toStar);
//        System.out.println(type);
//        System.out.println(country);
//        System.out.println(language);
//        System.out.println(showTime);
//        System.out.println(fileTime);
        // System.out.println(ImdLink);
        System.out.println(scores);
        System.out.println(anotherName);
        String strJson = gson.toJson(movie);
        filePipeline.process(strJson);


        //return JSONResult.success(ResultCode.SUCCESS,movie);
        //save(movie);
        //System.out.println(country);

    }

    public void save(Movie movie) {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            conn = JdbcUtil.getConnection();
            System.out.println(conn);
            String sql = "insert into movie(id,title,director,screen_writer,to_star,type,country,language,show_time,file_time,another_name,IMD_link,score) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
            st = conn.prepareStatement(sql);
            st.setString(1, movie.getId());
            st.setString(2, movie.getTitle());
            st.setString(3, movie.getDirector());
            st.setString(4, movie.getScreenWriter());
            st.setString(5, movie.getToStar());
            st.setString(6, movie.getType());
            st.setString(7, movie.getCountry());
            st.setString(8, movie.getLanguage());
            st.setString(9, movie.getShowTime());
            st.setString(10, movie.getFileTime());
            st.setString(11, movie.getAnotherName());
            st.setString(12, movie.getIMDLink());
            st.setString(13, movie.getScore());

            int res = st.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.release(conn, st, rs);
        }

    }




    /*public void update(){
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtil.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/
}
