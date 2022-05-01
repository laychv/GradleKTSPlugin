import org.gradle.kotlin.dsl.`kotlin-dsl`

plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
    mavenCentral()
}

dependencies {
    implementation(gradleApi())
    // 这里可以引用更多其他第三方库
    compileOnly("com.android.tools.build:gradle:7.1.1")
}

gradlePlugin {
    plugins {
        create("StandalonePlugin") {
            // 这里替代了 /META-INF/gradle-plugins 中的配置流程（即无需手动配置该目录了）
            id = "com.laychv.standalone"
            // 其他工程可以通过id指定的名字来 使用插件
            implementationClass = "com.laychv.standalone.StandalonePlugin" // 指向对应的 插件实现类
        }
    }
}