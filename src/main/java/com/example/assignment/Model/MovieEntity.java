package com.example.assignment.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "movies")
public class MovieEntity {

    /* Entities can be termed as a table of a database and below variables can be termed as the columns for that table. */

    @Id
    @Column(name = "tconst", nullable = false)
    private String tconst;

    @Column(name = "titletype", nullable = false)
    private String titleType;

    @Column(name = "primarytitle", nullable = false)
    private String primaryTitle;

    @Column(name = "runtimeminutes", nullable = false)
    private int runtimeMinutes;

    @Column(name = "genres", nullable = false)
    private String genres;
}

