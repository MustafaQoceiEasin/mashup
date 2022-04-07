package com.mustafa.mashup.service;

import com.mustafa.mashup.entity.coverart.CoverArt;
import com.mustafa.mashup.entity.coverart.Image;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

@Service
@Configurable
public class CoverArtService {

  private final String COVER_ART_URL = URLStringService.getCoverArtUrl();
  private RestTemplate restTemplate;
  private CoverArt coverArt;

  @Autowired
  private WebClient.Builder webClientBuilder;

  public List<Image> fetchCoverArtImageUrl(final String mbid) {

   restTemplate = HttpRequestFactoryService.createRestTemplate();
    try {
      //Get images for album mbid
      coverArt = restTemplate.getForObject(COVER_ART_URL + mbid, CoverArt.class);
    } //Try catch block to catch exceptions when response is 4xx
    catch (final HttpClientErrorException e) {
      for(int i=0; i< coverArt.getImages().size(); i++){
        //if image is not found, set image on album to null
        if(e.getStatusCode().is4xxClientError()) {
          coverArt.getImages().get(i).setImageURL(null);
          LoggerService.writeWarnMsg("Image url was not found, set ImageUrl to null");
        }
      }
    }
    return coverArt.getImages();
  }

}