package com.wheels2spin.serviceowner.custom;

import android.graphics.drawable.Drawable;

/**
 * Created by Abhinav Goel on 3/10/2015.
 */
public class Bike {
    private final String name;
    private final int price;
    private final Drawable image;

    public Bike(final String name, final int price, final Drawable image) {
        this.name = name;
        this.price = price;
        this.image = image;
    }

    public String getName() {
        return this.name;
    }

    public int getPrice() {
        return this.price;
    }

    public Drawable getImage() {
        return this.image;
    }
}
