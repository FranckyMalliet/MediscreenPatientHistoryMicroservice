package microservicepatienthistory.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "patienthistory")
public class PatientHistory {

    @Id
    private ObjectId patientHistoryId;

    private int patientId;
    private String notes;

    public ObjectId getPatientHistoryId() {
        return patientHistoryId;
    }

    public void setPatientHistoryId(ObjectId patientHistoryId) {
        this.patientHistoryId = patientHistoryId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
