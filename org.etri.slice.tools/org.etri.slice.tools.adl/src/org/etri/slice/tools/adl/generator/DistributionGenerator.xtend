package org.etri.slice.tools.adl.generator

import com.google.inject.Inject
import java.util.List
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtext.generator.IFileSystemAccess
import org.eclipse.xtext.naming.IQualifiedNameProvider
import org.etri.slice.tools.adl.domainmodel.AgentDeclaration
import org.etri.slice.tools.adl.domainmodel.DomainDeclaration

class DistributionGenerator implements IGeneratorForMultiInput {	

	@Inject extension IQualifiedNameProvider
		
	override doGenerate(List<Resource> resources, IFileSystemAccess fsa) {
		fsa.generateFile(OutputPathUtils.sliceDistribution + "/pom.xml", compileDistributionPOM(resources))
		fsa.generateFile(OutputPathUtils.sliceDistribution + "/run_slice.bat", compileRunBatch)
		fsa.generateFile(OutputPathUtils.sliceDistribution + "/run_slice.sh", compileRunShell)		
	}
	
	def compileDistributionPOM(List<Resource> resources) '''
		<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
			xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
		
			<modelVersion>4.0.0</modelVersion>
			<parent>
				<groupId>org.etri.slice</groupId>
				<artifactId>org.etri.slice</artifactId>
				<version>0.9.1</version>
				<relativePath>../pom.xml</relativePath>
			</parent>
		
			<artifactId>org.etri.slice.distribution</artifactId>
			<name>The SLICE distribution</name>
			<description>org.etri.slice.distribution :: distribution</description>
		
			<dependencies>
				<dependency>
					<groupId>org.apache.felix</groupId>
					<artifactId>org.apache.felix.ipojo.distribution.quickstart</artifactId>
					<version>${felix.ipojo.version}</version>
					<type>zip</type>
				</dependency>
				<dependency>
					<groupId>org.apache.felix</groupId>
					<artifactId>org.apache.felix.ipojo.handler.eventadmin</artifactId>
					<version>1.8.0</version>
				</dependency>
				<dependency>
					<groupId>org.apache.felix</groupId>
					<artifactId>org.apache.felix.eventadmin</artifactId>
					<version>1.4.2</version>
				</dependency>
				<dependency>
					<groupId>org.apache.felix</groupId>
					<artifactId>org.apache.felix.main</artifactId>
					<version>5.6.8</version>
				</dependency>
				<dependency>
				    <groupId>org.apache.felix</groupId>
				    <artifactId>org.apache.felix.configadmin</artifactId>
				    <version>1.8.4</version>
				</dependency>
				<dependency>
				    <groupId>org.apache.felix</groupId>
				    <artifactId>org.apache.felix.http.api</artifactId>
				    <version>2.3.2</version>
				</dependency>				
				<dependency>
				    <groupId>org.apache.felix</groupId>
				    <artifactId>org.apache.felix.http.jetty</artifactId>
				    <version>3.0.2</version>
				</dependency>
				<dependency>
				    <groupId>org.apache.felix</groupId>
				    <artifactId>org.apache.felix.http.servlet-api</artifactId>
				    <version>1.1.0</version>
				</dependency>
				<dependency>
				    <groupId>org.apache.felix</groupId>
				    <artifactId>org.apache.felix.webconsole</artifactId>
				    <version>4.3.4-all</version>
				    <scope>provided</scope>
				</dependency>								
				<dependency>
				    <groupId>org.apache.felix</groupId>
				    <artifactId>org.apache.felix.webconsole.plugins.obr</artifactId>
				    <version>1.0.4</version>
				</dependency>			
				<dependency>
				    <groupId>org.apache.felix</groupId>
				    <artifactId>org.apache.felix.webconsole.plugins.event</artifactId>
				    <version>1.1.8</version>
				</dependency>
				<dependency>
				    <groupId>org.apache.felix</groupId>
				    <artifactId>org.apache.felix.webconsole.plugins.memoryusage</artifactId>
				    <version>1.0.8</version>
				</dependency>
				<dependency>
					<groupId>org.etri.slice</groupId>
					<artifactId>org.etri.slice.api</artifactId>
					<version>0.9.1</version>
				</dependency>
				<dependency>
					<groupId>org.etri.slice</groupId>
					<artifactId>org.etri.slice.core</artifactId>
					<version>0.9.1</version>
				</dependency>
				<dependency>
					<groupId>org.etri.slice</groupId>
					<artifactId>org.etri.slice.commons</artifactId>
					<version>0.9.1</version>
				</dependency>										
				«FOR e: resources.map[allContents.toIterable.filter(typeof(DomainDeclaration))].flatten»
					<dependency>
						<groupId>org.etri.slice.commons</groupId>
						<artifactId>org.etri.slice.commons.«e.fullyQualifiedName»</artifactId>
						<version>0.9.1</version>
					</dependency>
				«ENDFOR»
				
				«IF resources.map[allContents.toIterable.filter(typeof(AgentDeclaration))].flatten.size > 0»
				«val agent = resources.map[allContents.toIterable.filter(typeof(AgentDeclaration))].flatten.get(0)»
				<dependency>
					<groupId>org.etri.slice</groupId>
					<artifactId>org.etri.slice.agents.«agent.eContainer.fullyQualifiedName».«agent.name.toLowerCase»</artifactId>
					<version>0.9.1</version>
				</dependency>
				<dependency>
					<groupId>org.etri.slice.devices</groupId>
					<artifactId>org.etri.slice.devices.«agent.eContainer.fullyQualifiedName».«agent.name.toLowerCase»</artifactId>
					<version>0.9.1</version>
				</dependency>
				«ENDIF»
				«IF resources.map[allContents.toIterable.filter(typeof(AgentDeclaration))].flatten.size > 1»
		<!--	
				«FOR e: resources.map[allContents.toIterable.filter(typeof(AgentDeclaration))].flatten»	
					<dependency>
						<groupId>org.etri.slice</groupId>
						<artifactId>org.etri.slice.agents.«e.eContainer.fullyQualifiedName».«e.name.toLowerCase»</artifactId>
						<version>0.9.1</version>
					</dependency>
					<dependency>
						<groupId>org.etri.slice.devices</groupId>
						<artifactId>org.etri.slice.devices.«e.eContainer.fullyQualifiedName».«e.name.toLowerCase»</artifactId>
						<version>0.9.1</version>
					</dependency>										
				«ENDFOR»	
		-->
				«ENDIF»							
			</dependencies>
		
			<build>
				<plugins>
					<plugin>
						<groupId>org.apache.maven.plugins</groupId>
						<artifactId>maven-dependency-plugin</artifactId>
						<version>3.0.1</version>
						<executions>
							<execution>
								<id>unpack-felix</id>
								<phase>compile</phase>
								<goals>
									<goal>unpack-dependencies</goal>
								</goals>
								<configuration>
									<includeArtifactIds>org.apache.felix.ipojo.distribution.quickstart</includeArtifactIds>
									<outputDirectory>${project.build.directory}/tmp</outputDirectory>
								</configuration>
							</execution>
							<execution>
								<id>copy-felix</id>
								<phase>package</phase>
								<goals>
									<goal>copy-dependencies</goal>
								</goals>
								<configuration>
									<includeArtifactIds>org.apache.felix.ipojo.handler.eventadmin,org.apache.felix.eventadmin,org.apache.felix.configadmin,
									org.apache.felix.http.jetty,org.apache.felix.http.api,org.apache.felix.http.servlet-api,org.apache.felix.webconsole,
									org.apache.felix.webconsole.plugins.obr,org.apache.felix.webconsole.plugins.event,org.apache.felix.webconsole.plugins.memoryusage</includeArtifactIds>
									<outputDirectory>${project.build.directory}/bundle</outputDirectory>
								</configuration>
							</execution>					
							<execution>
								<id>copy-bundles</id>
								<phase>package</phase>
								<goals>
									<goal>copy-dependencies</goal>
								</goals>
								<configuration>
									<includeGroupIds>${project.groupId}</includeGroupIds>
									<excludeArtifactIds>dependencies</excludeArtifactIds>
									<outputDirectory>${project.build.directory}/bundle</outputDirectory>
								</configuration>
							</execution>
						</executions>
					</plugin>
					<plugin>
						<artifactId>maven-resources-plugin</artifactId>
						<version>3.0.2</version>
						<executions>
							<execution>
								<goals>
									<goal>copy-resources</goal>
								</goals>
								<phase>compile</phase>
								<id>copy-distribution</id>
								<configuration>
									<outputDirectory>${project.build.directory}</outputDirectory>
									<resources>
										<resource>
											<directory>${project.build.directory}/tmp/ipojo-distribution-${felix.ipojo.version}</directory>
											<filtering>false</filtering>
										</resource>
										<resource>
											<directory>${project.basedir}</directory>
											<includes>
												<include>run_slice.bat</include>
												<include>run_slice.sh</include>
											</includes>
										</resource>
									</resources>
								</configuration>
							</execution>
						</executions>
					</plugin>
				</plugins>
			</build>
		</project>
	'''	

	def compileRunBatch() '''
		@echo off
		java -jar -Dcom.sun.management.jmxremote.port=3403 -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false ./bin/felix.jar
	'''
	
	def compileRunShell() '''
		java -jar -Dcom.sun.management.jmxremote.port=3403 -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false ./bin/felix.jar
	'''
	
	override doGenerate(Resource input, IFileSystemAccess fsa) {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
}
