package com.example.travel_android_app.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travel_android_app.Activities.Hotel;
import com.example.travel_android_app.Activities.ViewActivity;
import com.example.travel_android_app.R;

import java.util.List;

public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.ViewHolder> {

    private List<Hotel> hotelList;
    private Context context;
    public HotelAdapter(List<Hotel> hotelList, Context context) {
        this.hotelList = hotelList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_record, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Hotel hotel = hotelList.get(position);
        holder.titleTextView.setText(hotel.getTitle());
        holder.authorTextView.setText(hotel.getAuthor());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Launch ViewBookActivity with hotel details
                Intent intent = new Intent(context, ViewActivity.class);
                intent.putExtra("id", hotel.getId());
                intent.putExtra("title", hotel.getTitle());
                intent.putExtra("author", hotel.getAuthor());
                intent.putExtra("desc", hotel.getDescription());
                context.startActivity(intent);

            }
        });
    }


    @Override
    public int getItemCount() {
        return hotelList.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView authorTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.title);
            authorTextView = itemView.findViewById(R.id.book_title);
        }
    }
}