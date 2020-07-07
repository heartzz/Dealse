package com.harshank.dealse.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.harshank.dealse.R;
import com.harshank.dealse.manager.AppManager;
import com.harshank.dealse.objects.Users;
import com.harshank.dealse.utility.FirebaseConstants;
import com.harshank.dealse.utility.LoaderDialog;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by harshank on 16/5/17.
 */

public class SignInActivity extends AppCompatActivity {

    public static String TAG = "SignInActivity";
    EditText input_email;
    EditText input_password;
    Button btn_signin;
    String email,password;
    private Toolbar mToolbar;
    private FirebaseAuth mAuth;
    boolean isAvailable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        initToolbar();

        input_email = (EditText)findViewById(R.id.input_email);
        input_password = (EditText)findViewById(R.id.input_password);
        btn_signin = (Button)findViewById(R.id.btn_signin);

        mAuth = FirebaseAuth.getInstance();

        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn(input_email.getText().toString(),input_password.getText().toString());
            }
        });
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
    }

    private boolean validateForm() {
        boolean valid = true;

        String email = input_email.getText().toString();
        if (TextUtils.isEmpty(email)) {
            input_email.setError("Required.");
            valid = false;
        } else {
            input_email.setError(null);
        }

        String password = input_password.getText().toString();
        if (TextUtils.isEmpty(password)) {
            input_password.setError("Required.");
            valid = false;
        } else {
            input_password.setError(null);
        }

        return valid;
    }

    private void updateUI(final FirebaseUser User){

        if(User != null) {

            if (User.isEmailVerified()) {

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference(FirebaseConstants.KEY_USERS);
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        List<Users> userslist = new ArrayList<Users>();
                        for (DataSnapshot child : dataSnapshot.getChildren()) {
                            userslist.add(child.getValue(Users.class));
                        }

                        for (Users user : userslist) {

                            if (user.getEmail().equalsIgnoreCase(User.getEmail()) && user.getUsertype().equalsIgnoreCase(FirebaseConstants.TYPE_USER)) {

                                isAvailable = true;

                                AppManager.getInstance().getPref().edit().putString(FirebaseConstants.FIELD_ID, user.getId()).commit();
                                AppManager.getInstance().getPref().edit().putString(FirebaseConstants.FIELD_EMAIL, user.getEmail()).commit();

                                AppManager.getInstance().getPref().edit().putString(FirebaseConstants.FIELD_USERLOGO, user.getUserlogo()).commit();
                                AppManager.getInstance().getPref().edit().putString(FirebaseConstants.FIELD_USERCOVER, user.getUsercover()).commit();

                                LoaderDialog.hideLoader();

                                Intent mainscreenIntent = new Intent(SignInActivity.this, HomeScreen.class);
                                mainscreenIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(mainscreenIntent);

                                break;
                            } else {
                                isAvailable = false;
                                continue;
                            }
                        }
                        if (!isAvailable) {
                            LoaderDialog.hideLoader();
                            Toast.makeText(SignInActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        Log.w(TAG, "Failed to register user.", error.toException());
                    }
                });
            } else {
                Toast.makeText(SignInActivity.this, "Authentication failed.",
                        Toast.LENGTH_SHORT).show();
            }

        }
    }

    private void signIn(String email, String password) {
        Log.d(TAG, "signIn:" + email);
        if (!validateForm()) {
            return;
        }

        LoaderDialog.showLoader(SignInActivity.this);

        // [START sign_in_with_email]
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(SignInActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // [START_EXCLUDE]
                        if (!task.isSuccessful()) {
                            Toast.makeText(SignInActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                        LoaderDialog.hideLoader();
                        // [END_EXCLUDE]
                    }
                });
        // [END sign_in_with_email]
    }

    private void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        TextView toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        ImageView toolbar_back = (ImageView) findViewById(R.id.toolbar_back);
        getSupportActionBar().setTitle(null);
        //toolbar_title.setTypeface(GetFonts.getInstance().getRobotoFont(TravFeddHomeScreen.this));
        toolbar_title.setText("SignIn");
        toolbar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}