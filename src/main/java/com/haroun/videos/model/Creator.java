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
@Document(collection = "creators")
public class Creator {

    @Id
    private ObjectId accountId;
    private String avatar;
    private String email;
    private String username;
    private String password;
}
