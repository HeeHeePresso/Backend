package heeheepresso.recommend.domain.home.model;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "season_special_items")
public class SeasonSpecialItems {

    @Id
    private Long id;

    private String name;
}
