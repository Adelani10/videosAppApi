package com.haroun.videos.service;

import com.haroun.videos.model.Creator;
import com.haroun.videos.model.CreatorPrincipal;
import com.haroun.videos.model.Video;
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

  public String verify(Creator creator) {
    Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(creator.getEmail(), creator.getPassword()));
    if (authentication.isAuthenticated()) {
      return jwtService.generateToken(creator.getEmail());
    } else {
      return "fail";
    }
  }

  public String register(Creator creator) {
    creator.setPassword(encoder.encode(creator.getPassword()));
    creatorsRepository.save(creator);
    return verify(creator);
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

  public void removeBookmarks(Video video) {
    Creator creator = getCurrentCreator();

    if (creator == null) {
      throw new RuntimeException("No creator signed in yet");
    }

    try {
      // Check if bookmarks exist and contains the given video
      if (!creator.getBookmarks().isEmpty() && creator.getBookmarks().contains(video)) {
        creator.getBookmarks().remove(video);
        creatorsRepository.save(creator); // Persist changes
      } else {
        throw new RuntimeException("Video not found in bookmarks");
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public void addBookmarks(Video video) {
    Creator creator = getCurrentCreator();

    if (creator == null) {
      throw new RuntimeException("No creator signed in yet");
    }

    try {
      // Check if bookmarks exist and doesn't already contain the given video
      if (!creator.getBookmarks().contains(video)) {
        creator.getBookmarks().add(video);
        creatorsRepository.save(creator); // Persist changes
      } else {
        throw new RuntimeException("Video already contained in bookmark array");
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
