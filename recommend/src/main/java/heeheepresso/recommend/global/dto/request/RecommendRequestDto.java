package heeheepresso.recommend.global.dto.request;

public record RecommendRequestDto(String handler, String where, long userId, long storeId, int pageSize, int offset) {
}