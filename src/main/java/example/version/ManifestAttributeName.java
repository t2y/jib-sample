package example.version;

public enum ManifestAttributeName {

    ARTIFACT_ID("Artifact-Id") {
        @Override
        public String getJsonKey() {
            return "artifactId";
        }
    },

    REVISION("Revision") {
        @Override
        public String getJsonKey() {
            return "revision";
        }
    };

    private final String key;

    ManifestAttributeName(String key) {
        this.key = key;
    }

    public String getKey() {
        return this.key;
    }

    public abstract String getJsonKey();
}
