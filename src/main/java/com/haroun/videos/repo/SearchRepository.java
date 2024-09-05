package com.haroun.videos.repo;

import com.haroun.videos.model.Video;

import java.util.List;

public interface SearchRepository {
    List<Video> findByText(String text);
}
