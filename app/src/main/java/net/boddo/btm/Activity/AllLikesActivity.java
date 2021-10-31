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

public class AllLikesActivity extends AppCompatActivity {
    AllLikesActivity activity;
    AllLikesAdapter allLikesAdapter;
    RecyclerView rvAllLikes;
    int photoBlogIdValue;
    List<Likes.AllLike> loverList;

    TextView tvBackAllLikes;
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_likes);

        activity = this;
        loverList = new ArrayList<>();

        sharedpreferences = activity.getSharedPreferences("MyPref", Context.MODE_PRIVATE);

        rvAllLikes = findViewById(R.id.rvAllLikes);
        tvBackAllLikes = findViewById(R.id.tvBackAllLikes);

        tvBackAllLikes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(activity, FullPhotoViewActivity.class);
                startActivity(intent);*/

                finish();
            }
        });
         photoBlogIdValue = sharedpreferences.getInt(TAG_PHOTOBLOG_ID_VALUE, 0);

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Likes> call = apiInterface.getAllLikes(Constants.SECRET_KEY, photoBlogIdValue);
        call.enqueue(new Callback<Likes>() {
            @Override
            public void onResponse(Call<Likes> call, Response<Likes> response) {
                loverList = response.body().getAllLikes();
                if (response.body().getStatus().equals("success")) {

                    tvBackAllLikes.setText(String.valueOf(loverList.size() + " "+ "Likes"));

                   // String allLikes = String.valueOf(loverList.size());
                    //Toast.makeText(AllLikesActivity.this, allLikes, Toast.LENGTH_SHORT).show();

                    allLikesAdapter = new AllLikesAdapter(AllLikesActivity.this, loverList);
                    RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(activity, 1);
                    rvAllLikes.setLayoutManager(mLayoutManager);
                    //rvAllLikes.addItemDecoration(new GridSpacingItemDecoration(1, GridSpacingItemDecoration.dpToPx(10, activity), true));
                    rvAllLikes.setItemAnimator(new DefaultItemAnimator());
                    mLayoutManager.setAutoMeasureEnabled(false);

                    rvAllLikes.setHasFixedSize(true);
                    rvAllLikes.setLayoutManager(mLayoutManager);
                    rvAllLikes.setAdapter(allLikesAdapter);

                }
            }

            @Override
            public void onFailure(Call<Likes> call, Throwable t) {

            }
        });

    }
}