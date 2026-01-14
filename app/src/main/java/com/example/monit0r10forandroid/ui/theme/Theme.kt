package com.example.monit0r10forandroid.ui.theme

import android.R.attr.color
import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.darkColorScheme

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)
val Charcoal = Color(0xFF36454F)
val SlateGray = Color(0xFF708090)
val SoftPink = Color(0xFFFFB6C1)
val MintGreen = Color(0xFF98FF98)
val RoyalBlue = Color(0xFF4169E1)
val SunsetOrange = Color(0xFFFD5E53)



private val LightColors = lightColorScheme(
    primary = RoyalBlue,
    secondary = MintGreen,
    tertiary = SoftPink,
    background = Charcoal,
    surface = SlateGray,
    error = SunsetOrange,

)



@Composable
fun Monit0r10ForAndroidTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> lightColorScheme()
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
