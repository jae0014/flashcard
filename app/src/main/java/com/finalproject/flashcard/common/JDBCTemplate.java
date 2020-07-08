package com.finalproject.flashcard.common;

import java.io.*;
import java.sql.*;
import java.util.*;

public class JDBCTemplate {

    public static Connection getConnection(){
        Connection con = null;

        try {

            //위에 코드 줄여서 사용할 수 있음
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "sew", "1234");

            //자동 커밋 되지 않도록 트렌젝션 관리
            con.setAutoCommit(false);

        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return con;
    }

    public static void close(Connection con){
        try {
            if(con != null && !con.isClosed()){
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close(Statement stmt){
        try {
            if(stmt != null && !stmt.isClosed()){
                stmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close(ResultSet rset){
        try {
            if(rset != null && !rset.isClosed()){
                rset.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void commit(Connection con){
        try {
            if(con != null && !con.isClosed()){
                con.commit();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void rollback(Connection con){
        try {
            if(con != null && !con.isClosed()){
                con.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
