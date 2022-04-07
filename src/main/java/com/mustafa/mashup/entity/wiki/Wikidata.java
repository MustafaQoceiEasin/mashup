package com.mustafa.mashup.entity.wiki;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.HashMap;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Wikidata {

  @JsonProperty("entities")
  private Map<Object, Object> data;

  public Wikidata () {
    data = new HashMap<>();
  }
  public Map<Object, Object> getData(){
    return data;
  }

  @JsonAnySetter
  public void add(Object property, Object value){
    data.put(property, value);
  }
}
