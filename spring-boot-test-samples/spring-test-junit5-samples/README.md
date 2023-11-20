# JUnit Tutorials

also see: 
- https://howtodoinjava.com/series/junit/
- https://howtodoinjava.com/spring-boot2/testing/
- https://howtodoinjava.com/spring-boot/spring-boot-test-controller-service-dao/

[(Back to top)](#table-of-contents)

# Table of contents

- [JUnit Tutorials](#junit-tutorials)
- [Table of contents](#table-of-contents)
- [1. JUnit 5 Tutorials](#1.-junit-5-tutorials)
    - [Introduction](#introduction)
    - [Dependency](#dependency)
    - [LifeCycle](#lifecycle)
    - [Annotation](#annotation)
- [2. Advance Topics](#2.-advance-topics)
    - [JUnit 5 vs JUnit 4](#junit-5-vs-junit-4)
    - [JaCoCo Code Coverage](#jacoco-code-coverage)
    - [Test Templates](#test-templates)
    - [Test Reports in HTML](#test-reports-in-html)

# 1. JUnit 5 Tutorials

[(Back to top)](#table-of-contents)

## Introduction

> JUnit 5 = JUnit Platform + JUnit Jupiter + JUnit Vintage

![JUnit-5-Architecture](https://howtodoinjava.com/wp-content/uploads/2021/11/JUnit-5-Architecture.png)

[(Back to top)](#table-of-contents)

## Dependency

Let’s start with a brief look at the artifacts that are commonly used in real-world applications:

- junit-jupiter-api: It is the main module where all core annotations are located, such as @Test, Lifecycle method
  annotations and assertions.
- junit-jupiter-engine: It has test engine implementation which is required at runtime to execute the tests.
- junit-jupiter-params: It provides support for parameterized tests.\
- junit-platform-suite: It provides the @Suite support that makes the legacy JUnit 4’s JUnitPlatform runner obsolete.
- junit-vintage-engine: it contains the engine implementation to execute tests written in JUnit 3 or 4. For this
  purpose, of course, you also need the JUnit 3 or 4 jar.

Maven

```xml

<properties>
    <junit.jupiter.version>5.8.1</junit.jupiter.version>
    <junit.platform.version>1.8.1</junit.platform.version>
</properties>
<dependencies>
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter-engine</artifactId>
    <version>${junit.jupiter.version}</version>
    <scope>test</scope>
</dependency>
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter-api</artifactId>
    <version>${junit.jupiter.version}</version>
    <scope>test</scope>
</dependency>
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter-params</artifactId>
    <version>${junit.jupiter.version}</version>
    <scope>test</scope>
</dependency>
<dependency>
    <groupId>org.junit.platform</groupId>
    <artifactId>junit-platform-suite</artifactId>
    <version>${junit.platform.version}</version>
    <scope>test</scope>
</dependency>
</dependencies>
```

Gradle

```groovy
build.gradledependencies {
    testRuntime("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntime("org.junit.jupiter:junit-jupiter-engine:5.8.1")
    testRuntime("org.junit.jupiter:junit-jupiter-params:5.8.1")
    testRuntime("org.junit.platform:junit-platform-suite:1.8.1")
}
test {
    useJUnitPlatform()
}
```

![JUnit-Modules](https://howtodoinjava.com/wp-content/uploads/2021/11/JUnit-Modules.jpg)

[(Back to top)](#table-of-contents)

## LifeCycle

[(Back to top)](#table-of-contents)

## Annotation

[(Back to top)](#table-of-contents)

| Annotation | Description |  
| ---- | ---- |  
| @BeforeEach | The annotated method will be run before each test method in the test class. |  
| @AfterEach | The annotated method will be run after each test method in the test class. |  
| @BeforeAll | The annotated method will be run before all test methods in the test class. This method must be static. |  
| @AfterAll | The annotated method will be run after all test methods in the test class. This method must be static. |  
| @Test | It is used to mark a method as a junit test. |  
| @DisplayName | Used to provide any custom display name for a test class or test method |  
| @Disable | It is used to disable or ignore a test class or test method from the test suite. |  
| @Nested | Used to create nested test classes. |  
| @Tag | Mark test methods or test classes with tags for test discovery and filtering. |  
| @TestFactory | Mark a method as a test factory for dynamic tests. |  
| @ParameterizedTest | Denotes that a method is a parameterized test. |  
| @RepeatedTest | Denotes that a method is a test template for a repeated test. |  
| @TestClassOrder | Used to configure the test class execution order for @Nested test classes in the annotated test class. |  
| @TestMethodOrder | Used to configure the test method execution order for the annotated test class; similar to JUnit 4’s @FixMethodOrder. |  
| @Timeout | Used to fail a test, test factory, test template, or lifecycle method if its execution exceeds a given duration. |  
| @TempDir | Used to supply a temporary directory via field injection or parameter injection in a lifecycle method or test method. |

# 2. Advance Topics

[(Back to top)](#table-of-contents)

## JaCoCo Code Coverage

[(Back to top)](#table-of-contents)

1. Add the following dependencies plugins

```xml

<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-surefire-plugin</artifactId>
            <version>2.22.2</version>
            <configuration>
                <argLine>
                    --illegal-access=permit
                </argLine>
                <testFailureIgnore>true</testFailureIgnore>
                <forkCount>2</forkCount>
                <reuseForks>true</reuseForks>
                <argLine>${surefireArgLine}</argLine>
            </configuration>
        </plugin>
        <plugin>
            <groupId>org.jacoco</groupId>
            <artifactId>jacoco-maven-plugin</artifactId>
            <version>0.8.7</version>
            <executions>
                <execution>
                    <id>default-prepare-agent</id>
                    <goals>
                        <goal>prepare-agent</goal>
                    </goals>
                    <configuration>
                        <destFile>${project.build.directory}/coverage-reports/jacoco.exec</destFile>
                        <propertyName>surefireArgLine</propertyName>
                    </configuration>
                </execution>
                <execution>
                    <id>default-report</id>
                    <phase>test</phase>
                    <goals>
                        <goal>report</goal>
                    </goals>
                    <configuration>
                        <dataFile>${project.build.directory}/coverage-reports/jacoco.exec</dataFile>
                        <outputDirectory>${project.reporting.outputDirectory}/jacoco</outputDirectory>
                    </configuration>
                </execution>
                <execution>
                    <id>default-check</id>
                    <goals>
                        <goal>check</goal>
                    </goals>
                    <configuration>
                        <rules>
                            <rule>
                                <element>BUNDLE</element>
                                <limits>
                                    <limit>
                                        <counter>COMPLEXITY</counter>
                                        <value>COVEREDRATIO</value>
                                        <minimum>0.70</minimum>
                                    </limit>
                                </limits>
                            </rule>
                        </rules>
                    </configuration>
                </execution>
            </executions>
        </plugin>
    </plugins>
</build>
```

2. Maven package command `mvn package`

[//]: # (generate by https://github.com/hm0223/markdown-gen-tool)