package com.sorianotapia;

import java.util.ArrayList;

public interface EventFeed {
    public void subscribe(EventListener eventListener);
    public void notify(Object... args);
}
