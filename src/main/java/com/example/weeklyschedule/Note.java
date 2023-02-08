package com.example.weeklyschedule;

/**
 * @author Zachary Eubanks-Wilson
 */

public class Note {
    private String day;
    private String month;
    private String week;
    private String content;
    private String hour;

    Note(String day,String month,String week, String content, String hour){
        setDay(day);
        setMonth(month);
        setWeek(week);
        setContent(content);
        setHour(hour);
    }

    //return day
    public String getDay() {
        return day;
    }

    //set day
    public void setDay(String day) {
        this.day = day;
    }

    //get month
    public String getMonth() {
        return month;
    }

    //set month
    public void setMonth(String month) {
        this.month = month;
    }

    //get week
    public String getWeek() {
        return week;
    }

    //set week
    public void setWeek(String week) {
        this.week = week;
    }

    //get content
    public String getContent() {
        return content;
    }

    //set content
    public void setContent(String content) {
        this.content = content;
    }

    //get hour
    public String getHour(){
        return hour;
    }

    //set hour
    public void setHour(String hour){
        this.hour = hour;
    }

    //toString
    @Override
    public String toString(){
        return String.format("%s     %s, %s-%s: %s",day,hour,month,week,content);
    }
}
