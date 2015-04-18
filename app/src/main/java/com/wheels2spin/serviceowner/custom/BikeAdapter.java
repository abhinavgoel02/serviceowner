package com.wheels2spin.serviceowner.custom;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseImageView;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.codec.binary.StringUtils;
import com.wheels2spin.serviceowner.R;
import com.wheels2spin.serviceowner.parse.Bike;
import com.wheels2spin.serviceowner.parse.Rent;

/**
 * Created by Abhinav Goel on 3/29/2015.
 */
public class BikeAdapter extends ParseQueryAdapter<Bike> {
    public BikeAdapter(Context context) {
        super(context, new ParseQueryAdapter.QueryFactory<Bike>() {
            public ParseQuery<Bike> create() {
                // Here we can configure a ParseQuery to display
                // only top-rated meals.
                ParseQuery query = new ParseQuery("Bike");
                return query;
            }
        });
    }

    @Override
    public View getItemView(Bike bike, View v, ViewGroup parent) {

        if (v == null) {
            v = View.inflate(getContext(), R.layout.item_list_bikes, null);
        }

        super.getItemView(bike, v, parent);

        ParseImageView iconImage = (ParseImageView) v.findViewById(R.id.icon);

        ParseFile icon = bike.getIconFile();

        if (icon != null) {
            iconImage.setParseFile(icon);
            iconImage.loadInBackground(new GetDataCallback() {
                @Override
                public void done(byte[] data, ParseException e) {
                    // nothing to do
                }
            });
        }

        TextView nameTextView = (TextView) v.findViewById(R.id.bike_name);
        nameTextView.setText(bike.getName());


        final TextView priceTextView = (TextView) v.findViewById(R.id.bike_price);
        bike.getParseObject("rent").fetchIfNeededInBackground(new GetCallback<Rent>() {
            public void done(Rent rent, ParseException e) {

                int dailyRent = rent.getDailyRent();
                String price = "Rs. " + String.valueOf(dailyRent) + " per day";
                priceTextView.setText(price);
            }
        });

        return v;
    }
}
