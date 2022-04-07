package com.mustafa.mashup.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;

import com.mustafa.mashup.entity.coverart.CoverArt;
import com.mustafa.mashup.entity.musicbrainz.Artist;
import com.mustafa.mashup.entity.wiki.Wikidata;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
public class WikiServiceIT {
  private final String ARTIST_MBID = "5b11f4ce-a62d-471e-81fc-a69a8278c7da";
  private RestTemplate restTemplate;
  private Artist artist;

  @Autowired
  private MashupService mashupService;

  @BeforeEach
  void setup () {
    restTemplate = HttpRequestFactoryService.createRestTemplate();
  }

  @Test
  void shouldFindWikidata() {
    artist = restTemplate.getForObject(URLStringService.getArtistURL() + ARTIST_MBID + URLStringService.getJsonReleaseRelsUrl() ,Artist.class);

    assertThat(artist).isNotNull();
    assertThat(artist.getRelations()).isNotEmpty();
  }

  @Test
  void shouldFailToFindWikidataIdentifier() {
    String faultyMBID = ARTIST_MBID + "abcde";

    Exception exception = assertThrows(HttpClientErrorException.class, () -> {
      artist = restTemplate.getForObject(URLStringService.getArtistURL() + faultyMBID + URLStringService.getJsonReleaseRelsUrl() ,Artist.class);
    });


     Assertions.assertTrue(exception.getMessage().startsWith("400"));
  }

}


