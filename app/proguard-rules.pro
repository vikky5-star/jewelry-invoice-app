# iText PDF
-keep class com.itextpdf.** { *; }
-dontwarn com.itextpdf.**

# Room
-keep class * extends androidx.room.RoomDatabase
-keep @androidx.room.Entity class *

# Kotlin
-keepclassmembers class ** {
    *** Companion;
}
-keepclasseswithmembernames class ** {
    native <methods>;
}