package com.example.demo.dto

data class DTOUser(
    var name: String? = null,
    var password: String? = null
)

data class  UserPasswordDTO(
    var name: String = "",
    var prePassword: String ="",
    var password: String =""
)