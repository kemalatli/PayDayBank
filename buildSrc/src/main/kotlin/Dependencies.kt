package com.paydaybank.build

object Libs {

    object App{
        val compileSdkVersion = 29
        val buildToolsVersion = "29.0.2"
        val minSdkVersion = 23
        val targetSdkVersion = 29
        val applicationId = "com.paydaybank.android"
    }

    object PluginURLs{
        val jitpack = "https://jitpack.io"
        val sonatype = "https://oss.sonatype.org/content/repositories/snapshots/"
        val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.61"
        val android = "com.android.tools.build:gradle:4.1.0-beta01"
        val butterknife =  "com.jakewharton:butterknife-gradle-plugin:10.2.0"
        val hilt = "com.google.dagger:hilt-android-gradle-plugin:2.28-alpha"
    }

    object Plugins{
        val kapt = "kotlin-kapt"
        val android = "kotlin-android"
        val androidExtensions = "kotlin-android-extensions"
        val application = "com.android.application"
        val library = "com.android.library"
        val butterknife =  "com.jakewharton.butterknife"
    }

    object View{
        val material = "com.google.android.material:material:1.2.0-alpha04"
        val constraintLayout = "androidx.constraintlayout:constraintlayout:2.0.0-beta4"
        val coil = "io.coil-kt:coil:0.9.5"
        val coilSvg = "io.coil-kt:coil-svg:0.9.5"
        val countrySelect = "com.hbb20:ccp:2.4.0"
    }

    object Insetter{
        val version = "0.3.1"
        val core = "dev.chrisbanes:insetter:$version"
        val kotlin = "dev.chrisbanes:insetter-ktx:$version"
        val widgets = "dev.chrisbanes:insetter-widgets:$version"
    }

    object Epoxy {
        private const val version = "3.8.0"
        val epoxy = "com.airbnb.android:epoxy:$version"
        val paging = "com.airbnb.android:epoxy-paging:$version"
        val dataBinding = "com.airbnb.android:epoxy-databinding:$version"
        val processor = "com.airbnb.android:epoxy-processor:$version"
    }

    object Mvrx{
        val core = "com.airbnb.android:mvrx:1.3.0"
    }

    object Logging{
        val timber = "com.jakewharton.timber:timber:4.7.1"
    }

    object Fragment {
        private const val version = "1.2.0-rc05"
        val core = "androidx.fragment:fragment:$version"
        val fragmentKtx = "androidx.fragment:fragment-ktx:$version"

    }

    object Dagger {
        val version = "2.28.2-alpha"
        val hilt = "com.google.dagger:hilt-android:$version"
        val processor = "com.google.dagger:hilt-android-compiler:$version"
        val jetpack = "androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha01"
        val jetpackProcessor = "androidx.hilt:hilt-compiler:1.0.0-alpha01"
    }


    object Nav {
        val nav_version = "2.3.0-rc01"
        val fragment = "androidx.navigation:navigation-fragment-ktx:$nav_version"
        val ktx = "androidx.navigation:navigation-ui-ktx:$nav_version"
    }

    object Retrofit {
        private const val version = "2.9.0"
        val retrofit = "com.squareup.retrofit2:retrofit:$version"
        val retrofit_rxjava_adapter = "com.squareup.retrofit2:adapter-rxjava2:$version"
        val gsonConverter = "com.squareup.retrofit2:converter-gson:$version"
    }

    object OkHttp {
        private const val version = "4.5.0"
        val okhttp = "com.squareup.okhttp3:okhttp:$version"
        val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:$version"
    }

    object Fresco{
        val fresco = "com.facebook.fresco:fresco:2.1.0"
        val fresco_gif = "com.facebook.fresco:animated-gif:2.1.0"
    }

    object Coroutines {
        private const val version = "1.3.7"
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
    }

    object Room{
        private const val version = "2.2.5"
        const val core = "androidx.room:room-runtime:$version"
        const val processor = "androidx.room:room-compiler:$version"
        const val coroutines = "androidx.room:room-ktx:$version"
    }


}