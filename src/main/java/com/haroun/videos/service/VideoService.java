package com.haroun.videos.service;

import com.haroun.videos.model.Video;
import com.haroun.videos.repo.VideoRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class VideoService {

    @Autowired
    private VideoRepository videoRepo;

    public List<Video> getAllVideos() {
        return videoRepo.findAll();
    }

    public Video addVideo(Video video) {
        return videoRepo.save(video);
    }

    public List<Video> getVideosUploadedByCreator(int id) {
        List<Video> creatorVideos = new ArrayList<>();

        List<Video> allVideos = getAllVideos();

        try {
            for(Video video : allVideos){
                if(video.getCreator().getAccountId().getTimestamp() == id){
                    creatorVideos.add(video);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return creatorVideos;
    }
}
