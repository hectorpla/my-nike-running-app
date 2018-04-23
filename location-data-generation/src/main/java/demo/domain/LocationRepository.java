package demo.domain;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by hectorlueng on 4/22/18.
 */

public interface LocationRepository extends MongoRepository<Location, String> {
    // ...
}
