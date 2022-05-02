plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
    mavenCentral()
    google()
}

dependencies {
    implementation(gradleApi())
    implementation(gradleKotlinDsl())
    implementation(kotlin("stdlib"))
    // 这里可以引用更多其他第三方库
    implementation("com.android.tools.build:gradle:7.1.3")
}

gradlePlugin {
    plugins {
        create("HelloPlugin") {
            // 这里替代了 /META-INF/gradle-plugins 中的配置流程（即无需手动配置该目录了）
            id = "com.laychv.gradle"
            // 其他工程可以通过id指定的名字来 使用插件
            implementationClass = "com.laychv.gradle.HelloPlugin" // 指向对应的 插件实现类
        }
    }
}