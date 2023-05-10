package com.example.assignment.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "ratings")
public class RatingEntity {

    /* Entities can be termed as a table of a database and below variables can be termed as the columns for that table. */

    @Id
    @Column(name = "tconst", nullable = false)
    private String tconst;

    @Column(name = "averagerating", nullable = false)
    private float averageRating;

    @Column(name = "numvotes", nullable = false)
    private int numVotes;
}

