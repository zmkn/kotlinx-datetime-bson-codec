import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.gradle.jvm.tasks.Jar
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.ktlint)
    alias(libs.plugins.shadow)
}

group = "com.zmkn.bson"
version = "1.0.0-SNAPSHOT"

kotlin {
    jvmToolchain {
        languageVersion.set(JavaLanguageVersion.of(22))
    }
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_22)
    }
}

configure<org.jlleitschuh.gradle.ktlint.KtlintExtension> {
    filter {
        exclude {
            // 排除所有以 .gradle.kts 结尾的文件和 build/generated 目录下的所有文件
            it.file.name.endsWith(".gradle.kts") || it.file.path.startsWith(project.layout.buildDirectory.get().dir("generated").toString())
        }
    }
}

// 声明当前时间
val currentDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"))

tasks.withType<Jar> {
    archiveBaseName.set(project.name)
    archiveVersion.set("${project.version}.${currentDateTime}")
}

tasks.withType<ShadowJar> {
    archiveBaseName.set(project.name)
    archiveClassifier.set("all") // 定义生成的 JAR 分类器名
    archiveVersion.set("${project.version}.${currentDateTime}")

    dependencies {
        exclude {
            it.moduleGroup == "org.jetbrains.kotlin"
        }
        exclude {
            it.moduleGroup == "org.jetbrains.kotlinx"
        }
    }
}

tasks.test {
    useJUnitPlatform()
}

dependencies {
    api(libs.kotlinx.datetime) // Kotlin 日期类型库
    api(libs.mongodb.bson.kotlinx) // Mongodb Bson 序列化库
    testImplementation(kotlin("test")) // Kotlin 测试依赖
}
