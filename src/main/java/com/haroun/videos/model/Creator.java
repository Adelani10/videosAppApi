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
  private ObjectId accountId;
  private String email;
  private String username;
  private List<Video> bookmarks;
  private String password;

  @Override
  public String toString() {
    return "Creator{" +
        "accountId=" + accountId +
        ", email='" + email + '\'' +
        ", username='" + username + '\'' +
        ", bookmarks=" + bookmarks +
        ", password='" + password + '\'' +
        '}';
  }
}
