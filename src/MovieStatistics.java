import java.util.List;

public class MovieStatistics {
    //detta är själva hjälp klassen för alla analysfunktioner vi utför med movieanalysis

    public static <T, R> R utförAnalys(List<T> data, MovieAnalysis<T, R> analysFunktion) {
        return analysFunktion.analysera(data);
    }
}