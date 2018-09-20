package org.etri.slice.tools.adl.generator

import com.google.inject.Inject
import java.util.List
import org.eclipse.core.runtime.IStatus
import org.eclipse.core.runtime.Status
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.ui.statushandlers.StatusManager
import org.eclipse.xtext.generator.IFileSystemAccess
import org.eclipse.xtext.resource.XtextResource
import org.eclipse.xtext.util.CancelIndicator
import org.eclipse.xtext.validation.CheckMode
import org.etri.slice.tools.adl.domainmodel.AgentDeclaration
import org.etri.slice.tools.adl.validation.domain_dependency.DomainDependencyUtil
import org.etri.slice.tools.adl.validation.domain_dependency.DomainManager

public class ADLGenerator implements IGeneratorForMultiInput {

	@Inject AgentGenerator agentGenerator
	@Inject DeviceGenerator deviceGenerator
	@Inject DomainModelGenerator domainGenerator
	@Inject DistributionGenerator distributionGenerator

	@Inject extension DomainDependencyUtil
	@Inject DomainManager domainManager
	
	override void doGenerate(List<Resource> resources, IFileSystemAccess fsa) {
		resources.checkDomainDependencies

		if (!domainManager.existCycle) {
			generateMavenProject(resources, fsa)
			domainGenerator.doGenerate(resources, fsa)

			if (resources.map[allContents.toIterable.filter(typeof(AgentDeclaration))].flatten.size > 0) {
				agentGenerator.doGenerate(resources, fsa)
				deviceGenerator.doGenerate(resources, fsa)
				distributionGenerator.doGenerate(resources, fsa)
			}
		} else {
			domainManager.domains.values.forEach[d |
				val erros = (d.eObject.eResource as XtextResource).getResourceServiceProvider().getResourceValidator().validate(d.eObject.eResource, CheckMode.ALL, CancelIndicator.NullImpl); 

				if(d.hasCycle)
				{					
					val status = new Status(IStatus.ERROR, "org.etri.slice.tools.adl.ui", "domain " + d.domain + " has cycle.");
				  	StatusManager.getManager().handle(status, StatusManager.LOG);	
				}
				
				val r = d.eObject.eResource.resourceSet.resources.head;		
			]
		}
	}

	def generateMavenProject(List<Resource> resources, IFileSystemAccess fsa) {
		fsa.generateFile("license-header.txt", compileLicenseHeader)
		fsa.generateFile("pom.xml", compilePOM(resources))
	}

	def compileLicenseHeader() '''
		Copyright (c) ${inceptionyear}-${year} ${holder} (${contact})
		http://slice.etri.re.kr
		
		This file is part of ${name}
		
		This Program is free software; you can redistribute it and/or modify
		it under the terms of the GNU General Public License as published by
		the Free Software Foundation; either version 2, or (at your option)
		any later version.
		
		This Program is distributed in the hope that it will be useful,
		but WITHOUT ANY WARRANTY; without even the implied warranty of
		MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
		GNU General Public License for more details.
		
		You should have received a copy of the GNU General Public License
		along with ${name}; see the file COPYING.  If not, see
		<http://www.gnu.org/licenses/>.
	'''

