package com.recurjun.songr.data;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@RequiredArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long songId;

    @NonNull
    private String title;

    @NonNull
    private int length;

    @NonNull
    private int trackNumber;

    @ManyToOne
    @JoinColumn(name="album_id", nullable=false)
    private Album album;
}
