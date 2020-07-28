package com.example.finalproject;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class TripViewModel extends AndroidViewModel {
    private TripRepository repository;
    private LiveData<List<Trip>> allTrips;

    public TripViewModel(@NonNull Application application) {
        super(application);
        repository = new TripRepository(application);
        allTrips = repository.getAllTrips();
    }
    public void insert(Trip trip){
        repository.insert(trip);
    }
    public void update(Trip trip){
        repository.update(trip);
    }
    public void delete(Trip trip){
        repository.delete(trip);
    }
    public void deleteAllTrips(){
        repository.deteleAllTrips();
    }

    public LiveData<List<Trip>> getAllTrips() {
        return allTrips;
    }
}
