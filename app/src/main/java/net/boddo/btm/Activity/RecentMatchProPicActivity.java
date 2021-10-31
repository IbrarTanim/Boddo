package net.boddo.btm.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import net.boddo.btm.Adepter.ChatRequestAdepter;
import net.boddo.btm.Adepter.RecentMatchProPicAdepter;
import net.boddo.btm.Callbacks.ApiClient;
import net.boddo.btm.Callbacks.ApiInterface;
import net.boddo.btm.Model.RecentMatchModel;
import net.boddo.btm.R;
import net.boddo.btm.Utills.Constants;
import net.boddo.btm.Utills.Data;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecentMatchProPicActivity extends AppCompatActivity {

    private RecyclerView rvRecentMatch;
    private ArrayList<RecentMatchModel> recentMatchModelArrayList;
    private ApiInterface apiInterface;
    private RecentMatchProPicAdepter recentMatchAdepter;
    private TextView tvBackNewMatches;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent_match_pro_pic);

        rvRecentMatch = findViewById(R.id.rvRecentMatch);
        tvBackNewMatches = findViewById(R.id.tvBackNewMatches);
        recentMatchModelArrayList = new ArrayList<>();
        tvBackNewMatches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<RecentMatchModel> call = apiInterface.getRecentMatchData(Data.userId, Constants.SECRET_KEY);
        call.enqueue(new Callback<RecentMatchModel>() {
            @Override
            public void onResponse(Call<RecentMatchModel> call, Response<RecentMatchModel> response) {
                if(response.isSuccessful()){
                    if(response.body().getMatches().size()!=0){
                        recentMatchAdepter = new RecentMatchProPicAdepter(getApplicationContext(),response.body().getMatches());
                        GridLayoutManager glm = new GridLayoutManager(getApplicationContext(),2);
                        rvRecentMatch.setLayoutManager(glm);
                        rvRecentMatch.setAdapter(recentMatchAdepter);

                    }
                }
            }

            @Override
            public void onFailure(Call<RecentMatchModel> call, Throwable t) {

            }
        });
    }
}