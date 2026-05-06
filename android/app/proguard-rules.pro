# Keep Kotlinx Serialization metadata for @Serializable data classes.
-keepattributes *Annotation*, InnerClasses
-dontnote kotlinx.serialization.AnnotationsKt
-keep,includedescriptorclasses class com.siedlce.erasmusguide.**$$serializer { *; }
-keepclassmembers class com.siedlce.erasmusguide.** {
    *** Companion;
}
-keepclasseswithmembers class com.siedlce.erasmusguide.** {
    kotlinx.serialization.KSerializer serializer(...);
}

# Hilt / Dagger
-keep class dagger.hilt.** { *; }
-keep class * extends dagger.hilt.android.internal.managers.ApplicationComponentManager { *; }

# Google Maps
-keep class com.google.android.gms.maps.** { *; }
-keep interface com.google.android.gms.maps.** { *; }
