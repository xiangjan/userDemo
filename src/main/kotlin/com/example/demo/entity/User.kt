package com.example.demo.entity

import com.querydsl.core.annotations.QueryEntity
import javax.persistence.*

@Entity
@QueryEntity
@Table(name="user", schema="public")
class User (
    @Id
    @GeneratedValue()
    var id: Long? = null,
    var name: String? = null,
    var password: String? = null
)


