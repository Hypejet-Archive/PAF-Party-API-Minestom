@file:Suppress("UnstableApiUsage")

rootProject.name = "PAF-Party-API-Minestom"

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            library("minestom", "com.github.Minestom:Minestom:-SNAPSHOT")
            library("hypestom", "com.github.HypeJet:HypeStom:82a67118f6")
            library("lombok", "org.projectlombok:lombok:1.18.24")
            library("pafmysql", "com.github.CityWideMC:PAFMySQLMinestomAPI:-SNAPSHOT")
            library("jedis", "redis.clients:jedis:4.2.3")
        }
    }
}