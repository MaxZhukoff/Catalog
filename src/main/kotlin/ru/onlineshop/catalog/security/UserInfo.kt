package ru.onlineshop.catalog.security

import lombok.Builder
import java.util.*

@Builder
class UserInfo(
    val id: UUID,
    val username: String,
    val role: Role
)