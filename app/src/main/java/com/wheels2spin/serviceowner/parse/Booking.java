package com.wheels2spin.serviceowner.parse;

import com.parse.GetCallback;
import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Abhinav Goel on 4/18/2015.
 */
@ParseClassName("Booking")
public class Booking extends ParseObject {

    private Set<String> states = new HashSet<>(Arrays.asList("confirmed", "draft"));

    public Bike getBike() {
        final Bike[] bike = new Bike[1];
        getParseObject("bike")
                .fetchIfNeededInBackground(new GetCallback<Bike>() {
                    @Override
                    public void done(Bike result, ParseException e) {
                        bike[0] = result;
                    }
                });
        return bike[0];
    }

    public void setBike(Bike bike) {
        put("bike", bike);
    }

    public ParseUser getCustomer() {
        final ParseUser[] customer = new ParseUser[1];
        getParseUser("customer")
                .fetchIfNeededInBackground(new GetCallback<ParseUser>() {
                    @Override
                    public void done(ParseUser parseUser, ParseException e) {
                        customer[0] = parseUser;
                    }
                });
        return customer[0];
    }

    public void setCustomer(ParseUser customer) {
        put("customer", customer);
    }

    public ParseUser getServiceOwner() {
        final ParseUser[] serviceOwner = new ParseUser[1];
        getParseUser("serviceOwner")
                .fetchIfNeededInBackground(new GetCallback<ParseUser>() {
                    @Override
                    public void done(ParseUser parseUser, ParseException e) {
                        serviceOwner[0] = parseUser;
                    }
                });
        return serviceOwner[0];
    }

    public void setServiceOwner(ParseUser serviceOwner) {
        put("serviceOwner", serviceOwner);
    }

    public Date getStartDate() {
        return (Date) get("startDate");
    }

    public void setStartDate(Date startDate) {
        put("startDate", startDate);
    }

    public Date getEndDate() {
        return (Date) get("endDate");
    }

    public void setEndDate(Date endDate) {
        put("endDate", endDate);
    }

    public static ParseQuery<Booking> getQuery() {
        return ParseQuery.getQuery(Booking.class);
    }

    public String getState() {
        return getString("state");
    }

    public void setState(String state) {
        if (states.contains(state)) {
            put("state", state);
        }
    }
}

