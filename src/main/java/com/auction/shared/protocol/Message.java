package com.auction.shared.protocol;

import com.google.gson.JsonElement;

/** Phong bì chung của mọi message: id (null với push event), loại, và nội dung. */
public record Message(String id, MessageType type, JsonElement payload) {}