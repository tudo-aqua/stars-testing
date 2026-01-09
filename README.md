# stars-testing

A minimal STARS v2.0 testing project that demonstrates the STARS framework creation process.

## Project Structure

This project is set up similar to [stars-carla-experiments](https://github.com/tudo-aqua/stars-carla-experiments) but with a simplified structure focused on demonstrating the STARS v2.0 creation process.

## Setup

This project uses Gradle with a composite build that includes the STARS framework from a sibling directory.

### Prerequisites

1. Clone the [STARS framework](https://github.com/tudo-aqua/stars) repository in the parent directory:
   ```bash
   cd ..
   git clone https://github.com/tudo-aqua/stars.git
   cd stars-testing
   ```

2. Ensure you have Java 17 or later installed.

## Running

To run the STARS v2.0 testing application:

```bash
./gradlew run
```

On Windows:
```bash
./gradlew.bat run
```

## What This Project Does

The `Main.kt` file demonstrates the STARS v2.0 creation process by:

1. Creating a simple TSC (Traffic Scenario Classifier)
2. Building projections from the TSC
3. Setting up TSC Evaluation with metric providers
4. Printing information about the created structures

Unlike the stars-carla-experiments project, this does not download or process experiment data files - it focuses purely on demonstrating the STARS framework setup.