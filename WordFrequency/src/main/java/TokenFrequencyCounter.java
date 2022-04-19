import java.util.Map;

public class TokenFrequencyCounter {
    private Map<String, Integer> wordFrequency;

    void addToken(String token) {
        wordFrequency.compute(token, (key, count) -> count != null ? count + 1 : 1);
    }
}
