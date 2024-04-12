package heeheepresso.recommend.api;

import heeheepresso.recommend.domain.home.service.SeasonSpecialItemsService;
import heeheepresso.recommend.global.dto.request.RecommendRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("")
public class HomeRecommendController {
    private final SeasonSpecialItemsService seasonSpecialItemsService;

    @PostMapping
    public ResponseEntity<?> getSeasonSpecialItemList(@RequestBody RecommendRequestDto recommendRequestDto) {
        return ResponseEntity.ok().build();
    }
}
