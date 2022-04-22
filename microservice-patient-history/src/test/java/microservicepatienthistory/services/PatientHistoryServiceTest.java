package microservicepatienthistory.services;

import microservicepatienthistory.models.PatientHistory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class PatientHistoryServiceTest {

    @Autowired
    private PatientHistoryService patientHistoryService;

    @Test
    public void patientHistoryTest(){
        PatientHistory patientHistory = new PatientHistory();
        patientHistory.setPatientId(100);
        patientHistory.setNotes("No problem at all");

        //SAVE
        patientHistoryService.add(patientHistory);
        Assertions.assertNotNull(patientHistoryService.findById(patientHistory.getPatientHistoryId()));
        Assertions.assertEquals("No problem at all", patientHistoryService.findById(patientHistory.getPatientHistoryId()).getNotes());

        //UPDATE
        patientHistory.setNotes("I am in trouble !");
        patientHistoryService.update(patientHistory);
        Assertions.assertEquals("I am in trouble !", patientHistoryService.findById(patientHistory.getPatientHistoryId()).getNotes());

        //FIND
        List<PatientHistory> patientHistoryList = patientHistoryService.findAll();
        Assertions.assertTrue(patientHistoryList.size() > 0);

        //DELETE
        patientHistoryService.delete(patientHistory.getPatientHistoryId());
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> patientHistoryService.findById(patientHistory.getPatientHistoryId()));
        Assertions.assertEquals("Invalid patient history Id " + patientHistory.getPatientHistoryId(), exception.getMessage());
    }
}
