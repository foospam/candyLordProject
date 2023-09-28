package com.sorianotapia.places;

public enum PlaceName {
    PLACE_1, PLACE_2, PLACE_3, PLACE_4, PLACE_5, PLACE_6, PLACE_7, PLACE_8;
    private boolean used;

    public void setUsed(){
        used = true;
    }

    public boolean getUsed(){
        return used;
    }

//    public static PlaceName getRandomName(){
//        while (true) {
//            int index = (int) (Math.random() * PlaceName.values().length);
//            if (!values()[index].getUsed()) {
//                values()[index].setUsed();
//            }
//        }
//    }

}
