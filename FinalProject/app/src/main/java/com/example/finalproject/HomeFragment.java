package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.UUID;

import static android.app.Activity.RESULT_OK;

public class HomeFragment extends Fragment {
    public static final int ADD_TRIP_REQUEST = 1;
    public static final int EDIT_TRIP_REQUEST = 2;
    private TripViewModel tripViewModel;


    public HomeFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        setHasOptionsMenu(true);

        FloatingActionButton buttonAddTrip = (FloatingActionButton) view.findViewById(R.id.button_add_trip);
        buttonAddTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddEditTripActivity.class);
                startActivityForResult(intent, ADD_TRIP_REQUEST);

            }
        });

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);

        final TripAdapter adapter = new TripAdapter();
        recyclerView.setAdapter(adapter);

        tripViewModel = new ViewModelProvider(this).get(TripViewModel.class);
        tripViewModel.getAllTrips().observe(this, new Observer<List<Trip>>() {
            @Override
            public void onChanged(List<Trip> trips) {
                adapter.submitList(trips);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                tripViewModel.delete(adapter.getTripAt(viewHolder.getAdapterPosition()));
                Toast.makeText(getActivity(), "Trip deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);

        adapter.setOnItemClickListener(new TripAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Trip trip) {
            }

            @Override
            public void onLongClick(Trip trip) {
                Intent intent = new Intent(getActivity(), AddEditTripActivity.class);
                intent.putExtra(AddEditTripActivity.Extra_ID, trip.getId());
                intent.putExtra(AddEditTripActivity.Extra_NAME, trip.getName());
                intent.putExtra(AddEditTripActivity.Extra_DESTINATION, trip.getDestination());
                intent.putExtra(AddEditTripActivity.Extra_TYPE, trip.getType());
                intent.putExtra(AddEditTripActivity.Extra_PRICE, trip.getPrice());
                intent.putExtra(AddEditTripActivity.Extra_START_DATE, trip.getStartDate());
                intent.putExtra(AddEditTripActivity.Extra_END_DATE, trip.getEndDate());
                intent.putExtra(AddEditTripActivity.Extra_RATING, trip.getRating());
                startActivityForResult(intent, EDIT_TRIP_REQUEST);


            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_TRIP_REQUEST && resultCode == RESULT_OK) {
            String name = data.getStringExtra(AddEditTripActivity.Extra_NAME);
            String destination = data.getStringExtra(AddEditTripActivity.Extra_DESTINATION);
            String type = data.getStringExtra(AddEditTripActivity.Extra_TYPE);
            String price = data.getStringExtra(AddEditTripActivity.Extra_PRICE);
            String startDate = data.getStringExtra(AddEditTripActivity.Extra_START_DATE);
            String endDate = data.getStringExtra(AddEditTripActivity.Extra_END_DATE);
            String rating = data.getStringExtra(AddEditTripActivity.Extra_RATING);

            Trip trip = new Trip(name, destination, type, price, startDate, endDate, rating, UUID.randomUUID().toString());
            tripViewModel.insert(trip);

            Toast.makeText(getActivity(), "Trip saved", Toast.LENGTH_SHORT).show();
        } else if (requestCode == EDIT_TRIP_REQUEST && resultCode == RESULT_OK) {
            int id = data.getIntExtra(AddEditTripActivity.Extra_ID, -1);

            if (id == -1) {
                Toast.makeText(getActivity(), "Trip can't be updated", Toast.LENGTH_SHORT).show();
                return;
            }
            String name = data.getStringExtra(AddEditTripActivity.Extra_NAME);
            String destination = data.getStringExtra(AddEditTripActivity.Extra_DESTINATION);
            String type = data.getStringExtra(AddEditTripActivity.Extra_TYPE);
            String price = data.getStringExtra(AddEditTripActivity.Extra_PRICE);
            String startDate = data.getStringExtra(AddEditTripActivity.Extra_START_DATE);
            String endDate = data.getStringExtra(AddEditTripActivity.Extra_END_DATE);
            String rating = data.getStringExtra(AddEditTripActivity.Extra_RATING);

            Trip trip = new Trip(name, destination, type, price, startDate, endDate, rating, UUID.randomUUID().toString());
            trip.setId(id);
            tripViewModel.update(trip);
            Toast.makeText(getActivity(), "Trip updated", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(getActivity(), "Trip not saved", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.home_fragment_menu, menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_all_trips:
                tripViewModel.deleteAllTrips();
                Toast.makeText(getActivity(), "All trips deleted", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
