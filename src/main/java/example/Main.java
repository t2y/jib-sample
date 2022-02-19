package example;

import com.google.common.io.CharStreams;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;

public class Main {

    public static void main(String[] args) throws URISyntaxException, IOException {
        try (Reader reader = new InputStreamReader(
                Main.class.getResourceAsStream("/world"), StandardCharsets.UTF_8)) {
            String world = CharStreams.toString(reader);
            System.out.println("Hello " + world);
            for (var i = 0; i < args.length; i++) {
                System.out.println(String.format("args[%d]: %s", i, args[i]));
            }
        }
    }
}
