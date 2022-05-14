package com.example.movierating.entity;


import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class UserWithReview {
    @Embedded
    public User user;
    @Relation(
            parentColumn = "id",
            entityColumn = "id"

    ) public List<Review> reviewList;
}
