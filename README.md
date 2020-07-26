# API Testing Framework

This Framework is designed for Backend Testing of Webservices using BDD Cucumber with Java, RestAssured and JUnit utilizing Maven as dependency management tool.

## Technology Stack

- Java
- Cucumber
- RestAssured
- JUnit
- Maven

## Prerequisites

* [Java 1.8](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) - Java Dev Kit
* [Maven](https://maven.apache.org/download.cgi) - Dependency Manager
* [IntelliJ IDEA](https://www.jetbrains.com/idea/download) - IDE
* [Cucumber Plugin](https://plugins.jetbrains.com/plugin/7212-cucumber-for-java) - For IntelliJ IDE


## Project Structure

>businesslayer: This directory contains all the business logics to retrieve a single pojo response or list of pojo response

>constant: This package contains all the required constants of services, scenario names etc.

>pojo: This directory contains all the response pojos of each api.zippopotam endpoint

>util: This package contains Utility Classes

>config: This package contains baseUrl and service endpoints

>runner: This directory contains Test Runner class

>stepDefinitions: This directory contains implementation of feature files

>src/test/resources: This directory contains feature files and test cases for endpoints i.e. http://api.zippopotam.us/{country}/{postal-code} and http://api.zippopotam.us/{country}/{state}/{city}

## Run API Playground application as a Pre Requisite

We are using Zippopotam APIs as Application Under Test.

* URL : https://api.zippopotam.us/country/postal-code
* URL : https://api.zippopotam.us/country/state/city


## Getting Started

Following instructions will help you running this Api Test Framework project. First of all, checkout/clone this project from master branch on your local machine.

### Installation

Open the project in IntelliJ. Run the following command in Terminal and build the project. It will automatically download all the required dependencies.

```sh
$ mvn clean install
```

If the build is successful. All the required dependencies are installed successfully. But if the build fails, make sure to to resolve all the issues in order to execute tests successfully. Make sure that config.properties path in Property Reader class is set according to your Operating System Environment.

### Execute Tests

Run the following command in Terminal to execute tests.

```sh
$ mvn clean test
```

Or 
```sh
$ mvn test
```

Or 
```sh
$ mvn verify
```

### Test Report

You can find the Surefire HTML reports in the following directory of the Project.

```sh
\target\surefire-reports\index.html
```

Under the surefire-reports directory, open ‘index.html’ file to view reports.