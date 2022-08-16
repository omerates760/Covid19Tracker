package com.monofire.country

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.monofire.domain.model.CountryUiData
import com.monofire.domain.usecase.GetCountriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryViewModel @Inject constructor(
    private val getCountriesUseCase: GetCountriesUseCase
) : ViewModel() {
    private val _countries = MutableStateFlow<List<CountryUiData>>(emptyList())
    val countries = _countries.asStateFlow()

    init {
        getCountries()
    }

    private fun getCountries() {
        viewModelScope.launch {
            getCountriesUseCase.invoke().collect { response ->
                handleResponse(response)
            }
        }
    }

    private fun handleResponse(response: List<CountryUiData>) {
        if (_countries.value.isEmpty().not()) return
        _countries.value = response
    }
}