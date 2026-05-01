package com.devjavu.standardProject.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(1001, "Uncategorized error", HttpStatus.BAD_REQUEST),
    USER_EXISTED(1002, "User existed", HttpStatus.BAD_REQUEST),
    USERNAME_INVALID(1003, "Username must be at least 3 characters", HttpStatus.BAD_REQUEST),
    INVALID_PASSWORD(1004, "Password must be at least 8 characters", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(1005, "User not existed", HttpStatus.NOT_FOUND),
    UNAUTHENTICATED(1006, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1007, "You do not have permission", HttpStatus.FORBIDDEN),
    FAIL_ACCOUNT(1008,"Your password and username are fail",HttpStatus.BAD_REQUEST),
    INVALID_ROLE(1009,"invalid role",HttpStatus.NOT_FOUND),
    NOT_FOUND_DAUSACH(1010,"ko tim thay dau sach",HttpStatus.NOT_FOUND),
    NOT_FOUND_CUONSACH(1011,"ko tim thay cuon sach",HttpStatus.NOT_FOUND),
    NOT_FOUND_DOCGIA(1012,"ko tim thay doc gia",HttpStatus.NOT_FOUND),
    NOT_FOUND_PHIEU_MUON(1013,"ko tim thay phieu muon",HttpStatus.NOT_FOUND),
    NOT_FOUND_CHI_TIET_PHIEU_MUON(1014,"ko tim thay chi tiet phieu muon",HttpStatus.NOT_FOUND),
    NOT_FOUND_PHIEU_DAT_TRUOC(1015,"ko tim thay phieu dat truoc",HttpStatus.NOT_FOUND),
    NOT_FOUND_PHIEU_PHAT(1016,"ko tim thay phieu phat",HttpStatus.NOT_FOUND)

    ;

    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    private int code;
    private String message;
    private HttpStatusCode statusCode;
}
