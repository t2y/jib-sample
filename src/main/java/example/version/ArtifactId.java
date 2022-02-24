package example.version;

import java.util.Optional;

public enum ArtifactId {
    JIB_SAMPLE("jib-sample");

    private final String value;

    ArtifactId(String value) {
        this.value = value;
    }

    public String get() {
        return this.value;
    }

    public static Optional<ArtifactId> fromString(String value) {
        for (var appName : ArtifactId.values()) {
            if (appName.get().equals(value)) {
                return Optional.of(appName);
            }
        }
        return Optional.empty();
    }
}
