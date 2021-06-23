package com.abbasi.csvreader.presentation.preview

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abbasi.csvreader.commons.utils.Resource
import com.abbasi.csvreader.domain.FileRepository
import com.abbasi.csvreader.domain.ParserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PreviewViewModel @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val parserRepository: ParserRepository,
    private val fileRepository: FileRepository
) : ViewModel() {

    private val _parsedData = MutableLiveData<Resource<List<List<String>>>>()
    val parsedData: LiveData<Resource<List<List<String>>>> = _parsedData


    fun parse(uriText: String) {
        viewModelScope.launch(dispatcher) {

            _parsedData.postValue(Resource.Loading())

            val uri = Uri.parse(uriText)
            if (uri == null) {
                _parsedData.postValue(Resource.Invalid("Unable to load CSV"))
                return@launch
            }

            when (val resource = fileRepository.readCsv(uri)) {
                is Resource.Valid ->
                    _parsedData.postValue(Resource.Valid(parserRepository.parseCsv(resource.data)))
                is Resource.Invalid ->
                    _parsedData.postValue(Resource.Invalid(resource.message))
            }
        }
    }
}