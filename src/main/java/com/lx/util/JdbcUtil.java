package com.lx.util;


import java.sql.*;

/**
 * @Author: lx
 * @Date: 2019/8/1 9:19
 */
public class JdbcUtil {




    private static final String driver = "com.mysql.cj.jdbc.Driver";

    private static final String url= "jdbc:mysql://192.168.56.10/spider?serverTimezone=GMT%2B8";

    private static final String username = "lingx";

    private static final String password = "Lingx_2019";


    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }



    public static Connection getConnection()throws SQLException{
        return DriverManager.getConnection(url,username,password);
    }


    public static void release(Connection conn,Statement st,ResultSet rs){
        if (conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (st != null){
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}
