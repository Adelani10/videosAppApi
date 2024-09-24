package com.haroun.videos.service;

import com.haroun.videos.model.Creator;
import com.haroun.videos.model.CreatorPrincipal;
import com.haroun.videos.repo.CreatorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
  @Autowired
  CreatorsRepository creatorsRepo;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

    Creator creator = creatorsRepo.findByEmail(email);
    if (creator == null) {
      throw new UsernameNotFoundException("Creator not found");
    }

    return new CreatorPrincipal(creator);
  }
}
