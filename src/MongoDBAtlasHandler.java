import com.mongodb.client.*;
import org.bson.Document;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MongoDBAtlasHandler {

    public MongoDBAtlasHandler() {
        String uri = "mongodb+srv://mariajamalaki:MongoDBpass123@cluster0.bjvsb.mongodb.net/sample_mflix?retryWrites=true&w=majority&appName=Cluster0&authSource=admin";

        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("sample_mflix");
            MongoCollection<Document> moviesCollection = database.getCollection("movies");

            List<Movie> movieList = new ArrayList<>();
            for (Document doc : moviesCollection.find(new Document("year", 1975))) {
                movieList.add(Movie.fromDocument(doc));
            }

            //analysfunktionerna
            Optional<Movie> longestMovieRuntime = MovieStatistics.doTheAnalys(movieList, MovieStatistics.findLongestMovieRuntime);
            System.out.println("Longest movie runtime: " + longestMovieRuntime.map(Movie::getTitle).orElse("No movie founded"));

            Optional<String> popularActor = MovieStatistics.doTheAnalys(movieList, MovieStatistics.findTheMostPopularActor);
            System.out.println("Most popular actor: " + popularActor.orElse("No one founded"));

            Long countedUniqueLanguages = MovieStatistics.doTheAnalys(movieList, MovieStatistics.countUniqueLanguages);
            System.out.println("Antal unika språk: " + countedUniqueLanguages);



            // Skriver ut alla filmer
//            for (Movie movie : movieList) {
//                System.out.println(movie);
//
//            }
            System.out.println("Number of movies from 1975: " + getMovieCount1975(movieList));
            System.out.println("Longest movie runtime: " + getLongestMovieRuntime(movieList));
            System.out.println("Actors in highest-rated movie: " + getActorsInHighestRatedMovie(movieList));
            System.out.println("Actor who appeared in most movies: " + getMostFrequentActor(movieList));
            System.out.println("Number of unique languages in movies: " + countUniqueLanguages(movieList));
            System.out.println("Are there duplicate movie titles? " + hasDuplicateTitles(movieList));
            System.out.println("Movie with the fewest actors: " + getMovieWithLeastActors(movieList));
            System.out.println("Number of unique genres: " + getUniqueGenresCount(movieList));



        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public long getMovieCount1975(List<Movie> movieList) {
        return movieList.stream()
                .filter(movie -> movie.getYear() == 1975)
                .count();
    }

    public int getLongestMovieRuntime(List<Movie> movieList) {
        return movieList.stream()
                .mapToInt(Movie::getRuntime)
                .max()
                .orElse(0);
    }
    public List<String> getActorsInHighestRatedMovie(List<Movie> movieList) {
        return movieList.stream()
                .max(Comparator.comparingDouble(Movie::getImdbRating))  // hitta filmen med högst rating
                .map(Movie::getCast)                                    // Får ut cast-listan från den filmen
                .orElse(new ArrayList<>());
    }

    private long countUniqueLanguages(List<Movie> movieList) {
        return movieList.stream()
                .flatMap(movie -> movie.getLanguages().stream())
                .distinct()
                .count();
    }
    private boolean hasDuplicateTitles(List<Movie> movieList) {
        return movieList.stream()
                .map(Movie::getTitle)
                .anyMatch(title -> movieList.stream()
                        .map(Movie::getTitle)
                        .filter(t -> t.equals(title))
                        .count() > 1);
    }
    private String getMovieWithLeastActors(List<Movie> movieList) {
        return movieList.stream()
                .min(Comparator.comparingInt(movie -> movie.getCast().size()))  // Hitta filmen med minst antal i cast
                .map(Movie::getTitle)                                           // Få ut titeln från den filmen
                .orElse("");
    }
    private long getUniqueGenresCount(List<Movie> movieList) {
        return movieList.stream()
                .flatMap(movie -> movie.getGenres().stream())
                .distinct()
                .count();
    }
    public String getMostFrequentActor(List<Movie> movieList) {
        return movieList.stream()
                .flatMap(movie -> movie.getCast().stream())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting())) // Gruppera och räkna
                .entrySet().stream() //mest förekommande skådespelaren.
                .max(Map.Entry.comparingByValue())//entry med högst värde
                .map(Map.Entry::getKey)//namnet på skådespelaren fr entry i map som har högst värde
                .orElse("");
    }

}
