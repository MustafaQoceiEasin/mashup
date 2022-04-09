package com.mustafa.mashup.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;

import com.mustafa.mashup.entity.musicbrainz.Artist;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;

@SpringBootTest
public class WikiServiceIT {
  private final String ARTIST_MBID = "5b11f4ce-a62d-471e-81fc-a69a8278c7da";
  private WebClient webClient;
  private Artist artist;

  @Autowired
  private MashupService mashupService;

  @BeforeEach
  void setup () {
   webClient = WebClientFactoryService.createWebClient();
  }

  @Test
  void shouldFindWikidata() {
    artist = webClient
        .get()
        .uri(URLStringService.getMusicBrainzApiUrl() + ARTIST_MBID + URLStringService.getJsonReleaseRelsUrl())
        .retrieve()
        .bodyToMono(Artist.class)
        .block();

    assertThat(artist).isNotNull();
    assertThat(artist.getRelations()).isNotEmpty();
  }

  @Test
  void shouldFailToFindWikidataIdentifier() {
    String faultyMBID = ARTIST_MBID + "abcde";

    Exception exception = assertThrows(WebClientException.class, () -> {
      artist = webClient
          .get()
          .uri(URLStringService.getMusicBrainzApiUrl() + faultyMBID + URLStringService.getJsonReleaseRelsUrl())
          .retrieve()
          .bodyToMono(Artist.class)
          .block();
    });


     Assertions.assertTrue(exception.getMessage().startsWith("4"));
  }

}


