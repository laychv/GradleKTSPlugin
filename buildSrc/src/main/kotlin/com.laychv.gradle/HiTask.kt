package com.laychv.gradle

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

/**
 * 自定义Task，继承自DefaultTask
 * Task必须指定为
 * abstract class
 */
abstract class HiTask : DefaultTask() {
    @TaskAction
    fun taskAction() {
        println("Hi Task ! ${project.parent?.name}")
    }
}