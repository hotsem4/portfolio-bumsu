package com.choebum.portfolio.admin.data

import org.springframework.http.HttpStatus

class ApiResponse<T>(status: HttpStatus) : ResponseEntity<T>(status)