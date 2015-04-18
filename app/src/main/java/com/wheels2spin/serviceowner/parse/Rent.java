package com.wheels2spin.serviceowner.parse;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by Abhinav Goel on 3/28/2015.
 */
@ParseClassName("Rent")
public class Rent extends ParseObject {
    public int getDailyRent() {
        return getInt("daily");
    }

    public void setDailyRent(int dailyRent) {
        put("daily", dailyRent);
    }

    public int getWeeklyRent() {
        return getInt("weekly");
    }

    public void setWeeklyRent(int weeklyRent) {
        put("weekly", weeklyRent);
    }

    public int getMonthlyRent() {
        return getInt("monthly");
    }

    public void setMonthlyRent(int monthlyRent) {
        put("monthly", monthlyRent);
    }
}
