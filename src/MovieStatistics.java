import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class MovieStatistics {
    //detta är själva hjälp klassen för alla analysfunktioner vi utför med movieanalysis

    public static <T, R> R utförAnalys(List<T> data, MovieAnalysis<T, R> analysFunktion) {
        return analysFunktion.analysera(data);  // tar en annan funktion som parameter
    }

    public static final MovieAnalysis<Movie, Optional<Movie>> findTheLongestMovie =
            movies -> movies.stream()
                    .max(Comparator.comparingInt(Movie::getRuntime));

    public static final MovieAnalysis<Movie, Optional<String>> findTheMostPopularActor =
            movies -> movies.stream().flatMap(movie -> movie.getCast().stream()).collect(Collectors.groupingBy(
                    actor -> actor,Collectors.counting())).entrySet().stream().max(Map.Entry.comparingByValue())
                    .map(Map.Entry::getKey);


}

