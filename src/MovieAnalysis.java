import java.util.List;

@FunctionalInterface
public interface MovieAnalysis<T, R> {
    R analysera (List<T> data); //lista med saker av typ T
}