package com.example.movierating.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.movierating.dao.DAO_Movie;
import com.example.movierating.entity.Movie;
import com.example.movierating.entity.Review;
import com.example.movierating.entity.User;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Movie.class, Review.class, User.class}, version = 1, exportSchema = false)
public abstract class DB_Movie extends RoomDatabase {
    private static volatile DB_Movie instance;
    private static final String DATABASE_NAME = "Movie";
    private static final int NUM_OF_THREAD = 4;
    public static ExecutorService service
            = Executors.newFixedThreadPool(NUM_OF_THREAD);

    public abstract DAO_Movie dao_movie();

    public static DB_Movie getInstance(Context context) {
        if (instance == null)
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    DB_Movie.class, DATABASE_NAME).addCallback(callback).build();
        return instance;
    }

    public static void closeDatabase() {
        if (instance != null) instance.close();
    }

    private static RoomDatabase.Callback callback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            service.execute(() -> {
                DAO_Movie dao_movie = instance.dao_movie();
                dao_movie.deleteAll();
                //intsert data
                //String movieName, String description, String linkTrailer, String linkImg, Double rating, int year
                dao_movie.insertMovie(
                        new Movie("Moon Knight","Steven Grant discovers he's been granted the powers of an Egyptian moon god. But he soon finds out that these newfound powers can be both a blessing and a curse to his troubled life.",
                                "https://www.youtube.com/watch?v=x7Krla_UxRg&t=6s&ab_channel=MarvelEntertainment",
                                "https://s3-alpha-sig.figma.com/img/2990/1f76/ccb92499e34952ff64e23c8334d60e35?Expires=1652659200&Signature=LXFARDjLEc5V8Nnez8npFZmtdvstzrdyuQvDQ3ouplcC5Pg1dq2LxIZf1RR6ibmdEkwOq0gJ4hKb0USY6TwB0JUhBiKOE245I8XxXI~l-TGuL0MpU4uwA1ajWNja6rjEkMFyoF4dT5G0M0fbVyjW~gSJYyR8LZORLHljXHUdswS23q6oEvuTULMWhNzmfuL7Bgojtz4L60x-oyo7y87TFrHWOVnvE4UXIJA8XTTbJEC54IKEEwDGRHGGEyejb4UiGeIDuygveQaIg~BrIwXyD2~LhqVItmLqZHWlX55Az60iBGBbTondSCk4Ou3EQKLzEYY5jjohpO4MkmxNeIgcLQ__&Key-Pair-Id=APKAINTVSUGEWH5XD5UA",
                                4.9,
                                2022),
                        new Movie("Moon Knight","Steven Grant discovers he's been granted the powers of an Egyptian moon god. But he soon finds out that these newfound powers can be both a blessing and a curse to his troubled life.",
                                "https://www.youtube.com/watch?v=x7Krla_UxRg&t=6s&ab_channel=MarvelEntertainment",
                                "https://s3-alpha-sig.figma.com/img/2990/1f76/ccb92499e34952ff64e23c8334d60e35?Expires=1652659200&Signature=LXFARDjLEc5V8Nnez8npFZmtdvstzrdyuQvDQ3ouplcC5Pg1dq2LxIZf1RR6ibmdEkwOq0gJ4hKb0USY6TwB0JUhBiKOE245I8XxXI~l-TGuL0MpU4uwA1ajWNja6rjEkMFyoF4dT5G0M0fbVyjW~gSJYyR8LZORLHljXHUdswS23q6oEvuTULMWhNzmfuL7Bgojtz4L60x-oyo7y87TFrHWOVnvE4UXIJA8XTTbJEC54IKEEwDGRHGGEyejb4UiGeIDuygveQaIg~BrIwXyD2~LhqVItmLqZHWlX55Az60iBGBbTondSCk4Ou3EQKLzEYY5jjohpO4MkmxNeIgcLQ__&Key-Pair-Id=APKAINTVSUGEWH5XD5UA",
                                4.9,
                                2022),
                        new Movie("Moon Knight","Steven Grant discovers he's been granted the powers of an Egyptian moon god. But he soon finds out that these newfound powers can be both a blessing and a curse to his troubled life.",
                                "https://www.youtube.com/watch?v=x7Krla_UxRg&t=6s&ab_channel=MarvelEntertainment",
                                "https://s3-alpha-sig.figma.com/img/2990/1f76/ccb92499e34952ff64e23c8334d60e35?Expires=1652659200&Signature=LXFARDjLEc5V8Nnez8npFZmtdvstzrdyuQvDQ3ouplcC5Pg1dq2LxIZf1RR6ibmdEkwOq0gJ4hKb0USY6TwB0JUhBiKOE245I8XxXI~l-TGuL0MpU4uwA1ajWNja6rjEkMFyoF4dT5G0M0fbVyjW~gSJYyR8LZORLHljXHUdswS23q6oEvuTULMWhNzmfuL7Bgojtz4L60x-oyo7y87TFrHWOVnvE4UXIJA8XTTbJEC54IKEEwDGRHGGEyejb4UiGeIDuygveQaIg~BrIwXyD2~LhqVItmLqZHWlX55Az60iBGBbTondSCk4Ou3EQKLzEYY5jjohpO4MkmxNeIgcLQ__&Key-Pair-Id=APKAINTVSUGEWH5XD5UA",
                                4.9,
                                2022),
                        new Movie("Moon Knight","Steven Grant discovers he's been granted the powers of an Egyptian moon god. But he soon finds out that these newfound powers can be both a blessing and a curse to his troubled life.",
                                "https://www.youtube.com/watch?v=x7Krla_UxRg&t=6s&ab_channel=MarvelEntertainment",
                                "https://s3-alpha-sig.figma.com/img/2990/1f76/ccb92499e34952ff64e23c8334d60e35?Expires=1652659200&Signature=LXFARDjLEc5V8Nnez8npFZmtdvstzrdyuQvDQ3ouplcC5Pg1dq2LxIZf1RR6ibmdEkwOq0gJ4hKb0USY6TwB0JUhBiKOE245I8XxXI~l-TGuL0MpU4uwA1ajWNja6rjEkMFyoF4dT5G0M0fbVyjW~gSJYyR8LZORLHljXHUdswS23q6oEvuTULMWhNzmfuL7Bgojtz4L60x-oyo7y87TFrHWOVnvE4UXIJA8XTTbJEC54IKEEwDGRHGGEyejb4UiGeIDuygveQaIg~BrIwXyD2~LhqVItmLqZHWlX55Az60iBGBbTondSCk4Ou3EQKLzEYY5jjohpO4MkmxNeIgcLQ__&Key-Pair-Id=APKAINTVSUGEWH5XD5UA",
                                4.9,
                                2022),
                        new Movie("Moon Knight","Steven Grant discovers he's been granted the powers of an Egyptian moon god. But he soon finds out that these newfound powers can be both a blessing and a curse to his troubled life.",
                                "https://www.youtube.com/watch?v=x7Krla_UxRg&t=6s&ab_channel=MarvelEntertainment",
                                "https://s3-alpha-sig.figma.com/img/2990/1f76/ccb92499e34952ff64e23c8334d60e35?Expires=1652659200&Signature=LXFARDjLEc5V8Nnez8npFZmtdvstzrdyuQvDQ3ouplcC5Pg1dq2LxIZf1RR6ibmdEkwOq0gJ4hKb0USY6TwB0JUhBiKOE245I8XxXI~l-TGuL0MpU4uwA1ajWNja6rjEkMFyoF4dT5G0M0fbVyjW~gSJYyR8LZORLHljXHUdswS23q6oEvuTULMWhNzmfuL7Bgojtz4L60x-oyo7y87TFrHWOVnvE4UXIJA8XTTbJEC54IKEEwDGRHGGEyejb4UiGeIDuygveQaIg~BrIwXyD2~LhqVItmLqZHWlX55Az60iBGBbTondSCk4Ou3EQKLzEYY5jjohpO4MkmxNeIgcLQ__&Key-Pair-Id=APKAINTVSUGEWH5XD5UA",
                                4.9,
                                2022));
            });
        }
    };

}
