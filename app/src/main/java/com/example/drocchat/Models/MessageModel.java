package com.example.drocchat.Models;

public class MessageModel {
    String uid, message;
    Long timestamp;

    public MessageModel(String uid, String message, Long timestamp) {
        this.uid = uid;
        this.message = message;
        this.timestamp = timestamp;
    }

    public MessageModel(String uid, String message) {
        this.uid = uid;
        this.message = message;
    }

    public MessageModel(){}

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
