package dependencies

object Dependencies {
    const val desugar = "com.android.tools:desugar_jdk_libs:2.0.4"
    const val androidx_startup = "androidx.startup:startup-runtime:1.1.1"
    const val androidx_lifecycle_runtime_ktx = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
    const val androidx_viewpager2= "androidx.viewpager2:viewpager2:1.0.0"
    const val androidx_lifecycle_livedata_ktx = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
    const val androidx_lifecycle_viewmodel_ktx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val androidx_core_ktx = "androidx.core:core-ktx:1.12.0"
    const val androidx_browser = "androidx.browser:browser:1.7.0"
    const val androidx_recyclerview = "androidx.recyclerview:recyclerview:1.3.2"
    const val androidx_navigation_fragment_ktx = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val androidx_navigation_ui = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    const val androidx_appcompat = "androidx.appcompat:appcompat:1.6.1"
    const val androidx_work_runtime = "androidx.work:work-runtime:${Versions.work}"
    const val androidx_exinterface = "androidx.exifinterface:exifinterface:1.3.7"
    const val androidx_multidex = "androidx.multidex:multidex:2.0.1"
    const val androidx_preference_ktx = "androidx.preference:preference-ktx:1.2.1"
    const val androidx_fragment_ktx = "androidx.fragment:fragment-ktx:${Versions.androidx_fragment}"
    const val android_material = "com.google.android.material:material:1.11.0"
    const val android_flexbox = "com.google.android.flexbox:flexbox:3.0.0"
    const val play_services_maps = "com.google.android.gms:play-services-maps:18.2.0"
    const val play_services_location = "com.google.android.gms:play-services-location:20.0.0" // Check if map screens still work when upgrading and location works as expected https://github.com/getodk/collect/issues/6027, especially after moving to FusedLocationProviderClient.
    const val play_services_oss_licenses = "com.google.android.gms:play-services-oss-licenses:17.0.1"
    const val mapbox_android_sdk = "com.mapbox.maps:android:10.16.4"
    const val osmdroid = "org.osmdroid:osmdroid-android:6.1.18"
    const val squareup_okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp3}"
    const val squareup_okhttp_tls = "com.squareup.okhttp3:okhttp-tls:${Versions.okhttp3}"
    const val burgstaller_okhttp_digest = "io.github.rburgst:okhttp-digest:3.1.0"
    const val persian_joda_time = "com.github.mohamadian:persianjodatime:1.2"
    const val myanmar_calendar = "com.github.chanmratekoko:myanmar-calendar:1.0.6.RC3" // Check if https://github.com/chanmratekoko/mmcalendar/issues/4 no longer takes place before upgrading
    const val bikram_sambat = "bikramsambat:bikram-sambat:1.1.0"
    const val danlew_android_joda = "net.danlew:android.joda:2.12.7"
    const val rarepebble_colorpicker = "com.github.martin-stone:hsv-alpha-color-picker-android:3.1.0"
    const val commons_io = "commons-io:commons-io:2.5" // Commons 2.6+ introduce java.nio usage that we can't access until our minSdkVersion >= 26 (https://developer.android.com/reference/java/io/File#toPath())
    const val opencsv = "com.opencsv:opencsv:5.9"
    const val javarosa_online = "org.getodk:javarosa:5.0.0-SNAPSHOT"
    const val javarosa_local = "org.getodk:javarosa:local"
    const val javarosa = javarosa_online
    const val karumi_dexter = "com.karumi:dexter:6.2.3"
    const val zxing_android_embedded = "com.journeyapps:zxing-android-embedded:4.3.0"
    const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    const val dagger_compiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
    const val dagger_android = "com.google.dagger:dagger-android:${Versions.dagger}"
    const val dagger_android_processor = "com.google.dagger:dagger-android-processor:${Versions.dagger}"
    const val kotlinx_coroutines_android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3"
    const val armen101_audio_recorder_view = "com.github.Armen101:AudioRecordView:1.0.5"
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val glide_compiler = "com.github.bumptech.glide:compiler:${Versions.glide}"
    const val caverock_androidsvg = "com.caverock:androidsvg-aar:1.4"
    const val mp4parser_muxer = "org.mp4parser:muxer:1.9.41" // Check if https://github.com/getodk/collect/issues/5323 no longer takes place before upgrading
    const val kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib:1.9.22"
    const val gson = "com.google.code.gson:gson:2.10.1"
    const val firebase_analytics = "com.google.firebase:firebase-analytics:21.5.0"
    const val firebase_crashlytics = "com.google.firebase:firebase-crashlytics:18.6.1"
    const val leakcanary = "com.squareup.leakcanary:leakcanary-android:2.13"
    const val timber = "com.jakewharton.timber:timber:5.0.1"
    const val slf4j_api = "org.slf4j:slf4j-api:2.0.12"
    const val slf4j_timber = "com.arcao:slf4j-timber:3.1@aar"
    const val emoji_java = "com.vdurmont:emoji-java:5.1.1"
    const val json_schema_validator = "com.networknt:json-schema-validator:1.3.2"
    const val splashscreen = "androidx.core:core-splashscreen:1.0.1"
    const val camerax_core = "androidx.camera:camera-core:${Versions.camerax}"
    const val camerax_view = "androidx.camera:camera-view:${Versions.camerax}"
    const val camerax_lifecycle = "androidx.camera:camera-lifecycle:${Versions.camerax}"
    const val camerax_camera2 = "androidx.camera:camera-camera2:${Versions.camerax}"
    const val camerax_video = "androidx.camera:camera-video:${Versions.camerax}"
    const val jsoup = "org.jsoup:jsoup:1.17.2"

    // Test dependencies
    const val junit = "junit:junit:4.13.2"
    const val mockito_android = "org.mockito:mockito-android:5.10.0"
    const val mockito_inline = "org.mockito:mockito-inline:5.2.0"
    const val mockito_kotlin = "org.mockito.kotlin:mockito-kotlin:5.2.1"
    const val androidx_fragment_testing = "androidx.fragment:fragment-testing:${Versions.androidx_fragment}"
    const val androidx_arch_core_testing = "androidx.arch.core:core-testing:2.2.0"
    const val androidx_work_testing = "androidx.work:work-testing:${Versions.work}"
    const val androidx_test_core_ktx = "androidx.test:core-ktx:1.5.0"
    const val androidx_test_rules = "androidx.test:rules:1.5.0"
    const val androidx_test_espresso_contrib = "androidx.test.espresso:espresso-contrib:${Versions.espresso}"
    const val androidx_test_espresso_core = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    const val androidx_test_espresso_intents = "androidx.test.espresso:espresso-intents:${Versions.espresso}"
    const val androidx_test_ext_junit = "androidx.test.ext:junit:1.1.5"
    const val okhttp3_mockwebserver = "com.squareup.okhttp3:mockwebserver:${Versions.okhttp3}"
    const val hamcrest = "org.hamcrest:hamcrest:2.2"
    const val robolectric = "org.robolectric:robolectric:${Versions.robolectric}"
    const val robolectric_shadows_multidex = "org.robolectric:shadows-multidex:${Versions.robolectric}"
    const val uiautomator = "androidx.test.uiautomator:uiautomator:2.2.0"
}
