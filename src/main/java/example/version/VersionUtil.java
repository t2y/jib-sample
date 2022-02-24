package example.version;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;
import java.util.jar.Manifest;
import java.util.stream.Collectors;

public class VersionUtil {
    private static final String MANIFEST_PATH = "META-INF/MANIFEST.MF";

    public static Map<String, Manifest> getManifests() throws IOException, URISyntaxException {
        var map = new HashMap<String, Manifest>();
        var resources = VersionUtil.class.getClassLoader().getResources(MANIFEST_PATH);
        while (resources.hasMoreElements()) {
            var elem = resources.nextElement();
            var part = elem.toURI().getSchemeSpecificPart();
            if (part != null) {
                try (var stream = elem.openStream()) {
                    map.put(part, new Manifest(stream));
                }
            }
        }
        return map;
    }

    public static List<AppManifest> getAppManifests() throws IOException, URISyntaxException {
        var results = new ArrayList<AppManifest>();
        var map = getManifests();
        for (var entry : map.entrySet()) {
            var manifest = entry.getValue();
            var mainAttr = manifest.getMainAttributes();
            if (mainAttr == null) {
                continue;
            }
            var artifactId = mainAttr.getValue(ManifestAttributeName.ARTIFACT_ID.getKey());
            var opt = ArtifactId.fromString(artifactId);
            if (opt.isEmpty()) {
                continue;
            }
            results.add(new AppManifest(opt.get(), manifest));
        }
        return results;
    }

    public static Optional<AppManifest> getAppManifest(ArtifactId artifactId) throws IOException, URISyntaxException {
        var appManifests = getAppManifests();
        var filtered = appManifests.stream()
                .filter(manifest -> manifest.getAppName() == artifactId)
                .collect(Collectors.toList());
        if (filtered.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(filtered.get(0));
    }
}