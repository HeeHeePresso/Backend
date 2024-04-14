package heeheepresso.recommend.global.dto.response;

import java.util.List;
import java.util.Map;

public record ResponseTestDto(String handler, List<Map<String, Integer>> recommendedMenus) {
}
