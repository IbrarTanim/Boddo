package net.boddo.btm.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
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
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecentMatchProPicActivity extends AppCompatActivity {

    private RecyclerView rvRecentMatch;
    private ArrayList<RecentMatchModel> recentMatchModelArrayList;
    private ApiInterface apiInterface;
    private RecentMatchProPicAdepter recentMatchAdepter;
    private TextView tvBackNewMatches;
    private RecentMatchModel recentMatchModel;
    private List<RecentMatchModel.Match> matchList;
    private LinearLayout llmNoMatch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent_match_pro_pic);

        rvRecentMatch = findViewById(R.id.rvRecentMatch);
        tvBackNewMatches = findViewById(R.id.tvBackNewMatches);
        llmNoMatch = findViewById(R.id.llmNoMatch);
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
                        // Testing .....
                        recentMatchModel = response.body();
                        matchList = recentMatchModel.getMatches();
                        rvRecentMatch.setVisibility(View.VISIBLE);
                        llmNoMatch.setVisibility(View.GONE);
                      //  matchList.clear();
                        recentMatchAdepter = new RecentMatchProPicAdepter(getApplicationContext(),matchList);
                        GridLayoutManager glm = new GridLayoutManager(getApplicationContext(),2);
                        rvRecentMatch.setLayoutManager(glm);
                        rvRecentMatch.setAdapter(recentMatchAdepter);

                    }else {
                        rvRecentMatch.setVisibility(View.GONE);
                        llmNoMatch.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onFailure(Call<RecentMatchModel> call, Throwable t) {

            }
        });
    }
}