package demo.ServiceLayer.Exception.Definition;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class TokenExpiredException extends RuntimeException {
    private final String message;
    private final Object entity;
}
