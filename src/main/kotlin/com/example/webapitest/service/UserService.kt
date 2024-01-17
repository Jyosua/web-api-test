package com.example.webapitest.service

//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import com.example.webapitest.model.User
import com.example.webapitest.model.dto.CreateUserRequest
import com.example.webapitest.repository.UserRepository
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class UserService(private val userRepository: UserRepository) {

    //private val passwordEncoder = BCryptPasswordEncoder()

    @Transactional
    fun getUserById(id: Long): User? {
        return userRepository.getByOrNull(id)
    }

    @Transactional
    fun getUsers(page: Int, size: Int): List<User> {
        return userRepository.getBy(page, size)
    }

    @Transactional
    fun addUser(createUserRequest: CreateUserRequest): User {
        val newUser = User(
                id = null,
                organizationId = createUserRequest.organizationId,
                name = createUserRequest.name,
                email = createUserRequest.email,
                password = createUserRequest.password, //hashPassword(createUserRequest.password),
                verified = false,
        )
        return userRepository.insert(newUser)
    }

    /*private fun hashPassword(password: String): String {
        // Implement password hashing logic here
        return BCryptPasswordEncoder().encode(password)
    }*/
}