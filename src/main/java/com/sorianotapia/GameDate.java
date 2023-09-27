package com.sorianotapia;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;

public class GameDate implements EventFeed {

    ArrayList<TimeListener> listeners;
    LocalDate value;

    public GameDate(){

        value = LocalDate.of(1988, 4, 1);
        listeners = new ArrayList<>();
    }

    public void updateDate(){
        updateDate(1);
    }

    public void updateDate(int days){
        value = value.plusDays(days);
        notify(days);
    }

    public String getStringDate(){
        return value.format(DateTimeFormatter.ofPattern("MM/dd/uu"));
    }


    public void notify(int days){
        for (TimeListener listener : listeners) {
            listener.updateTime(days);
        }
    }

    @Override
    public void subscribe(EventListener eventListener) {
        listeners.add((TimeListener) eventListener);
    }

    @Override
    public void notify(Object... args) {
        for (TimeListener listener: listeners) {
            listener.updateTime((Integer) args[0]);
        }
    }


}
