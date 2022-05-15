package com.example.movierating.entity;




import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "Movie")
public class Movie {
    @PrimaryKey(autoGenerate = true)
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ColumnInfo(name = "movieName")
    private String movieName;
    @ColumnInfo(name = "description")
    private String description;
    @ColumnInfo(name = "linkTrailer")
    private String linkTrailer;
    @ColumnInfo(name = "linkImg")
    private String linkImg;
    @ColumnInfo(name = "rating")
    private Double rating;
    @ColumnInfo(name = "year")
    private int year;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Movie() {
    }

    public String getLinkImg() {
        return linkImg;
    }

    public void setLinkImg(String linkImg) {
        this.linkImg = linkImg;
    }

    @Ignore

    public Movie(String description,int id,String linkImg,String linkTrailer,String movieName,Double rating, int year) {
        this.id=id;
        this.movieName = movieName;
        this.description = description;
        this.linkTrailer = linkTrailer;
        this.linkImg = linkImg;
        this.rating = rating;
        this.year = year;
    }
    public Movie(String movieName, String description, String linkTrailer, String linkImg, Double rating, int year) {
        this.movieName = movieName;
        this.description = description;
        this.linkTrailer = linkTrailer;
        this.linkImg = linkImg;
        this.rating = rating;
        this.year = year;
    }



    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLinkTrailer() {
        return linkTrailer;
    }

    public void setLinkTrailer(String linkTrailer) {
        this.linkTrailer = linkTrailer;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", movieName='" + movieName + '\'' +
                ", description='" + description + '\'' +
                ", linkTrailer='" + linkTrailer + '\'' +
                ", linkImg='" + linkImg + '\'' +
                ", rating=" + rating +
                ", year=" + year +
                '}';
    }
}
