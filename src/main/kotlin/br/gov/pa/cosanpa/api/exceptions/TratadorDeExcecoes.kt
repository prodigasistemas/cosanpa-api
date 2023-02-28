package br.gov.pa.cosanpa.api.exceptions

import br.gov.pa.cosanpa.api.exceptions.view.ErrorView
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class TratadorDeExcecoes {

    @ExceptionHandler(NaoEncontradoException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handlerNotFound(exception: NaoEncontradoException, request: HttpServletRequest): ErrorView {
        return ErrorView(
            status = HttpStatus.NOT_FOUND.value(),
            error = HttpStatus.NOT_FOUND.name,
            message = exception.message,
            path = request.servletPath
        )
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handlerValidationError(exception: MethodArgumentNotValidException, request: HttpServletRequest): ErrorView {
        val erroMensagem: HashMap<String, String?> = hashMapOf()
        exception.bindingResult.fieldErrors.forEach { e -> erroMensagem[e.field] = e.defaultMessage }

        return ErrorView(
            status = HttpStatus.BAD_REQUEST.value(),
            error = HttpStatus.BAD_REQUEST.name,
            message = erroMensagem.toString(),
            path = request.servletPath
        )
    }

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handlerServerError(exception: Exception, request: HttpServletRequest): ErrorView {
        return ErrorView(
            status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
            error = HttpStatus.INTERNAL_SERVER_ERROR.name,
            message = exception.message,
            path = request.servletPath
        )
    }
}