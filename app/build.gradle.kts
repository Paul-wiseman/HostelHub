plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.safe.args)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.wiseman.hostelworldassessmentapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.wiseman.hostelworldassessmentapp"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            buildConfigField("String", "BASE_URL", "\"https://gist.githubusercontent.com/PedroTrabulo-Hostelworld/\"")
        }

        release {
            buildConfigField("String", "BASE_URL", "\"https://gist.githubusercontent.com/PedroTrabulo-Hostelworld/\"")
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
        freeCompilerArgs += "-Xcontext-receivers"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    buildFeatures {
        buildConfig = true
        viewBinding = true
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    testImplementation(libs.junit.jupiter)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
    implementation(libs.retrofit)
    implementation(libs.retrofit.moshi.converter)
    implementation(libs.logging.interceptor)
    implementation(libs.kotlin.either.core)
    implementation(libs.navigation.component.fragment)
    implementation(libs.navigation.component.ui)
    implementation(libs.rxkotlin)
    implementation(libs.retrofit.rxjava.adapter)
    implementation(libs.rxkotlin.android)
    implementation(libs.lottie.animation)
    implementation(libs.coil)
    implementation(libs.kotlin.serialization.json)
    implementation(libs.coil.network)
    implementation(libs.viewpager.indicator)
    implementation(kotlin("reflect"))
    testImplementation(libs.mockk)
}