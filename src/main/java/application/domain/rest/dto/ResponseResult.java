package application.domain.rest.dto;

/**
 * @author violet.
 */
public record ResponseResult<T>(int code, T data) {
    @Override
    public int code() {
        return code;
    }
}
