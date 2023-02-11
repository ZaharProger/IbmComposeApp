package com.example.ibmcomposeapp.constants

enum class ValidationTypes(val regex: Regex) {
    VALIDATE_WEIGHT("[0123456789.]+".toRegex()),
    VALIDATE_HEIGHT("[0123456789]+".toRegex())
}