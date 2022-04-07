package com.mustafa.mashup.entity.musicbrainz;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mustafa.mashup.entity.coverart.CoverArt;
import com.mustafa.mashup.entity.coverart.Image;
import java.util.List;
import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Album {

  private UUID id;
  private String title;
  @JsonProperty("first-release-date")
  private String firstReleaseDate;
  private List<Image> images;

  public Album() {
  }

  public Album(UUID id, String title, String firstReleaseDate, List<Image> images) {
    this.id = id;
    this.title = title;
    this.firstReleaseDate = firstReleaseDate;
    this.images = images;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getFirstReleaseDate() {
    return firstReleaseDate;
  }

  public void setFirstReleaseDate(String firstReleaseDate) {
    this.firstReleaseDate = firstReleaseDate;
  }

  public List<Image> getImages() {
    return images;
  }

  public void setImages(List<Image> images) {
    this.images = images;
  }
}

