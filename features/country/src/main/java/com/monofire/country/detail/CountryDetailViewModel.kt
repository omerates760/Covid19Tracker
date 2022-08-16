package com.monofire.country.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.monofire.domain.model.CountryDetailUiData
import com.monofire.domain.usecase.GetCountryDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryDetailViewModel @Inject constructor(
    private val getCountryDetailUseCase: GetCountryDetailUseCase
) : ViewModel() {
    private val _country = MutableStateFlow<CountryDetailUiData?>(null)
    val country = _country.asStateFlow()

    fun getCountryDetail(countryName: String) {
        viewModelScope.launch {
            getCountryDetailUseCase.invoke(countryName)?.collect { response ->
                _country.value = response
            }
        }
    }
}