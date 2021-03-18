package br.com.task.exceptions.handler;

import br.com.task.exceptions.TaskException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class TaskHandler {

    @ExceptionHandler(TaskException.class)
    public ResponseEntity<ErrorTicket> handlerTaskNotFound(TaskException exception) {
        ErrorTicket error = ErrorTicket.builder()
                .status(HttpStatus.NOT_FOUND)
                .detail(exception.getMensagem())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok().body(error);
    }
}
