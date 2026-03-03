# MinMax Triangle Path

This is a structural Scala 3 project that reads a triangle structure of numbers from standard input and identifies the path from top to bottom that yields the minimum sum. 

## Requirements

- [Java Development Kit (JDK) 11+](https://adoptium.net/)
- [SBT (Scala Build Tool)](https://www.scala-sbt.org/download.html)

## How to Build and Run

To ensure that the application properly reads from standard input (such as redirection from a file), the application is built as a fat JAR using `sbt-assembly`.

### 1. Build the executable JAR
Open your terminal in the project's root directory and run:

```bash
sbt assembly
```

This will create an executable JAR file at `target/scala-3.3.1/minmax-triangle-path-assembly-0.1.0-SNAPSHOT.jar`.

### 2. Run the Program

You can now use standard input redirection to pipe your triangle file into the program:

```bash
java -jar target/scala-3.3.1/minmax-triangle-path-assembly-0.1.0-SNAPSHOT.jar < data_small.txt
```

Alternatively, you could pipe input over via `cat`:

```bash
cat data_big.txt | java -jar target/scala-3.3.1/minmax-triangle-path-assembly-0.1.0-SNAPSHOT.jar
```

## Running the Tests

To run the unit tests, use the following command:

```bash
sbt test
```