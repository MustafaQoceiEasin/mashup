# Mashup with different APIs (Musicbrainz, CoverArt, Wikidata, Wikipedia)

### Created in Java with spring boot.
### Link to postman example requests : https://www.postman.com/speeding-meadow-346025/workspace/mashup -- the requests for this api is under "mashup" collection
### API can be used in postman and in browser, but postman creates a prettier, more readable json structure
### Link to github repo : https://github.com/MustafaQoceiEasin/mashup

### Before starting, use command : mvn clean package
### To start, use command : __**mvn spring-boot:run**__ , or start the main class "MashupApplication". 

### First request might take a bit longer because of Nettys connection establishment

# About :

### This REST API will take in an MBID(musicbrainz identifier) , look up an artist on the musicbrainz API and return information about the artist, and a list of released albums. You will also find urls that contain a wiki identifier.

### The MBID of the albums are used on Cover Art Archive API to look up the cover images of the albums.

### The wiki identifier that was fetched from the musicbrainz api is used on the wikidata api to get the title of the english wikipedia page for the artist

### The title is used on the wikipedia api to get a description for the artist

### All this information is combined to return a long json response body with the combined information about the artist


# Frameworks and libraries
### The libary used for making the async API calls is called WebClient
### JDK 8+
