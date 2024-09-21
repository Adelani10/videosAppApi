package com.haroun.videos.service;

import com.haroun.videos.model.Creator;
import com.haroun.videos.model.Video;
import com.haroun.videos.repo.SearchRepository;
import com.haroun.videos.repo.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class VideoService {

  @Autowired
  private VideoRepository videoRepo;

  @Autowired
  private SearchRepository searchRepository;

  public List<Video> getAllVideos() {
    return videoRepo.findAll();
  }

  public Video addVideo(Video video) {
    return videoRepo.save(video);
  }

  public List<Video> getVideosUploadedByCreator(String username) {
    List<Video> creatorVideos = new ArrayList<>();

    List<Video> allVideos = getAllVideos();

    try {
      for (Video video : allVideos) {
        if (video.getCreator().getUsername().equals(username)) {
          creatorVideos.add(video);
        }
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    return creatorVideos;
  }

  public List<Video> searchByText(String text) {
    return searchRepository.findByText(text);
  }

  public void addBookmarks(Video video, int id) {
    List<Video> allVideos = getAllVideos();

    Video editedVidObject = allVideos.stream()
        .filter(vid -> vid.getCfId().getTimestamp() == id)
        .findFirst()
        .orElse(null);

    if (editedVidObject == null) {
      throw new RuntimeException("Video not found with id: " + id);
    }

    try {
      // Check if bookmarks exist and doesn't already contain the given video
      Creator creator = editedVidObject.getCreator();
      if (creator != null && !creator.getBookmarks().contains(video)) {
        creator.getBookmarks().add(video);
        videoRepo.save(editedVidObject); // Persist changes
      } else {
        throw new RuntimeException("Video already contained in bookmark");
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public void removeBookmarks(Video video, int id) {
    List<Video> allVideos = getAllVideos();

    // Find the video with the matching ID using equals for Object comparison
    Video editedVidObject = allVideos.stream()
        .filter(vid -> vid.getCfId().getTimestamp() == id)
        .findFirst()
        .orElse(null);

    // Check if the video was found
    if (editedVidObject == null) {
      throw new RuntimeException("Video not found with id: " + id);
    }

    try {
      // Check if bookmarks exist and contains the given video
      Creator creator = editedVidObject.getCreator();
      if (creator != null && !creator.getBookmarks().isEmpty() && creator.getBookmarks().contains(video)) {
        creator.getBookmarks().remove(video);
        videoRepo.save(editedVidObject); // Persist changes
      } else {
        throw new RuntimeException("Video not found in bookmarks");
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}
