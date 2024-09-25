package com.haroun.videos.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "videosCollection")
public class Video {


  @Id
  private ObjectId cfId;
  private String title;
  private String prompt;
  private String thumbnail;
  private String video;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Video video = (Video) o;
    return Objects.equals(cfId.getTimestamp(), video.cfId.getTimestamp());
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(cfId.getTimestamp());
  }

  @Override
  public String toString() {
    return "Video{" +
        "cfId=" + cfId +
        ", title='" + title + '\'' +
        ", prompt='" + prompt + '\'' +
        ", thumbnail='" + thumbnail + '\'' +
        ", video='" + video + '\'' +
        '}';
  }
}
