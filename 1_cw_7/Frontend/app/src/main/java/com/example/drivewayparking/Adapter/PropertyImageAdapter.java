package com.example.drivewayparking.Adapter;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drivewayparking.Model.ImageResponse;
import com.example.drivewayparking.R;

import org.java_websocket.util.Base64;

import java.io.IOException;
import java.util.List;

public class PropertyImageAdapter extends RecyclerView.Adapter<PropertyImageAdapter.PropertyImageHolder> {
    private final Context context;
    private final List<ImageResponse> imageList;

    public PropertyImageAdapter(Context context, List<ImageResponse> imageList) {
        this.context = context;
        this.imageList = imageList;
    }


    @NonNull
    @Override
    public PropertyImageAdapter.PropertyImageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.property_image_list_view, parent, false);
        return new PropertyImageHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PropertyImageAdapter.PropertyImageHolder holder, int position) {
       final ImageResponse image = imageList.get(position);
        byte[] decodedString = new byte[0];
        try {
            decodedString = Base64.decode(image.getContent(), android.util.Base64.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        holder.property_image.setImageBitmap(decodedByte);
        //holder.property_image.setRotation(90);
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    static class PropertyImageHolder extends RecyclerView.ViewHolder {

        ImageView property_image;
        public PropertyImageHolder(@NonNull View itemView) {
            super(itemView);
            property_image = itemView.findViewById(R.id.imageView_property);
        }
    }
}
