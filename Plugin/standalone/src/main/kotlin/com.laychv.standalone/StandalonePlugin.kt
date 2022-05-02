package com.laychv.standalone

import com.android.build.gradle.AppPlugin
//import com.android.build.gradle.internal.plugins.AppPlugin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.hasPlugin

/**
 * 独立插件
 */
class StandalonePlugin : Plugin<Project> {

    override fun apply(project: Project) {
        println("This is Standalone Plugin From ##Standalone## : ${project.parent?.name}")
        if (project.plugins.hasPlugin(AppPlugin::class)) {
            println("Has Plugin App Plugin class !!!")
        }
    }
}