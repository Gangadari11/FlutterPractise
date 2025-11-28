allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

val newBuildDir: Directory =
    rootProject.layout.buildDirectory
        .dir("../../build")
        .get()
rootProject.layout.buildDirectory.value(newBuildDir)

subprojects {
    val newSubprojectBuildDir: Directory = newBuildDir.dir(project.name)
    project.layout.buildDirectory.value(newSubprojectBuildDir)
}
 // Removed forced evaluation of ":app" to avoid configuring the :app project during root configuration,
 // which can trigger NDK/source.properties errors; evaluate :app only when necessary.

tasks.register<Delete>("clean") {
    delete(rootProject.layout.buildDirectory)
}




// ///********************Replace  with this **************************/
// allprojects {
//     repositories {
//         google()
//         mavenCentral()
//     }
// }

// // Move build directory safely, without forcing early evaluation
// gradle.beforeProject {
//     layout.buildDirectory.set(
//         rootProject.layout.buildDirectory.dir("../../build").map {
//             it.dir(project.name)
//         }
//     )
// }

// tasks.register<Delete>("clean") {
//     delete(rootProject.layout.buildDirectory)
// }
