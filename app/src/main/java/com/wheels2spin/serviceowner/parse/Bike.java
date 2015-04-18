package com.wheels2spin.serviceowner.parse;

import com.parse.GetCallback;
import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Abhinav Goel on 3/28/2015.
 */
@ParseClassName("Bike")
public class Bike extends ParseObject {

    public Bike() { }

    public String getName() {
        return getString("name");
    }

    public void setName(String name) {
        put("name", name);
    }

    public ParseUser getOwner() {
        return getParseUser("owner");
    }

    public void setOwner(ParseUser owner) {
        put("owner", owner);
    }

    public int getYear() {
        return getInt("year");
    }

    public void setYear(int year) {
        put("year", year);
    }

    public ParseFile getIconFile() {
        return getParseFile("icon");
    }

    public void setIconFile(ParseFile icon) {
        put ("icon", icon);
    }

    public ParseFile getPhotoFile() {
        return getParseFile("photo");
    }

    public void setPhotoFile(ParseFile photo) {
        put("photo", photo);
    }

    public Rent getRent() {
        final Rent[] rent = new Rent[1];
        getParseObject("rent")
                .fetchIfNeededInBackground(new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject parseObject, ParseException e) {
                        rent[0] = (Rent) parseObject;
                    }
                });
        return rent[0];
    }

    public void setRent(Rent rent) {
        put("rent", rent);
    }
}
