//package com.sorianotapia.events;
//
//import java.util.ArrayList;
//import java.util.LinkedList;
//
//public class EventHandler {
//
//    LinkedList<EventMessage> eventQueue;
//
//    public void pushEventMessage(EventMessage eventMessage){
//        eventQueue.push(eventMessage);
//    }
//
//    private void runEvent(EventMessage eventMessage){
//        Event event = eventMessage.getEvent();
//        event.run();
//    }
//
//    private void handleEvents(){
//        while(!eventQueue.isEmpty()){
//            runEvent(eventQueue.poll().getEvent().run());
//        }
//    }
//
//
//}
