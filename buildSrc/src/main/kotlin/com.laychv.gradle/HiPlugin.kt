package com.laychv.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.create

/**
 * Gradle7.x
 * 自定义Gradle插件
 */
class HiPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        println("This is Hi Plugin From ##buildSrc## : ${project.parent?.name}")
        project.tasks.create<HiTask>("HiTask")
    }
}
