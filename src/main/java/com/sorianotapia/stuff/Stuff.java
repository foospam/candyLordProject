package com.sorianotapia.stuff;

import java.util.concurrent.ThreadLocalRandom;

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

    public void randomPriceUpdate(int percentage){

        double randomIncrease = ((1 + Math.random()*percentage)+100)/100;
        double randomDecrease = ((1 - Math.random()*percentage)+100)/100;
//        System.out.println(randomFactor+ " "+price+" "+(price*randomFactor));
//        int newPrice = (int) (price*randomFactor);

        double randomFactor = Math.random() < 0.5 ? randomIncrease : randomDecrease;

        price = (int) (price * randomFactor);
    }

    public void priceUp() {updatePrice(price, maxPrice);}

    public void priceDown() {
        updatePrice(minPrice, price);
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public void priceUp(int percent){
        price += (int) (price * (percent)/100);
        price = Math.min(price, maxPrice);
    }

    public void priceDown(int percent){
        price -= (int) (price * (percent)/100);
        price = Math.max(price, minPrice);
    }
}
