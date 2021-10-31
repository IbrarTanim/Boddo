package net.boddo.btm.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import net.boddo.btm.Adepter.AllLikesAdapter;
import net.boddo.btm.Adepter.photoblog.PhotoLovedAdapter;
import net.boddo.btm.Callbacks.ApiClient;
import net.boddo.btm.Callbacks.ApiInterface;
import net.boddo.btm.Model.Likes;
import net.boddo.btm.R;
import net.boddo.btm.Utills.Constants;
import net.boddo.btm.Utills.GridSpacingItemDecoration;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static net.boddo.btm.Utills.StaticAccess.TAG_PHOTOBLOG_ID_VALUE;

public class AccountCloseActivity extends AppCompatActivity {
    AccountCloseActivity activity;
    TextView tvBackAllLikes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_close);

        activity = this;

        tvBackAllLikes = findViewById(R.id.tvBackAllLikes);

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