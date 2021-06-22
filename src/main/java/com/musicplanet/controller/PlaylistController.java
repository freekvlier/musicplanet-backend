package com.musicplanet.controller;

import com.musicplanet.dto.PlaylistDTO;
import com.musicplanet.entities.Playlist;
import com.musicplanet.services.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/playlists")
public class PlaylistController {

    @Autowired
    private PlaylistService playlistService;

    @CrossOrigin
    @GetMapping("")
    public List<Playlist> getAll(){
        return playlistService.getAll();
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Playlist>> get(@PathVariable Long id){
        try{
            Optional<Playlist> playlist = playlistService.getById(id);
            return new ResponseEntity<>(playlist, HttpStatus.OK);
        } catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @GetMapping("/name/{name}")
    public List<Playlist> getByName(@PathVariable("name") String name) {
        return playlistService.GetByName(name);
    }

    @CrossOrigin
    @PostMapping("/")
    public void add(@RequestBody PlaylistDTO playlistDTO){
        Playlist playlist = new Playlist(playlistDTO);
        playlistService.save(playlist);
    }

    @CrossOrigin
    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> update(@RequestBody PlaylistDTO playlistDTO, @PathVariable Long id){
        try{
            Optional<Playlist> existPlaylist = playlistService.getById(id);

            if(!existPlaylist.isPresent()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            playlistDTO.setId(id);
            Playlist playlist = new Playlist(playlistDTO);
            playlistService.save(playlist);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        playlistService.delete(id);
    }

    @CrossOrigin
    @DeleteMapping("/")
    public ResponseEntity<HttpStatus> deleteAll() {
        try {
            playlistService.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
