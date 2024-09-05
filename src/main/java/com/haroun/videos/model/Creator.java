package com.haroun.videos.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "creatorsCollection")
public class Creator {

    @Id
    private ObjectId id;
    private String avatar;
    private String email;
    private List<Video> bookmarks;
    private String username;
    private String password;

    public Creator(List<Video> bookmarks) {
        this.bookmarks = bookmarks;
    }
}
