package com.auction.shared.protocol;

/** Các loại message trao đổi giữa client và server. */
public enum MessageType {
    // client -> server (request)
    REGISTER,
    LOGIN,
    GET_AUCTIONS,
    GET_AUCTION_DETAIL,
    SUBSCRIBE_AUCTION,
    UNSUBSCRIBE_AUCTION,
    PLACE_BID,
    CREATE_ITEM,
    UPDATE_ITEM,
    DELETE_ITEM,
    PING,

    // server -> client (response, cùng id với request)
    OK,
    ERROR,

    // server -> client (push event, không có id)
    BID_UPDATED,
    AUCTION_STATUS_CHANGED,
    AUCTION_EXTENDED
}