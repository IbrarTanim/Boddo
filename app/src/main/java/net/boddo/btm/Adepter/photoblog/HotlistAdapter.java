package net.boddo.btm.Adepter.photoblog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.makeramen.roundedimageview.RoundedImageView;

import net.boddo.btm.Model.Hotlist;
import net.boddo.btm.R;
import net.boddo.btm.Utills.Data;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HotlistAdapter extends RecyclerView.Adapter<HotlistAdapter.HotlistViewHolder> {

    private Context ctx;
    private Hotlist[] hotlist;

    private OnPhotoblogImageClickListener listener;

    public void setProfileImageClickListener(OnPhotoblogImageClickListener listener) {
        this.listener = listener;
    }

    public HotlistAdapter(Context context, Hotlist[] hotlist) {
        this.ctx = context;
        this.hotlist = hotlist;
    }

    @NonNull
    @Override
    public HotlistViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.round_shape_photoblog_image_layout, viewGroup, false);
        return new HotlistViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HotlistViewHolder hotlistViewHolder, final int i) {

        /*if (i == 0) {
            Glide.with(ctx).load(Data.profilePhoto).apply(RequestOptions.bitmapTransform(new RoundedCorners(10))).into(hotlistViewHolder.imageView);
            //Glide.with(getActivity()).load(Data.profilePhoto).apply(RequestOptions.bitmapTransform(new RoundedCorners(10))).into(userImageView);

        } else {
            Glide.with(ctx).load(hotlist[i].getProfilePhoto()).apply(RequestOptions.bitmapTransform(new RoundedCorners(10))).into(hotlistViewHolder.imageView);
        }*/

        Glide.with(ctx).load(hotlist[i].getProfilePhoto()).apply(RequestOptions.bitmapTransform(new RoundedCorners(10))).into(hotlistViewHolder.imageView);


        hotlistViewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onImageClick(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return hotlist.length;
    }

    public class HotlistViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.hot_list_image_view)
        //ImageView imageView;
         RoundedImageView imageView;

        public HotlistViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnPhotoblogImageClickListener {
        void onImageClick(int position);
    }
}
