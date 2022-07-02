package com.fakestore.util

import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import javax.xml.transform.ErrorListener

/**EXTENSION FUNCTIONS**/
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

//search ext
inline fun SearchView.onQueryTextChange(crossinline listener:(String)->Unit){
this.setOnQueryTextListener(object:SearchView.OnQueryTextListener{
    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        listener(newText.orEmpty())
        return true
    }

})
}

 //exhaustive
val <T> T.exhaustive: T
get() = this