package com.mustafa.mashup.entity.coverart;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CoverArt {

  private List<Image> images;

  public CoverArt() {
  }

  public CoverArt(List<Image> images) {
    this.images = images;
  }

  public List<Image> getImages() {
    return images;
  }

  public void setImages(List<Image> images) {
    this.images = images;
  }

}