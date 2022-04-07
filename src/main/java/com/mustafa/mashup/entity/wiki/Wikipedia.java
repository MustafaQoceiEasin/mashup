package com.mustafa.mashup.entity.wiki;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.HashMap;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Wikipedia {

  @JsonProperty("query")
  private Map<String, Object> data;

  public Wikipedia () {
    data = new HashMap<>();
  }
  public Map<String, Object> getData(){
    return data;
  }

  @JsonAnySetter
  public void add(String property, Object value){
    data.put(property, value);
  }
}
