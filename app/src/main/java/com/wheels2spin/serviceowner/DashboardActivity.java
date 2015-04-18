package com.wheels2spin.serviceowner;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;
import com.parse.ui.ParseLoginBuilder;
import com.wheels2spin.serviceowner.custom.BikeAdapter;
import com.wheels2spin.serviceowner.parse.Bike;


public class DashboardActivity extends ListActivity {

    private ParseQueryAdapter<Bike> bikeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser == null) {
            // Start Parse Login Activity if user is not logged in
            ParseLoginBuilder builder = new ParseLoginBuilder(DashboardActivity.this);
            startActivityForResult(builder.build(), 0);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        getListView().setClickable(false);

        bikeAdapter = new BikeAdapter(this);

        setListAdapter(bikeAdapter);
    }

    private void newBike() {
        Intent newBikeIntent = new Intent(this, NewBikeActivity.class);
        startActivityForResult(newBikeIntent, 0);
    }

    private void updateBikeList() {
        bikeAdapter.loadObjects();
        setListAdapter(bikeAdapter);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            // If a new post has been added, update
            // the list of posts
            updateBikeList();
        }
    }


    public void OnItemFragmentInteraction(String id) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dashboard, menu);
        return true;
    }

    /*
	 * Posting meals and refreshing the list will be controlled from the Action
	 * Bar.
	 */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_refresh: {
                updateBikeList();
                break;
            }

            case R.id.action_new: {
                newBike();
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }


}
