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

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class PatientHistoryRestControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private static MockMvc mockMvc;

    @Autowired
    private PatientHistoryRestController patientHistoryRestController;

    @BeforeEach
    public void setupMockMvc(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void givenAnUrl_ReturnAStringMessage() throws Exception {
        //WHEN
        String message = patientHistoryRestController.greetingsMessage();
        Assertions.assertNotNull(message);
        Assertions.assertEquals("Greetings from patientHistory !", message);

        //THEN
        mockMvc.perform(get("/index"))
                .andExpect(status().isOk());
    }

    @Test
    public void givenAnUrl_ReturnAllPatientHistory() throws Exception {
        //WHEN
        List<PatientHistory> patientHistoryList = patientHistoryRestController.findAll();
        Assertions.assertNotNull(patientHistoryList);

        //THEN
        mockMvc.perform(get("/patientHistory/assess"))
                .andExpect(status().isOk());
    }

    @Test
    public void givenAnId_ReturnAllPatientHistoryFromThisId() throws Exception {
        //GIVEN
        int patientId = 5;

        //WHEN
        List<PatientHistory> patientHistoryList = patientHistoryRestController.findPatientHistoryListById(patientId);
        Assertions.assertNotNull(patientHistoryList);

        //THEN
        mockMvc.perform(get("/patientHistory/assess/patientHistoryId")
                        .param("patientId", String.valueOf(patientId)))
                .andExpect(status().isOk());
    }
}
