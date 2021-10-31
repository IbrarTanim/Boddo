package net.boddo.btm.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import net.boddo.btm.Adepter.ChatRequestAdapter;
import net.boddo.btm.Adepter.ChatRequestAdepter;
import net.boddo.btm.Callbacks.ApiClient;
import net.boddo.btm.Callbacks.ApiInterface;
import net.boddo.btm.Event.Event;
import net.boddo.btm.Fragment.ChatRequestFragment;
import net.boddo.btm.Model.ChatRequest;
import net.boddo.btm.Model.RecentMatchModel;
import net.boddo.btm.R;
import net.boddo.btm.Services.FirebaseCloudMessagingService;
import net.boddo.btm.Utills.Constants;
import net.boddo.btm.Utills.Data;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatRequestActivity extends AppCompatActivity {

    private RecyclerView rvChartRequest;
    private ArrayList<ChatRequest> chatRequestModelArrayList;
    private ApiInterface apiInterface;
    private ChatRequestAdepter chatRequestAdepter;
    private TextView tvBackAllLikes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_request);

        rvChartRequest = findViewById(R.id.rvChartRequest);
        tvBackAllLikes = findViewById(R.id.tvBackAllLikes);
        chatRequestModelArrayList = new ArrayList<>();
        tvBackAllLikes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<ChatRequest> call = apiInterface.getAllRequestedList(Constants.SECRET_KEY, Data.userId);
        call.enqueue(new Callback<ChatRequest>() {
            @Override
            public void onResponse(Call<ChatRequest> call, Response<ChatRequest> response) {
                if(response.isSuccessful()){
                    if(response.body().getRequestedMessage().size()!=0){
                        chatRequestAdepter = new ChatRequestAdepter(getApplicationContext(),response.body().getRequestedMessage());
                        GridLayoutManager glm = new GridLayoutManager(getApplicationContext(),2);
                        rvChartRequest.setLayoutManager(glm);
                        rvChartRequest.setAdapter(chatRequestAdepter);

                    }
                }
            }

            @Override
            public void onFailure(Call<ChatRequest> call, Throwable t) {

            }
        });


    }

}