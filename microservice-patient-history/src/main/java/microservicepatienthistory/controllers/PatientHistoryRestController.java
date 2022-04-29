package microservicepatienthistory.controllers;

import microservicepatienthistory.models.PatientHistory;
import microservicepatienthistory.services.PatientHistoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PatientHistoryRestController {

    private PatientHistoryService patientHistoryService;

    public PatientHistoryRestController(PatientHistoryService patientHistoryService){
        this.patientHistoryService = patientHistoryService;
    }

    @GetMapping("index")
    public String home(){
        return "Greetings from patientHistory !";
    }

    @GetMapping("/patientHistory/assess")
    public List<PatientHistory> findAll(){
        return patientHistoryService.findAll();
    }

    @GetMapping("/patientHistory/assess/patientHistoryId")
    public List<PatientHistory> findPatientHistoryListById(@RequestParam("id") int patientId){
        return patientHistoryService.findAllById(patientId);
    }
}
