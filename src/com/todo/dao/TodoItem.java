package com.todo.dao;

import java.util.Date;
import java.text.SimpleDateFormat;

public class TodoItem {
    private String title;
    private String desc;
    private Date current_date;
    private String time;
    
    SimpleDateFormat format = new SimpleDateFormat("yyyy년 MM월dd일 HH시mm분ss초");


    public TodoItem(String title, String desc){
        this.title=title;
        this.desc=desc;
        this.current_date=new Date();
        this.time = format.format(current_date);
    }
    
    public TodoItem(String title, String desc, String time) {
    	this.title=title;
        this.desc=desc;
        this.time = time;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Date getCurrent_date() {
        return current_date;
    }

    public void setCurrent_date(Date current_date) {
        this.current_date = current_date;
    }
    public String getTime() {
    	return time;
    }
    public void setTime(String time) {
    	this.time = time;
    }
    public String toSaveString() {
    	return title + "##" + desc + "##" + time + "\n";
    }
}
