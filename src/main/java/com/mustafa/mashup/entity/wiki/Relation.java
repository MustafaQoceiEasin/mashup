package com.mustafa.mashup.entity.wiki;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Relation {

  private Url url;

  public Relation() {

  }

  public Relation(Url url) {
    this.url = url;
  }

  public Url getUrl() {
    return url;
  }

  public void setUrl(Url url) {
    this.url = url;
  }

}
