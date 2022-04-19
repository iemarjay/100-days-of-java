import java.util.Arrays;
import java.util.stream.Stream;

public class StringTokenizer {
    Stream<String> tokenize(String line) {
        return Arrays.stream(line.split("\\s+"));
    }
}
