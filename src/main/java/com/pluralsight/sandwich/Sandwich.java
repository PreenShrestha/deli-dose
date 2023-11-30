package com.pluralsight.sandwich;

import com.pluralsight.order.OrderItem;
import com.pluralsight.toppings.Topping;

import java.util.ArrayList;
import java.util.List;

public class Sandwich implements OrderItem {
    private double price;
    private BreadType breadType;
    private SandwichSize size;
    private boolean isToasted;
    private static List<Topping> toppings;
    private static List<Topping> extras;

    public Sandwich(BreadType breadType, SandwichSize size) {
        this.breadType = breadType;
        this.size = size;
        toppings = new ArrayList<>();
        extras = new ArrayList<>();
    }

    public static void addTopping(Topping topping) {
        toppings.add(topping);
    }

    public static void addExtra(Topping topping) {
        extras.add(topping);
    }

    public double getPrice(int a) {
        switch (size){
            case FOUR -> price = SandwichSize.FOUR.getPrice();
            case EIGHT -> price = SandwichSize.EIGHT.getPrice();
            case TWELVE -> price = SandwichSize.TWELVE.getPrice();
        }
        return price;
    }


    @Override
    public double getPrice(){
        price = size.getPrice();
        System.out.println(size);
        System.out.println(price + "This is initial price loop");
        for (Topping topping: toppings
        ) {
            if(size == SandwichSize.FOUR){
                price += topping.getPrice4Inch();
            } else if (size == SandwichSize.EIGHT){
                price += topping.getPrice8Inch();
            } else if (size == SandwichSize.TWELVE) {
                price += topping.getPrice12Inch();
            }
        }
        double extraPrice = 0;
        for(Topping extra : extras) {
            if (extras.contains(Topping.EXTRA_MEAT) || extras.contains(Topping.EXTRA_CHEESE)) {
                if (size == SandwichSize.FOUR) {
                    extraPrice += extra.getPrice4Inch();
                } else if (size == SandwichSize.EIGHT) {
                    extraPrice += extra.getPrice8Inch();
                } else if (size == SandwichSize.TWELVE) {
                    extraPrice += extra.getPrice12Inch();
                }
//                    System.out.println( + "This is extra meat");
            } //else if (extras.contains(Topping.EXTRA_CHEESE)) {
//                    if(size ==SandwichSize.FOUR){
//                        price += extra.getPrice4Inch();
//                    } else if (size == SandwichSize.EIGHT) {
//                        price += extra.getPrice8Inch();
//                    } else if (size == SandwichSize.TWELVE) {
//                        price += extra.getPrice12Inch();
//                    }
//                }
        }
        return price + extraPrice;
    }

    public BreadType getBreadType() {
        return breadType;
    }

    public SandwichSize getSize() {
        return size;
    }

    public boolean isToasted() {
        return isToasted;
    }

    public void setToasted(boolean toasted) {
        isToasted = toasted;
    }

    @Override
    public String getStringDetails() {
        return  String.format("%58s\n", "Deli Dose Sandwiches") +
                String.format("%65s\n" , "85 Broad Street, New York, NY 10004") +
                "-----------------------------------------------------------------------------------------------\n"+
                String.format("%50s\n", "RECEIPT") +
                "SANDWICH: " +
                "BREAD: " + getBreadType() + "\n" +
                "SIZE: " + getSize() + "\n" +
                "TOPPINGS: " + toppings + "\n" +
                "EXTRAS: " + extras + "\n" +
                "TOASTED: " + (isToasted ? "Yes" : "No") + "\n"+
                "TOTAL: ------------------------------------------------------------------------- PRICE: " + String.format("($%.2f)", getPrice()) + "\n";
    }
}
