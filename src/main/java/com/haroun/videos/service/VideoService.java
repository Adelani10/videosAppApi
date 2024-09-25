package com.haroun.videos.service;

import com.haroun.videos.model.Video;
import com.haroun.videos.repo.SearchRepository;
import com.haroun.videos.repo.VideoRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class VideoService {

  @Autowired
  private VideoRepository videoRepo;

  @Autowired
  private CreatorService creatorService;

  @Autowired
  private SearchRepository searchRepository;

  public List<Video> getAllVideos() {
    return videoRepo.findAll();
  }

  public Video addVideo(Video video) {
    return videoRepo.save(video);
  }

  public List<Video> getCreatorVideos() {
    ObjectId currentCreatorId = creatorService.getCurrentCreator().getAccountId();
    return videoRepo.findByUserId(currentCreatorId);
  }

  public List<Video> searchByText(String text) {
    return searchRepository.findByText(text);
  }

}
