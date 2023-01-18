package com.example.todo

import com.google.gson.annotations.SerializedName

final data class ApiResponse (
    @SerializedName("users" ) var users : ArrayList<Users> = arrayListOf(),
    @SerializedName("total" ) var total : Int?             = null,
    @SerializedName("skip"  ) var skip  : Int?             = null,
    @SerializedName("limit" ) var limit : Int?             = null
    )

data class Users (
    @SerializedName("id"         ) var id         : Int?     = null,
    @SerializedName("firstName"  ) var firstName  : String?  = null,
    @SerializedName("lastName"   ) var lastName   : String?  = null,
    @SerializedName("image"      ) var image      : String?  = null,
)