package net.boddo.btm.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import net.boddo.btm.R;

public class DeleteAccountActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvMeetSomeone,tvMeetSomeoneElseWhere,tvResetMyAccount,
            tvNoLongerInterested,tvQualityOfApp,tvOther;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_account);

        tvMeetSomeone = findViewById(R.id.tvMeetSomeone);
        tvMeetSomeoneElseWhere = findViewById(R.id.tvMeetSomeoneElseWhere);
        tvResetMyAccount = findViewById(R.id.tvResetMyAccount);
        tvNoLongerInterested = findViewById(R.id.tvNoLongerInterested);
        tvQualityOfApp = findViewById(R.id.tvQualityOfApp);
        tvOther = findViewById(R.id.tvOther);

        tvMeetSomeone.setOnClickListener(this);
        tvMeetSomeoneElseWhere.setOnClickListener(this);
        tvResetMyAccount.setOnClickListener(this);
        tvNoLongerInterested.setOnClickListener(this);
        tvQualityOfApp.setOnClickListener(this);
        tvOther.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvMeetSomeone:
                tvMeetSomeone.setBackground(getResources().getDrawable(R.drawable.green_shade_button_bg));
                tvMeetSomeoneElseWhere.setBackground(getResources().getDrawable(R.drawable.username_save_button));
                tvResetMyAccount.setBackground(getResources().getDrawable(R.drawable.username_save_button));
                tvNoLongerInterested.setBackground(getResources().getDrawable(R.drawable.username_save_button));
                tvQualityOfApp.setBackground(getResources().getDrawable(R.drawable.username_save_button));
                tvOther.setBackground(getResources().getDrawable(R.drawable.username_save_button));
                break;

            case R.id.tvMeetSomeoneElseWhere:
                tvMeetSomeone.setBackground(getResources().getDrawable(R.drawable.username_save_button));
                tvMeetSomeoneElseWhere.setBackground(getResources().getDrawable(R.drawable.green_shade_button_bg));
                tvResetMyAccount.setBackground(getResources().getDrawable(R.drawable.username_save_button));
                tvNoLongerInterested.setBackground(getResources().getDrawable(R.drawable.username_save_button));
                tvQualityOfApp.setBackground(getResources().getDrawable(R.drawable.username_save_button));
                tvOther.setBackground(getResources().getDrawable(R.drawable.username_save_button));
                break;

            case R.id.tvResetMyAccount:
                tvMeetSomeone.setBackground(getResources().getDrawable(R.drawable.username_save_button));
                tvMeetSomeoneElseWhere.setBackground(getResources().getDrawable(R.drawable.username_save_button));
                tvResetMyAccount.setBackground(getResources().getDrawable(R.drawable.green_shade_button_bg));
                tvNoLongerInterested.setBackground(getResources().getDrawable(R.drawable.username_save_button));
                tvQualityOfApp.setBackground(getResources().getDrawable(R.drawable.username_save_button));
                tvOther.setBackground(getResources().getDrawable(R.drawable.username_save_button));
                break;

            case R.id.tvNoLongerInterested:
                tvMeetSomeone.setBackground(getResources().getDrawable(R.drawable.username_save_button));
                tvMeetSomeoneElseWhere.setBackground(getResources().getDrawable(R.drawable.username_save_button));
                tvResetMyAccount.setBackground(getResources().getDrawable(R.drawable.username_save_button));
                tvNoLongerInterested.setBackground(getResources().getDrawable(R.drawable.green_shade_button_bg));
                tvQualityOfApp.setBackground(getResources().getDrawable(R.drawable.username_save_button));
                tvOther.setBackground(getResources().getDrawable(R.drawable.username_save_button));
                break;

            case R.id.tvQualityOfApp:
                tvMeetSomeone.setBackground(getResources().getDrawable(R.drawable.username_save_button));
                tvMeetSomeoneElseWhere.setBackground(getResources().getDrawable(R.drawable.username_save_button));
                tvResetMyAccount.setBackground(getResources().getDrawable(R.drawable.username_save_button));
                tvNoLongerInterested.setBackground(getResources().getDrawable(R.drawable.username_save_button));
                tvQualityOfApp.setBackground(getResources().getDrawable(R.drawable.green_shade_button_bg));
                tvOther.setBackground(getResources().getDrawable(R.drawable.username_save_button));
                break;

            case R.id.tvOther:
                tvMeetSomeone.setBackground(getResources().getDrawable(R.drawable.username_save_button));
                tvMeetSomeoneElseWhere.setBackground(getResources().getDrawable(R.drawable.username_save_button));
                tvResetMyAccount.setBackground(getResources().getDrawable(R.drawable.username_save_button));
                tvNoLongerInterested.setBackground(getResources().getDrawable(R.drawable.username_save_button));
                tvQualityOfApp.setBackground(getResources().getDrawable(R.drawable.username_save_button));
                tvOther.setBackground(getResources().getDrawable(R.drawable.green_shade_button_bg));
                break;


        }
    }
}