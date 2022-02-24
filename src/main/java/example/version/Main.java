package example.version;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URISyntaxException;

public class Main {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) throws IOException, URISyntaxException {
        var appManifests = VersionUtil.getAppManifests();
        System.out.println(mapper.writeValueAsString(appManifests));
    }
}
