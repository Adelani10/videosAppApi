package com.haroun.videos.repo;

import com.haroun.videos.model.Video;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class SearchRepoImplementation implements SearchRepository {

  @Autowired
  MongoClient client;

  @Autowired
  MongoConverter converter;

  @Override
  public List<Video> findByText(String text) {


    final List<Video> videos = new ArrayList<>();


    MongoDatabase database = client.getDatabase("videos");
    MongoCollection<Document> collection = database.getCollection("videosCollection");

    AggregateIterable<Document> result = collection.aggregate(Arrays.asList(
        new Document("$search",
            new Document("index", "default")
                .append("text",
                    new Document("query", text)
                        .append("path",
                            Arrays.asList("title", "prompt", "thumbnail", "video")))),
        new Document("$sort",
            new Document("cfid", 1L)),
        new Document("$limit", 3L))

    );
    result.forEach(doc -> videos.add(converter.read(Video.class, doc)));

    return videos;
  }
}
