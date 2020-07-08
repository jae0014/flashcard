package com.finalproject.flashcard.myjava.model.dao;

import com.finalproject.flashcard.myjava.model.vo.Member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.finalproject.flashcard.common.JDBCTemplate.close;

public class FlashcardDao {
    public int insertMember(Member user, Connection con) {
        int result = 0;

        PreparedStatement pstmt = null;
        String query =
                "INSERT INTO MEMBER (UID, PWD, NAME, EMAIL,    ADDRESS, INTRODUCE,NICKNAME, BIRTH_DATE,   UPDATE_DATE, ENROLL_DATE, STATUS)" +
                "VALUES (?,?,?,?  ,?,?,?,?,  SYSDATE, SYSDATE, 'Y');";

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, user.getUserID());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getUserName());
            pstmt.setString(4, user.getEmail());
            pstmt.setString(5, user.getAddress());
            pstmt.setString(6, user.getIntroduce());
            pstmt.setString(7, user.getNickName());
            pstmt.setString(8, user.getBirthday());
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Member loginMember(Member user, Connection con) {

        Member u = null;
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        try {
            u = new Member();
            String query = "SELECT UID,NAME,NICKNAME FROM MEMBER ";

            pstmt = con.prepareStatement(query);

            rset = pstmt.executeQuery();
            while(rset.next())
            {
                u.setUserID(rset.getString("UID"));
                u.setUserName(rset.getString("NAME"));
                u.setNickName(rset.getString("NICKNAME"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
        }

        return u;
    }
}
