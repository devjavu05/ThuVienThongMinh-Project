package com.devjavu.standardProject.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Có lỗi không xác định xảy ra", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(1001, "Dữ liệu không hợp lệ", HttpStatus.BAD_REQUEST),
    USER_EXISTED(1002, "Tài khoản đã tồn tại", HttpStatus.BAD_REQUEST),
    USERNAME_INVALID(1003, "Tên đăng nhập phải có ít nhất 3 ký tự", HttpStatus.BAD_REQUEST),
    INVALID_PASSWORD(1004, "Mật khẩu phải có ít nhất 8 ký tự", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(1005, "Không tìm thấy tài khoản", HttpStatus.NOT_FOUND),
    UNAUTHENTICATED(1006, "Bạn chưa đăng nhập", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1007, "Bạn không có quyền truy cập chức năng này", HttpStatus.FORBIDDEN),
    FAIL_ACCOUNT(1008, "Tên đăng nhập hoặc mật khẩu không chính xác", HttpStatus.BAD_REQUEST),
    INVALID_ROLE(1009, "Vai trò không hợp lệ", HttpStatus.NOT_FOUND),
    NOT_FOUND_DAUSACH(1010, "Không tìm thấy đầu sách", HttpStatus.NOT_FOUND),
    NOT_FOUND_CUONSACH(1011, "Không tìm thấy cuốn sách", HttpStatus.NOT_FOUND),
    NOT_FOUND_DOCGIA(1012, "Không tìm thấy độc giả", HttpStatus.NOT_FOUND),
    NOT_FOUND_PHIEU_MUON(1013, "Không tìm thấy phiếu mượn", HttpStatus.NOT_FOUND),
    NOT_FOUND_CHI_TIET_PHIEU_MUON(1014, "Không tìm thấy chi tiết phiếu mượn", HttpStatus.NOT_FOUND),
    NOT_FOUND_PHIEU_DAT_TRUOC(1015, "Không tìm thấy phiếu đặt trước", HttpStatus.NOT_FOUND),
    NOT_FOUND_PHIEU_PHAT(1016, "Không tìm thấy phiếu phạt", HttpStatus.NOT_FOUND),
    LOGIN_REQUIRED_FIELDS(1017, "Vui lòng điền đầy đủ tên đăng nhập và mật khẩu", HttpStatus.BAD_REQUEST),
    ACCOUNT_LOCKED(1018, "Tài khoản đang bị khóa. Vui lòng liên hệ thủ thư", HttpStatus.BAD_REQUEST),
    FORGOT_PASSWORD_EMAIL_REQUIRED(1019, "Vui lòng nhập email để khôi phục mật khẩu", HttpStatus.BAD_REQUEST),
    EMAIL_NOT_FOUND(1020, "Không tìm thấy email độc giả trong hệ thống", HttpStatus.NOT_FOUND),
    READER_NOT_ELIGIBLE(1021, "Tài khoản không đủ điều kiện thực hiện giao dịch", HttpStatus.BAD_REQUEST),
    BOOK_AVAILABLE_DIRECT_BORROW(1022, "Sách hiện đã có sẵn trên kệ, vui lòng đến thư viện để mượn trực tiếp", HttpStatus.BAD_REQUEST),
    MAX_RESERVATION_LIMIT(1023, "Bạn đã đạt giới hạn đặt trước tối đa 2 đầu sách", HttpStatus.BAD_REQUEST),
    RESERVATION_FOR_BORROWED_TITLE(1024, "Bạn không thể đặt trước đầu sách mà mình đang mượn", HttpStatus.BAD_REQUEST),
    DUPLICATE_ACTIVE_RESERVATION(1025, "Bạn đã có một yêu cầu đặt trước đang chờ cho đầu sách này", HttpStatus.BAD_REQUEST),
    EBOOK_UNDER_MAINTENANCE(1026, "Tài liệu đang được bảo trì, vui lòng thử lại sau", HttpStatus.BAD_REQUEST),
    EBOOK_DAILY_LIMIT(1027, "Bạn đã đạt giới hạn mua tối đa 5 E-Book/ngày", HttpStatus.BAD_REQUEST),
    EBOOK_PREMIUM_REQUIRED(1028, "Chỉ tài khoản Premium mới được mua sách này", HttpStatus.BAD_REQUEST),
    EBOOK_INSUFFICIENT_BALANCE(1029, "Số dư không đủ để thực hiện giao dịch", HttpStatus.BAD_REQUEST),
    EBOOK_NOT_FOUND(1030, "Không tìm thấy E-Book phù hợp", HttpStatus.NOT_FOUND),
    EBOOK_ALREADY_PURCHASED(1031, "Bạn đã mua E-Book này trước đó", HttpStatus.BAD_REQUEST),
    REVIEW_NOT_ALLOWED(1032, "Bạn chỉ có thể đánh giá những sách đã từng mượn/đọc", HttpStatus.BAD_REQUEST),
    REVIEW_INVALID_CONTENT(1033, "Nội dung chứa từ ngữ không hợp lệ", HttpStatus.BAD_REQUEST),
    REVIEW_INVALID_RATING(1034, "Điểm đánh giá phải từ 1 đến 5 sao", HttpStatus.BAD_REQUEST),
    NOT_FOUND_THONG_BAO(1035, "Không tìm thấy thông báo", HttpStatus.NOT_FOUND),
    RENEWAL_LIMIT_REACHED(1036, "Bạn đã đạt giới hạn gia hạn tối đa (2 lần)", HttpStatus.BAD_REQUEST),
    RENEWAL_BLOCKED_BY_RESERVATION(1037, "Sách này đã có người đặt trước, bạn vui lòng trả sách đúng hạn", HttpStatus.BAD_REQUEST),
    RENEWAL_OVERDUE_NOT_ALLOWED(1038, "Sách đã quá hạn, bạn không thể gia hạn mà phải ra thư viện nộp phạt và làm thủ tục mới", HttpStatus.BAD_REQUEST),
    RESERVATION_PRIORITY_REQUIRED(1039, "Đầu sách này đang có người đặt trước. Thủ thư phải lập phiếu mượn cho người đứng đầu hàng chờ trước", HttpStatus.BAD_REQUEST);

    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    private final int code;
    private final String message;
    private final HttpStatusCode statusCode;
}
