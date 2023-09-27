package com.sorianotapia.fromVersion1;

public enum Drugs {
    COCAINE(35000, 50000),
    CRACK(20000, 40000),
    HEROIN(10000, 20000),
    ACID(1500, 4000),
    CRYSTAL(500, 1500),
    GRASS(100, 500),
    SPEED(50, 150),
    LUDES(5, 25);
    private int price;
    private final int maxPrice;
    private final int minPrice;

    private Drugs(int minPrice, int maxPrice) {
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.price = (int) (Math.random() * (maxPrice - minPrice) + minPrice);
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
}