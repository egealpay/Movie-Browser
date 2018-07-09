# Retrofit2
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions
-keepclassmembers class ** {
   @retrofit2.http.Body <methods>;
   @retrofit2.http.GET <methods>;
   @retrofit2.http.POST <methods>;
   @retrofit2.http.Query <methods>;
}
-dontwarn okhttp3.**
-dontwarn okio.**
-dontwarn com.squareup.okhttp.** # Fresco

# NetworkBus 2.0
-keepclassmembers class ** {
   @com.monitise.mea.android.network.bus.OnResponse <methods>;
   @com.monitise.mea.android.network.bus.OnError <methods>;
}

# Excluded Network dependency
-dontwarn !com.monitise.mea.android.network.bus.**
-dontwarn !com.monitise.mea.android.network.common.**
-dontwarn com.monitise.mea.android.network.**

# Fragment Args
-keep class com.hannesdorfmann.fragmentargs.** { *; }

# Fresco
# Keep our interfaces so they can be used by other ProGuard rules.
# See http://sourceforge.net/p/proguard/bugs/466/
-keep,allowobfuscation @interface com.facebook.common.internal.DoNotStrip

# Do not strip any method/class that is annotated with @DoNotStrip
-keep @com.facebook.common.internal.DoNotStrip class *
-keepclassmembers class * {
   @com.facebook.common.internal.DoNotStrip *;
}
# Keep native methods
-keepclassmembers class * {
   native <methods>;
}

-dontwarn javax.annotation.**
