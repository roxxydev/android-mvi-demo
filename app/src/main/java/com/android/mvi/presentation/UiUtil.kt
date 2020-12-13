package com.android.mvi.presentation

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.android.mvi.R
import com.bumptech.glide.Glide

class UiUtil {

    companion object {

        fun displayToast(context: Context, message: String) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }

        /**
         * Helper method for navigating between fragments in NavigationHost.
         */
        fun navigateTo(navControllerView: View, action: Int, bundle: Bundle?) {
            Navigation
                .findNavController(navControllerView)
                .navigate(action, bundle)
        }

        /**
         * Helper method for displaying and loading image to ImageView using Glide.
         * SwipeRefreshLayout CircularProgressDrawable will be used as placeholder while
         * Material Design progress indicator is not yet supported.
         */
        fun displayImage(context: Context, imgUrl: String?, imageView: ImageView) {
            val circularProgressDrawable = CircularProgressDrawable(context)
            circularProgressDrawable.setColorSchemeColors(
                ContextCompat.getColor(context, R.color.colorPrimary),
                ContextCompat.getColor(context, R.color.colorAccent),
                ContextCompat.getColor(context, R.color.colorPrimaryDark))
            circularProgressDrawable.strokeWidth = 10f
            circularProgressDrawable.centerRadius = 30f
            circularProgressDrawable.start()

            Glide.with(context)
                .asBitmap()
                .load(imgUrl)
                .placeholder(circularProgressDrawable)
                .error(ContextCompat.getDrawable(context, R.drawable.ic_img_broken))
                .fallback(ContextCompat.getDrawable(context, R.drawable.ic_img_fallback))
                .into(imageView)
        }
    }
}