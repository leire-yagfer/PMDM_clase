plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)

}

android {
    namespace = "com.example.recyclersuperheroes"
    compileSdk = 35

    buildFeatures{
        viewBinding=true
    }
    defaultConfig {
        applicationId = "com.example.recyclersuperheroes"
        minSdk = 24
        targetSdk = 35
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation("androidx.recyclerview:recyclerview:1.3.2")
    /* LIBRERIAS GLIDE, aunque la documentación oficial nos
  * marca la sentencia sin parentesis la sintaxis de esta versión de gradle es necesario
  * parentesis y entre comillas dobles*/
    implementation ("com.github.bumptech.glide:glide:4.16.0")

    /* Comando que necesita instalar plugin ksp y
    sustituye a annotation que ya no se usa en gradle kotlin
    ver documentación
    * https://kotlinlang.org/docs/ksp-quickstart.html#add-a-processor  */

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

}