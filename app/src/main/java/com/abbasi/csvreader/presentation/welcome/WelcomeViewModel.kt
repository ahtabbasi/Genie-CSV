package com.abbasi.csvreader.presentation.welcome

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.abbasi.csvreader.commons.utils.Event

class WelcomeViewModel : ViewModel() {

    private val _selectFileClicked = MutableLiveData<Event<Boolean>>()
    val selectFileClicked: LiveData<Event<Boolean>> = _selectFileClicked

    fun onSelectFileClicked() {
        _selectFileClicked.postValue(Event(true))
    }
}