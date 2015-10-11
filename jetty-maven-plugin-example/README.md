## Intro
This example shows how to quickly test your webapp with jetty (which requires less configuration to autobuild to tomcat

## Instructions
mvn jetty:run
navigate to http://localhost:8080/abc




				<plugin>
					<groupId>org.eclipse.jetty</groupId>
					<artifactId>jetty-maven-plugin</artifactId>
					<version>9.2.11.v20150529</version>
					<configuration>
						<scanIntervalSeconds>10</scanIntervalSeconds>
						<webApp>
							<contextPath>/spring</contextPath>
						</webApp>
					</configuration>
				</plugin>
