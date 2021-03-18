package br.com.task.exceptions.handler;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
public class ErrorTicket {
    private HttpStatus status;
    private String detail;
    private LocalDateTime timestamp;

    public ErrorTicket(HttpStatus status, String detail, LocalDateTime timestamp) {
        super();
        this.status = status;
        this.detail = detail;
        this.timestamp = timestamp;
    }
}
