package com.example.webapitest.repository

import com.example.webapitest.model.User
import com.example.webapitest.repository.db.UserEntity
import com.example.webapitest.repository.db.toUser
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository

@Repository
class UserRepository {

    fun getByOrNull(id: Long): User? = transaction {
        return@transaction UserEntity.findById(id)?.toUser()
    }

    fun getBy(page: Int, pageSize: Int): List<User> {
        val offset = page * pageSize
        return transaction {
            UserEntity.all()
                    .limit(pageSize, offset.toLong())
                    .map { it.toUser() }
        }
    }

    fun list(): List<User> {
        return UserEntity.all().map { it.toUser() }
    }

    fun insert(user: User): User {
        val entity = UserEntity.new {
            organizationId = user.organizationId
            name = user.name
            email = user.email
            password = user.password
            verified = user.verified
        }
        // FIXME: insert した User（not null であること）をデータベースから取得して返却すること
        return entity.toUser()
    }
}