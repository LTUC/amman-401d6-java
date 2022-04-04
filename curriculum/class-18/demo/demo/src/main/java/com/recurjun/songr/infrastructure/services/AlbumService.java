package com.recurjun.songr.infrastructure.services;

import com.recurjun.songr.data.Album;
import com.recurjun.songr.data.Song;

import java.util.List;

public interface AlbumService {
    void saveAlbum(Album album);
    List<Album> getAllAlbums();
    Album getAlbum(Long id);
    void deleteAlbum(Album album);
    void updateAlbum(Album album);

    void addSong(Long albumId, Song song);
    List<Song> getAllSongs();
}
