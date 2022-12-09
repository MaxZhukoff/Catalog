package ru.onlineshop.catalog.config

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import lombok.RequiredArgsConstructor
import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import ru.onlineshop.catalog.security.UserInfo

@RequiredArgsConstructor
@Component
class JwtRequestFilter(
    var jwtUtil: JwtUtil
): OncePerRequestFilter() {

    private val ROLE_PREFIX = "ROLE_"

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {

        val tokenString: String = request.getHeader("Authorization")

        if (!tokenString.startsWith("Bearer "))
            return

        val token = tokenString.substring("Bearer ".length)

        val userInfo: UserInfo = jwtUtil.readToken(token)

        SecurityContextHolder.getContext().authentication = createAuthentication(userInfo)

        filterChain.doFilter(request, response)
    }

    private fun createAuthentication(userInfo: UserInfo): Authentication {

        val authorities: List<GrantedAuthority> = listOf(SimpleGrantedAuthority(ROLE_PREFIX + userInfo.role.toString()))

        return object : AbstractAuthenticationToken(authorities) {

            private lateinit var userInfo: UserInfo;

            override fun getCredentials(): Any {
                return this
            }

            override fun getPrincipal(): Any {

                this.userInfo = userInfo

                return this.userInfo
            }

            override fun isAuthenticated(): Boolean {
                return true
            }
        }
    }

}