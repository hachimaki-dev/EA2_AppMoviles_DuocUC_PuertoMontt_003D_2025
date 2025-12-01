plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.duocappmoviles003d"
    compileSdk = 36   // <--- CORREGIDO

    defaultConfig {
        applicationId = "com.example.duocappmoviles003d"
        minSdk = 27
        targetSdk = 36   // <--- CORREGIDO
        versionCode = 1
        versionName = "1.0"

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
        sourceCompatibility = JavaVersion.VERSION_17   // <--- RECOMENDADO PARA COMPOSE
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"   // <--- RECOMENDADO PARA LIBRERÍAS MODERNAS
    }

    buildFeatures {
        compose = true
    }
}

dependencies {

    // ---------- SUPABASE ----------
    implementation("io.github.jan-tennert.supabase:gotrue-kt:2.5.1")
    implementation("io.github.jan-tennert.supabase:postgrest-kt:2.5.1")

    // ---------- RETROFIT ----------
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

    // ---------- COROUTINES ----------
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.0")

    // ---------- ANDROIDX & COMPOSE ----------
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)

    // Navegación Compose
    implementation("androidx.navigation:navigation-compose:2.7.7")

    // Tests
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}
