package com.haroun.videos.service;

import com.haroun.videos.model.Creator;
import com.haroun.videos.model.CreatorPrincipal;
import com.haroun.videos.repo.CreatorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreatorService {

  BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  JWTService jwtService;

  @Autowired
  private CreatorsRepository creatorsRepository;

  public Creator register(Creator creator) {
    creator.setPassword(encoder.encode(creator.getPassword()));
    return creatorsRepository.save(creator);
  }

  public String verify(Creator creator) {
    Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(creator.getEmail(), creator.getPassword()));
    if (authentication.isAuthenticated()) {
      return jwtService.generateToken(creator.getEmail());
    } else {
      return "fail";
    }
  }

  public Creator getCurrentCreator() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication != null && authentication.isAuthenticated()) {
      Object principal = authentication.getPrincipal();
      if (principal instanceof CreatorPrincipal) {
        return ((CreatorPrincipal) principal).getCreator();
      } else {
        throw new RuntimeException("Principal isn't an instance of CreatorPrincipal");
      }
    }
    return null;
  }

//  public void removeBookmarks(Video video, int id) {
//    List<Video> allVideos = getAllVideos();
//
//    // Find the video with the matching ID using equals for Object comparison
//    Video editedVidObject = allVideos.stream()
//        .filter(vid -> vid.getCfId().getTimestamp() == id)
//        .findFirst()
//        .orElse(null);
//
//    // Check if the video was found
//    if (editedVidObject == null) {
//      throw new RuntimeException("Video not found with id: " + id);
//    }
//
//    try {
//      // Check if bookmarks exist and contains the given video
//      Creator creator = editedVidObject.getCreator();
//      if (creator != null && !creator.getBookmarks().isEmpty() && creator.getBookmarks().contains(video)) {
//        creator.getBookmarks().remove(video);
//        videoRepo.save(editedVidObject); // Persist changes
//      } else {
//        throw new RuntimeException("Video not found in bookmarks");
//      }
//    } catch (Exception e) {
//      throw new RuntimeException(e);
//    }
//  }

//  public void addBookmarks(Video video, int id) {
//    List<Creator> allCreators = creatorsRepository.findAll();
//
//    Video editedVidObject = allVideos.stream()
//        .filter(vid -> vid.getCfId().getTimestamp() == id)
//        .findFirst()
//        .orElse(null);
//
//    if (editedVidObject == null) {
//      throw new RuntimeException("Video not found with id: " + id);
//    }
//
//    try {
//      // Check if bookmarks exist and doesn't already contain the given video
//      Creator creator = editedVidObject.getCreator();
//      if (creator != null && !creator.getBookmarks().contains(video)) {
//        creator.getBookmarks().add(video);
//        videoRepo.save(editedVidObject); // Persist changes
//      } else {
//        throw new RuntimeException("Video already contained in bookmark");
//      }
//    } catch (Exception e) {
//      throw new RuntimeException(e);
//    }
//  }
}
