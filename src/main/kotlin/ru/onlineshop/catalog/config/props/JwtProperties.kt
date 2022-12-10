package ru.onlineshop.catalog.config.props

import org.jetbrains.annotations.NotNull
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties("jwt")
class JwtProperties {

    @NotNull
    lateinit var secret: String
}