Lucene JBoss Module
===================

Packaging of [Apache Lucene](http://lucene.apache.org) as a [JBoss Module](https://docs.jboss.org/author/display/MODULES/Home).

Historically the Hibernate Search project has been releasing such modules, for convenience of Apache Lucene
users running applications on WildFly or JBoss EAP.

We now moved these artifacts to a "Lucene only" bundle so that other projects using Lucene don't have to
download the Hibernate Search specific modules, and to attempt to have a single consistent distribution
of such modules.

This should also make it easier to release a new bundle of these modules as soon as a new Lucene version
is released, without necessarily waiting for Hibernate Search to have adopted the new Lucene version.

== Versions

We will use an `X.Y.Z.qualifier` pattern as recommended by [JBoss Project Versioning](https://developer.jboss.org/wiki/JBossProjectVersioning),
wherein the `X.Y.Z` section will match the version of the Apache Lucene version included in the modules,
possibly using a `0` for the last figure when this is missing in the Lucene version scheme.

We will add an additional qualifier, lexicographically increasing with further releases, to distinguish
different releases of this package when still containing the same version of Apache Lucene library.
This might be useful to address problems in the package structure, or any other reason to have
to release a new version of these modules containing the same Lucene version as a previously
released copy of these modules.

An example version could be `5.5.0.wildfly02` to contain Apache Lucene version `5.5`.

== Usage

Extract the produced module zip in the `/modules` directory of your WildFly 10 distribution.

The modules are distributed as Maven artifacts, so when using Maven you could automate this step
for example using the `maven-dependency-plugin`.

	<plugin>
	    <artifactId>maven-dependency-plugin</artifactId>
	    <executions>
		<execution>
		    <id>unpack</id>
		    <phase>pre-integration-test</phase>
		    <goals>
		        <goal>unpack</goal>
		    </goals>
		    <configuration>
		        <artifactItems>
		            <artifactItem>
		                <groupId>org.wildfly</groupId>
		                <artifactId>wildfly-dist</artifactId>
		                <version>${wildfly.version}</version>
		                <type>zip</type>
		                <overWrite>true</overWrite>
		                <outputDirectory>${project.build.directory}/node1</outputDirectory>
		            </artifactItem>
		            <artifactItem>
		                <groupId>org.hibernate.lucene-modules</groupId>
		                <artifactId>lucene-modules</artifactId>
		                <version>${lucene-modules.version}</version>
		                <type>zip</type>
		                <classifier>dist</classifier>
		                <overWrite>true</overWrite>
		                <outputDirectory>${project.build.directory}/node1/wildfly-${wildfly.version}/modules</outputDirectory>
		            </artifactItem>
		        </artifactItems>
		    </configuration>
		</execution>
	    </executions>
	</plugin>

This will make them available as an opt-in dependency to any application deployed on WildFly.
To enable the dependency there are various options, documented in [https://docs.jboss.org/author/display/WFLY10/Class+Loading+in+WildFly](Class Loading in WildFly).


