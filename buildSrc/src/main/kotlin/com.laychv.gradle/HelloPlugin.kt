package com.laychv.gradle

import com.android.build.gradle.AppExtension
import com.android.build.gradle.internal.plugins.AppPlugin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.hasPlugin

/**
 * @author: LayChv
 * @date:   2022/5/2
 * @des:
 */
class HelloPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        println("This is Hi Plugin From ##buildSrc## : ${project.parent?.name}")
        project.tasks.create<HiTask>("HiTask")

        if (project.plugins.hasPlugin(AppPlugin::class.java)) {
            println("Hello Plugin has Plugin !!!")
        }

        if (project.plugins.hasPlugin(AppPlugin::class)) {
            val appExtension = project.extensions.getByType(AppExtension::class)
//            appExtension.registerTransform()
            println(appExtension)
        }
    }
}
