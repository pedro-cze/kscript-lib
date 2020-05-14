`library info` {
    name = "kscript-utils"
    description = "Set of kotlin functions to ease creation of basic my2n scripts."
}

plugins {
    id("org.jetbrains.kotlin.jvm") version "1.3.61"
}

repositories {
    mavenCentral()
    maven {
        url = uri("https://kotlin.bintray.com/kotlin-dev")
    }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlin:kotlin-script-runtime:1.3.61")
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.3.61")
    implementation("com.squareup.okhttp3:okhttp:4.3.1")
    implementation("com.beust:klaxon:5.2")
    implementation("com.github.andrewoma.kwery:core:0.17")
    compileOnly("com.github.holgerbrandl:kscript-annotations:1.2")
    compileOnly("com.offbytwo:docopt:0.6.0.20150202")

    testImplementation("org.junit.jupiter:junit-jupiter:5.6.1")
    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testCompileOnly("org.junit.jupiter:junit-jupiter-params")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
    testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0:")

}

tasks {
    compileKotlin {
        org.jetbrains.kotlin.platform.jvm.JvmPlatforms.jvmPlatformByTargetVersion(org.jetbrains.kotlin.config.JvmTarget.JVM_1_8)
    }
}

excludeJUnit4()