	def compilePOM(List<Resource> resources) '''
		<?xml version="1.0" encoding="UTF-8"?>
		<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
			xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
		
			<modelVersion>4.0.0</modelVersion>
			<groupId>org.etri.slice</groupId>
			<artifactId>org.etri.slice</artifactId>
			<version>0.9.1</version>
		
			<packaging>pom</packaging>
			<name>The ROOT project of SLICE components and applications</name>
			<description>org.etri.slice parent</description>
		
			<modules>
				«IF resources.map[allContents.toIterable.filter(typeof(AgentDeclaration))].flatten.size > 0»
					<module>org.etri.slice.agents</module>
					<module>org.etri.slice.devices</module>
				«ENDIF»
				<module>org.etri.slice.models</module>
				«IF resources.map[allContents.toIterable.filter(typeof(AgentDeclaration))].flatten.size > 0»
					<module>org.etri.slice.rules</module>
					<module>org.etri.slice.distribution</module>
				«ENDIF»
			</modules>
		
			<properties>
				<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
				<project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
				<felix.ipojo.version>1.12.1</felix.ipojo.version>
				<project.inceptionYear>2017</project.inceptionYear>
				<slice.home>/Users/yhsuh/development/slice-project/git/slice/org.etri.slice.distribution</slice.home>
			</properties>
			
			<distributionManagement>
				<repository>
					<id>slice-obr-hosted</id>
					<url>http://129.254.88.119:8081/nexus/content/repositories/slice-obr/</url>
				</repository>
			</distributionManagement>
		
			<repositories>
				<repository>
					<id>local-repo</id>
					<url>file://${slice.home}/repository</url>
					<releases>
						<enabled>true</enabled>
						<checksumPolicy>ignore</checksumPolicy>
						<updatePolicy>always</updatePolicy>
					</releases>
					<snapshots>
						<enabled>true</enabled>
						<checksumPolicy>ignore</checksumPolicy>
						<updatePolicy>always</updatePolicy>
					</snapshots>
				</repository>			
				<repository>
					<id>central</id>
					<url>https://repo.maven.apache.org/maven2/</url>
				</repository>
			</repositories>
		
			<dependencies>
				<dependency>
					<groupId>org.apache.felix</groupId>
					<artifactId>org.apache.felix.ipojo</artifactId>
					<version>${felix.ipojo.version}</version>
				</dependency>
				<dependency>
					<groupId>org.apache.felix</groupId>
					<artifactId>org.apache.felix.ipojo.gogo</artifactId>
					<version>${felix.ipojo.version}</version>
				</dependency>
				<dependency>
					<groupId>org.apache.felix</groupId>
					<artifactId>org.apache.felix.ipojo.annotations</artifactId>
					<version>${felix.ipojo.version}</version>
				</dependency>
				<dependency>
					<groupId>org.apache.felix</groupId>
					<artifactId>org.apache.felix.ipojo.handler.eventadmin</artifactId>
					<version>1.8.0</version>
				</dependency>
				<dependency>
					<groupId>com.mycila</groupId>
					<artifactId>license-maven-plugin</artifactId>
					<version>3.0</version>
				</dependency>
			</dependencies>
		
			<build>
				<pluginManagement>
					<plugins>
						<plugin>
							<groupId>org.apache.felix</groupId>
							<artifactId>maven-bundle-plugin</artifactId>
							<configuration>
								<remoteOBR>slice-obr-hosted</remoteOBR>
							</configuration>
							<version>3.3.0</version>
							<extensions>true</extensions>
						</plugin>
						<plugin>
							<groupId>org.apache.felix</groupId>
							<artifactId>maven-ipojo-plugin</artifactId>
							<version>1.12.1</version>
							<executions>
								<execution>
									<goals>
										<goal>ipojo-bundle</goal>
									</goals>
								</execution>
							</executions>
						</plugin>
						<plugin>
							<groupId>org.apache.maven.plugins</groupId>
							<artifactId>maven-compiler-plugin</artifactId>
							<version>3.1</version>
							<configuration>
								<fork>true</fork>
								<meminitial>512m</meminitial>
								<maxmem>1024m</maxmem>
								<source>1.8</source>
								<target>1.8</target>
								<encoding>UTF-8</encoding>
							</configuration>
						</plugin>
						<plugin>
							<groupId>org.codehaus.mojo</groupId>
							<artifactId>properties-maven-plugin</artifactId>
							<version>1.0.0</version>
							<executions>
								<execution>
									<phase>initialize</phase>
									<goals>
										<goal>read-project-properties</goal>
									</goals>
									<configuration>
										<files>
											<file>bundle.properties</file>
										</files>
									</configuration>
								</execution>
							</executions>
						</plugin>
						<plugin>
							<groupId>com.mycila</groupId>
							<artifactId>license-maven-plugin</artifactId>
							<version>3.0</version>
							<inherited>false</inherited>
							<configuration>
								<header>${project.basedir}/license-header.txt</header>
								<aggregate>true</aggregate>
								<properties>
									<name>${project.name}</name>
									<inceptionyear>${project.inceptionYear}</inceptionyear>
									<year>2017</year>
									<holder>SLICE project team</holder>
									<contact>yhsuh@etri.re.kr</contact>
								</properties>
								<excludes>
									<exclude>**/*.xml</exclude>
									<exclude>**/*.properties</exclude>
									<exclude>**/*.xtrg</exclude>
									<exclude>**/*.xcmd</exclude>
									<exclude>**/*.xrea</exclude>
									<exclude>**/*.xobj</exclude>
									<exclude>**/*.pom</exclude>
									<exclude>**/*.png</exclude>
									<exclude>**/*.jpg</exclude>
									<exclude>**/*.PNG</exclude>
									<exclude>**/*.options</exclude>
									<exclude>**/*.txt</exclude>
									<exclude>**/*.drl</exclude>
								</excludes>
								<includes>
									<include>**/org.etri.slice.agents/**</include>
									<include>**/org.etri.slice.commons/**</include>
									<include>**/org.etri.slice.rules/**</include>
								</includes>
								<strictCheck>true</strictCheck>
								<aggregate>true</aggregate>
							</configuration>
							<executions>
								<execution>
									<goals>
										<goal>format</goal>
									</goals>
									<phase>process-sources</phase>
								</execution>
							</executions>
						</plugin>
						<plugin>
							<groupId>org.apache.maven.plugins</groupId>
							<artifactId>maven-eclipse-plugin</artifactId>
							<version>2.10</version>
							<configuration>
								<downloadSources>true</downloadSources>
							</configuration>
						</plugin>
					</plugins>
				</pluginManagement>
				<plugins>
					<plugin>
						<groupId>com.mycila</groupId>
						<artifactId>license-maven-plugin</artifactId>
					</plugin>
				</plugins>
			</build>
		</project>
	'''
	
	override void doGenerate(Resource resource, IFileSystemAccess fsa) {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}	
}


