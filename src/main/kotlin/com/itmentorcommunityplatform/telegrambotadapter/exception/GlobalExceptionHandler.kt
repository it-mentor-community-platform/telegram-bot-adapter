package com.itmentorcommunityplatform.telegrambotadapter.exception

import com.itmentorcommunityplatform.telegrambotadapter.dto.response.ErrorResponseDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(TaskCountOutOfBoundException::class)
    fun handleTaskCountOutOfBoundException(exception: TaskCountOutOfBoundException): ResponseEntity<ErrorResponseDto> {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(ErrorResponseDto(exception.message.toString()))
    }

    @ExceptionHandler(Exception::class)
    fun handleException(exception: Exception): ResponseEntity<ErrorResponseDto> {
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(ErrorResponseDto("Internal server error"))
    }

}