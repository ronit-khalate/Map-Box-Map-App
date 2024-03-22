

pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }

}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()




        // Mapbox Maven repository
        maven {
            // Do not change the username below. It should always be "mapbox" (not your username).
            credentials{
                username = "mapbox"
                password = "sk.eyJ1Ijoicm9uaXQ1IiwiYSI6ImNsdTByaWV4MzBjNHkyamsxemhpcmRsbjIifQ.G1bh-WQChwidPsb83wrbvg"
            }
            url = uri("https://api.mapbox.com/downloads/v2/releases/maven")
            // Use the secret token stored in gradle.properties as the password
//            credentials.password ="pk.eyJ1Ijoicm9uaXQ1IiwiYSI6ImNsdHlrdTFyZTBkNmkyaW80OWo3cHhrdXAifQ.iZXQXjF0yiYEBRAADylW6w"
            authentication.create<BasicAuthentication>("basic")
        }

    }
}

rootProject.name = "Vyorius Drones Assignment"
include(":app")
