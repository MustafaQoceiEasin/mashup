# Mashup with different APIs (Musicbrainz, CoverArt, Wikidata, Wikipedia)

### Created in Java with spring boot.
### Link to postman example requests : https://www.postman.com/speeding-meadow-346025/workspace/mashup -- the requests for this api is under "mashup" collection

### Link to github repo : https://github.com/MustafaQoceiEasin/mashup

### To start, use command : __**mvn spring-boot:run**__

#About :

### This REST API will take in an MBID(musicbrainz identifier) , look up an artist on the musicbrainz API and return information about the artist, and a list of released albums. You will also find urls that contain a wiki identifier.

### The MBID of the albums are used on Cover Art Archive API to look up the cover images of the albums.

### The wiki identifier that was fetched from the musicbrainz api is used on the wikidata api to get the title of the english wikipedia page for the artist

### The title is used on the wikipedia api to get a description for the artist

### All this information is combined to return a long json response body with the combined information about the artist


#Frameworks and libraries
### The libary used for making the api calls is the built in library in spring-boot called RestTemplate.
#Negative
###RestTemplate makes synchronous calls, which made this REST API very slow when it's used on cover arts API, since cover arts API had redirected links.
###Each request sent will take about 50 seconds for a response
