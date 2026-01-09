plugins {
  kotlin("jvm") version "2.3.0"
  application
}

group = "tools.aqua"

version = "1.0"

repositories { mavenCentral() }

dependencies {
  testImplementation(kotlin("test"))
  implementation(group = "tools.aqua", name = "stars-core")
  implementation(group = "tools.aqua", name = "stars-logic-kcmftbl")
  implementation(group = "tools.aqua", name = "stars-data-av")
  implementation(group = "tools.aqua", name = "stars-importer-carla")
}

tasks.test { useJUnitPlatform() }

application { mainClass.set("tools.aqua.stars.testing.MainKt") }

kotlin { jvmToolchain(17) }
