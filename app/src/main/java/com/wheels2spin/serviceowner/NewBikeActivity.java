package com.wheels2spin.serviceowner;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;


import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.SaveCallback;
import com.wheels2spin.serviceowner.parse.Bike;

public class NewBikeActivity
        extends Activity
        implements
        BikePhotoFragment.OnFragmentInteractionListener,
        BikeDetailsFragment.OnAddBikeDetailsEventListener {

    private Bike bike;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        bike = new Bike();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bike);
        if (savedInstanceState == null) {

            FragmentManager manager = getFragmentManager();
            Fragment fragment = manager.findFragmentById(R.id.container);
            if (fragment == null) {
                fragment = new BikeDetailsFragment();
                manager.beginTransaction()
                        .add(R.id.container, fragment)
                        .commit();
            }
        }
    }

    public Bike getCurrentBike() {
        return bike;
    }

    @Override
    public void onSubmit() {
        bike.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    setResult(Activity.RESULT_OK);
                    finish();
                } else {
                    Toast.makeText(
                            getApplicationContext(),
                            "Error saving: " + e.getMessage(),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onNext() {
        Fragment fragment = new BikePhotoFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_bike, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {



        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_add_bike, container, false);
            return rootView;
        }


    }
}
