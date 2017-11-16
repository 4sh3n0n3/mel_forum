package com.ss13.pojo;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Profile {
    private int id;
    private String avatar;
    private String firstName;
    private String lastName;
    private int rating;
    private String aboutSelf;
    private Date registrationDate;
    private List<Reward> rewards_list = new LinkedList<Reward>();

    public void setId(int id) {
        this.id = id;
    }

    public void setRewards_list(List<Reward> rewards_list) {
        this.rewards_list = rewards_list;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setAboutSelf(String aboutSelf) {
        this.aboutSelf = aboutSelf;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getRating() {
        return rating;
    }

    public String getAboutSelf() {
        return aboutSelf;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public List<Reward> getRewards_list() {
        return rewards_list;
    }

    public int getId() {
        return id;
    }
}
