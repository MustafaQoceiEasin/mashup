package com.mustafa.mashup.service;

public class URLStringService {
  private static final String ARTIST_URL = "http://musicbrainz.org/ws/2/artist/";
  private static final String JSON_RELS_URL = "?&fmt=json&inc=url-rels";
  private static final String JSON_RELEASEGROUPS_URL = "?&fmt=json&inc=release-groups";
  private static final String JSON_RELEASE_RELS_URL = "?&fmt=json&inc=url-rels+release-groups";
  private static final String COVER_ART_URL = "http://coverartarchive.org/release-group/";
  private static final String WIKIDATA_IDENTIFIER_URL = "https://www.wikidata.org/wiki/";
  private static final String WIKIDATA_SITELINKS_URL = "https://www.wikidata.org/w/api.php?action=wbgetentities&format=json&props=sitelinks&ids=";
  private static final String WIKIPEDIA_URL = "https://en.wikipedia.org/w/api.php?action=query&format=json&prop=extracts&titles=";

  public static String getArtistURL() {
    return ARTIST_URL;
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

  public static String getCoverArtUrl() {
    return COVER_ART_URL;
  }

  public static String getWikidataIdentifierUrl() {
    return WIKIDATA_IDENTIFIER_URL;
  }

  public static String getWikidataSitelinksUrl() {
    return WIKIDATA_SITELINKS_URL;
  }

  public static String getWikipediaUrl() {
    return WIKIPEDIA_URL;
  }


}
