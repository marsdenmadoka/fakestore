package com.fakestore.util

import android.app.Activity
import android.content.Intent
import android.view.View
import android.widget.Spinner
import androidx.appcompat.widget.SearchView

/**EXTENSION FUNCTIONS**/

//search ext
inline fun SearchView.onQueryTextChange(crossinline listener: (String) -> Unit) {
    this.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            return true
        }
        override fun onQueryTextChange(newText: String?): Boolean {
            listener(newText.orEmpty())
            /**if(!newText.isNullOrBlank()){
            listener(newText)}*/
            return true
        }

    })
}

fun View.enable(enabled: Boolean) {
    isEnabled = enabled
    alpha = if (enabled) 1f else 0.5f //playing with opacity of our view/button
}

fun View.visible(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.GONE
}

//starting a new activity
fun <A : Activity> Activity.startNewActivity(activity: Class<A>) {
    Intent(this, activity).also {
        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(it)
    }
}

// fun Spinner.setItemSelectedListener(itemSelectedListener: ItemSelectedListener?) {
//    setSpinnerItemSelectedListener(itemSelectedListener)
//}
/**exhaustive*/
val <T> T.exhaustive: T
    get() = this







/**
fun View.showSnackbar(
message: String,
action: (() -> Unit)? = null
//duration: Int = Snackbar.LENGTH_LONG, //  view: View = requireView(),
) {
val snackbar = Snackbar.make(this, message, Snackbar.LENGTH_LONG)
//retry
action?.let {
snackbar.setAction("retry") { it() }
}
snackbar.show()
}
//error et
//fun Fragment.handleApiError(
//    failure: Resource.Error<Throwable>,
//    retry: (() -> Unit)? = null
//) {
//    when {
//         -> requireView().showSnackbar("please check your internet", retry)
//    }.exhaustive
//}
 **/