package com.example.finalproject;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class TripAdapter extends ListAdapter<Trip, TripAdapter.TripHolder> {
    private OnItemClickListener listener;

    public TripAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Trip> DIFF_CALLBACK = new DiffUtil.ItemCallback<Trip>() {
        @Override
        public boolean areItemsTheSame(@NonNull Trip oldItem, @NonNull Trip newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Trip oldItem, @NonNull Trip newItem) {
            return oldItem.getName().equals(newItem.getName()) &&
                    oldItem.getDestination().equals(newItem.getDestination()) &&
                    oldItem.getType().equals(newItem.getType()) &&
                    oldItem.getPrice().equals(newItem.getPrice()) &&
                    oldItem.getStartDate().equals(newItem.getStartDate()) &&
                    oldItem.getEndDate().equals(newItem.getEndDate()) &&
                    oldItem.getRating().equals(newItem.getRating()) &&
                    oldItem.getUrlImage().equals(newItem.getUrlImage());
        }
    };

    @NonNull
    @Override
    public TripHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.trip_item, parent, false);
        return new TripHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TripHolder holder, int position) {
        Trip currentTrip = getItem(position);
        holder.textViewName.setText(currentTrip.getName());
        holder.textViewDestination.setText(currentTrip.getDestination());
        holder.textViewPrice.setText(String.valueOf(currentTrip.getPrice()));
        holder.imageViewPicture.setImageDrawable(null);
        Picasso.get().load(currentTrip.getUrlImage()).into(holder.getImageViewPicture());
    }

    public Trip getTripAt(int position) {
        return getItem(position);
    }

    class TripHolder extends RecyclerView.ViewHolder {
        private TextView textViewName;
        private TextView textViewDestination;
        private TextView textViewPrice;
        private ImageView imageViewPicture;

        public TripHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.text_view_name);
            textViewDestination = itemView.findViewById(R.id.text_view_destination);
            textViewPrice = itemView.findViewById(R.id.text_view_price);
            imageViewPicture = itemView.findViewById(R.id.image_view_picture);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onLongClick(getItem(position));
                    }
                }
            });
        }

        public TextView getTextViewName() {
            return textViewName;
        }

        public TextView getTextViewDestination() {
            return textViewDestination;
        }

        public TextView getTextViewPrice() {
            return textViewPrice;
        }

        public ImageView getImageViewPicture() {
            return imageViewPicture;
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Trip trip);

        void onLongClick(Trip trip);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
