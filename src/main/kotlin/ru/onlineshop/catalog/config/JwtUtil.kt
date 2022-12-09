package ru.onlineshop.catalog.config

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Component
import ru.onlineshop.catalog.config.props.JwtProperties
import ru.onlineshop.catalog.security.Role
import ru.onlineshop.catalog.security.UserInfo
import java.nio.charset.StandardCharsets
import java.util.*

@Component
@RequiredArgsConstructor
class JwtUtil(
    val jwtProperties: JwtProperties
) {

    fun readToken(token: String): UserInfo {

        val claims: Claims = Jwts
            .parserBuilder()
            .setSigningKey(jwtProperties.secret.toByteArray(StandardCharsets.UTF_8))
            .build()
            .parseClaimsJws(token)
            .body

        val id: UUID = UUID.fromString(claims["id"].toString())

        val username: String = claims.get("username", String::class.java)
            ?: throw IllegalArgumentException("username is null")

        val role: Role = Role.valueOf(claims.get("role", String::class.java))

        return UserInfo(id, username, role)
    }
}