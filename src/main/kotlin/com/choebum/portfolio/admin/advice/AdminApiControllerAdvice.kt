package com.choebum.portfolio.admin.advice

import com.choebum.portfolio.admin.exception.AdminException
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class AdminApiControllerAdvice { // Controller 자체에 @ExceptionHandler를 넣어도된다. 하지만 그렇게 사용하다 보면 관리도 어렵고
    // Controller의 역할과 멀어지게 되기 때문에 관리도 불편하게 느낄 수 있다.
    // 이러한 부분을 해소하기 위해 나온게 Advice다.
    val log = LoggerFactory.getLogger(AdminApiControllerAdvice::class.java)

    @ExceptionHandler
    fun handleException(e: AdminException): ResponseEntity<String> {
        log.info(e.message, e)
        return ResponseEntity.status(e.httpStatus).body(e.message)
    }

    @ExceptionHandler
    fun handleException(e: MethodArgumentNotValidException): ResponseEntity<String> {
        log.info(e.message, e)

        val fieldError = e.bindingResult.fieldErrors[0]
        val message = "[${fieldError.field} ${fieldError.defaultMessage}]"
        return ResponseEntity.badRequest().body(message)
    }

    @ExceptionHandler
    fun handleException(e: Exception): ResponseEntity<String> {
        log.info(e.message, e)
        return ResponseEntity.internalServerError().body("시스템 오류가 발생했습니다.")
    }
}