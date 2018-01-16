package org.hibernate.lucene.module;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.lucene.util.Version;

public final class VersionsHelper {

	private final String implVersion;

	public VersionsHelper() {
		this.implVersion = implVersion();
	}

	public String getImplVersion() {
		return implVersion;
	}

	public String getModuleSlot() {
		return getPropertiesVariable("lucene.slot.id");
	}

	private String implVersion() {
		return Version.class.getPackage().getImplementationVersion();
	}

	private static String getPropertiesVariable(String key) {
		Properties projectCompilationProperties = new Properties();
		final InputStream resourceAsStream = VersionsHelper.class.getClassLoader()
				.getResourceAsStream("module-versions.properties");
		try {
			projectCompilationProperties.load(resourceAsStream);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				resourceAsStream.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return projectCompilationProperties.getProperty(key);
	}

}
