//package heeheepresso.recommend.domain.home.service;
//
//import heeheepresso.recommend.domain.home.model.SeasonSpecialItem;
//import heeheepresso.recommend.domain.home.repository.SeasonSpecialItemsRepository;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Slf4j
//@RequiredArgsConstructor
//@Service
//public class SeasonSpecialItemService {
//    private final SeasonSpecialItemsRepository seasonSpecialItemsRepository;
//
//    public Iterable<SeasonSpecialItem> getSeasonSpecialItems() {
//        return seasonSpecialItemsRepository.findAll();
//    }
//
////    public SeasonSpecialItem getSeasonSpecialItem(Long id) {
////        return seasonSpecialItemsRepository.findById(id).orElse(null);
////    }
////
////    public SeasonSpecialItem createSeasonSpecialItem(SeasonSpecialItem seasonSpecialItem) {
////        return seasonSpecialItemsRepository.save(seasonSpecialItem);
////    }
////
////    public void deleteSeasonSpecialItem(Long id) {
////        seasonSpecialItemsRepository.deleteById(id);
////    }
//}
