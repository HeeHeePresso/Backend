package heeheepresso.recommend;

//import heeheepresso.recommend.domain.home.repository.SeasonSpecialItemsRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
//@EnableElasticsearchRepositories(
//		includeFilters = {
//				@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SeasonSpecialItemsRepository.class),
//		}
//)
public class RecommendApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecommendApplication.class, args);
	}

}
