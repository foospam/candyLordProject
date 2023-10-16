package com.sorianotapia.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class GameDate implements TimeFeed {

    ArrayList<TimeListener> listeners;
    LocalDate value;

    public GameDate(){
        value = LocalDate.of(1988, 4, 1);
        listeners = new ArrayList<>();
    }

    public GameDate(String value){
        this();
        this.value = LocalDate.parse(value, DateTimeFormatter.ofPattern("MM/dd/uu"));
    }

    public void updateDate(int days){
        value = value.plusDays(days);
        notify(days);
    }

    public String getStringDate(){
        return value.format(DateTimeFormatter.ofPattern("MM/dd/uu"));
    }

    @Override
    public void subscribe(TimeListener timeListener) {

        listeners.add(timeListener);
    }

    public void notify(int days){
        for (TimeListener listener : listeners) {
            listener.updateTime(days);
        }
    }
}
