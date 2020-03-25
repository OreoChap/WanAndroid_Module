package com.oreooo.todolist.lItepal;

import org.litepal.crud.LitePalSupport;

public class Project extends LitePalSupport {

    private String addTime;
    private String thePlan ;
    private String doneTime;
    private String doneDate ;
    private boolean done ;

    public Project() {

    }

    public Project(String addTime, String thePlan) {
        this.addTime = addTime;
        this.thePlan = thePlan;
        this.doneTime = "0";
        this.doneDate = "0";
        this.done = false;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public String getThePlan() {
        return thePlan;
    }

    public void setThePlan(String thePlan) {
        this.thePlan = thePlan;
    }

    public String getDoneTime() {
        return doneTime;
    }

    public void setDoneTime(String doneTime) {
        this.doneTime = doneTime;
    }

    public String getDoneDate() {
        return doneDate;
    }

    public void setDoneDate(String doneDate) {
        this.doneDate = doneDate;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
