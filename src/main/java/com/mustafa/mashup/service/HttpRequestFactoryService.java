package com.mustafa.mashup.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.List;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultRedirectStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.HttpContext;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class HttpRequestFactoryService {

  private static RestTemplate restTemplate;
  private static RestTemplateBuilder restTemplateBuilder;

  private static HttpComponentsClientHttpRequestFactory getHttpFactory() {
    CloseableHttpClient httpClient = HttpClientBuilder
        .create()
        .setRedirectStrategy( new DefaultRedirectStrategy() {
          public boolean isRedirected(HttpRequest request, HttpResponse response,
              HttpContext context) throws ProtocolException {

            System.out.println(response);

            // If redirect intercept intermediate response.
            if (super.isRedirected(request, response, context)){
              int statusCode  = response.getStatusLine().getStatusCode();
              String redirectURL = response.getFirstHeader("Location").getValue();
              System.out.println("redirectURL: " + redirectURL);
              return true;
            }
            return false;
          }
        })
        .build();
    HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient);

    return factory;
  }

  private static List<HttpMessageConverter<?>> getHttpMessageConverter() {
    MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
    mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
    HttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
    List<HttpMessageConverter<?>> httpMessageConverter = Lists.newArrayList();
    httpMessageConverter.add(mappingJackson2HttpMessageConverter);
    httpMessageConverter.add(stringHttpMessageConverter);

    return httpMessageConverter;
  }

  public static ObjectMapper createObjectMapper() {
    ObjectMapper objectMapper = new ObjectMapper();

    return objectMapper;
  }

  public static RestTemplate createRestTemplate () {
    restTemplateBuilder = new RestTemplateBuilder();
    restTemplate = restTemplateBuilder.build();
    restTemplate.setRequestFactory(getHttpFactory());
    restTemplate.setMessageConverters(getHttpMessageConverter());

    return restTemplate;
  }

}
