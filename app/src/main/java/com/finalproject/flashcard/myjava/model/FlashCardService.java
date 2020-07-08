package com.finalproject.flashcard.myjava.model;

import com.finalproject.flashcard.myjava.model.dao.FlashcardDao;
import com.finalproject.flashcard.myjava.model.vo.Member;

import java.sql.Connection;

import static com.finalproject.flashcard.common.JDBCTemplate.*;

public class FlashCardService {

    public int insertMember(Member m) {
        Connection conn = getConnection();
        FlashcardDao dao = new FlashcardDao();
        int result = dao.insertMember(m,conn);
        if(result > 0)
            commit(conn);
        else
            rollback(conn);
        close(conn);
        return result;
    }
    public Member loginMember(Member m) {
        Connection conn = getConnection();
        FlashcardDao dao = new FlashcardDao();
        Member result = dao.loginMember(m,conn);
        close(conn);
        return result;
    }
}
