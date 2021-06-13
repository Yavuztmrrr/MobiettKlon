package com.h5190041.mobiett.data.model

import com.google.gson.annotations.SerializedName


data class LoginResponseItem(
        @SerializedName("email")
    val email: String,
        @SerializedName("parola")
    val parola: String
)