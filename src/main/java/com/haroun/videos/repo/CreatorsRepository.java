package com.haroun.videos.repo;

import com.haroun.videos.model.Creator;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreatorsRepository extends MongoRepository<Creator, ObjectId> {

  Creator findByEmail(String email);
}
