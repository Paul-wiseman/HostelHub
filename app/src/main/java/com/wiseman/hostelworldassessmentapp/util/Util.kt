package com.wiseman.hostelworldassessmentapp.util

import android.content.Context
import android.icu.util.Currency
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import coil3.load
import coil3.request.crossfade
import com.wiseman.hostelworldassessmentapp.R
import com.wiseman.hostelworldassessmentapp.domain.model.LowestPricePerNight
import com.wiseman.hostelworldassessmentapp.domain.model.OverallRating
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.text.DecimalFormat

/**
 * Animates a view with a fade-in and slide-up effect.
 *
 * This function cancels any existing animations on the view, sets its initial
 * state to be invisible and translated downwards, and then starts an animation
 * that fades it in and slides it up to its original position.
 *
 * @param view The view to animate.
 * @param pos The position of the view in a list (used to stagger the animation).
 */

fun animate(view: View, pos: Int) {
    view.animate().cancel()
    view.translationY = 100F
    view.alpha = 0F
    view.animate().alpha(1.0f).translationY(0F).setDuration(300)
        .startDelay = (pos * 100).toLong()
}

/**
 * Collects values from a flow within a fragment's lifecycle.
 *
 * This function launches a coroutine that collects values from the flow and
 * executes the provided `onCollect` lambda function with each collected value.
 * The collection is automatically stopped and restarted when the fragment's
 * lifecycle state changes between STARTED and STOPPED.
 *
 * @param T The type of values emitted by the flow.
 * @param onCollect The lambda function to execute with each collected value.
 * @receiver The flow to collect from.
 */

context(Fragment)
inline fun <T> Flow<T>.collectInFragment(
    crossinline onCollect: (T) -> Unit
) = lifecycleScope.launch {
    viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
        collectLatest { data: T ->
            onCollect(data)
        }
    }
}

/**
 * Collects values from a flow within an activity's lifecycle.
 *
 * This function launches a coroutine that collects values from the flow and
 * executes the provided `onCollect` lambda function with each collected value.
 * The collection is automatically stopped and restarted when the activity's
 * lifecycle state changes between STARTED and STOPPED.
 *
 * @param T The type of values emitted by the flow.
 * @param onCollect The lambda function to execute with each collected value.
 * @receiver The flow to collect from.
 */

context(AppCompatActivity)
inline fun <T> Flow<T>.collectInActivity(
    crossinline onCollect: (T) -> Unit
) = lifecycleScope.launch {
    repeatOnLifecycle(Lifecycle.State.STARTED) {
        collectLatest { data: T ->
            onCollect(data)
        }
    }
}


/**
 * Loads an image into an ImageView with crossfade animation.
 *
 * This function loads an image from the specified URL into the ImageView using
 * Coil, applying a crossfade animation for a smooth transition.
 *
 * @param imageUrl The URL of the image to load.
 * @receiver The ImageView to load the image into.
 */
fun ImageView.loadImage(imageUrl: String) {
    load(imageUrl) {
        crossfade(true)
    }
}

/**
 * Gets the currency symbol for a given currency code.
 *
 * This function attempts to retrieve the currency symbol for the provided
 * currency code using the `Currency` class. If the currency code is invalid,
 * it returns the original currency code as a fallback.
 *
 * @param currencyCode The currency code (e.g., "USD", "EUR").
 * @return The currency symbol (e.g., "$", "€") or the original currency code
 *         if the symbol cannot be determined.
 */

fun getCurrencySymbolFromCode(currencyCode: String): String {
    return try {
        val currency = Currency.getInstance(currencyCode)
        currency.symbol
    } catch (e: IllegalArgumentException) {
        currencyCode
    }
}

/**
 * Shows an error dialog with a title and message.
 *
 * This function creates and displays an AlertDialog with an error theme,
 * showing the provided title and message. The dialog has an "OK" button
 * to dismiss it.
 *
 * @param context The context used to create the dialog.
 * @param title The title of the dialog.
 * @param message The message to display in the dialog.
 */
fun showErrorDialog(context: Context, title: String, message: String) {
    val dialog = AlertDialog.Builder(context, R.style.ErrorDialogTheme)
        .setTitle(title)
        .setMessage(message)
        .setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
        }
        .create()

    dialog.show()

    dialog.getButton(AlertDialog.BUTTON_POSITIVE)
        .setTextColor(ContextCompat.getColor(context, R.color.error_dialog_button_color))
}


/**
 * Maps a value to a scale between 1 and 10.
 *
 * This function maps an integer value to a scale between 1 and 10, using a
 * linear transformation. If the value is outside the range of 1 to 100, it
 * is treated as a double and returned as is.
 *
 * @param value The integer value to map.
 * @return The mapped value as a string, formatted to two decimal places.
 */
fun mapValueToScale(value: Int): String {
    val rating = if (value in 1..100) {
        ((value - 1) * 9.0 / 99.0) + 1
    } else {
        value.toDouble()
    }
    val decimalFormat = DecimalFormat("#.##")
    return decimalFormat.format(rating)
}


/**
 * Formats a price with its currency symbol.
 *
 * This function formats a [LowestPricePerNight] object into a string
 * representation that includes the currency symbol and the price value.
 *
 * @param price The [LowestPricePerNight] object to format.
 * @return The formatted price string (e.g., "$100").
 */
fun formatPrice(price: LowestPricePerNight): String {
    return String.format(
        "%s%s",
        getCurrencySymbolFromCode(price.currency),
        price.value
    )
}


/**
 * Formats a rating with its overall value and number of ratings.
 *
 * This function formats an [OverallRating] object into a string
 * representation that includes the overall rating value (mapped to a scale
 * between 1 and 10) and the number of ratings.
 *
 * @param rating The [OverallRating] object to format.
 * @return The formatted rating string (e.g., "8.5 (1234)").
 */
fun formatRating(rating: OverallRating): String {
    return String.format(
        "%s (%s)",
        mapValueToScale(rating.overall),
        rating.numberOfRatings
    )
}



