package com.example.drivewayparking.Adapter;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drivewayparking.Model.MessageRequest;
import com.example.drivewayparking.Model.MessageResponse;
import com.example.drivewayparking.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter {

    private static final int TYPE_MESSAGE_SENT = 0;
    private static final int TYPE_MESSAGE_RECEIVED = 1;
    private static final int TYPE_IMAGE_SENT = 2;
    private static final int TYPE_IMAGE_RECEIVED = 3;

    private final LayoutInflater inflater;
    private final List<MessageResponse> messages = new ArrayList<>();
    private String date;
    private String time;

    public MessageAdapter (LayoutInflater inflater) {
        this.inflater = inflater;
    }

    public MessageAdapter(LayoutInflater inflater, String date, String time) {
        this.inflater = inflater;
        this.date = date;
        this.time = time;

    }

    private class SentMessageHolder extends RecyclerView.ViewHolder {

        TextView messageTxt;
        TextView sentDate;
        TextView sentTime;

        public SentMessageHolder(@NonNull View itemView) {
            super(itemView);
            messageTxt = itemView.findViewById(R.id.sent_text);
            sentTime = itemView.findViewById(R.id.sent_time);
        }
    }

    private class SentImageHolder extends RecyclerView.ViewHolder {

        ImageView imageView;

        public SentImageHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
        }
    }

    private class ReceivedMessageHolder extends RecyclerView.ViewHolder {

        TextView nameTxt, messageTxt, receivedTime;

        public ReceivedMessageHolder(@NonNull View itemView) {
            super(itemView);

            nameTxt = itemView.findViewById(R.id.nameTxt);
            messageTxt = itemView.findViewById(R.id.received_text);
            receivedTime = itemView.findViewById(R.id.received_time);
        }
    }

    private class ReceivedImageHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView nameTxt;

        public ReceivedImageHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            nameTxt = itemView.findViewById(R.id.nameTxt);

        }
    }

    @Override
    public int getItemViewType(int position) {

        MessageResponse message = messages.get(position);
            if (message.isSent()) {
                    return TYPE_MESSAGE_SENT;

            } else {
                    return TYPE_MESSAGE_RECEIVED;
            }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;

        switch (viewType) {
            case TYPE_MESSAGE_SENT:
                view = inflater.inflate(R.layout.item_sent_message, parent, false);
                return new SentMessageHolder(view);
            case TYPE_MESSAGE_RECEIVED:

                view = inflater.inflate(R.layout.item_received_message, parent, false);
                return new ReceivedMessageHolder(view);


        }

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        MessageResponse message = messages.get(position);


            if (message.isSent()) {

                    SentMessageHolder messageHolder = (SentMessageHolder) holder;
                    messageHolder.messageTxt.setText(message.getMessage());
                    if(message.getSent() != null) {
                        Date date = new Date(message.getSent().getTime());
                        SimpleDateFormat simpleTime = new SimpleDateFormat("HH:mm");
                        messageHolder.sentTime.setText(simpleTime.format(date));

                    }
            } else {
                ReceivedMessageHolder messageHolder = (ReceivedMessageHolder) holder;
                messageHolder.nameTxt.setText(message.getName());
                messageHolder.messageTxt.setText(message.getMessage());
                if(message.getSent() != null){
                Date date = new Date(message.getSent().getTime());
                SimpleDateFormat simpleTime = new SimpleDateFormat("HH:mm");
                messageHolder.receivedTime.setText(simpleTime.format(date));
            }
        }
    }

    private Bitmap getBitmapFromString(String image) {

        byte[] bytes = Base64.decode(image, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public void addItem (MessageResponse message) {
        messages.add(message);
        Collections.sort(messages, new Comparator<MessageResponse>() {
            @Override
            public int compare(MessageResponse o1, MessageResponse o2) {
                if(o1.getSent() == null || o2.getSent() == null)
                    return 0;
                return o1.getSent().compareTo(o2.getSent());
            }
        });
        notifyDataSetChanged();
    }



}