package com.doctorjiyo.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import com.doctorjiyo.app.API.Model.Chat;
import com.doctorjiyo.app.R;

public class MessegeAdapter extends RecyclerView.Adapter<MessegeAdapter.ViewHolder> {

    public static final int MSG_TYPE_LEFT=0;
    public static final int MSG_TYPE_RIGHT=1;
    private Context mcontext;
    private List<Chat> mchat;

    public MessegeAdapter(Context mcontext, List<Chat> mchat) {
        this.mcontext = mcontext;
        this.mchat = mchat;

    }

    public MessegeAdapter(List<Chat> mchat) {
        this.mchat = mchat;
    }



    @NonNull
    @Override
    public MessegeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if(viewType==MSG_TYPE_RIGHT){
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_sending_msg_bubble,parent,false);
            ViewHolder viewHolder=new  MessegeAdapter.ViewHolder(view);
            return viewHolder;
        }
        else
        {
          View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_receiving_msg_bubble,parent,false);
            ViewHolder viewHolder=new  MessegeAdapter.ViewHolder(view);
            return viewHolder;
        }
    }



    @Override
    public void onBindViewHolder(@NonNull final MessegeAdapter.ViewHolder holder, final int position) {

        Chat chat=mchat.get(position);
        holder.show_msg.setText(chat.getMessage());
        holder.showtime.setText(chat.getMsgTime());


    }

    @Override
    public int getItemCount() {
        return mchat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView show_msg,showtime;
        View view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            view=itemView;
            show_msg=itemView.findViewById(R.id.msg);
            showtime=itemView.findViewById(R.id.time);

        }
    }

    @Override
    public int getItemViewType(int position) {

        if (mchat.get(position).getSendertype().equals("Patient")){
            return MSG_TYPE_RIGHT;
        }
        else {
            return MSG_TYPE_LEFT;
        }
    }


}
































