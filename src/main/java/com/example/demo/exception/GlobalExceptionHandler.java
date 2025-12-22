// package com.example.demo.exception;

// import org.springframework.http.*;
// import org.springframework.web.bind.annotation.*;

// import jakarta.servlet.http.HttpServletRequest;

// @RestControllerAdvice
// public class GlobalExceptionHandler{

//     @ExceptionHandler(ResourceNotFoundException.class)
//     public ResponseEntity<ApiErrorResponse> handleNotFound(
//             ResourceNotFoundException ex,
//             HttpServletRequest request) {

//         ApiErrorResponse error = new ApiErrorResponse(
//                 HttpStatus.NOT_FOUND.value(),
//                 "NOT_FOUND",
//                 ex.getMessage(),
//                 request.getRequestURI()
//         );
//         return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
//     }

//     @ExceptionHandler(IllegalArgumentException.class)
//     public ResponseEntity<ApiErrorResponse> handleBadRequest(
//             IllegalArgumentException ex,
//             HttpServletRequest request) {

//         ApiErrorResponse error = new ApiErrorResponse(
//                 HttpStatus.BAD_REQUEST.value(),
//                 "BAD_REQUEST",
//                 ex.getMessage(),
//                 request.getRequestURI()
//         );
//         return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
//     }
// }
