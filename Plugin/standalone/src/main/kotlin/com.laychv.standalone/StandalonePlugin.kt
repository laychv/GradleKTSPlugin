package com.laychv.standalone

import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * 独立插件
 */
class StandalonePlugin : Plugin<Project> {

    override fun apply(project: Project) {
        println("This is Standalone Plugin From ##Standalone## : ${project.parent?.name}")
    }
}