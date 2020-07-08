package com.finalproject.flashcard.myjava.model.vo;

import java.util.Objects;

public class Member {


    private String userID;
    private String password;
    private String userName;
    private String email;
    private String address;
    private String introduce;
    private String nickName;
    private String birthday;


    public Member(){}


    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Member)) return false;
        Member member = (Member) o;
        return Objects.equals(userID, member.userID) &&
                Objects.equals(password, member.password) &&
                Objects.equals(userName, member.userName) &&
                Objects.equals(email, member.email) &&
                Objects.equals(address, member.address) &&
                Objects.equals(introduce, member.introduce) &&
                Objects.equals(nickName, member.nickName) &&
                Objects.equals(birthday, member.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userID, password, userName, email, address, introduce, nickName, birthday);
    }

    @Override
    public String toString() {
        return "Member{" +
                "userID='" + userID + '\'' +
                ", password='" + password + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", introduce='" + introduce + '\'' +
                ", nickName='" + nickName + '\'' +
                ", birthday='" + birthday + '\'' +
                '}';
    }
}
