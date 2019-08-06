package com.lx.spider.entity;

import lombok.Data;

/**
 * @Author: lx
 * @Date: 2019/7/31 21:35
 */
@Data
public class Movie {

    private String id;

    private String title;

    private String year;

    private String director;

    private String screenWriter;

    private String toStar;

    private String type;

    private String country;

    private String language;

    private String showTime;

    private String fileTime;

    private String anotherName;

    private String IMDLink;

    private String score;

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getScreenWriter() {
        return screenWriter;
    }

    public void setScreenWriter(String screenWriter) {
        this.screenWriter = screenWriter;
    }

    public String getToStar() {
        return toStar;
    }

    public void setToStar(String toStar) {
        this.toStar = toStar;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getShowTime() {
        return showTime;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }

    public String getFileTime() {
        return fileTime;
    }

    public void setFileTime(String fileTime) {
        this.fileTime = fileTime;
    }

    public String getAnotherName() {
        return anotherName;
    }

    public void setAnotherName(String anotherName) {
        this.anotherName = anotherName;
    }

    public String getIMDLink() {
        return IMDLink;
    }

    public void setIMDLink(String IMDLink) {
        this.IMDLink = IMDLink;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }






}
