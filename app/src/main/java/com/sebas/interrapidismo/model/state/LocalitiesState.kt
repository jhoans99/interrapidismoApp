package com.sebas.interrapidismo.model.state

import com.sebas.interrapidismo.network.model.response.LocalitiesResponse

data class LocalitiesState(
    val isLoading: Boolean = false,
    val listLocalitiesState: ArrayList<LocalitiesResponse> = ArrayList()
)
