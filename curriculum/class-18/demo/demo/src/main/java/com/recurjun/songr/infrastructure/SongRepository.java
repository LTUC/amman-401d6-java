package com.recurjun.songr.infrastructure;

import com.recurjun.songr.data.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
}
