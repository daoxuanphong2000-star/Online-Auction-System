# Giao thức Client–Server

## Quy ước chung
- Truyền qua TCP socket. Mỗi message là **một dòng JSON**, kết thúc bằng `\n`.
- Giá tiền: số nguyên `long`, đơn vị đồng.
- Thời gian: `long`, epoch millis (UTC).
- Trạng thái phiên: `OPEN | RUNNING | FINISHED | PAID | CANCELED`.
- Vai trò: `BIDDER | SELLER | ADMIN`.

## Phong bì
```json
{"id": "c-1", "type": "LOGIN", "payload": {}}
```
- `id`: client tự sinh cho mỗi request; server trả lời với cùng `id`. Push event không có `id`.

## Request (Client -> Server)

### REGISTER
- payload: `{"username": "", "password": "", "role": "BIDDER|SELLER"}`
- OK: `{"userId": ""}`
- ERROR: `INVALID_REQUEST` (trùng tên, thiếu trường)

### LOGIN
- payload: `{"username": "", "password": ""}`
- OK: `{"userId": "", "username": "", "role": ""}`
- ERROR: `NOT_AUTHENTICATED`

### GET_AUCTIONS
- payload: `{}`
- OK: `{"auctions": [AuctionSummary, ...]}`

### GET_AUCTION_DETAIL
- payload: `{"auctionId": ""}`
- OK: `AuctionDetail`
- ERROR: `AUCTION_NOT_FOUND`

### SUBSCRIBE_AUCTION / UNSUBSCRIBE_AUCTION
- payload: `{"auctionId": ""}`
- OK: `{}`. Sau khi subscribe, client nhận push event của phiên đó.

### PLACE_BID
- payload: `{"auctionId": "", "amount": 1200000}`
- OK: `{"currentPrice": 1200000}`
- ERROR: `BID_TOO_LOW`, `AUCTION_CLOSED`, `NOT_AUTHENTICATED`, `FORBIDDEN`

### CREATE_ITEM / UPDATE_ITEM / DELETE_ITEM (chỉ SELLER)
- payload: `ItemDTO` (có trường `type`: `ELECTRONICS|ART|VEHICLE`)
- ERROR: `FORBIDDEN`, `INVALID_REQUEST`

### PING
- payload: `{}`; OK: `{}`

## Push event (Server -> Client, không có id)

### BID_UPDATED
```json
{"type":"BID_UPDATED","payload":{"auctionId":"A5","currentPrice":1200000,"bidderName":"an","timestamp":1760000000000,"seq":42}}
```
`seq` tăng dần theo từng phiên; client bỏ qua event có `seq` nhỏ hơn giá trị đã nhận.

### AUCTION_STATUS_CHANGED
`{"auctionId": "", "status": "FINISHED", "winnerName": "an"}`

### AUCTION_EXTENDED
`{"auctionId": "", "newEndTime": 1760000060000}`

## Quy tắc bảo mật
- Không bao giờ gửi `password` xuống client.
- Chỉ server truy cập database.