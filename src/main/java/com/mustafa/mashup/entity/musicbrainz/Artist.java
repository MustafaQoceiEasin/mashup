package com.mustafa.mashup.entity.musicbrainz;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mustafa.mashup.entity.wiki.Relation;
import java.util.List;
import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Artist {

  private UUID id;
  private String name;
  private String country;
  private String disambiguation;
  @JsonProperty("description")
  private String extracts;
  @JsonProperty("release-groups")
  private List<Album> albums;
  @JsonProperty("relations")
  private List<Relation> relations;

  public Artist() {

  }

  public Artist(UUID id, String name, String country, String disambiguation,
      String extract, List<Album> albums,  List<Relation> relations) {
    this.id = id;
    this.name = name;
    this.country = country;
    this.disambiguation = disambiguation;
    this.albums = albums;
    this.relations = relations;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getDisambiguation() {
    return disambiguation;
  }

  public void setDisambiguation(String disambiguation) {
    this.disambiguation = disambiguation;
  }

  public String getExtracts() {
    return extracts;
  }

  public void setExtracts(String extracts) {
    this.extracts = extracts;
  }

    public List<Album> getAlbums() {
    return albums;
  }

  public void setAlbums(
      List<Album> albums) {
    this.albums = albums;
  }

  public List<Relation> getRelations() {
    return relations;
  }

  public void setRelations(List<Relation> relations) {
    this.relations = relations;
  }

}
