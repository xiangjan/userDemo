package com.example.demo.service

import com.example.demo.dto.DTOUser
import com.example.demo.dto.UserPasswordDTO
import com.example.demo.entity.QUser.user
import com.example.demo.entity.User
import com.example.demo.repository.UserRepository
import org.mindrot.jbcrypt.BCrypt
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    val userRepository: UserRepository
) {
    //회원 가입
    fun crateUser(dtoUser: DTOUser): ResponseEntity<*> {
        //check the user exist
        val user = userRepository.findOne(user.name.eq(dtoUser.name)).orElse(null)
        if(user!=null){
            return ResponseEntity("The user already register.", HttpStatus.BAD_REQUEST)
        }

        return ResponseEntity(userRepository.save(
            User(name = dtoUser.name, password =BCrypt.hashpw(dtoUser.password,BCrypt.gensalt()
            ))),HttpStatus.OK
        )
    }
    //비번 변경
    fun updateUser(userPasswordDTO: UserPasswordDTO): ResponseEntity<*>{
        var userInfo = userRepository.findOne(user.name.eq(userPasswordDTO.name)).orElse(null)
        //사용자 존재여부 체크
        if(userInfo==null){
            return ResponseEntity("Can not find user!", HttpStatus.BAD_REQUEST)
        }
        //이전 암호 체크와 새 비번으로 수정
        if(BCrypt.checkpw(userPasswordDTO.prePassword,userInfo.password)) {
                userInfo.password = BCrypt.hashpw(userPasswordDTO.password, BCrypt.gensalt())
                return ResponseEntity(userRepository.save(userInfo), HttpStatus.OK)
        }
        else{
            return ResponseEntity("The previous pass was wrong!",HttpStatus.BAD_REQUEST)
        }
    }
    //회원 삭제
    @Transactional
    fun deleteUser(userName:String){
        return userRepository.deleteByName(userName)
    }
}
