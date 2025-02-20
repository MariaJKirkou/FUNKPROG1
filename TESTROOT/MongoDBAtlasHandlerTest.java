import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;

class MongoDBAtlasHandlerTest {
    //testdata som kan återanvändas i alla tester
    List<Movie> testList = Arrays.asList(
            new Movie("M001", "Movie One", 1975, List.of("Drama"), "Director One",
                    List.of("Actor One", "Actor Two"), 9.3, List.of("English"), 230),
            new Movie("M002", "Movie Two", 1975, List.of("Action"), "Director Two",
                    List.of("Actor Three"), 8.5, List.of("English", "Swedish"), 180),
            new Movie("M003", "Movie Three", 1976, List.of("Comedy"), "Director Three",
                    List.of("Actor One"), 7.8, List.of("English"), 150)
    );
    MongoDBAtlasHandler mongoDBAtlasHandler = new MongoDBAtlasHandler();
    List<Movie> blancList = Collections.emptyList();

    @Test
    void getMovieCount1975() {
        long count = mongoDBAtlasHandler.getMovieCount1975(testList);
        assertEquals(2, count);
        assertNotEquals(3,count);
        System.out.println("Number of movies from 1975: " + count);
    }
    @Test
    void getLongestMovieRuntime() {
        int longestMovieRuntime = mongoDBAtlasHandler.getLongestMovieRuntime(testList);
        assertEquals(230, longestMovieRuntime);
        assertNotEquals(150,longestMovieRuntime);
        System.out.println("Longest movie runtime: " + longestMovieRuntime + " minutes");
    }

}