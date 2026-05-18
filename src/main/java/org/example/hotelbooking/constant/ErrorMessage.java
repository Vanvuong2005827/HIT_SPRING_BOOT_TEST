package org.example.hotelbooking.constant;

public final class ErrorMessage {

    private ErrorMessage() {
    }

    public static final String ROOM_NOT_AVAILABLE = "Phòng không tồn tại hoặc đã được đặt trong thời gian này.";
    public static final String GUESTS_EXCEED_CAPACITY = "Số lượng khách vượt quá sức chứa tối đa của phòng.";
    public static final String INVALID_DATES = "Ngày nhận hoặc trả phòng không hợp lệ (check-out phải sau check-in).";
    public static final String RENTAL_PERIOD_TOO_LONG = "Thời gian thuê phòng không được vượt quá 30 ngày.";
    public static final String BOOKING_NOT_PENDING = "Chỉ có thể hủy đơn đặt phòng đang chờ xử lý (PENDING).";

    public static final String INVALID_REQUEST_BODY_LOG = "Invalid request body.";
    public static final String INVALID_REQUEST_PARAMETERS_LOG = "Invalid request parameters.";
    public static final String INVALID_REQUEST_USER = "Lỗi, vui lòng kiểm tra lại thông tin đã nhập.";
    public static final String VALIDATION_BODY_DEV = "Validation failed for request body";
    public static final String VALIDATION_PARAMETERS_DEV = "Validation failed for request parameters";
    public static final String RESOURCE_NOT_FOUND_FORMAT = "%s with ID %s not found.";
    public static final String RESOURCE_NOT_FOUND_USER = "Không tìm thấy tài nguyên.";
    public static final String CONFLICT_USER = "Không thể thực hiện thao tác do xung đột dữ liệu.";
    public static final String INTERNAL_ERROR_LOG = "Internal Error.";
    public static final String INTERNAL_ERROR_USER = "Đã xảy ra lỗi nội bộ.";
}