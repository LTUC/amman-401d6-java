package com.recurjun.songr.infrastructure.services;

import com.recurjun.songr.data.Album;
import com.recurjun.songr.data.Song;
import com.recurjun.songr.infrastructure.AlbumRepository;
import com.recurjun.songr.infrastructure.SongRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("albumService")
public class AlbumServiceImpl implements AlbumService {
    private final AlbumRepository albumRepository;
    private final SongRepository songRepository;

    public AlbumServiceImpl(AlbumRepository albumRepository,
                            SongRepository songRepository) {
        this.albumRepository = albumRepository;
        this.songRepository = songRepository;
    }

    @Override
    public void saveAlbum(Album album) {
        albumRepository.save(album);
    }

    @Override
    public List<Album> getAllAlbums() {
        return albumRepository.findAll();
    }

    @Override
    public Album getAlbum(Long id) {
        return albumRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Album Id:" + id));
    }

    @Override
    public void deleteAlbum(Album album) {
        albumRepository.delete(album);
    }

    @Override
    public void updateAlbum(Album album) {
        albumRepository.save(album); // TODO: 7/29/21 this can be done better
    }

    @Override
    public void addSong(Long albumId, Song song) {
        Album album = this.getAlbum(albumId);
        song.setAlbum(album);

        songRepository.save(song);
    }

    @Override
    public List<Song> getAllSongs() {
        return songRepository.findAll();
    }
}
