package com.recurjun.songr.web;

import com.recurjun.songr.data.Album;
import com.recurjun.songr.data.Song;
import com.recurjun.songr.infrastructure.services.AlbumService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;

@Controller
public class AlbumController {

    private final AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping("/albums")
    String getAlbums(Model model) {
        model.addAttribute("albums", albumService.getAllAlbums());

        return "albums";
    }

    @PostMapping("/albums")
    RedirectView newAlbum(@RequestParam String title,
                          @RequestParam String artist,
                          @RequestParam int songCount,
                          @RequestParam int length) {
        albumService.saveAlbum(new Album(title, artist, songCount, length));

        return new RedirectView("/albums");
    }

    @GetMapping("/edit/{id}")
    String showUpdateForm(@PathVariable("id") long id, Model model) {
        Album album = albumService.getAlbum(id);

        model.addAttribute("album", album);
        return "update-album";
    }

    @PostMapping("/update/{id}")
    String updateAlbum(@PathVariable("id") long id, @Valid Album album, BindingResult result, Model model) {
        if (result.hasErrors()) {
            album.setAlbumId(id);
            return "update-album";
        }

        albumService.saveAlbum(album);
        return "redirect:/albums";
    }

    @GetMapping("/delete/{id}")
    String deleteAlbum(@PathVariable("id") long id, Model model) {
        Album album = albumService.getAlbum(id);
        albumService.deleteAlbum(album);
        return "redirect:/albums";
    }

    @GetMapping("/view/{id}")
    String showAlbum(@PathVariable("id") long id, Model model) {
        Album album = albumService.getAlbum(id);

        model.addAttribute("album", album);
        model.addAttribute("songs", album.getSongs());

        return "album";
    }

    @PostMapping("/albums/{id}")
    RedirectView addSong(@RequestParam String title,
                         @RequestParam int length,
                         @RequestParam int trackNumber,
                         @PathVariable("id") long id) {
        albumService.addSong(id, new Song(title, length, trackNumber));

        return new RedirectView("/view/" + id);
    }

    @GetMapping("/songs")
    String showAllSongs(Model model) {
        model.addAttribute("songs", albumService.getAllSongs());

        return "songs";
    }
}
