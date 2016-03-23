package com.pickth.schoolproject;

/**
 * Created by Kim on 2016-03-16.
 */
public class ProjectListItem {
    private String name;
    private String date;
    private String explanation;

    public ProjectListItem(String name, String date, String explanation){
        this.date = date;
        this.name = name;
        this.explanation = explanation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }
}
