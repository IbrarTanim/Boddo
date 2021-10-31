package net.boddo.btm.Fragment;


import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.tabs.TabLayout;
import com.nex3z.notificationbadge.NotificationBadge;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import net.boddo.btm.Activity.HotlistActivityNew;
import net.boddo.btm.Activity.PictureUploadActivity;
import net.boddo.btm.Adepter.PhotoBlogTabAdapter;
import net.boddo.btm.Adepter.photoblog.HotlistAdapter;
import net.boddo.btm.Callbacks.ApiClient;
import net.boddo.btm.Callbacks.ApiInterface;
import net.boddo.btm.Event.Event;
import net.boddo.btm.Activity.ImageUploadActivity;
import net.boddo.btm.Model.Hotlist;
import net.boddo.btm.R;
import net.boddo.btm.Utills.Constants;
import net.boddo.btm.Utills.Data;
import net.boddo.btm.Utills.SearchUser;
import net.boddo.btm.interfaces.PictureUploadListener;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;

public class PhotoBlogFragment extends Fragment implements HotlistAdapter.OnPhotoblogImageClickListener, PictureUploadListener {


    private static final String TAG = "PhotoBlogFragment";
    private PhotoBlogTabAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    NotificationBadge badgingTopPhotoCountView;
    NotificationBadge badgingPhotoBlogCountView;

//    String URL = "https://bluetigermobile.com/palup/apis/random_hotlist.php";

    Hotlist hotlist[];
    HotlistAdapter hotListAdapter;


    public PhotoBlogFragment() {
        // Required empty public constructor
    }


    /*images at the top of the photo-blog  declaration
     * */
    @BindView(R.id.user_image_view)
    ImageView userImageView;

    ImageView ivHotlistIcon;

    //@BindView(R.id.TvJoinHotlist)
    TextView TvJoinHotlist;
    CircleImageView civJoinHotlist;


   /* @BindView(R.id.profile_image_hotlist)
    CardView cvHotlist;*/

    RelativeLayout profile_image_hotlist;

    @BindView(R.id.recent_hot_list_recycler_view)
    RecyclerView hotListRecyclerView;

    @BindView(R.id.upload_new_image)
    ImageView btnUploadNewImage;

    //@BindView(R.id.text_view_hotlist)
    //TextView hotlistTitle;

    @BindView(R.id.top_photo_layout)
    LinearLayout hotlistLayout;

    BroadcastReceiver br;

