package com.mustafa.mashup.service;

import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import java.util.concurrent.TimeUnit;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

public class WebClientFactoryService {

  public static WebClient createWebClient () {
    HttpClient httpClient = HttpClient.create()
        .doOnConnected(conn ->
         conn.addHandlerLast(new ReadTimeoutHandler(5000, TimeUnit.MILLISECONDS))
        .addHandlerLast(new WriteTimeoutHandler(5000, TimeUnit.MILLISECONDS)))
        .compress(true)
        .followRedirect(true);

    WebClient.Builder builder = WebClient.builder()
        .clientConnector(new ReactorClientHttpConnector(httpClient));

    WebClient webClient = builder.build();
    return webClient;
  }
}
