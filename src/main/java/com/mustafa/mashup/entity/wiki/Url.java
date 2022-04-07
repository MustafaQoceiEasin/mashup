package com.mustafa.mashup.entity.wiki;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Url {

  private UUID id;
  private String resource;

  public Url() {
  }

  public Url(UUID id, String resource) {
    this.id = id;
    this.resource = resource;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getResource() {
    return resource;
  }

  public void setResource(String resource) {
    this.resource = resource;
  }}
