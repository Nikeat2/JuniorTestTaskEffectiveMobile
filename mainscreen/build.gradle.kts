plugins {
    id("com.android.library")
    alias(libs.plugins.kotlin.android)
    id ("kotlin-kapt")
}

android {
    namespace = "com.example.mainscreen"
    compileSdk = 35

    defaultConfig {
        //applicationId = "com.example.mainscreen"
        minSdk = 26
        testOptions.targetSdk = 35
        //versionCode = 1
        //versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
    }

    viewBinding {
        enable = true
    }
}

dependencies {
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.10.0")
    implementation(project(":domain"))
    implementation (libs.glide)
    implementation (libs.androidx.fragment.ktx)
    implementation(project (":data"))

    //Dagger
    implementation (libs.dagger)
    kapt (libs.dagger.compiler)
    implementation (libs.dagger.android)
    implementation (libs.dagger.android.support)
    kapt (libs.dagger.android.processor)

    //Room
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.common)

    //noinspection KaptUsageInsteadOfKsp
    kapt(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)


    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}