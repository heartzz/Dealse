package com.harshank.dealse.activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.harshank.dealse.R;
import com.harshank.dealse.utility.Tools;

import java.util.ArrayList;
import java.util.Calendar;

public class Activity_registration extends AppCompatActivity {

    Toolbar mToolbar;
    Button button_signin;
    EditText et_mobile,et_etgender,et_etbirthdate,et_password,et_firstname,et_etemail;
  //  ArrayList<AddUserResponse> addUserResponse;
    public String firstNameSting,lastNameSting,passwordString,mobileString,genderString,dateOfBirthString,cityString,countryString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrationnew);
        initToolbar();

        button_signin = (Button)findViewById(R.id.button_signin);
        et_mobile = (EditText)findViewById(R.id.et_mobile);
        et_password = (EditText)findViewById(R.id.et_password);
        et_firstname = (EditText)findViewById(R.id.et_firstname);
        et_etemail = (EditText)findViewById(R.id.et_etemail);
        et_etgender = (EditText)findViewById(R.id.et_etgender);




        button_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!et_mobile.getText().toString().equalsIgnoreCase("") && et_mobile.getText().toString().length() == 10) {

                    /*firstNameSting = firstnameet.getText().toString();
                    lastNameSting = lastnameet.getText().toString();
                    passwordString = phoneet.getText().toString();
                    mobileString = phoneet.getText().toString();
                    dateOfBirthString = dateofbirthet.getText().toString();
                    genderString = genderet.getText().toString();
                    cityString = cityet.getText().toString();
                    countryString = countryet.getText().toString();*/

                    if(!et_password.getText().toString().equalsIgnoreCase("")){
                        if(!et_firstname.getText().toString().equalsIgnoreCase("")) {
                            if(!et_etemail.getText().toString().equalsIgnoreCase("")) {
                                if(!et_etgender.getText().toString().equalsIgnoreCase("")) {

                                    Intent mainintent = new Intent(Activity_registration.this, Activity_otpscreen.class);
                                    mainintent.putExtra("mobile","+91"+ et_mobile.getText().toString());
                                    startActivity(mainintent);

                                }else{
                                    Snackbar.make(v, "Please select the gender", Snackbar.LENGTH_LONG)
                                            .setAction("Action", null).show();
                                }

                            }else{
                                Snackbar.make(v, "Please enter the email", Snackbar.LENGTH_LONG)
                                        .setAction("Action", null).show();
                            }
                        }else{
                                Snackbar.make(v, "Please enter the name", Snackbar.LENGTH_LONG)
                                        .setAction("Action", null).show();
                            }
                    }else{
                        Snackbar.make(v, "Please enter the password", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                }else{
                    Snackbar.make(v, "Please enter valid mobile number", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });

        et_etgender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence colors[] = new CharSequence[]{"Male", "Female"};

                AlertDialog.Builder builder = new AlertDialog.Builder(Activity_registration.this);
                builder.setTitle("Select Gender");
                builder.setItems(colors, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // the user clicked on colors[which]

                        if (which == 0) {
                            et_etgender.setText("Male");
                        } else if (which == 1) {
                            et_etgender.setText("Female");
                        }
                    }
                });
                builder.show();
            }
        });

    }
    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Register");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Tools.setSystemBarColor(this, R.color.pink_400);
    }
    public class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        int byear, bmonth, bday;

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar calendar = Calendar.getInstance();
            byear = calendar.get(Calendar.YEAR);
            bmonth = calendar.get(Calendar.MONTH);
            bday = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dpd = new DatePickerDialog(getActivity(),
                    AlertDialog.THEME_DEVICE_DEFAULT_LIGHT, this, byear, bmonth, bday);

           // dpd.setTitle("Select Date"); // Uncomment this line to activate it

            // Return the DatePickerDialog
            return dpd;
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the chosen date
            String date = day + "/" + (month + 1) + "/" + year;

            if (year > byear) {
                Toast.makeText(Activity_registration.this, "Birthdate cannot be greater than today", Toast.LENGTH_SHORT).show();
                et_etbirthdate.setText("");
                et_etbirthdate.setHint("Birthdate");
            } else if (year == byear && month > bmonth) {
                Toast.makeText(Activity_registration.this, "Birthdate cannot be greater than today", Toast.LENGTH_SHORT).show();
                et_etbirthdate.setText("");
                et_etbirthdate.setHint("Birthdate");
            } else if (month == bmonth && day > bday) {
                Toast.makeText(Activity_registration.this, "Birthdate cannot be greater than today", Toast.LENGTH_SHORT).show();
                et_etbirthdate.setText("");
                et_etbirthdate.setHint("Birthdate");
            } else {
            et_etbirthdate.setText(date);
            }
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        } else {
            Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    /*public void addIndividualUser(AddUser addUser) {
        final Adduserapi adduserapi = Ppmrapplicationclass.retrofit.create(Adduserapi.class);
        Call<AddUserResponse> call = adduserapi.addUserApi(addUser);
        call.enqueue(new Callback<AddUserResponse>() {
            @Override
            public void onResponse(Call<AddUserResponse> call, Response<AddUserResponse> response) {
                addUserResponse = new ArrayList<AddUserResponse>();
                addUserResponse.add(response.body());

                if(addUserResponse != null) {
                    try {
                        if (addUserResponse.get(0).code.equalsIgnoreCase("200")) {

                            SharedPreferences.Editor editor = PpmrApplicationManager.getInstance().getEditor();
//                            editor.putString("firstName", addUserResponse.get(0).data.firstName);
//                            editor.putString("lastName", addUserResponsesList.get(0).data.firstName);
//                            editor.putString("mobile", addUserResponsesList.get(0).data.mobile);
//                            editor.putString("_id", addUserResponsesList.get(0).data._id);
//                            editor.putString("type", "I");
                            editor.commit();


                            Intent mainintent = new Intent(Activity_registration.this, Activity_otpscreen.class);
                            mainintent.putExtra("mobile", ccp.getFullNumberWithPlus());
                            startActivity(mainintent);

                        }
                    }catch (Exception e){
                        e.printStackTrace();
                        Toast.makeText(Activity_registration.this,"Mobile is already taken.",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(Activity_registration.this,"Mobile is already taken.",Toast.LENGTH_SHORT).show();
                }

            }
            @Override
            public void onFailure(Call<AddUserResponse> call, Throwable t) {
                Toast.makeText(Activity_registration.this,"Please again try later",Toast.LENGTH_SHORT).show();
            }
        });
    }*/

}
