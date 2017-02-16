package org.hibernate.lucene.module;

import org.apache.lucene.util.Version;

public final class LuceneVersion {

    private final String implVersion;

    public LuceneVersion() {
        this.implVersion = implVersion();
    }

    public String getImplVersion() {
        return implVersion;
    }

    private String implVersion() {
        return Version.class.getPackage().getImplementationVersion();
    }

}