    int itemScrolledCount;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_photo_blog, container, false);
        ButterKnife.bind(this, view);


        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(br);
        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        tabLayout.setLeft(5);
        tabLayout.setRight(1 + 5);

        ivHotlistIcon = view.findViewById(R.id.ivHotlistIcon);
        ivHotlistIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), HotlistActivityNew.class);
                startActivity(intent);
            }
        });

        badgingTopPhotoCountView = view.findViewById(R.id.badging_top_photo_view);
        badgingPhotoBlogCountView = view.findViewById(R.id.badging_photo_blog_view);

        profile_image_hotlist = view.findViewById(R.id.profile_image_hotlist);
        profile_image_hotlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), HotlistActivityNew.class);
                startActivity(intent);
            }
        });

        TvJoinHotlist = view.findViewById(R.id.TvJoinHotlist);
        civJoinHotlist = view.findViewById(R.id.civJoinHotlist);

       /* if (Data.profilePhoto.isEmpty() == true) {
            TvJoinHotlist.setText("Join Hotlist");
        } else {
            TvJoinHotlist.setText("Joined");
        }*/


        Glide.with(getActivity()).load(Data.profilePhoto).apply(RequestOptions.bitmapTransform(new RoundedCorners(10))).into(userImageView);
        getRecentHotList();
        setup();
        adapter = new PhotoBlogTabAdapter(getActivity().getSupportFragmentManager());
        adapter.addFragment(new FragmentPhotoBlogAllUser(), "Just In");
        /*adapter.addFragment(new FriendsFragment(), "Friends");
        adapter.addFragment(new TrendyFragment(), "Trendy");*/
        adapter.addFragment(new TopPhotoFragment(), "Top photos");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        userImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), HotlistActivityNew.class);
                startActivity(intent);
            }
        });
        return view;
    }

    private void getRecentHotList() {
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Hotlist[]> call = apiInterface.getRecentHotlist();
        call.enqueue(new Callback<Hotlist[]>() {
            @Override
            public void onResponse(Call<Hotlist[]> call, retrofit2.Response<Hotlist[]> response) {
                hotlist = response.body();
                if (hotlist.length > 0) {
                    hotlist = response.body();
                    prepareList();
                }
            }

            @Override
            public void onFailure(Call<Hotlist[]> call, Throwable t) {
                Log.d(TAG, t.getMessage());
            }
        });
    }

    private void prepareList() {
        hotListRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        hotListAdapter = new HotlistAdapter(getContext(), hotlist);
        hotListAdapter.setProfileImageClickListener(this);
        hotListRecyclerView.setAdapter(hotListAdapter);


        /* RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false);
        hotListRecyclerView.setLayoutManager(mLayoutManager);
        hotListRecyclerView.addItemDecoration(new GridSpacingItemDecoration(1, GridSpacingItemDecoration.dpToPx(10, getActivity()), true));
        hotListRecyclerView.setItemAnimator(new DefaultItemAnimator());
        hotListAdapter = new HotlistAdapter(getContext(), hotlist);
        hotListRecyclerView.setAdapter(hotListAdapter);*/


    }

    /*@OnClick(R.id.profile_image_hotlist)
    public void onHotlistImageClicked() {
        Intent intent = new Intent(getActivity(), HotlistActivityNew.class);
        startActivity(intent);
    }*/

    @Override
    public void onStart() {
        super.onStart();

        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(br);

    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(Event event) {

        String rokanA = event.getEventType();
        if (event.getEventType().equals(Constants.PHOTO_BLOG_NOTIFICATION) || event.getEventType().equals(Constants.SET_PHOTO_BLOGE_COUNT)) {
            badgingPhotoBlogCountView.setNumber(Data.PhotoBlogCount);
            badgingPhotoBlogCountView.setAnimationEnabled(true);
        }

        if (event.getEventType().equals(Constants.PHOTO_BLOG_NOTIFICATION) || event.getEventType().equals(Constants.SET_PHOTO_BLOGE_COUNT)) {
            badgingTopPhotoCountView.setNumber(Data.TopPhotoCount);
            badgingTopPhotoCountView.setAnimationEnabled(true);
        }
    }


    @Override
    public void onImageClick(int position) {
        //setFragment(new ProfileFragment());

        showAllData(position);
    }


    private void showAllData(int position) {

        List<Hotlist> hotlistsData = Arrays.asList(hotlist);
        Data.pd = new ProgressDialog(getContext());
        Data.pd.setTitle("Loading...");
        Data.pd.setMessage("Please wait for a while...");
        SearchUser userProfile = new SearchUser(getContext());
        Data.otherUserId = hotlistsData.get(position).getUserId();
        userProfile.searchUserInfo();
        Data.pd.show();


    }


    @OnClick(R.id.upload_new_image)
    public void onUploadNewImageButtonClicked() {

        Intent intent = new Intent(getActivity(), ImageUploadActivity.class);
        //Intent intent = new Intent(getActivity(), PictureUploadActivity.class);
        startActivity(intent);


    }

    private void setup() {
        br = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent i) {
                itemScrolledCount = i.getIntExtra(Constants.SCROLLED_COUNT, 0);
                if (itemScrolledCount > 1) {
                    hotlistLayout.setVisibility(View.GONE);
                    //hotlistTitle.setVisibility(View.GONE);
                } else {
                    hotlistLayout.setVisibility(View.VISIBLE);
                    //hotlistTitle.setVisibility(View.VISIBLE);
                }
            }
        };
        getContext().registerReceiver(br, new IntentFilter(Constants.SCROLLED_BROAD_CAST));
    }


    @Override
    public void onSuccess() {

    }

    @Override
    public void onFailed() {

    }


    private void setFragment(Fragment fragment) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment).disallowAddToBackStack();
        fragmentTransaction.commit();
    }


}
