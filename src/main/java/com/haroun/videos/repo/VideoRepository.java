package com.haroun.videos.repo;

import com.haroun.videos.model.Video;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VideoRepository extends MongoRepository<Video, ObjectId> {
}
