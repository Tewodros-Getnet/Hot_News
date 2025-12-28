pluginManagement {
    repositories {
        // This filtered block is the modern best practice for Android projects
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal() // Sources for Kotlin, KSP, and other community plugins
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        // You could add other repositories here if needed (e.g., for specific libraries)
    }
}

rootProject.name = "Hot News"
include(":app")