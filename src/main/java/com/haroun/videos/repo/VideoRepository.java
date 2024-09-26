package com.haroun.videos.repo;

import com.haroun.videos.model.Video;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface VideoRepository extends MongoRepository<Video, ObjectId> {
  List<Video> findByCreatorId(ObjectId creatorId);
}
