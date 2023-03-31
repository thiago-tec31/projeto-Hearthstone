package com.example.projecthearthstone.core.model


sealed class Resource<out T: Any> {
    data class Success<out T: Any >(val data: T): Resource<T>()
    data class Fail(val status: ApiError, val message: String) : Resource<Nothing>()
}