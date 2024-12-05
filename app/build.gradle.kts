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

        testInstrumentationRunner = "com.wiseman.hostelworldassessmentapp.CustomTestRunner"
    }

    buildTypes {
        debug {
            buildConfigField(
                "String",
                "BASE_URL",
                "\"https://gist.githubusercontent.com/PedroTrabulo-Hostelworld/\""
            )
        }

        release {
            buildConfigField(
                "String",
                "BASE_URL",
                "\"https://gist.githubusercontent.com/PedroTrabulo-Hostelworld/\""
            )
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
            resources {
                excludes += "/META-INF/{AL2.0,LGPL2.1}"
                merges += "META-INF/LICENSE.md"
                merges += "META-INF/LICENSE-notice.md"
            }
        }
    }


    testOptions {
        unitTests.isIncludeAndroidResources = true
        managedDevices {
            devices {
                create<com.android.build.api.dsl.ManagedVirtualDevice>("nexusOneApi30") {
                    device = "Nexus One"
                    apiLevel = 30
                    systemImageSource = "aosp-atd"
                }
            }
        }
    }

    namespace = "com.wiseman.hostelworldassessmentapp"
    testNamespace = "com.wiseman.hostelworldassessmentapp.test"



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
    implementation(libs.androidx.rules)
    testImplementation(libs.junit)
    testImplementation(libs.junit.jupiter)
    androidTestImplementation(libs.junit.jupiter)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(libs.hilt.android)
    implementation(libs.hilt.android.test)
    kspAndroidTest(libs.hilt.android.compiler)
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
    testImplementation(libs.turbine)
    testImplementation(libs.coroutine.test)
    androidTestImplementation(libs.mockk)

    androidTestImplementation(libs.fragment.test)

    androidTestImplementation("androidx.test:core:1.4.0") {
        exclude(module = "androidx.test.ext:junit")
    }

    androidTestImplementation(libs.androidx.test)
    androidTestImplementation(libs.androidx.junit.v113)
    androidTestImplementation(libs.androidx.junit.ktx)
    androidTestImplementation(libs.androidx.truth)
    debugImplementation(libs.androidx.monitor)
    androidTestImplementation(libs.androidx.espresso.contrib)
    androidTestImplementation(libs.androidx.espresso.accessibility)
}