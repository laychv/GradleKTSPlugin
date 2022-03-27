# Gradle7自定义Gradle插件

Bumblebee支持KTS创建的脚本，Groovy测试发现找不到脚本id。 演示如何创建Gradle-KTS脚本，成功运行本地脚本项目，全部迁移至KTS。

演示2中方式打包插件

## buildSrc

## 迁移前后对比

setting.gradle

```Groovy
pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
include ':helloplugin'
rootProject.name = "GradlePlugin"
```

setting.gradle.kts

```Kotlin
include(":helloplugin")// 可变参数，添加其他module
rootProject.name = "GradleKTSPlugin"
```

build.gradle

```Groovy
plugins {
    id 'com.android.application' version '7.1.1' apply false
    id 'com.android.library' version '7.1.1' apply false
    id 'org.jetbrains.kotlin.android' version '1.6.10' apply false
}

task clean(type: Delete) {
    delete rootProject.buildDir
}

task hello() {
    println("This is task ")
}
```

build.gradle.kts

```Kotlin
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.1.1")
        classpath(kotlin("gradle-plugin", version = "1.6.10"))
    }
}
allprojects {
    repositories {
        google()
        mavenCentral()
    }
}
```

app/build.gradle

```Groovy
// import com.laychv.gradle.HiPlugin

plugins {
    id 'com.android.application'
    id 'org.jetbrains.kotlin.android'
    id 'com.laychv.gradle'
}

// 这种方式，找不到apply
//apply<HiPlugin>()

android {
    compileSdk 31

    defaultConfig {
        applicationId "com.laychv.gradleplugin"
        minSdk 23
        targetSdk 31
        versionCode 1
        versionName "1.0"

        testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }
    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = '1.8'
    }
}

dependencies {
    implementation 'androidx.core:core-ktx:1.7.0'
    implementation 'androidx.appcompat:appcompat:1.3.0'
    implementation 'com.google.android.material:material:1.4.0'
    implementation 'androidx.constraintlayout:constraintlayout:2.0.4'
    testImplementation 'junit:junit:4.13.2'
    androidTestImplementation 'androidx.test.ext:junit:1.1.3'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.4.0'
}
```

app/build.gradle.kts

```Kotlin
plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.laychv.gradle")
}

android {
    compileSdk = 31

    defaultConfig {
        applicationId = "com.laychv.gradleplugin"
        minSdk = 23
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        getByName("debug") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.appcompat:appcompat:1.3.0")
    implementation("com.google.android.material:material:1.4.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}
```

## Composite Build

独立插件，包含setting.gradle，build.gradle，发布给其他人使用。

新建Plugin文件夹，创建standalone文件夹，创建src/main/kotlin文件夹，创建包名com.laychv.standalone

如果编译出现问题，单独编译Plugin项目，但是会产生多余文件:可以删除

build.gradle/gradlew/gradlew.bat/local.properties

产生多余的文件夹：

gradle/.gradle/.idea

## 问题

引入插件

apply<HiPlugin>()

找不到apply，但是使用id却没问题。

## 参考

buildSrc参考

[自定义Gradle参考文档](https://101.dev/t/gradle-agp-api/525)

[迁移KTS参考文档](https://developer.android.google.cn/studio/build/migrate-to-kts)

[参考IOSched项目](https://github.com/google/iosched)

独立插件参考

[SampleCompositeBuild](https://docs.gradle.org/current/samples/sample_composite_builds_basics.html)

---

附上Android Studio版本

```
Android Studio Bumblebee | 2021.1.1 Patch 1
Build #AI-211.7628.21.2111.8139111, built on February 2, 2022
Runtime version: 11.0.11+0-b60-7772763 aarch64
VM: OpenJDK 64-Bit Server VM by JetBrains s.r.o.
macOS 12.2.1
GC: G1 Young Generation, G1 Old Generation
Memory: 1280M
Cores: 10
Registry: external.system.auto.import.disabled=true
Non-Bundled Plugins: cn.haojiyou.CodeGlance3 (2.1.0),
ASM Bytecode Viewer (7.2),
mobi.hsz.idea.gitignore (4.1.0),
Dart (211.7811),
org.jetbrains.kotlin (211-1.6.10-release-923-AS7442.40),
io.flutter (65.2.2),
cn.yiiguxing.plugin.translate (3.3-203u212)

```