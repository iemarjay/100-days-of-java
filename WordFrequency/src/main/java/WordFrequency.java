import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.stream.Stream;

public class WordFrequency {
    private Map<String, Integer> wordFrequency;
    private final WordTokenizer tokenizer;
    private final TokenFrequencyCounter tokenFrequencyCounter;
    private final StringToWordSplitter splitter;
    private static final String resourcePath = "../resources/wmf.txt";

    static void main(String[] args) throws IOException {
        init().generateWordFrequency(defaultReader().lines());
    }

    WordFrequency(WordTokenizer tokenizer, TokenFrequencyCounter tokenFrequencyCounter, StringToWordSplitter splitter) {
        this.tokenizer = tokenizer;
        this.tokenFrequencyCounter = tokenFrequencyCounter;
        this.splitter = splitter;
    }

    private void generateWordFrequency(Stream<String> lines) {
        lines.forEach(line -> {
            Stream<String> stream = splitter.splitStringToWord(line);
            stream.forEach(word -> {
                String token = tokenizer.tokenize(word);
                tokenFrequencyCounter.addToken(token);
            });
        });
    }

    private static BufferedReader defaultReader() throws IOException {
        return new BufferedReader(new FileReader(resourcePath, StandardCharsets.UTF_8));
    }

    static WordFrequency init() throws IOException {
        return new WordFrequency(new WordTokenizer(), new TokenFrequencyCounter(), new StringToWordSplitter());
    }
}
