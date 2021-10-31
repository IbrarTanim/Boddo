package net.boddo.btm.Adepter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import net.boddo.btm.R;

public class PalupPlusSliderAdapter extends PagerAdapter {


    int image[] = {
            R.drawable.like_slider,
            R.drawable.favorite_slider,
            R.drawable.visitor_slider,
            R.drawable.chat_request_slider,
            R.drawable.share_picture_slider,
            R.drawable.top_photos_slider,
            R.drawable.global_chat_room_slider,
            R.drawable.no_ads_slider
    };

    private Context context;
    private LayoutInflater layoutInflater;

    public PalupPlusSliderAdapter(Context context) {
        this.context = context;

    }

    @Override
    public int getCount() {
        //todo this will be imageList

        return image.length;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return (view == (RelativeLayout) o);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.profile_image_sliding_layout, container, false);
        final ImageView imageView = view.findViewById(R.id.sender_imageView);
        imageView.setImageResource(image[position]);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout) object);
    }
}
