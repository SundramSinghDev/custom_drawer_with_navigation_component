package com.magentotwo.navigation_drawer_example

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatTextView
import androidx.navigation.findNavController

class SideDrawerStaticMenus @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.navigation_drawer_menu, this, true)

        // Set click listeners for navigation buttons
        findViewById<AppCompatTextView>(R.id.homeNavigation).setOnClickListener {
            navigateToHome()
        }

        findViewById<AppCompatTextView>(R.id.galleryNavigation).setOnClickListener {
            navigateToProfile()
        }

        findViewById<AppCompatTextView>(R.id.slideShowNavigation).setOnClickListener {
            navigateToSlideShow()
        }
    }

    private fun navigateToHome() {
        // Use Navigation Component to navigate to the Home destination
        (context as MainActivity).findNavController(R.id.nav_host_fragment_content_main).navigate(R.id.nav_home)
        (context as MainActivity).onGetDrawerLayout().closeDrawers()
    }

    private fun navigateToProfile() {
        // Use Navigation Component to navigate to the Profile destination
        (context as MainActivity).findNavController(R.id.nav_host_fragment_content_main).navigate(R.id.nav_gallery)
        (context as MainActivity).onGetDrawerLayout().closeDrawers()
    }

    private fun navigateToSlideShow() {
        // Use Navigation Component to navigate to the Profile destination
        (context as MainActivity).findNavController(R.id.nav_host_fragment_content_main).navigate(R.id.nav_slideshow)
        (context as MainActivity).onGetDrawerLayout().closeDrawers()
    }
}
