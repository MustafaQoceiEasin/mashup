package com.mustafa.mashup.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;

import com.mustafa.mashup.entity.coverart.CoverArt;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;

@SpringBootTest
public class CoverArtServiceIT {

  private final String ALBUM_MBID = "1b022e01-4da6-387b-8658-8678046e4cef";
  private WebClient webClient;
  private CoverArt coverArt;

  @BeforeEach
  void setup() {
    webClient = WebClientFactoryService.createWebClient();
  }

  @Test
  void shouldFindAlbumWithMBID() {
      coverArt = webClient
          .get()
          .uri(URLStringService.getCoverartApiUrl() + ALBUM_MBID)
          .retrieve()
          .bodyToMono(CoverArt.class)
          .block();

    assertThat(coverArt).isNotNull();
    assertThat(coverArt.getImages()).isNotEmpty();
  }

  @Test
  void shouldFailAtFindingAlbumWithMBID() {
    String faultyMBID = ALBUM_MBID + "abcde";

    Exception exception = assertThrows(WebClientException.class, () -> {
      coverArt = webClient
          .get()
          .uri(URLStringService.getCoverartApiUrl() + faultyMBID)
          .retrieve()
          .bodyToMono(CoverArt.class)
          .block();
    });

   Assertions.assertTrue(exception.getMessage().startsWith("4"));
  }

}
