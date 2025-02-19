import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;

class MongoDBAtlasHandlerTest {

@Test
void thisOneCountMoviesFrom1975() {

    Movie movie1 = new Movie(
            "1",
            "Test Movie 1",
            1975,
            asList("Drama"),
            "Director 1",
            asList("Actor 1"),
            8.0,
            asList("English"),
            120
    );

    Movie movie2 = new Movie(
            "2",
            "Test Movie 2",
            1975,
            asList("Action"),
            "Director 2",
            asList("Actor 2"),
            7.0,
            asList("English"),
            110
    );

    Movie movie3 = new Movie(
            "3",
            "Test Movie 3",
            1976,
            asList("Comedy"),
            "Director 3",
            asList("Actor 3"),
            6.0,
            asList("English"),
            100
    );

    List<Movie> movies = asList(movie1, movie2, movie3);
    MongoDBAtlasHandler handler = new MongoDBAtlasHandler();
    long result = handler.getMovieCount1975(movies);

    assertEquals(2,result, "Should find exactly 2 movies from 1975");
}

@Test
public void shouldFindLongestMovieRuntime() {
    Movie movie1 = new Movie(
            "1",
            "Test Movie 1",
            1975,
            asList("Drama"),
            "Director 1",
            asList("Actor 1"),
            8.0,
            asList("English"),
            120  // 120 minuter
    );
    Movie movie2 = new Movie(
            "2",
            "Test Movie 2",
            1975,
            asList("Comedy"),
            "Director 2",
            asList("Actor 2"),
            7.0,
            asList("English"),
            230  // 230
    );
    Movie movie3 = new Movie(
            "3",
            "Test Movie 3",
            1976,
            asList("Action"),
            "Director 3",
            asList("Actor 3"),
            6.0,
            asList("English"),
            90
    );

    List<Movie> movies = asList(movie1, movie2, movie3);
    MongoDBAtlasHandler handler = new MongoDBAtlasHandler();

    int result = handler.getLongestMovieRuntime(movies);

    assertEquals(230,result, "Should find longest runtime of 180 minutes");

}
@Test
    public void shouldFindActorsInHighestRatedMovie() {
    Movie movie1 = new Movie(
            "1",
            "Movie 1",
            2000,
            null,
            "Director",
            asList("Actor A","Actor B","Actor C","Actor D"),
            9.0,
            null,
            120
    );
    Movie movie2 = new Movie(
            "2",
            "Movie 2",
            2001,
            null,
            "Director",
            asList("Actor A","Actor B"),
            8.0,
            null,
            120
    );

    Movie movie3 = new Movie(
            "3",
            "Movie 3",
            2000,
            null,
            "Director",
            asList("Actor E"),             // Denna skådespelare
            7.5,                                  // Rating 7.5
            null,
            120
    );

    List<Movie> movies = asList(movie1, movie2, movie3);
    MongoDBAtlasHandler handler = new MongoDBAtlasHandler();

    List<String> result = handler.getActorsInHighestRatedMovie(movies);
    assertEquals(asList("Actor A","Actor B","Actor C","Actor D"),result);
}

//@Test
//    public void theMostFrequentActor() {
////
////}



}