package com.mustafa.mashup.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;

import com.mustafa.mashup.entity.coverart.CoverArt;
import com.mustafa.mashup.entity.musicbrainz.Artist;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
public class CoverArtServiceIT {

  private final String ALBUM_MBID = "1b022e01-4da6-387b-8658-8678046e4cef";
  private RestTemplate restTemplate;
  private CoverArt coverArt;

  @BeforeEach
  void setup() {
    restTemplate = HttpRequestFactoryService.createRestTemplate();
  }

  @Test
  void shouldFindAlbumWithMBID() {
    coverArt = restTemplate.getForObject(URLStringService.getCoverArtUrl()+ ALBUM_MBID , CoverArt.class);

    assertThat(coverArt).isNotNull();
    assertThat(coverArt.getImages()).isNotEmpty();
  }

  @Test
  void shouldFailAtFindingAlbumWithMBID() {
    String faultyMBID = ALBUM_MBID + "abcde";

    Exception exception = assertThrows(HttpClientErrorException.class, () -> {
      coverArt = restTemplate.getForObject(URLStringService.getCoverArtUrl()+ faultyMBID , CoverArt.class);
    });

   Assertions.assertTrue(exception.getMessage().startsWith("400 BAD REQUEST"));
  }

}
