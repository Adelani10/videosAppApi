package com.haroun.videos.service;

import com.haroun.videos.model.Creator;
import com.haroun.videos.repo.CreatorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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
}
