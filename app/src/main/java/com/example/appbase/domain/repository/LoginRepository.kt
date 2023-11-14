package com.example.appbase.domain.repository

interface LoginRepository {

    suspend fun hacerLogin(user:String, pass: String): String
}