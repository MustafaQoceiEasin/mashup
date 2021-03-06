package com.mustafa.mashup.service;

import com.mustafa.mashup.entity.coverart.Image;
import com.mustafa.mashup.entity.musicbrainz.Album;
import com.mustafa.mashup.entity.musicbrainz.Artist;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@Configurable
public class MashupService {

  private final String MUSICBRAINZ_API_URL = URLStringService.getMusicBrainzApiUrl();
  private final String JSON_RELEASEGROUPS_URL = URLStringService.getJsonReleaseGroupsURL();
  private WebClient webClient;
  private Artist artist;
  private Album album;
  private List<Album> oldAlbumList;
  private List<Album> newAlbumList;
  private List<Image> images;

  @Autowired
  private CoverArtService coverArtService;

  @Autowired
  private WikiService wikidataService;

  public Artist fetchArtistInformation(final String mbid) {
    webClient = WebClientFactoryService.createWebClient();
    try {
      artist = webClient
          .get()
          .uri(MUSICBRAINZ_API_URL + mbid + JSON_RELEASEGROUPS_URL)
          .retrieve()
          .bodyToMono(Artist.class)
          .block();
    }catch (HttpClientErrorException | NullPointerException e) {
      LoggerService.writeErrorMsg("COULD NOT FIND ARTIST WITH MBID : " + mbid);
    }
    oldAlbumList = artist.getAlbums();
    newAlbumList = new ArrayList<>();
      //Iterate through albums without an image, create new list for albums with an image link
      for (int i = 0; i < oldAlbumList.size(); i++) {
        album = oldAlbumList.get(i);
        images = coverArtService.fetchCoverArtImageUrl(album.getId().toString());
        album.setImages(images);
        newAlbumList.add(album);
      }
    artist.setAlbums(newAlbumList);
    try {
      artist.setExtracts(wikidataService.getWikipediaDescription(mbid));
      LoggerService.writeInfoMsg("GOT DESCRIPTION FOR ARTIST");
    } catch (IOException e) {
      e.printStackTrace();
      LoggerService.writeErrorMsg("FAILED TO GET DESCRIPTION FOR ARTIST");
    }
    return artist;
  }
}
