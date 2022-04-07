package com.mustafa.mashup.entity.coverart;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Image {

  @JsonProperty("image")
  private String imageURL;

  public Image() {
  }

  public Image(String imageURL) {
    this.imageURL = imageURL;
  }

  public String getImageURL() {
    return imageURL;
  }

  public void setImageURL(String imageURL) {
    this.imageURL = imageURL;
  }


}