package net.boddo.btm.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import net.boddo.btm.Activity.Settings.SettingsActivity;
import net.boddo.btm.BuildConfig;
import net.boddo.btm.R;
import net.boddo.btm.Utills.Data;

public class ProfileAndAccountsActivity extends AppCompatActivity implements View.OnClickListener {
    ProfileAndAccountsActivity activity;
    Intent intent;
    TextView tvBackProfileAndAccounts, tvUID, tvVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_and_accounts);

        activity = this;
        tvBackProfileAndAccounts = findViewById(R.id.tvBackProfileAndAccounts);
        tvUID = findViewById(R.id.tvUID);
        tvVersion = findViewById(R.id.tvVersion);
        tvUID.setText("UID: " + Data.userId);
        tvVersion.setText(BuildConfig.VERSION_NAME);
        tvBackProfileAndAccounts.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvBackProfileAndAccounts:
                intent = new Intent(activity, SettingsActivity.class);
                startActivity(intent);
                finish();
                break;

        }
    }
}