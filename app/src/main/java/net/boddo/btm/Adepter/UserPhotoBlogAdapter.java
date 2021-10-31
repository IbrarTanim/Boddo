package net.boddo.btm.Adepter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.daasuu.bl.BubbleLayout;

import net.boddo.btm.Activity.FullImageFromOwnProfileActivity;
import net.boddo.btm.Model.UserPhotoBlogImages;
import net.boddo.btm.R;

import com.squareup.picasso.Picasso;

public class UserPhotoBlogAdapter extends RecyclerView.Adapter<UserPhotoBlogAdapter.UserPhotoBlogViewHolder> {

    private Context context;
    private UserPhotoBlogImages[] userPhotoBlogImagesList;

    //OthersProfilePhotoFragment, MyBlogPhotoActivity
    public UserPhotoBlogAdapter(Context context, UserPhotoBlogImages[] userPhotoBlogImagesList) {
        this.context = context;
        this.userPhotoBlogImagesList = userPhotoBlogImagesList;
    }

    @NonNull
    @Override
    public UserPhotoBlogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_images_list_row, parent, false);

        return new UserPhotoBlogViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserPhotoBlogViewHolder holder, final int position) {


        Picasso.get().load(userPhotoBlogImagesList[position].getPhoto()).into(holder.userImage);

        if (!userPhotoBlogImagesList[position].getDescription().equals("")) {
            holder.post.setVisibility(View.VISIBLE);
            holder.post.setText(userPhotoBlogImagesList[position].getDescription());
        }

        holder.userLikedCount.setText(userPhotoBlogImagesList[position].getLike());
        holder.userCommentCount.setText(userPhotoBlogImagesList[position].getComment());
        holder.userCount.setText(userPhotoBlogImagesList[position].getMatched());


        /*holder.imageViewLove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLoveListener.giveLove(position);
            }
        });*/

        String check = userPhotoBlogImagesList[position].getActionAt();
        if (userPhotoBlogImagesList[position].getActionAt().equals("pending")) {
            holder.llPrivacy.setVisibility(View.VISIBLE);
            holder.tvStatusReview.setText("Status: " + userPhotoBlogImagesList[position].getActionAt());
        } else {
            holder.llPrivacy.setVisibility(View.GONE);
        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FullImageFromOwnProfileActivity.class);
                intent.putExtra("PhotoBlog", (Parcelable) userPhotoBlogImagesList[position]);
                intent.putExtra("position", position);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return userPhotoBlogImagesList.length;
    }

    public class UserPhotoBlogViewHolder extends RecyclerView.ViewHolder {

        ImageView userImage;
        ImageView imageViewLove;
        TextView post, tvStatusReview;
        LinearLayout llPrivacy;

        //LinearLayout statusLayout;
        //BubbleLayout bubbleLayout;

        TextView userCount, userLikedCount, userCommentCount;

        public UserPhotoBlogViewHolder(@NonNull View itemView) {
            super(itemView);
            userImage = itemView.findViewById(R.id.photo_blog_ImageView);
            imageViewLove = itemView.findViewById(R.id.image_view_giving_love);
            userCount = itemView.findViewById(R.id.user_count_textView);
            userLikedCount = itemView.findViewById(R.id.love_count_textView);
            userCommentCount = itemView.findViewById(R.id.comment_count_textView);
            post = itemView.findViewById(R.id.post);
            tvStatusReview = itemView.findViewById(R.id.tvStatusReview);
            llPrivacy = itemView.findViewById(R.id.llPrivacy);
            //statusLayout = itemView.findViewById(R.id.status_layotu);

            //bubbleLayout = itemView.findViewById(R.id.bubble_layout);


        }
    }
}
