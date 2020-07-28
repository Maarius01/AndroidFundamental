package com.example.finalproject;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Update;

import java.util.List;

public class TripRepository {
    private TripDao tripDao;
    private LiveData<List<Trip>> allTrips;

    public TripRepository(Application application) {
        TripDatabase database = TripDatabase.getInstance(application);
        tripDao = database.tripDao();
        allTrips = tripDao.getAllTrips();
    }

    public void insert(Trip trip) {
        new InsertTripAsyncTask(tripDao).execute(trip);
    }

    public void update(Trip trip) {
        new UpdateTripAsyncTask(tripDao).execute(trip);
    }

    public void delete(Trip trip) {
        new DeleteTripAsyncTask(tripDao).execute(trip);
    }

    public void deteleAllTrips() {
        new DeleteAllTripsAsyncTask(tripDao).execute();
    }

    public LiveData<List<Trip>> getAllTrips() {
        return allTrips;
    }

    private static class InsertTripAsyncTask extends AsyncTask<Trip, Void, Void> {
        private TripDao tripDao;

        private InsertTripAsyncTask(TripDao tripDao) {
            this.tripDao = tripDao;
        }

        @Override
        protected Void doInBackground(Trip... trips) {
            tripDao.insert(trips[0]);
            return null;
        }
    }

    private static class UpdateTripAsyncTask extends AsyncTask<Trip, Void, Void> {
        private TripDao tripDao;

        private UpdateTripAsyncTask(TripDao tripDao) {
            this.tripDao = tripDao;
        }

        @Override
        protected Void doInBackground(Trip... trips) {
            tripDao.update(trips[0]);
            return null;
        }
    }

    private static class DeleteTripAsyncTask extends AsyncTask<Trip, Void, Void> {
        private TripDao tripDao;

        private DeleteTripAsyncTask(TripDao tripDao) {
            this.tripDao = tripDao;
        }

        @Override
        protected Void doInBackground(Trip... trips) {
            tripDao.delete(trips[0]);
            return null;
        }
    }

    private static class DeleteAllTripsAsyncTask extends AsyncTask<Void, Void, Void> {
        private TripDao tripDao;

        private DeleteAllTripsAsyncTask(TripDao tripDao) {
            this.tripDao = tripDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            tripDao.deleteAllTrips();
            return null;
        }
    }
}
