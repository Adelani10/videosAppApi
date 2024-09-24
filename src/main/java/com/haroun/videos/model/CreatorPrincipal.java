package com.haroun.videos.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class CreatorPrincipal implements UserDetails {

  @Autowired
  Creator creator;

  public CreatorPrincipal(Creator creator) {
    this.creator = creator;
  }


  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Collections.singleton(new SimpleGrantedAuthority("CREATOR"));
  }

  @Override
  public String getPassword() {
    return creator.getPassword();
  }

  @Override
  public String getUsername() {
    return creator.getEmail();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
