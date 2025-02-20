import java.util.List;

// T är typen på datan vi ska analysera Movies
//R är typen på resultatet vi vill ha Long/String

@FunctionalInterface
public interface MovieAnalysis<T, R> {
    R analysera (List<T> data); //lista med saker av typ T
}