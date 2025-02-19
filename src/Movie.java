import org.bson.Document;

import java.util.List;

public class Movie {
    private String id;
    private String title;
    private int year;
    private int runtime;
    private List<String> genres;
    private String director;
    private List<String> cast;
    private double imdbRating;
    private List<String> languages;


    public Movie(String id, String title, int year, List<String> genres, String director, List<String> cast, double imdbRating, List<String> languages, int runtime) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.runtime = runtime;
        this.genres = genres;
        this.director = director;
        this.cast = cast;
        this.imdbRating = imdbRating;
        this.languages = languages;
    }


    public static Movie fromDocument(Document doc) {
        return new Movie(
                doc.getObjectId("_id").toString(),
                doc.getString("title"),
                doc.getInteger("year", 0),
                doc.getList("genres", String.class),
                doc.getString("director"),
                doc.getList("cast", String.class),
                doc.get("imdb", Document.class) != null ? doc.get("imdb", Document.class).getDouble("rating") : 0.0,
                doc.getList("languages", String.class),
                doc.getInteger("runtime", 0)
        );
    }


    public String getId() { return id; }
    public String getTitle() { return title; }
    public int getYear() { return year; }
    public int getRuntime() { return runtime; }
    public List<String> getGenres() { return genres; }
    public String getDirector() { return director; }
    public List<String> getCast() { return cast; }
    public double getImdbRating() { return imdbRating; }
    public List<String> getLanguages() { return languages; }

    @Override
    public String toString() {
        return "Movie{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", year=" + year +
                ", runtime=" + runtime +
                ", genres=" + genres +
                ", director='" + director + '\'' +
                ", cast=" + cast +
                ", imdbRating=" + imdbRating +
                ", languages=" + languages +
                '}';
    }
}
