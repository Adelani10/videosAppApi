package com.haroun.videos.repo;

import com.haroun.videos.model.Video;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface VideoRepository extends MongoRepository<Video, ObjectId> {
}
