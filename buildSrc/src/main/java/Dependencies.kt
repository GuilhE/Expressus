@file:Suppress("unused")

object SDK {
    @Suppress("MemberVisibilityCanBePrivate")
    const val compile = 31
    const val target = compile
    const val min = 24
}

object Versions {

    object JetBrains {
        const val kotlin = "1.6.10"
        const val kotlinxSerialization = "1.3.2"
        const val kotlinxCoroutines = "1.6.1-native-mt"
        const val kotlinxAtomicFu = "0.17.2"

        object Compose {
            const val desktop = "1.0.1"
        }
    }

    object Multiplatform {
        const val kermit = "1.1.2"
        const val koin = "3.2.0"
        const val multiplatformSettings = "0.8.1"
        const val mokoMvvm = "0.13.0"
        const val orbitMviCore = "4.3.2"
    }

    object Android {

        object Orbit {
            const val compose = "4.3.2"
        }

        object Core {
            const val core = "1.6.0"
            const val material = "1.7.0-alpha01"
            const val lifecycleKtx = "2.5.0-rc01"
        }

        object Compose {
            const val core = "1.2.0-alpha07"
            const val compiler = "1.2.0-alpha07"
            const val activity = "1.5.0-alpha01"
        }
    }

    object Gradle {
        const val dependencyUpdate = "0.42.0"
        const val androidTools = "7.2.0"
    }
}

object Libs {

    object Multiplatform {
        const val kermit = "co.touchlab:kermit:${Versions.Multiplatform.kermit}"
        const val mokoMvvm = "dev.icerock.moko:mvvm-core:${Versions.Multiplatform.mokoMvvm}"
        const val orbitMviCore = "org.orbit-mvi:orbit-core:${Versions.Multiplatform.orbitMviCore}"
        const val multiplatformSettings = "com.russhwolf:multiplatform-settings:${Versions.Multiplatform.multiplatformSettings}"
    }

    object Android {
        object Orbit {
            const val compose = "org.orbit-mvi:orbit-compose:${Versions.Android.Orbit.compose}"
        }

        object Core {
            const val material = "com.google.android.material:material:${Versions.Android.Core.material}"
        }

        object Lifecycle {
            const val lifecycleRuntimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.Android.Core.lifecycleKtx}"
        }

        object Compose {
            const val activity = "androidx.activity:activity-compose:${Versions.Android.Compose.activity}"
            const val material = "androidx.compose.material:material:${Versions.Android.Compose.core}"
            const val ui = "androidx.compose.ui:ui:${Versions.Android.Compose.core}"
            const val uiTooling = "androidx.compose.ui:ui-tooling:${Versions.Android.Compose.core}"
            const val uiToolingPreview = "androidx.compose.ui:ui-tooling-preview:${Versions.Android.Compose.core}"
            const val runtime = "androidx.compose.runtime:runtime:${Versions.Android.Compose.core}"
            const val foundationLayout = "androidx.compose.foundation:foundation-layout:${Versions.Android.Compose.core}"
        }
    }

    object JetBrains {
        const val serializationJson = "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.JetBrains.kotlinxSerialization}"
        const val atomicFu = "org.jetbrains.kotlinx:atomicfu:${Versions.JetBrains.kotlinxAtomicFu}"
        const val kotlinxCoroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.JetBrains.kotlinxCoroutines}"
    }

    object Koin {
        const val core = "io.insert-koin:koin-core:${Versions.Multiplatform.koin}"
        const val android = "io.insert-koin:koin-android:${Versions.Multiplatform.koin}"
    }

    object Gradle {
        const val dependencyUpdate = "com.github.ben-manes.versions"
        const val androidTools = "com.android.tools.build:gradle:${Versions.Gradle.androidTools}"
        const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.JetBrains.kotlin}"
        const val kotlinSerialization = "org.jetbrains.kotlin:kotlin-serialization:${Versions.JetBrains.kotlin}"
    }
}
