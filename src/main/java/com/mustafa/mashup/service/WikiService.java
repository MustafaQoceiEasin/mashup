package com.mustafa.mashup.service;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mustafa.mashup.entity.musicbrainz.Artist;
import com.mustafa.mashup.entity.wiki.Relation;
import com.mustafa.mashup.entity.wiki.Wikidata;
import com.mustafa.mashup.entity.wiki.Wikipedia;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Configurable
public class WikiService {

  private final String ARTIST_URL = URLStringService.getArtistURL();
  private final String RELS_JSON_URL = URLStringService.getJsonRelsURL();
  private final String WIKIDATA_URL = URLStringService.getWikidataIdentifierUrl();
  private final String WIKI_SITELINK_URL = URLStringService.getWikidataSitelinksUrl();
  private final String WIKIPEDIA_URL = URLStringService.getWikipediaUrl();
  private RestTemplate restTemplate;
  private ObjectMapper objectMapper;
  private List<Relation> relationsList;
  private Artist artist;
  private Relation relation;
  private String wikiIdentifier;
  private String wikipediaTitle;
  private String wikipediaDescription;
  private Wikidata wikidata;
  private Wikipedia wikipedia;
  private Map<Object, Object> identifierMap;


  public String getWikipediaDescription(final String mbid) throws IOException {
    restTemplate = HttpRequestFactoryService.createRestTemplate();
    //Get wikidata url that has the wikidata identifier for the artist wikipedia page
    artist = restTemplate.getForObject(ARTIST_URL + mbid + RELS_JSON_URL, Artist.class);
    relationsList = artist.getRelations();
    for(int i=0; i< relationsList.size(); i++) {
      if(relationsList.get(i).getUrl().getResource().startsWith(WIKIDATA_URL)) {
        relation = relationsList.get(i);
        //remove the url and keep only the identifier
        wikiIdentifier = relation.getUrl().getResource().replaceAll(WIKIDATA_URL,"");
        LoggerService.writeInfoMsg("GOT WIKI IDENTIFIER : " + wikiIdentifier);
      }
    }

    wikidata = restTemplate.getForObject(WIKI_SITELINK_URL + wikiIdentifier, Wikidata.class);
    identifierMap = wikidata.getData();
    //Map dynamic id as value, get json responsebody
    identifierMap.put(wikidata,wikiIdentifier);

    objectMapper = HttpRequestFactoryService.createObjectMapper();
    try {
      //Convert Map to JSON String
      String jsonString = objectMapper.writeValueAsString(identifierMap);

      //Get only title enwiki & title value
      String jsonTitle = jsonString.substring(jsonString.lastIndexOf("\"enwiki\",\"title\":") + "\"title\":\"".length(), jsonString.length() - 1);

      //remove characters at next comma (after title value)
      String[] separated = jsonTitle.split(",");

      //Remove double-quotes,title
      String title = separated[0].replaceAll("title|\"|:","");
      LoggerService.writeInfoMsg("CONVERTED MAP TO JSON STRING");
      wikipediaTitle = title;
    } catch (JsonGenerationException e) {
      e.printStackTrace();
    }

    //Wikipedia url to get description, mapping json as object
    wikipedia = restTemplate.getForObject(WIKIPEDIA_URL + wikipediaTitle, Wikipedia.class);
    wikipedia.add("",wikipedia);

    //removing html tags and json keys from description
    String[] removeTags =  Jsoup.parse(wikipedia.getData().toString()).text().split("extract= ");
    //removing json closing curlybraces at the end
    String removeCurlyBraces = removeTags[1].replaceAll("\\}|"+wikipedia+"|=","");
    //removing comma and empty spaces at the end of description
    wikipediaDescription = removeCurlyBraces.substring(0, removeCurlyBraces.length() - 2);

    return wikipediaDescription;
  }

}
