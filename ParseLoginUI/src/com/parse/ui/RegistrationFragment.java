package com.parse.ui;

import android.app.Activity;
import android.app.Fragment;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.parse.ui.parse.Address;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;


public class RegistrationFragment extends ParseLoginFragmentBase implements View.OnClickListener {

    private EditText companyNameField;
    private EditText companyPhoneField;
    private EditText companyShopNoField;
    private EditText companyLocalityField;
    private EditText companyAreaField;
    private EditText companyCityField;
    private EditText companyPinCodeField;
    private Button checkOutTimeButton;
    private Button completeRegistrationButton;
    private int checkOutTime;
    private boolean isCheckOutTimeSet = false;
    private OnRegistrationSuccessListener onRegistrationSuccessListener;

    public RegistrationFragment() { }

    public static RegistrationFragment newInstance() {
        return new RegistrationFragment();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ParseOnLoginSuccessListener) {
            onRegistrationSuccessListener = (OnRegistrationSuccessListener) activity;
        } else {
            throw new IllegalArgumentException(
                    "Activity must implemement OnRegistrationSuccessListener");
        }

        if (activity instanceof ParseOnLoadingListener) {
            onLoadingListener = (ParseOnLoadingListener) activity;
        } else {
            throw new IllegalArgumentException(
                    "Activity must implemement ParseOnLoadingListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_registration, container, false);

        // Company info
        companyNameField = (EditText) v.findViewById(R.id.register_company_name);
        companyPhoneField = (EditText) v.findViewById(R.id.register_company_phone);

        // Address related info
        companyShopNoField = (EditText) v.findViewById(R.id.register_company_shop_no);
        companyLocalityField = (EditText) v.findViewById(R.id.register_company_shop_no);
        companyAreaField = (EditText) v.findViewById(R.id.register_company_area);
        companyCityField = (EditText) v.findViewById(R.id.register_company_city);
        companyPinCodeField = (EditText) v.findViewById(R.id.register_company_pin_code);

        final Calendar calendar = Calendar.getInstance();
        final TimePickerDialog checkOutTimePicker = new TimePickerDialog(getActivity(),
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        checkOutTime = hourOfDay;
                        isCheckOutTimeSet = true;
                        checkOutTimeButton.setText(String.valueOf(checkOutTime));
                    }
                },
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                false);

                // Check-out time
        checkOutTimeButton = (Button) v.findViewById(R.id.register_check_out_time);
        checkOutTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkOutTimePicker.show();
            }
        });

        completeRegistrationButton = (Button) v.findViewById(R.id.register_complete_registration);
        completeRegistrationButton.setOnClickListener(this);

        return v;
    }

    public interface OnRegistrationSuccessListener {
        public void onRegistrationSuccess();
    }

    private void registrationSuccess() {
        onRegistrationSuccessListener.onRegistrationSuccess();
    }

    public void onClick(View v) {
        final String companyName = companyNameField.getText().toString();
        final String companyPhone = companyPhoneField.getText().toString();
        final String companyShopNo = companyShopNoField.getText().toString();
        final String companyArea = companyAreaField.getText().toString();
        final String companyCity = companyCityField.getText().toString();
        final String companyLocality = companyLocalityField.getText().toString();
        final String companyPinCode = companyPinCodeField.getText().toString();

        if (companyName.length() == 0) {
            showToast(R.string.no_company_name_toast);
        }else if (companyPhone.length() == 0) {
            showToast(R.string.no_company_phone_toast);
        } else if (companyShopNo.length() == 0 || companyArea.length() == 0 || companyCity.length() == 0 || companyPinCode.length() == 0) {
            showToast(R.string.no_company_address_toast);
        } else if (!isCheckOutTimeSet) {
            showToast(R.string.no_check_out_time_toast);
        } else {

            ParseUser currentUser = ParseUser.getCurrentUser();
            currentUser.put(W2SConstants.USER_OBJECT_COMPANY_NAME_FIELD, companyName);
            Address companyAddress = new Address();
            companyAddress.setShopNumber(companyShopNo);
            companyAddress.setLocality(companyLocality);
            companyAddress.setArea(companyArea);
            companyAddress.setCity(companyCity);
            companyAddress.setPinCode(companyPinCode);

            currentUser.put(W2SConstants.USER_OBJECT_COMPANY_ADDRESS_FIELD, companyAddress);
            currentUser.put(W2SConstants.USER_OBJECT_COMPANY_PHONE_FIELD, companyPhone);
            currentUser.put(W2SConstants.USER_OBJECT_COMPANY_CHECK_OUT_TIME, checkOutTime);
            currentUser.saveInBackground();

            registrationSuccess();
        }
    }
}