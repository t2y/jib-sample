package example.version;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.tuple.Pair;

import java.util.*;
import java.util.jar.Attributes;
import java.util.jar.Manifest;
import java.util.stream.Collectors;

public class AppManifest {

    private final ArtifactId artifactId;
    private final Manifest manifest;

    public AppManifest(ArtifactId artifactId, Manifest manifest) {
        this.artifactId = artifactId;
        this.manifest = manifest;
    }

    public AppManifest() {
        this(null, null);
    }

    @JsonIgnore
    public ArtifactId getAppName() {
        return this.artifactId;
    }

    @JsonIgnore
    public Manifest getManifest() {
        return this.manifest;
    }

    void showMainAttributes() {
        for (var entry : this.manifest.getMainAttributes().entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    private Pair<String, String> getNameValue(Attributes attr, ManifestAttributeName name) {
        return Pair.of(name.getJsonKey(), Optional.ofNullable(attr.getValue(name.getKey())).orElse("-"));
    }

    private List<Pair<String, String>> getNameValues(Attributes attr) {
        return Arrays.stream(ManifestAttributeName.values())
                .map(name -> this.getNameValue(attr, name))
                .collect(Collectors.toList());
    }

    @JsonAnyGetter
    public Map<String, String> getMetadata() {
        var map = new HashMap<String, String>();
        if (this.manifest == null) {
            return map;
        }
        var attr = this.manifest.getMainAttributes();
        for (var nameValue : this.getNameValues(attr)) {
            map.put(nameValue.getKey(), nameValue.getValue());
        }
        return map;
    }
}
