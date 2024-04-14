package heeheepresso.recommend.api;

//import heeheepresso.recommend.domain.home.service.SeasonSpecialItemsService;
//import heeheepresso.recommend.domain.home.service.SeasonSpecialItemService;
import heeheepresso.recommend.global.common.CommonApiResponse;
import heeheepresso.recommend.global.dto.request.RecommendRequestDto;
import heeheepresso.recommend.global.dto.response.ResponseTestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/home")
public class HomeRecommendController {
//    private final SeasonSpecialItemService seasonSpecialItemService;

    @PostMapping("/recommend")
    public ResponseEntity<?> getSeasonSpecialItemList(@RequestBody RecommendRequestDto recommendRequestDto) {
        List<Map<String, Integer>> testResult = new ArrayList<>();
        Map<String, Integer> testData1 = new HashMap<>();
        testData1.put("menuId",1);
        Map<String, Integer> testData2 = new HashMap<>();
        testData2.put("menuId",2);
        testResult.add(testData1);
        testResult.add(testData2);
        ResponseTestDto responseTestDto = new ResponseTestDto("String", testResult);

        return CommonApiResponse.success(responseTestDto, HttpStatus.OK);
    }

//    @GetMapping("/findAll")
//    public Iterable<SeasonSpecialItem> findAll(@RequestBody RecommendRequestDto recommendRequestDto) {
//        return seasonSpecialItemService.getSeasonSpecialItems();
//    }
}
