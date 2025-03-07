package com.choebum.portfolio.domain.entity

import jakarta.persistence.*
import jakarta.servlet.http.HttpServletRequest

@Entity
class HttpInterface(
    httpServletRequest: HttpServletRequest,
) : BaseEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "http_interface_id")
    var id: Long? = null

    var cookies: String? = httpServletRequest.cookies
        ?.map { "${it.name}: ${it.value}" }
        ?.toString()

    var referer: String? =
        httpServletRequest.getHeader("referer") // 특정 사이트를 검색해서 들어가게 될 경우 특정 사이트는 우리의 요청을 듣고 응답을 해주는데 그때 요청이 어디서 부터 왔는지 판단

    var localAddr: String? = httpServletRequest.localAddr

    var remoteAddr: String? = httpServletRequest.remoteAddr

    var remoteHost: String? = httpServletRequest.remoteHost

    var requestUri: String? = httpServletRequest.requestURI

    var userAgent: String? = httpServletRequest.getHeader("User-Agent")  // 브라우저 정보

}