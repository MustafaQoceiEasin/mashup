package com.mustafa.mashup.service;

public class URLStringService {
  private static final String MUSICBRAINZ_API_URL = "http://musicbrainz.org/ws/2/artist/";
  private static final String JSON_RELS_URL = "?&fmt=json&inc=url-rels";
  private static final String JSON_RELEASEGROUPS_URL = "?&fmt=json&inc=release-groups";
  private static final String JSON_RELEASE_RELS_URL = "?&fmt=json&inc=url-rels+release-groups";
  private static final String COVERART_API_URL = "http://coverartarchive.org/release-group/";
  private static final String WIKIDATA_API_URL = "https://www.wikidata.org/wiki/";
  private static final String WIKIDATA_SITELINKS_URL = "https://www.wikidata.org/w/api.php?action=wbgetentities&format=json&props=sitelinks&ids=";
  private static final String WIKIPEIDA_TITLE_URL = "https://en.wikipedia.org/w/api.php?action=query&format=json&prop=extracts&titles=";

  public static String getMusicBrainzApiUrl() {
    return MUSICBRAINZ_API_URL;
  }

  public static String getJsonRelsURL() {
    return JSON_RELS_URL;
  }

  public static String getJsonReleaseGroupsURL() {
    return JSON_RELEASEGROUPS_URL;
  }

  public static String getJsonReleaseRelsUrl () {
    return JSON_RELEASE_RELS_URL;
  }

  public static String getCoverartApiUrl() {
    return COVERART_API_URL;
  }

  public static String getWikidataApiUrl() {
    return WIKIDATA_API_URL;
  }

  public static String getWikidataSitelinksUrl() {
    return WIKIDATA_SITELINKS_URL;
  }

  public static String getWikipeidaTitleUrl() {
    return WIKIPEIDA_TITLE_URL;
  }


}
