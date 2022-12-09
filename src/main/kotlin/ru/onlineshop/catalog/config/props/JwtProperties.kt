package ru.onlineshop.catalog.config.props

import lombok.Data
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
@Data
class JwtProperties {

    @Value("\${jwt.secret}")
    lateinit var secret: String
}