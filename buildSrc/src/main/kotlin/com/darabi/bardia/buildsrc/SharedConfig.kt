package com.darabi.bardia.buildsrc

import org.gradle.api.artifacts.dsl.DependencyHandler
import java.util.*
import kotlin.collections.ArrayList

object Application {

    // Main Configs
    const val ID = "com.darabi.testapplication"
    const val MIN_SDK_VERSION = 21
    const val TARGET_SDK_VERSION = 33
    const val VERSION_CODE = 1
    const val VERSION_NAME = "1.0.0"
    const val JVM_VERSION = "1.8"
}

object Dependencies {

    private const val KAPT = "kapt"
    private const val IMPLEMENTATION = "implementation"

    private const val CORE_VERSION = "1.9.0"
    private const val APPCOMPAT_VERSION = "1.5.1"
    private const val COROUTINES_VERSION = "1.4.2"
    private const val WORK_MANAGER_VERSION = "2.7.1"
    private const val STARTUP_VERSION = "1.1.1"
    private const val CONSTRAINT_VERSION = "2.1.4"
    private const val MATERIAL_VERSION = "1.7.0"
    private const val LIFECYCLE_VERSION = "2.2.0"
    private const val HILT_VERSION = "2.44"
    private const val HILT_WORK_VERSION = "1.0.0"
    private const val GSON_VERSION = "2.8.8"

    private val libs = listOf (

        // core libs
        "androidx.core:core-ktx:${CORE_VERSION}",
        "androidx.appcompat:appcompat:${APPCOMPAT_VERSION}",
        "androidx.activity:activity-ktx:${APPCOMPAT_VERSION}",
        "androidx.fragment:fragment-ktx:${APPCOMPAT_VERSION}",
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${COROUTINES_VERSION}",
        "androidx.work:work-runtime-ktx:$WORK_MANAGER_VERSION",
        "androidx.startup:startup-runtime:$STARTUP_VERSION",

        // di
        "com.google.dagger:hilt-android:${HILT_VERSION}",
        "androidx.hilt:hilt-work:${HILT_WORK_VERSION}",

        // design libs
        "androidx.constraintlayout:constraintlayout:${CONSTRAINT_VERSION}",
        "com.google.android.material:material:${MATERIAL_VERSION}",

        // lifeccyle libs
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${LIFECYCLE_VERSION}",
        "androidx.lifecycle:lifecycle-extensions:${LIFECYCLE_VERSION}",
        "androidx.lifecycle:lifecycle-livedata-ktx:${LIFECYCLE_VERSION}",

        // gson
        "com.google.code.gson:gson:${GSON_VERSION}",
    )

    private val processors = listOf (
        // di
        "com.google.dagger:hilt-android-compiler:$HILT_VERSION",
        "androidx.hilt:hilt-compiler:$HILT_WORK_VERSION",
    )

    fun DependencyHandler.dependencies() {

        libs.forEach { dependecy ->
            add(IMPLEMENTATION, dependecy)
        }

        processors.forEach { proccessor ->
            add(KAPT, proccessor)
        }
    }
}