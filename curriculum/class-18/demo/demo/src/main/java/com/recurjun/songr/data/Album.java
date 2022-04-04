package com.recurjun.songr.data;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long albumId;

    @NonNull
    private String title;

    @NonNull
    private String artist;

    @Setter
    private String imageUrl;

    @NonNull
    private int songCount;

    @NonNull
    private int length;

    @OneToMany(mappedBy="album", cascade = CascadeType.ALL)
    private List<Song> songs = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;
}
