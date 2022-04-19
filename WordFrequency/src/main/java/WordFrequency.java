import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordFrequency {
    private final WordNormalizer normalizer;
    private final StringTokenizer tokenizer;
    private static final String resourcePath = "../resources/wmf.txt";

    static void main(String[] args) throws IOException {
        Map<String, Long> wordFrequency = init().generateWordFrequency(defaultReader().lines());
    }

    WordFrequency(WordNormalizer normalizer, StringTokenizer tokenizer) {
        this.normalizer = normalizer;
        this.tokenizer = tokenizer;
    }

    private Map<String, Long> generateWordFrequency(Stream<String> lines) {
        return lines.flatMap(tokenizer::tokenize)
                .map(normalizer::normalize)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    private static BufferedReader defaultReader() throws IOException {
        return new BufferedReader(new FileReader(resourcePath, StandardCharsets.UTF_8));
    }

    static WordFrequency init() throws IOException {
        return new WordFrequency(new WordNormalizer(), new StringTokenizer());
    }
}
