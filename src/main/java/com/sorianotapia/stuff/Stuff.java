package com.sorianotapia.stuff;

public class Stuff {
    private final String name;
    private int price;
    private final int maxPrice;
    private final int minPrice;

    public Stuff(String name, int maxPrice, int minPrice) {
        this.name = name;
        this.maxPrice = maxPrice;
        this.minPrice = minPrice;
        updatePrice(minPrice, maxPrice);
    }

    public void updatePrice(int minPrice, int maxPrice) {
        this.price = (int) (int) (Math.random() * (maxPrice - minPrice) + minPrice);
    }

    public void updatePrice() {
        updatePrice(minPrice, maxPrice);
    }

    public void priceUp() {
        updatePrice(price, maxPrice);
    }

    public void priceDown() {
        updatePrice(minPrice, price);
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }
}
