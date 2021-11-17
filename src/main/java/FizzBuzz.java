import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FizzBuzz {
    public static void main(String[] args) {
        print(100);
    }

    public static void print(int max) {
        String text = IntStream.range(1, max).mapToObj(number -> {
            String echo = "";

            if (number % 5 == 0) {
                echo += "Fizz";
            }
            if (number % 3 == 0) {
                echo += "Buzz";
            }

            if (echo.isEmpty()) {
                echo = String.valueOf(number);
            }

            return echo;
        }).collect(Collectors.joining("\n"));

        System.out.println(text);
    }
}
