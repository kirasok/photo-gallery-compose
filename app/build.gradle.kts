import org.jetbrains.kotlin.gradle.internal.ensureParentDirsCreated
import java.util.Base64

plugins {
  alias(libs.plugins.androidApplication)
  alias(libs.plugins.jetbrainsKotlinAndroid)
  alias(libs.plugins.ksp)
  alias(libs.plugins.hilt)
}

android {
  namespace = "io.github.kirasok.photogallerycompose"
  compileSdk = 34

  defaultConfig {
    applicationId = "io.github.kirasok.photogallerycompose"
    minSdk = 24
    targetSdk = 34
    versionCode = System.getenv("VERSION_CODE")?.toIntOrNull() ?: 1
    versionName = System.getenv("VERSION") ?: "0.0.0"

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    vectorDrawables {
      useSupportLibrary = true
    }
  }

  signingConfigs {
    create("release") {
      val keystore = File(projectDir, System.getenv("KEYSTORE_PATH") ?: "keystore.keystore").apply {
        ensureParentDirsCreated()
        createNewFile()
        val base64: String = System.getenv("KEYSTORE")?.replace("\n", "")
          ?: "" // Base64 doesn't consider string valid if it has \n character
        writeBytes(Base64.getDecoder().decode(base64))
      }
      storeFile = keystore
      storePassword = System.getenv("KEYSTORE_PASSWORD") ?: ""
      keyAlias = System.getenv("KEYSTORE_ALIAS") ?: ""
      keyPassword = System.getenv("KEYSTORE_ALIAS_PASSWORD") ?: ""
    }
  }

  buildTypes {
    release {
      isMinifyEnabled = true
      signingConfig = signingConfigs.getByName("release")
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
  }
  kotlinOptions {
    jvmTarget = JavaVersion.VERSION_17.toString()
  }
  buildFeatures {
    compose = true
    buildConfig = true
  }
  composeOptions {
    kotlinCompilerExtensionVersion = "1.5.11"
  }
  packaging {
    resources {
      excludes += "/META-INF/{AL2.0,LGPL2.1}"
    }
  }

  buildTypes.forEach {
    it.buildConfigField("String", "API_KEY", System.getenv("PHOTO_API_KEY").toString())
  }
}

dependencies {

  implementation(libs.androidx.core.ktx)
  implementation(libs.androidx.lifecycle.runtime.ktx)
  implementation(libs.androidx.activity.compose)
  implementation(platform(libs.androidx.compose.bom))
  implementation(libs.androidx.ui)
  implementation(libs.androidx.ui.graphics)
  implementation(libs.androidx.ui.tooling.preview)
  implementation(libs.androidx.material3)
  implementation(libs.hilt.android)
  implementation(libs.converter.gson)
  implementation(libs.androidx.paging.runtime)
  implementation(libs.androidx.paging.compose)
  implementation(libs.coil.compose)
  ksp(libs.hilt.compiler)
  implementation(libs.androidx.lifecycle.viewmodel.compose)
  implementation(libs.androidx.navigation.compose)
  implementation(libs.androidx.material.icons.extended)
  implementation(libs.androidx.hilt.navigation.compose)
  implementation(libs.androidx.room.runtime)
  ksp(libs.androidx.room.compiler)
  implementation(libs.androidx.room.ktx)
  implementation(libs.retrofit)
  implementation(libs.coil)
  testImplementation(libs.junit)
  androidTestImplementation(libs.androidx.junit)
  androidTestImplementation(libs.androidx.espresso.core)
  androidTestImplementation(platform(libs.androidx.compose.bom))
  androidTestImplementation(libs.androidx.ui.test.junit4)
  debugImplementation(libs.androidx.ui.tooling)
  debugImplementation(libs.androidx.ui.test.manifest)
}