package com.perfect.core;

/**
 * Created by yousheng on 15/12/16.
 */
public class SystemUserInfo {
    //    private String ip;
    private String userId;

    private String user;

    private boolean isSuper;

//    public String getIp() {
//        return ip;
//    }
//
//    public SystemUserInfo setIp(String ip) {
//        this.ip = ip;
//        return this;
//    }

    public String getUser() {
        return user;
    }

    public SystemUserInfo setUser(String user) {
        this.user = user;
        return this;
    }

    @Override
    public String toString() {
        return "SystemUserInfo{" +
                "user='" + user + '\'' +
                ", isSuper=" + isSuper +
                '}';
    }

    public boolean isSuper() {
        return isSuper;
    }

    public SystemUserInfo setIsSuper(boolean isSuper) {
        this.isSuper = isSuper;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public SystemUserInfo setUserId(String userId) {
        this.userId = userId;
        return this;
    }
}
