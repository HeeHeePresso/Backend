package heeheepresso.recommend.domain.home.repository;

import heeheepresso.recommend.domain.home.model.SeasonSpecialItems;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface SeasonSpecialItemsRepository extends ElasticsearchRepository<SeasonSpecialItems, Long>{

}
