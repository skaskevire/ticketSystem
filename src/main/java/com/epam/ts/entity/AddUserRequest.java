package com.epam.ts.entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AddUserRequest {
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
