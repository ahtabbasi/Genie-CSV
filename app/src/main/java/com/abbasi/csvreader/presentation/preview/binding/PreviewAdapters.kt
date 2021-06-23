package com.abbasi.csvreader.presentation.preview.binding

import android.view.View
import androidx.databinding.BindingAdapter
import com.abbasi.csvreader.commons.utils.Resource
import com.abbasi.csvreader.commons.utils.gone
import com.abbasi.csvreader.commons.utils.visible


@BindingAdapter("app:visibleOnLoading")
fun visibleOnLoading(
    view: View,
    resource: Resource<Any>?
) {
    when (resource) {
        is Resource.Invalid -> view.gone()
        is Resource.Loading -> view.visible()
        is Resource.Valid -> view.gone()
    }
}


@BindingAdapter("app:hideOnLoading")
fun hideOnLoading(
    view: View,
    resource: Resource<Any>?
) {
    when (resource) {
        is Resource.Invalid -> view.gone()
        is Resource.Loading -> view.gone()
        is Resource.Valid -> view.visible()
    }
}