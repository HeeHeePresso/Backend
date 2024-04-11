package heeheepresso.recommend.global.config;

import heeheepresso.recommend.domain.home.repository.SeasonSpecialItemsRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackageClasses = SeasonSpecialItemsRepository.class)
public class ElasticSearchConfig extends AbstractElasticsearchConfiguration {

}
