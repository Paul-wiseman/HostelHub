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
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import coil3.load
import coil3.request.crossfade
import com.wiseman.hostelworldassessmentapp.R
import com.wiseman.hostelworldassessmentapp.domain.model.LowestPricePerNight
import com.wiseman.hostelworldassessmentapp.domain.model.OverallRating
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.text.DecimalFormat

fun animate(view: View, pos: Int) {
    view.animate().cancel()
    view.translationY = 100F
    view.alpha = 0F
    view.animate().alpha(1.0f).translationY(0F).setDuration(300)
        .startDelay = (pos * 100).toLong()
}

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

fun ImageView.loadImage(imageUrl: String) {
    load(imageUrl) {
        crossfade(true)
    }
}

fun getCurrencySymbolFromCode(currencyCode: String): String {
    return try {
        val currency = Currency.getInstance(currencyCode)
        currency.symbol
    } catch (e: IllegalArgumentException) {
        currencyCode
    }
}

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

fun mapValueToScale(value: Int): String {
    val rating = if (value in 1..100) {
        ((value - 1) * 9.0 / 99.0) + 1
    } else {
        value.toDouble()
    }
    val decimalFormat = DecimalFormat("#.##")
    return decimalFormat.format(rating)
}

fun formatPrice(price: LowestPricePerNight): String {
    return String.format(
        "%s%s",
        getCurrencySymbolFromCode(price.currency),
        price.value
    )
}

fun formatRating(rating: OverallRating): String {
    return String.format(
        "%s (%s)",
        mapValueToScale(rating.overall),
        rating.numberOfRatings
    )
}



