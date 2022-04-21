package microservicepatienthistory.controllers;

import microservicepatienthistory.models.PatientHistory;
import microservicepatienthistory.services.PatientHistoryService;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class PatientHistoryController {

    private static Logger logger = LoggerFactory.getLogger(PatientHistoryController.class);
    private PatientHistoryService patientHistoryService;

    public PatientHistoryController(PatientHistoryService patientHistoryService){
        this.patientHistoryService = patientHistoryService;
    }

    @GetMapping("/patientHistory/home")
    public String home(){
        return "home";
    }

    @GetMapping("/patientHistory/list")
    public String userNotesList(Model model){
        model.addAttribute("patientHistoryList", patientHistoryService.findAll());
        return "patientHistory/list";
    }

    @GetMapping("/patientHistory/add")
    public String addUserNotes(PatientHistory patientHistory, Model model){
        model.addAttribute("patientHistory", patientHistory);
        return "patientHistory/add";
    }

    @PostMapping("/patientHistory/validate")
    public String validate(@Valid PatientHistory patientHistory, Model model, BindingResult result){
        if(result.hasErrors()){
            logger.debug("patientHistory incorrect");
            return "patientHistory/add";
        }

        patientHistoryService.add(patientHistory);

        logger.debug("Adding a new patientHistory to mongo database");
        model.addAttribute("patientHistoryList", patientHistoryService.findAll());
        return "redirect:/patientHistory/list";
    }

    @GetMapping("/patientHistory/update/{id}")
    public String showUpdateForm(@PathVariable("id") ObjectId patientHistoryId, Model model){
        PatientHistory patientHistory = patientHistoryService.findById(patientHistoryId);
        model.addAttribute("patientHistory", patientHistory);
        return "patientHistory/update";
    }

    @PostMapping("patientHistory/update/{id}")
    public String update(@PathVariable("id") ObjectId patientHistoryId, @Valid PatientHistory patientHistory, Model model, BindingResult result){
        if(result.hasErrors()){
            logger.debug("PatientHistory not found or incorrect");
            return "patientHistory/update";
        }

        patientHistory.setPatientHistoryId(patientHistoryId);
        patientHistoryService.update(patientHistory);

        logger.debug("Updating patientHistory number " + patientHistory.getPatientHistoryId());
        model.addAttribute("patientHistoryList", patientHistoryService.findAll());
        return "redirect:/patientHistory/list";
    }

    @GetMapping("/patientHistory/delete/{id}")
    public String delete(@PathVariable("id") ObjectId patientHistoryId, Model model){
        logger.debug("Deleting patient with id number : " + patientHistoryId);
        patientHistoryService.delete(patientHistoryId);
        model.addAttribute("patientHistoryList", patientHistoryService.findAll());
        return "redirect:/patientHistory/list";
    }
}
