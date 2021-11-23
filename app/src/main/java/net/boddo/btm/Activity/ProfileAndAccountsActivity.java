package net.boddo.btm.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.boddo.btm.Activity.Settings.SettingsActivity;
import net.boddo.btm.BuildConfig;
import net.boddo.btm.Callbacks.ApiClient;
import net.boddo.btm.Callbacks.ApiInterface;
import net.boddo.btm.Model.ProfileUser;
import net.boddo.btm.Model.User;
import net.boddo.btm.R;
import net.boddo.btm.Utills.AuthPreference;
import net.boddo.btm.Utills.Constants;
import net.boddo.btm.Utills.Data;
import net.boddo.btm.Utills.SharedPref;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static net.boddo.btm.Utills.Constants.EMAIL;

public class ProfileAndAccountsActivity extends AppCompatActivity implements View.OnClickListener {
    ProfileAndAccountsActivity activity;
    Intent intent;
    ApiInterface apiInterface;
    private SharedPref sharedPref;
    private TextView tvBackProfileAndAccounts, tvUID, tvVersion,tvFullName,
            tvUserName,tvGender,tvDate,tvEmailVerified,tvPassword,tvEmail;
    private LinearLayout llDeleteAccount;
    private AuthPreference authPreference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_and_accounts);

        activity = this;
        authPreference = new AuthPreference(activity);
        tvBackProfileAndAccounts = findViewById(R.id.tvBackProfileAndAccounts);
        tvUID = findViewById(R.id.tvUID);
        tvVersion = findViewById(R.id.tvVersion);
        tvFullName = findViewById(R.id.tvFullName);
        tvUserName = findViewById(R.id.tvUserName);
        tvEmail = findViewById(R.id.tvEmail);
        tvGender = findViewById(R.id.tvGender);
        tvDate = findViewById(R.id.tvBirthDate);
        tvEmailVerified = findViewById(R.id.tvEmailVerified);
        tvPassword = findViewById(R.id.tvPassword);
        llDeleteAccount = findViewById(R.id.llDeleteAccount);

        tvFullName.setText(Data.userFirstName);
        tvUserName.setText(Data.userName);
        tvGender.setText(Data.userGender);
        tvDate.setText(Data.userDateOfBirgh);

        tvEmail.setText(AuthPreference.getEmail("email"));
        if(Data.isUserEmailVerified){
            tvEmailVerified.setText("(Verified)");
        }else {
            tvEmailVerified.setText("(Not Verified)");
        }

        tvUID.setText(Data.userId);



        tvUID.setText("UID: " + Data.userId);
        tvVersion.setText(BuildConfig.VERSION_NAME);
        tvBackProfileAndAccounts.setOnClickListener(this);
        tvFullName.setOnClickListener(this);
        tvUserName.setOnClickListener(this);
        tvGender.setOnClickListener(this);
        tvEmailVerified.setOnClickListener(this);
        llDeleteAccount.setOnClickListener(this);
        tvPassword.setOnClickListener(this);
        tvDate.setOnClickListener(this);
        tvEmail.setOnClickListener(this);

     //   profile_update();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvBackProfileAndAccounts:
                intent = new Intent(activity, SettingsActivity.class);
                startActivity(intent);
                finish();
                break;
                case R.id.tvFullName:
                    intent = new Intent(activity,FullNameActivity.class);
                    startActivity(intent);
                    finish();
                break;
                case R.id.tvUserName:
                    intent = new Intent(activity,UserName.class);
                    startActivity(intent);
                    finish();
                    break;
                case R.id.tvGender:
                    intent = new Intent(activity,GenderActivity.class);
                    startActivity(intent);
                    finish();
                    break;
                case R.id.tvBirthDate:
                    intent = new Intent(activity, BirthDateActivity.class);
                    startActivity(intent);
                    finish();
                    break;
                case R.id.tvEmailVerified:
                    intent = new Intent(activity,EmailVerifiedActivity.class);
                    startActivity(intent);
                    finish();
                finish();
                break;
                case R.id.tvPassword:
                startActivity(new Intent(activity,PasswordActivity.class));
                finish();
                break;
                case R.id.llDeleteAccount:
                startActivity(new Intent(activity,DeleteAccountActivity.class));
                finish();
                break;

        }
    }
}