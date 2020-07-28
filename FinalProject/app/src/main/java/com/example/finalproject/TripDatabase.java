package com.example.finalproject;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.Date;

@Database(entities = Trip.class, version = 1)
public abstract class TripDatabase extends RoomDatabase {
    private static TripDatabase instance;

    public abstract TripDao tripDao();

    public static synchronized TripDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    TripDatabase.class, "trip_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private TripDao tripDao;

        private PopulateDbAsyncTask(TripDatabase db) {
            tripDao = db.tripDao();

        }

        @Override
        protected Void doInBackground(Void... voids) {
            tripDao.insert(new Trip("name 1", "destination 1", "type 1", "1", "date 1", "date 2", "1", "urlImage 1"));
            tripDao.insert(new Trip("name 2", "destination 2", "type 2", "2", "date 3", "date 4", "2", "urlImage 2"));
            tripDao.insert(new Trip("name 3", "destination 3", "type 3", "3", "date 5", "date 6", "3", "urlImage 3"));
            return null;
        }
    }

}
