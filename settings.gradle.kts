rootProject.name = "stars-testing"

plugins { id("org.gradle.toolchains.foojay-resolver-convention").version("1.0.0") }

// Include the STARS build from the parent directory
includeBuild("../stars")
