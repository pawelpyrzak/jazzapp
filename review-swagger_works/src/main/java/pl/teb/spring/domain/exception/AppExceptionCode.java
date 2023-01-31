package pl.teb.spring.domain.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum AppExceptionCode {
    INVALID_EMAIL("Invalid email.", 400),
    NO_SUCH_USER("There is no such user with passed uuid.", 400),
    NO_SUCH_PRODUCT("There is no such product. ", 400),
    NO_SUCH_CATEGORY("There is no such category. ", 400);

    private final String message;
    private final int status;

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }

    public DomainException createException() {
        return new DomainException(this);
    }
}
