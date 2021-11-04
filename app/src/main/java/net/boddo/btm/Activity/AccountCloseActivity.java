package net.boddo.btm.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import net.boddo.btm.R;

public class AccountCloseActivity extends AppCompatActivity {
    AccountCloseActivity activity;
    TextView tvBackAllLikes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_close);

        activity = this;

        tvBackAllLikes = findViewById(R.id.tvBackAllLStory);

        tvBackAllLikes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(activity, FullPhotoViewActivity.class);
                startActivity(intent);*/

                finish();
            }
        });

    }
}