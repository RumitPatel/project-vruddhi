package com.project.vruddhi.extensions

import androidx.fragment.app.Fragment
import com.project.vruddhi.activities.MainActivity

/**
 * Extensions for fragment
 */

///////////////////////////////////////////////////////////////////////////////////////

fun Fragment.setTitle(title : String) {
    (activity as MainActivity).getBindings()?.layToolbar?.txtTitle?.text = title
}