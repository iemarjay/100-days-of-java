public class WordNormalizer {

    public String normalize(String word) {
        String normalized = word.toLowerCase();
        normalized = removeSpecialChars(normalized);

        return normalized;
    }

    private String removeSpecialChars(String word) {
        return word.replaceAll("[^a-zA-Z0-9]", "");
    }
}
