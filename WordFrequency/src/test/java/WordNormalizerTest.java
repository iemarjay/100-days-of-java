import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class WordNormalizerTest {
    @Test
    public void test_makes_words_lowercase() {

        String normalize1 = new WordTokenizer().tokenize("Abds");
        String normalize2 = new WordTokenizer().tokenize("ABSD");
        String normalize3 = new WordTokenizer().tokenize("qwertY");
        String normalize4 = new WordTokenizer().tokenize("qwerty");

        assertThat(normalize1).isEqualTo("abds");
        assertThat(normalize2).isEqualTo("absd");
        assertThat(normalize3).isEqualTo("qwerty");
        assertThat(normalize4).isEqualTo("qwerty");
    }

    @Test
    public void test_removes_special_characters() {
        String word = "EerrJJ3234/4303;'";
        String normalized = new WordTokenizer().tokenize(word);

        assertThat(normalized).isEqualTo("eerrjj32344303");
    }
}
