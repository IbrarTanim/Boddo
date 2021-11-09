package net.boddo.btm.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import net.boddo.btm.R;

public class StoryActivity extends AppCompatActivity {

    private ProgressBar storyPB;
    private LinearLayout llmNoMatch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        storyPB = findViewById(R.id.storyPB);
        llmNoMatch = findViewById(R.id.llmNoMatch);

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {

                storyPB.setVisibility(View.VISIBLE);
                llmNoMatch.setVisibility(View.VISIBLE);
            }
        }, 2000);
    }

}