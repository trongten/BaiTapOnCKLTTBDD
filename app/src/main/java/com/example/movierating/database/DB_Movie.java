package com.example.movierating.database;


import android.content.Context;


import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.movierating.dao.DAO_Movie;
import com.example.movierating.entity.Movie;
import com.example.movierating.entity.Review;
import com.example.movierating.entity.User;

@Database(entities = {Movie.class, Review.class, User.class}, version = 1)
public abstract class DB_Movie extends RoomDatabase {

    private static DB_Movie INSTANCE;

    public abstract DAO_Movie dao_movie();

    public static DB_Movie getInMemoryDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.inMemoryDatabaseBuilder(context.getApplicationContext(), DB_Movie.class)
                            // To simplify the codelab, allow queries on the main thread.
                            // Don't do this on a real app! See PersistenceBasicSample for an example.
                            .allowMainThreadQueries()
                            .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }



}
