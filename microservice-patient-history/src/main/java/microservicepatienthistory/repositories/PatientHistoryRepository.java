package microservicepatienthistory.repositories;

import microservicepatienthistory.models.PatientHistory;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientHistoryRepository extends MongoRepository<PatientHistory, ObjectId> {

    @Query("{patientId:?0}")
    List<PatientHistory> findAllPatientHistoryById(Integer patientId);
}
