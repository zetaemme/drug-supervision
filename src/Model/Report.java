package Model;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class Report {
    private final SimpleStringProperty id;
    private final SimpleObjectProperty<Patient> patient;
    private final SimpleObjectProperty<Reaction> reaction;
    private final SimpleStringProperty reportDate;
    private final SimpleStringProperty reactionDate;
    private final SimpleObjectProperty<Therapy> therapy;

    public Report(Patient patient, Reaction reaction, String reportDate, String reactionDate, Therapy therapy) {
        this.id = new SimpleStringProperty(
                patient.getId().substring(0, 3) + reaction.getReactionName().substring(0, 4) +
                therapy.getId().substring(0, 4)
        );
        this.patient = new SimpleObjectProperty<>(patient);
        this.reaction = new SimpleObjectProperty<>(reaction);
        this.reportDate = new SimpleStringProperty(reportDate);
        this.reactionDate = new SimpleStringProperty(reactionDate);
        this.therapy = new SimpleObjectProperty<>(therapy);
    }

    public SimpleStringProperty getId() {
        return id;
    }

    public Patient getPatient() {
        return patient.get();
    }

    public void setPatient(Patient patient) {
        this.patient.set(patient);
    }

    public String getReportDate() {
        return reportDate.get();
    }

    public String getReactionDate() {
        return reactionDate.get();
    }

    public Therapy getTherapy() {
        return therapy.get();
    }
}
