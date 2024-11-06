package com.sebas.interrapidismo.commons

sealed class Result<out R> {
    data class Success<out T>(val data:T) : Result<T>()
    class Error<out T>(val message: String) : Result<T>()
    data object Loading: Result<Nothing>()
}