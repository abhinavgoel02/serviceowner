package com.parse.ui.parse;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by Abhinav Goel on 4/9/2015.
 */
@ParseClassName("Address")
public class Address extends ParseObject {
    public Address() {}

    public String getShopNumber() {
        return getString("shopNumber");
    }

    public void setShopNumber(String shopNumber) {
        put("shopNumber", shopNumber);
    }

    public String getLocality() {
        return getString("locality");
    }

    public void setLocality(String locality) {
        put("locality", locality);
    }

    public String getArea() {
        return getString("area");
    }

    public void setArea(String area) {
        put("area", area);
    }

    public String getCity() {
        return getString("city");
    }

    public void setCity(String city) {
        put("city", city);
    }

    public String getPinCode() {
        return getString("pinCode");
    }

    public void setPinCode(String pinCode) {
        put("pinCode", pinCode);
    }

}
