package com.samet.orderapp.util

import androidx.fragment.app.Fragment
import com.samet.orderapp.R


fun Fragment.replace(fragment: Fragment){
    requireActivity().supportFragmentManager.beginTransaction()
        .replace(R.id.hostFragment,fragment)
        .addToBackStack(null)
        .commit()
}