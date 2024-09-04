package com.haroun.videos.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document (collection = "videosCollection")
public class Video {

    @Id
    private ObjectId id;
    private String title;
    private String prompt;
    private String thumbnail;
    private String video;
    private Creator creator;


}
