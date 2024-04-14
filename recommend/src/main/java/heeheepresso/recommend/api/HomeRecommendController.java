package heeheepresso.recommend.api;

//import heeheepresso.recommend.domain.home.service.SeasonSpecialItemsService;
import heeheepresso.recommend.domain.home.model.SeasonSpecialItem;
import heeheepresso.recommend.global.common.CommonApiResponse;
import heeheepresso.recommend.global.dto.request.RecommendRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("")
public class HomeRecommendController {
//    private final SeasonSpecialItemService seasonSpecialItemService;

    @PostMapping
    public ResponseEntity<?> getSeasonSpecialItemList(@RequestBody RecommendRequestDto recommendRequestDto) {
        return CommonApiResponse.success(recommendRequestDto, HttpStatus.OK);
    }

//    @GetMapping("/findAll")
//    public Iterable<SeasonSpecialItem> findAll(@RequestBody RecommendRequestDto recommendRequestDto) {
//        return seasonSpecialItemService.getSeasonSpecialItems();
//    }
}
