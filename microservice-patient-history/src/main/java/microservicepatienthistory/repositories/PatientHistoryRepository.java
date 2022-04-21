package microservicepatienthistory.repositories;

import microservicepatienthistory.models.PatientHistory;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientHistoryRepository extends MongoRepository<PatientHistory, ObjectId> {

}
