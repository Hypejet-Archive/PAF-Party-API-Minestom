@file:Suppress("UnstableApiUsage")

rootProject.name = "PAF-Party-API-Minestom"

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            library("minestom", "com.github.Minestom:Minestom:-SNAPSHOT")
            library("citystom", "com.github.CityWideMC:CityStom:-SNAPSHOT")
            library("lombok", "org.projectlombok:lombok:1.18.24")
            library("pafmysql", "de.simonsator:Party-and-Friends-MySQL-Edition-Minestom-API:1.5.4-RELEASE")
            library("jedis", "redis.clients:jedis:4.2.3")
        }
    }
}