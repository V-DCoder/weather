package com.apps.demo.apixuweather.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Singleton


class ViewModelFactory @Inject constructor(var forecastViewModel: TodaysForecastViewModel) : ViewModelProvider.Factory {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val viewModel: ViewModel
        if (modelClass == TodaysForecastViewModel::class.java) {
            viewModel = forecastViewModel
        } else {
            throw RuntimeException("unsupported view model class: $modelClass this factory is designed to work with 1 class only")
        }

        return viewModel as T
    }


}