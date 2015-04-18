package com.wheels2spin.serviceowner;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseUser;
import com.wheels2spin.serviceowner.parse.Bike;
import com.wheels2spin.serviceowner.parse.Rent;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BikeDetailsFragment.OnAddBikeDetailsEventListener} interface
 * to handle interaction events.
 */
public class BikeDetailsFragment extends Fragment implements View.OnClickListener {

    private OnAddBikeDetailsEventListener mListener;

    private EditText bikeNameField;
    private EditText bikeNumberField;
    private EditText bikeYearField;
    private EditText dailyRentField;
    private EditText weeklyRentField;
    private EditText monthlyRentField;
    private CheckBox isGearedCheckBox;
    private CheckBox isInsuredCheckBox;

    public BikeDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onClick(View v) {
        final String bikeName = bikeNameField.getText().toString();
        final String bikeNumber = bikeNumberField.getText().toString();
        final String bikeYear = bikeYearField.getText().toString();

        final String dailyRent = dailyRentField.getText().toString();
        final String weeklyRent = weeklyRentField.getText().toString();
        final String monthlyRent = monthlyRentField.getText().toString();

        final boolean isGeared = isGearedCheckBox.isChecked();
        final boolean isInsured = isInsuredCheckBox.isChecked();

        if (bikeName.isEmpty()) {
            showToast(R.string.no_bike_name_toast);
        } else if (bikeNumber.isEmpty()) {
            showToast(R.string.no_bike_number_toast);
        } else if (bikeYear.isEmpty()) {
            showToast(R.string.no_bike_year_toast);
        } else if (dailyRent.isEmpty()) {
            showToast(R.string.no_daily_rent_toast);
        } else if (weeklyRent.isEmpty()) {
            showToast(R.string.no_weekly_rent_toast);
        } else if (monthlyRent.isEmpty()) {
            showToast(R.string.no_monthly_rent_toast);
        } else {
            Bike bike = ((NewBikeActivity) getActivity()).getCurrentBike();
            bike.setName(bikeName);
            bike.setYear(Integer.parseInt(bikeYear));
            bike.setOwner(ParseUser.getCurrentUser());
            Rent rent = new Rent();
            rent.setDailyRent(Integer.parseInt(dailyRent));
            rent.setWeeklyRent(Integer.parseInt(weeklyRent));
            rent.setMonthlyRent(Integer.parseInt(monthlyRent));
            bike.setRent(rent);
            onNextButtonPressed();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_bike, container, false);

        bikeNameField = (EditText) rootView.findViewById(R.id.bike_name);
        bikeNumberField = (EditText) rootView.findViewById(R.id.bike_number);
        bikeYearField = (EditText) rootView.findViewById(R.id.bike_year);

        dailyRentField = (EditText) rootView.findViewById(R.id.daily);
        weeklyRentField = (EditText) rootView.findViewById(R.id.weekly);
        monthlyRentField = (EditText) rootView.findViewById(R.id.monthly);

        isGearedCheckBox = (CheckBox) rootView.findViewById(R.id.geared);
        isInsuredCheckBox = (CheckBox) rootView.findViewById(R.id.insured);

        Button nextButton = (Button) rootView.findViewById(R.id.next);
        nextButton.setOnClickListener(this);

        return rootView;
    }

    protected void showToast(int id) {
        showToast(getString(id));
    }

    protected void showToast(CharSequence text) {
        Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onNextButtonPressed() {
        if (mListener != null) {
            mListener.onNext();
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnAddBikeDetailsEventListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnAddBikeDetailsEventListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnAddBikeDetailsEventListener {
        // TODO: Update argument type and name
        public void onNext();
    }

}
