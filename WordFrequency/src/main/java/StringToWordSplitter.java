import java.util.Arrays;
import java.util.stream.Stream;

public class StringToWordSplitter {

    Stream<String> splitStringToWord(String line) {
        return Arrays.stream(line.split("\\s+"));
    }
}
