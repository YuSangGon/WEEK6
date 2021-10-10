package com.todo.dao;

import java.util.Date;
import java.text.SimpleDateFormat;

public class TodoItem {
    private String title;
    private String desc;
    private Date current_date;
    private String time;
    private String category;
    private String due_date;
    private int id;
    private int is_completed;
    
    SimpleDateFormat format = new SimpleDateFormat("yyyy년 MM월dd일 HH시mm분ss초");


    public TodoItem(String title, String desc, String category, String due_date){
        this.title=title;
        this.desc=desc;
        this.current_date=new Date();
        this.time = format.format(current_date);
        this.category = category;
        this.due_date = due_date;
        this.is_completed = 0;
    }
    
    public TodoItem(String title, String desc, String time, String category, String due_date) {
    	this.title=title;
        this.desc=desc;
        this.time = time;
        this.category = category;
        this.due_date = due_date;
    }
    
    public int getIs_Completed() {
    	return is_completed;
    }
    
    public void setIs_Completed(int is_completed) {
    	this.is_completed = is_completed;
    }
    
    public int getId() {
    	return id;
    }
    
    public void setId(int id) {
    	this.id = id;
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
    public String getCategory() {
    	return category;
    }
    public void setCategory(String cate) {
    	category = cate;
    }
    public String getDue_date() {
    	return due_date;
    }
    public void setDue_date(String date) {
    	due_date = date;
    }
    public String getTime() {
    	return time;
    }
    public void setTime(String time) {
    	this.time = time;
    }
    public String toSaveString() {
    	return category + "##" + title + "##" + desc + "##" + due_date + "##" + time + "\n";
    }
    public String toString() {
    	if(is_completed == 0)
    		return id + " [" + category + "] " + title + " - " + desc + " - " + due_date + " - " + time;
    	return id + " [" + category + "] " + title + "[V] - " + desc + " - " + due_date + " - " + time;
    }
}
