package com.monofire.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.monofire.domain.model.HistoryUiData
import com.monofire.domain.usecase.GetHistoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val getHistoryUseCase: GetHistoryUseCase
) : ViewModel() {

    private val _histories = MutableStateFlow<List<HistoryUiData>>(emptyList())
    val histories = _histories.asStateFlow()


    fun getHistory(countryName: String,day:String) {
        viewModelScope.launch {
            getHistoryUseCase.invoke(countryName, day).collect {
                _histories.value = it
            }
        }
    }

}
