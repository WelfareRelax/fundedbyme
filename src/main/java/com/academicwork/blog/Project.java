package com.academicwork.blog;

import java.math.BigDecimal;

public class Project {

    public long id; //TODO: tillfällig för att inte förstöra annan kod
    public long projectID;
    public String title;
    public long userID;
    public String description;
    public BigDecimal requestedAmount;


    public Project(){

    }
    public Project(long id, String title){
        this.title=title;
        this.id=id;
    }

    public void setTitle(String title) {
        this.title = title;
    }



    public void setDescription(String description) {
        this.description = description;
    }

    public void setRequestedAmount(BigDecimal requestedAmount) {
        this.requestedAmount = requestedAmount;
    }



    public String getTitle() {
        return title;
    }



    public String getDescription() {
        return description;
    }

    public BigDecimal getRequestedAmount() {
        return requestedAmount;
    }

    public long getProjectID() {
        return projectID;
    }

    public long getUserID() {
        return userID;
    }

    public void setProjectID(long projectID) {
        this.projectID = projectID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }
}
