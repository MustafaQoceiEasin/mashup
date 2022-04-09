package com.mustafa.mashup.service;

import com.mustafa.mashup.entity.coverart.CoverArt;
import com.mustafa.mashup.entity.coverart.Image;
import java.util.List;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;

@Service
@Configurable
public class CoverArtService {

  private final String COVERART_API_URL = URLStringService.getCoverartApiUrl();
  private CoverArt coverArt;
  private WebClient webClient;

  public List<Image> fetchCoverArtImageUrl(final String mbid) {
    webClient = WebClientFactoryService.createWebClient();
    try {
      //Send an async API request to the CoverArt API with the album MBID
      coverArt =  webClient
          .get()
          .uri(COVERART_API_URL + mbid)
          .retrieve()
          .bodyToMono(CoverArt.class)
          .block();
      //Get images for album mbid
    } //Try catch block to catch exceptions when response is 4xx
    catch (final WebClientException e) {
      for(int i=0; i< coverArt.getImages().size(); i++){
        //if image is not found, set image on album to null
        if(e.getMessage().startsWith("4")) {
          coverArt.getImages().get(i).setImageURL(null);
          LoggerService.writeWarnMsg("Image url was not found, set ImageUrl to null");
        }
      }
    }
    return coverArt.getImages();
  }

}