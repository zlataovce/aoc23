import java.util.Locale

plugins {
    kotlin("jvm") version "1.9.21"
}

group = "me.kcra"
version = "1.0.0-SNAPSHOT"
description = "solutions for the 2023 Advent of Code"

repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain(8)
}

// adds tasks for running individual solutions under the "aoc" group
// inputs are defined in <solution file name>_input.txt files adjacent to the solution source file
sourceSets["main"]?.let { mainSrcSet ->
    mainSrcSet.kotlin.files.forEach { file ->
        if (file.extension != "kt") return@forEach

        val name = file.nameWithoutExtension
        val capitalName = name.replaceFirstChar { it.titlecase(Locale.getDefault()) }

        tasks.register<JavaExec>("run$capitalName") {
            group = "aoc"
            mainClass = "${capitalName}Kt"
            classpath = mainSrcSet.runtimeClasspath

            val inputFile = file.resolveSibling("${name}_input.txt")
            if (inputFile.isFile) {
                standardInput = inputFile.inputStream()
            }
        }
    }
}
