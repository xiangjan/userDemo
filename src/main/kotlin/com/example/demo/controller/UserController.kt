package com.example.demo.controller

import com.example.demo.dto.DTOUser
import com.example.demo.dto.UserPasswordDTO
import com.example.demo.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class UserController(
    val userService: UserService
) {
    @PostMapping("/users/sign-up")
    fun creat_user(@RequestBody dtoUser: DTOUser): ResponseEntity<*> {
        return userService.crateUser(dtoUser)
    }
    @PostMapping("/users/{userName}/changepw")
    fun updatePassword(@PathVariable userName: String, @RequestBody dtoUserPasswordDTO: UserPasswordDTO):ResponseEntity<*>{
        if(userName!=dtoUserPasswordDTO.name) {
            return ResponseEntity("The ID is not correct", HttpStatus.BAD_REQUEST)
        }
        return userService.updateUser(dtoUserPasswordDTO)
    }
    @DeleteMapping("/users/{userName}")
    fun deleteUser(@PathVariable userName: String): ResponseEntity<*>{
        return ResponseEntity(userService.deleteUser(userName),HttpStatus.OK)
    }
}