package backend.expenses.service.dbCounter;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class CounterService {
    private final MongoTemplate mongoTemplate;

    public CounterService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public int getNextSequence(String counterId) {
        Query query = new Query(Criteria.where("_id").is(counterId));

        Update update = new Update().inc("seq", 1);

        Counter counter = mongoTemplate.findAndModify(query, update, Counter.class);

        if (counter == null) {
            counter = new Counter(counterId, 1);
            mongoTemplate.insert(counter);
            return 1;
        }
        return counter.getSeq();
    }
}
