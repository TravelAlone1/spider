package com.lx.spider.pipeline;

import java.io.*;

/**
 * @Author: lx
 * @Date: 2019/8/5 21:54
 */
public class FilePipeline implements Pipeline {
    public String path;

    public FilePipeline(String path){
        this.path = path;
    }
    @Override
    public void process(String input) {
        File file = new File(path);
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        FileOutputStream fos = null;
        FileWriter fw = null;

        try {

            fw = new FileWriter(file, true);
            fos = new FileOutputStream(file,true);//打开流
            PrintWriter pw = new PrintWriter(fw);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos, "UTF-8"));

            bw.write(input);

            //换行
            bw.newLine();
            bw.close();

            //fos.write(outStr);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
