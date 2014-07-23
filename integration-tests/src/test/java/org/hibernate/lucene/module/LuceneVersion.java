package org.hibernate.lucene.module;

import org.apache.lucene.util.Version;

import java.util.ArrayList;
import java.util.List;

public final class LuceneVersion {

    private final String implVersion;
    private final Version compatVersion;

    public LuceneVersion() {
        this.implVersion = implVersion();
        this.compatVersion = version();
    }

    public Version getCompatVersion() {
        return compatVersion;
    }

    public String getImplVersion() {
        return implVersion;
    }

    private Version version() {
        return nonDeprecatedConstants(Version.class).iterator().next();
    }

    private String implVersion() {
        return Version.class.getPackage().getImplementationVersion();
    }

    private <E extends Enum<E>> List<E> nonDeprecatedConstants(Class<E> enumType) {
        List<E> results = new ArrayList<>();
        for (java.lang.reflect.Field f : enumType.getDeclaredFields()) {
            if (f.isEnumConstant() && !f.isAnnotationPresent(Deprecated.class)) {
                results.add(Enum.valueOf(enumType, f.getName()));
            }
        }
        return results;
    }

}
