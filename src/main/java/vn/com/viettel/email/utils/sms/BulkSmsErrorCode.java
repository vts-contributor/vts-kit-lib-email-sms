package vn.com.viettel.email.utils.sms;


public enum BulkSmsErrorCode {
    SUCCSESS_1(1L, "Thành công"),
    CONNECTION_REFUSED_500(500L, "ConnectException"),
    CONNECTION_TIMEOUT_605(605L, "Connection timeout"),
    ERROR_999(999L, "Lỗi không xác định");

    private final Long code;
    private final String message;

    private BulkSmsErrorCode(Long code, String message) {
        this.code = code;
        this.message = message;
    }

    public Long getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
}
