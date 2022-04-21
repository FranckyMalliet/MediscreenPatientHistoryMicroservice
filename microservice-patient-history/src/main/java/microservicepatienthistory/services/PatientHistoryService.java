package microservicepatienthistory.services;

import microservicepatienthistory.models.PatientHistory;
import microservicepatienthistory.repositories.PatientHistoryRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientHistoryService {

    private final static Logger logger = LoggerFactory.getLogger(PatientHistoryService.class);
    private PatientHistoryRepository patientHistoryRepository;

    public PatientHistoryService(PatientHistoryRepository patientHistoryRepository){
        this.patientHistoryRepository = patientHistoryRepository;
    }

    public void add(PatientHistory patientHistory){
        logger.debug("Adding a new userNotes for user ");
        patientHistoryRepository.save(patientHistory);
    }

    public PatientHistory findById(ObjectId patientHistoryId){
        logger.debug("Retrieving note with id number : " + patientHistoryId);
        return patientHistoryRepository.findById(patientHistoryId).orElseThrow(() -> new IllegalArgumentException(("Invalid patient history Id " + patientHistoryId)));
    }

    public List<PatientHistory> findAll(){
        logger.debug("Retrieving all patientHistory notes from database");
        return patientHistoryRepository.findAll();
    }

    public void update(PatientHistory patientHistory){
        logger.debug("Updating notes from user with id number : " + patientHistory.getPatientId());
        patientHistoryRepository.save(patientHistory);
    }

    public void delete(ObjectId patientHistoryId){
        logger.debug("Deleting userNotes with id number : " + patientHistoryId);
        patientHistoryRepository.deleteById(patientHistoryId);
    }
}