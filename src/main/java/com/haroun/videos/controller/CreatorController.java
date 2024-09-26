package com.haroun.videos.controller;


import com.haroun.videos.model.Creator;
import com.haroun.videos.model.Video;
import com.haroun.videos.service.CreatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/api/v1")
public class CreatorController {

  @Autowired
  private CreatorService creatorService;

  @PostMapping("/register")
  public Creator register(@RequestBody Creator creator) {
    return creatorService.register(creator);
  }

  @PostMapping("/login")
  public String login(@RequestBody Creator creator) {
    return creatorService.verify(creator);
  }

  @GetMapping("/creator")
  public ResponseEntity<Creator> getCurrentCreator() {
    if (creatorService.getCurrentCreator() != null) {
      return new ResponseEntity<>(creatorService.getCurrentCreator(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(creatorService.getCurrentCreator(), HttpStatus.BAD_REQUEST);
    }
  }

  @PutMapping("/bookmark")
  public void addBookmarks(@RequestBody Video video) {
    creatorService.addBookmarks(video);
  }

  @PutMapping("/remove_bookmark")
  public void removeBookmarks(@RequestBody Video video) {
    creatorService.removeBookmarks(video);
  }
}
