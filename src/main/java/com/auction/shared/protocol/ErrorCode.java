package com.auction.shared.protocol;

/** Mã lỗi server trả về trong message ERROR. */
public enum ErrorCode {
    INVALID_REQUEST,
    NOT_AUTHENTICATED,
    FORBIDDEN,
    BID_TOO_LOW,
    AUCTION_CLOSED,
    AUCTION_NOT_FOUND,
    INTERNAL_ERROR
}