package microservicepatienthistory.controllers;

import microservicepatienthistory.models.PatientHistory;
import microservicepatienthistory.services.PatientHistoryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class PatientHistoryControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private static MockMvc mockMvc;

    @Autowired
    private PatientHistoryService patientHistoryService;

    @BeforeEach
    public void setupMockMvc(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void givenAnUrl_ReturnAHomePageOfThisMicroservice() throws Exception {
        mockMvc.perform(get("/patientHistory/home"))
                .andExpect(status().isOk());
    }

    @Test
    public void givenAnUrl_ReturnAPageWithAllPatientHistories() throws Exception {
        mockMvc.perform(get("/patientHistory/list"))
                .andExpect(status().isOk());
    }

    @Test
    public void givenAnUrl_ReturnAPageToAddANewPatientHistory() throws Exception {
        mockMvc.perform(get("/patientHistory/add"))
                .andExpect(status().isOk());
    }

    @Test
    public void givenAPatientHistory_AddItToDatabase_ReturnAPageWithAllPatientHistories() throws Exception {
        PatientHistory patientHistory = new PatientHistory();
        patientHistory.setPatientId(100);
        patientHistory.setNotes("No problem at all");

        patientHistoryService.add(patientHistory);
        Assertions.assertNotNull(patientHistoryService.findById(patientHistory.getPatientHistoryId()));
        Assertions.assertEquals("No problem at all", patientHistoryService.findById(patientHistory.getPatientHistoryId()).getNotes());

        mockMvc.perform(post("/patientHistory/validate"))
                .andExpect(status().isFound());

        mockMvc.perform(get("/patientHistory/list"))
                .andExpect(status().isOk());

        patientHistoryService.delete(patientHistory.getPatientHistoryId());
    }

    @Test
    public void givenAnId_ReturnPathToAPageWithASpecificPatientHistory() throws Exception {
        PatientHistory patientHistory = new PatientHistory();
        patientHistory.setPatientId(100);
        patientHistory.setNotes("No problem at all");

        patientHistoryService.add(patientHistory);
        Assertions.assertNotNull(patientHistoryService.findById(patientHistory.getPatientHistoryId()));

        mockMvc.perform(get("/patientHistory/update/{id}", patientHistory.getPatientHistoryId()))
                .andExpect(status().isOk());

        patientHistoryService.delete(patientHistory.getPatientHistoryId());
    }

    @Test
    public void givenNewInformationToAPatientHistory_UpdateItIntoTheDatabase_ReturnAPageWithAllPatientHistories() throws Exception {
        PatientHistory patientHistory = new PatientHistory();
        patientHistory.setPatientId(100);
        patientHistory.setNotes("No problem at all");

        patientHistoryService.add(patientHistory);
        Assertions.assertNotNull(patientHistoryService.findById(patientHistory.getPatientHistoryId()));

        patientHistory.setNotes("I am in trouble !");
        patientHistoryService.update(patientHistory);
        Assertions.assertEquals("I am in trouble !", patientHistoryService.findById(patientHistory.getPatientHistoryId()).getNotes());

        mockMvc.perform(get("/patientHistory/list"))
                .andExpect(status().isOk());

        patientHistoryService.delete(patientHistory.getPatientHistoryId());
    }

    @Test
    public void givenAnId_DeleteAPatientHistoryInTheDatabase_ReturnAPageWithAllPatientHistories() throws Exception {
        PatientHistory patientHistory = new PatientHistory();
        patientHistory.setPatientId(100);
        patientHistory.setNotes("No problem at all");

        patientHistoryService.add(patientHistory);
        Assertions.assertNotNull(patientHistoryService.findById(patientHistory.getPatientHistoryId()));

        patientHistoryService.delete(patientHistory.getPatientHistoryId());
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> patientHistoryService.findById(patientHistory.getPatientHistoryId()));
        Assertions.assertEquals("Invalid patient history Id " + patientHistory.getPatientHistoryId(), exception.getMessage());

        mockMvc.perform(get("/patientHistory/list"))
                .andExpect(status().isOk());
    }
}
