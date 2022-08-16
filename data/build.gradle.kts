plugins {
    id(Plugins.androidLib)
    id(Plugins.kotlinAndroid)
    id(Plugins.kotlinKapt)
    id(Plugins.hiltAndroid)

}

android {
    compileSdk = 32

    defaultConfig {
        minSdk = 21
        targetSdk = 32

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "BASE_URL", "\"https://covid-193.p.rapidapi.com/\"")
        }
        debug {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "BASE_URL", "\"https://covid-193.p.rapidapi.com/\"")
            buildConfigField(
                "String",
                "API_KEY",
                "\"0670273133msheda077b008ca0d2p140ad2jsn2f66321fba44\""
            )
            buildConfigField("String", "API_HOST", "\"covid-193.p.rapidapi.com\"")

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

    implementation(Dependencies.coreKtx)
    implementation(Dependencies.appCompat)
    implementation(Dependencies.room)
    annotationProcessor(Dependencies.roomCompiler)
    kapt(Dependencies.roomCompiler)
    implementation(Dependencies.roomKtx)

    implementation(Dependencies.hiltAndroid)
    kapt(Dependencies.hiltAndroidKapt)

    implementation(Dependencies.retrofit)
    implementation(Dependencies.retrofitGsonConverter)

    implementation(Dependencies.okHttpInterceptor)

    implementation(Dependencies.workManager)
    implementation(Dependencies.hiltWorkManager)
    kapt(Dependencies.hiltCompiler)

}
kapt {
    correctErrorTypes = true
}