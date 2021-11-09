package net.boddo.btm.Adepter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import net.boddo.btm.Model.ChatRequest;
import net.boddo.btm.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChatRequestAdepter extends RecyclerView.Adapter<ChatRequestAdepter.ChatRequestViewHolder> {

    private Context context;
    private ArrayList<ChatRequest.RequestedMessage> chatRequestModelArrayList;

    public ChatRequestAdepter(Context context, List<ChatRequest.RequestedMessage> chatRequestModelArrayList) {
        this.context = context;
        this.chatRequestModelArrayList = (ArrayList<ChatRequest.RequestedMessage>) chatRequestModelArrayList;
    }

    public ChatRequestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(context).inflate(R.layout.item_all_chat_request,parent,false);
        return new ChatRequestViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ChatRequestAdepter.ChatRequestViewHolder holder, int position) {
        holder.name.setText(chatRequestModelArrayList.get(position).getFirstName()+", ");
      //  holder.age.setText(chatRequestModelArrayList.get(position).getLastMessageTime());
      //  holder.address.setText(chatRequestModelArrayList.get(position).getFirstName());
        Picasso.get().load(chatRequestModelArrayList.get(position).getProfilePhoto()).into(holder.imageView);
        //Picasso.get().load(R.drawable.ic_message_icon_31_10_2021).into(holder.civMessageRecentMatch);

    }

    @Override
    public int getItemCount() {
        return chatRequestModelArrayList.size();
    }


    public class ChatRequestViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView name,age,address;
        CircleImageView civMessageRecentMatch;

        public ChatRequestViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.civChatRequestItem);
            name = itemView.findViewById(R.id.chatRequest_usernameTV);
           // age = itemView.findViewById(R.id.chatRequestAgeTV);
          //  address = itemView.findViewById(R.id.chatRequestAddressTV);
          //  civMessageRecentMatch = itemView.findViewById(R.id.civMessageRecentMatch);

        }
    }

}
