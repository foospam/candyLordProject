package com.sorianotapia.goods;

public class StuffCarrier {

    private String name;
    private int hold;
    private int price;

    public String getName() {
        return name;
    }

    public int getHold() {
        return hold;
    }

    public int getPrice() {
        return price;
    }

    public StuffCarrier(String name, int hold, int price) {
        this.name = name;
        this.hold = hold;
        this.price = price;
    }

}
