package net.boddo.btm.Activity.about;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import net.boddo.btm.R;
import net.boddo.btm.Utills.AboutUpdate;
import net.boddo.btm.Utills.Data;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HeightActivity extends AppCompatActivity {

    HeightActivity activity;

    NumberPicker npHeight;
    TextView tvSaveHeihgt, tvBack;

    String value = "";
    String key = "height";
    boolean isChanged = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_height);
        activity = this;

        //ButterKnife.bind(this);

        npHeight = findViewById(R.id.npHeight);


        if (!Data.userHeight.equals("")) {
            String userHeight = Data.userHeight;
            npHeight.setValue(201);
            Toast.makeText(activity, userHeight, Toast.LENGTH_SHORT).show();
            //npHeight.setValue(Integer.parseInt(userHeight));

        }



        npHeight.setMinValue(140);
        npHeight.setMaxValue(220);
        npHeight.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                value = String.valueOf(newVal);
                isChanged = true;
                saveToServer();
            }
        });

        tvSaveHeihgt = findViewById(R.id.tvSaveHeihgt);
        tvBack = findViewById(R.id.tvBack);
        tvSaveHeihgt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataSave();
            }
        });

        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }


    private void dataSave() {
        if (isChanged) {
            if (AboutUpdate.result) {
                Data.userHeight = value;
                Toast.makeText(this, "Update Successfull", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }


    private void saveToServer() {
        AboutUpdate update = new AboutUpdate(this);
        update.updateAbout(key, value);
    }

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, HeightActivity.class);
        return intent;
    }
}
