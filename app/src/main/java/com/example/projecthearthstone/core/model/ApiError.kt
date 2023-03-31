package com.example.projecthearthstone.core.model

enum class ApiError(val type: String) {
    TIMEOUT("Timeout"),
    FAIL("Fail"),
    EXCEPTION("Exception"),
    NO_CONNECTION("No connection")
}