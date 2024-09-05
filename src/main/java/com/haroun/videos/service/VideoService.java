package com.haroun.videos.service;

import com.haroun.videos.model.Video;
import com.haroun.videos.repo.SearchRepository;
import com.haroun.videos.repo.VideoRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


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
            for(Video video : allVideos){
                if(video.getCreator().getUsername().equals(username)){
                    creatorVideos.add(video);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return creatorVideos;
    }

    public Video addBookmarks(Video video, int id){
        Video editedVid = new Video();

        List<Video> allVideos = getAllVideos();

        for(Video vid : allVideos){
            if(vid.getCfId() == id){
                editedVid = vid;
            }
        }

        try {
            if(!editedVid.getCreator().getBookmarks().contains(video)){
                editedVid.getCreator().getBookmarks().add(video);
                return videoRepo.save(editedVid);
            } else {
                return videoRepo.save(editedVid);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Video> searchByText(String text) {
        return searchRepository.findByText(text);
    }
}
