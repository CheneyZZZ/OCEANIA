package com.example.codeneuron.VO;

public class ProjectForm {
    private String name;

    private int userId;

    private double closenessThreshold;

    public ProjectForm(){}
    public ProjectForm(String name, int userId, double closenessThreshold){
        this.name = name;
        this.userId=userId;
        this.closenessThreshold=closenessThreshold;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getClosenessThreshold() {
        return closenessThreshold;
    }

    public void setClosenessThreshold(double closenessThreshold) {
        this.closenessThreshold = closenessThreshold;
    }
}
