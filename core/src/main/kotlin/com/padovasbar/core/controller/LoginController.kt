package com.padovasbar.core.controller

import com.padovasbar.core.repository.LoginRepository
import java.time.LocalDateTime
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin
@RequestMapping("/login")
class LoginController(private val loginRepository: LoginRepository) {

    @GetMapping("/valid-session")
    fun getById() =
        loginRepository.findById(1).get().lastLoginAt > LocalDateTime.now().minusMinutes(5)
    

    @GetMapping("/validate/{password}")
    fun validatePassword(@PathVariable password: String) : Boolean {
        val realPassword = loginRepository.findById(1).get()
        return if(password == realPassword.password){
            realPassword.lastLoginAt = LocalDateTime.now()
            loginRepository.save(realPassword)
            true
        } else{
            false
        }

    }
}