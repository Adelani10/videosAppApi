package com.haroun.videos.controller;

import com.haroun.videos.model.Video;
import com.haroun.videos.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/api/v1")
public class VideoController {

  @Autowired
  private VideoService videoService;

  @GetMapping("/videos")
  public ResponseEntity<List<Video>> getAllVideos() {
    return new ResponseEntity<>(videoService.getAllVideos(), HttpStatus.OK);
  }

//  @GetMapping("/videos/{username}")
//  public ResponseEntity<List<Video>> getVideosUploadedByCreator(@PathVariable String username) {
//    return new ResponseEntity<>(videoService.getVideosUploadedByCreator(username), HttpStatus.OK);
//  }

  @PostMapping("/videos")
  public ResponseEntity<Video> addVideo(@RequestBody Video video) {
    return new ResponseEntity<>(videoService.addVideo(video), HttpStatus.CREATED);
  }

  @GetMapping("/videos/search/{text}")
  public ResponseEntity<List<Video>> searchByText(@PathVariable String text) {
    return new ResponseEntity<>(videoService.searchByText(text), HttpStatus.OK);
  }


}
