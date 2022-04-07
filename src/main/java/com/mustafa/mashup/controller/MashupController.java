package com.mustafa.mashup.controller;

import com.mustafa.mashup.entity.musicbrainz.Artist;
import com.mustafa.mashup.service.MashupService;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mashup/artist")
public class MashupController {

  @Autowired
  private MashupService mashupService;

  @GetMapping(path = "/{id}", produces = "application/json")
  public Artist fetchArtist(
      //id is Artist MBID
      @PathVariable String id,
      //fmt is json format, default response is xml
      @RequestParam(defaultValue = "json") String fmt,
      //inc is include release-groups, aka Albums for artist
      @RequestParam(defaultValue = "release-groups") String inc) throws IOException {



    return mashupService.fetchArtistInformation(id);
  }

}
