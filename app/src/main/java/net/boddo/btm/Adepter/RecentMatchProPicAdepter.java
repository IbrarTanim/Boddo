package net.boddo.btm.Adepter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.siyamed.shapeimageview.CircularImageView;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import net.boddo.btm.Activity.PrivateChatActivity;
import net.boddo.btm.Model.RecentMatchModel;
import net.boddo.btm.R;
import net.boddo.btm.Utills.Data;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecentMatchProPicAdepter extends RecyclerView.Adapter<RecentMatchProPicAdepter.RecentMatchProPicViewHolder> {

    private Context context;
    private List<RecentMatchModel.Match> recentMatchModelArrayList;

    public RecentMatchProPicAdepter(Context context, List<RecentMatchModel.Match> recentMatchModelArrayList) {
        this.context = context;
        this.recentMatchModelArrayList = recentMatchModelArrayList;
    }

    @NonNull
    @Override
    public RecentMatchProPicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_all_recent_match, parent, false);
        return new RecentMatchProPicViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecentMatchProPicAdepter.RecentMatchProPicViewHolder holder, int position) {
        holder.name.setText(recentMatchModelArrayList.get(position).getUserName() + ", ");
        holder.age.setText(recentMatchModelArrayList.get(position).getUserId());
        holder.address.setText(recentMatchModelArrayList.get(position).getFirstName());
        Picasso.get().load(recentMatchModelArrayList.get(position).getProfilePhoto()).into(holder.imageView);
        //Picasso.get().load(R.drawable.ic_message_icon_31_10_2021).into(holder.civMessageRecentMatch);

    }

    @Override
    public int getItemCount() {
        return recentMatchModelArrayList.size();
    }

    class RecentMatchProPicViewHolder extends RecyclerView.ViewHolder {

        RoundedImageView imageView;
        TextView name, age, address;
        CircleImageView civMessageRecentMatch;

        public RecentMatchProPicViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.chatRequestIV);
            name = itemView.findViewById(R.id.chatRequestNameTV);
            age = itemView.findViewById(R.id.chatRequestAgeTV);
            address = itemView.findViewById(R.id.chatRequestAddressTV);
            civMessageRecentMatch = itemView.findViewById(R.id.civMessageRecentMatch);
            civMessageRecentMatch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                     //Toast.makeText(context, "Hi", Toast.LENGTH_SHORT).show();
                    Toast.makeText(context, Data.otherUserId, Toast.LENGTH_SHORT).show();


                    /*Intent intent = new Intent(context, PrivateChatActivity.class);
                    *//*if (Data.isMatched.equals("yes")) {
                        isMatch = true;
                    }*//*
                    //intent.putExtra("other_user_id", Data.otherUserId);
                    context.startActivity(intent);*/

                }
            });






        }
    }
}
