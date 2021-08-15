package com.example.drocchat.Models;

public class Users {

//    users getter and setter. :D
    String username, email, password, id, profilePic, lastMessage, Status;

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getEmail() {
        return email;
    }
    public String getStatus() {
        return Status;
    }
    public void setStatus(String status) {
        Status = status;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getProfilePic() {
        return profilePic;
    }
    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }
    public String getLastMessage() {
        return lastMessage;
    }
    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }


// users constructor. :D
    public Users(String username, String email, String password, String id, String profilePic, String lastMessage) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.id = id;
        this.profilePic = profilePic;
        this.lastMessage = lastMessage;
    }

//    separate users constructor for signup activity. :D
    public Users() {
    }
    public Users(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;

    }


    public Users(String email) {
        this.email = email;
    }

//    I write this code again bcoz i was getting some errors. :D
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
}


// so this is very important model in any social media app.
// it is basic template form for all the users characteristics.