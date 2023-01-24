package com.example.drivewayparking.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drivewayparking.Activity.ViewSpecificDisputeActivity;
import com.example.drivewayparking.Model.DisputeResponse;
import com.example.drivewayparking.R;

import java.util.List;

public class DisputeAdapter extends RecyclerView.Adapter<DisputeAdapter.DisputeViewHolder>{

    private final Context context;
    private final List<DisputeResponse> disputeList;

    public DisputeAdapter(Context context, List<DisputeResponse> disputeList) {
        this.context = context;
        this.disputeList = disputeList;
    }

    @NonNull
    @Override
    public DisputeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.dispute_list_view, parent, false);
        return new DisputeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DisputeAdapter.DisputeViewHolder holder, int position) {
        DisputeResponse list = disputeList.get(position);
        holder.dispute_id.setText("Dispute ID: " + list.getId());
        holder.dispute_solved.setText("Resolved: " + list.isResolved());

        holder.dispute_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ViewSpecificDisputeActivity.class);
                Bundle bundle = new Bundle();
                bundle.putLong("did", list.getId());
                bundle.putBoolean("resolved", list.isResolved());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return disputeList.size();
    }

    static class DisputeViewHolder extends RecyclerView.ViewHolder{

        TextView dispute_id, dispute_solved;
        LinearLayout dispute_layout;

        public DisputeViewHolder(@NonNull View itemView) {
            super(itemView);
            dispute_id = itemView.findViewById(R.id.dispute_id);
            dispute_solved = itemView.findViewById(R.id.dispute_solved);
            dispute_layout = itemView.findViewById(R.id.dispute_layout);
        }

    }
}
