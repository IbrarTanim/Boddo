package net.boddo.btm.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.boddo.btm.Adepter.ChatRequestAdepter;
import net.boddo.btm.Callbacks.ApiClient;
import net.boddo.btm.Callbacks.ApiInterface;
import net.boddo.btm.Model.ChatRequest;
import net.boddo.btm.R;
import net.boddo.btm.Utills.Constants;
import net.boddo.btm.Utills.Data;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatRequestActivity extends AppCompatActivity {

    private RecyclerView rvChartRequest;
    private ArrayList<ChatRequest> chatRequestModelArrayList;
    private ApiInterface apiInterface;
    private ChatRequestAdepter chatRequestAdepter;
    private TextView tvBackAllLikes;
    private LinearLayout llmNoChatRequest;
    private ChatRequest chatRequest;
    private List<ChatRequest.RequestedMessage> requestedMessageList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_request);

        rvChartRequest = findViewById(R.id.rvStory);
        tvBackAllLikes = findViewById(R.id.tvBackAllLStory);
        llmNoChatRequest = findViewById(R.id.llmStory);
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
                        //dipto testing
                        chatRequest = response.body();
                        requestedMessageList = chatRequest.getRequestedMessage();
                        llmNoChatRequest.setVisibility(View.GONE);
                        rvChartRequest.setVisibility(View.VISIBLE);
                        chatRequestAdepter = new ChatRequestAdepter(getApplicationContext(),requestedMessageList);
                        GridLayoutManager glm = new GridLayoutManager(getApplicationContext(),2);
                        rvChartRequest.setLayoutManager(glm);
                        rvChartRequest.setAdapter(chatRequestAdepter);

                    }else {
                        llmNoChatRequest.setVisibility(View.VISIBLE);
                        rvChartRequest.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onFailure(Call<ChatRequest> call, Throwable t) {

            }
        });


    }

}