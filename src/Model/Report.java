package Model;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.Date;

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

    public SimpleObjectProperty<Patient> getPatientProperty() {
        return patient;
    }

    public Reaction getReaction() {
        return reaction.get();
    }

    public void setReaction(Reaction reaction) {
        this.reaction.set(reaction);
    }

    public SimpleObjectProperty<Reaction> getReactionProperty() {
        return reaction;
    }

    public String getReportDate() {
        return reportDate.get();
    }

    public void setReportDate(String reportDate) {
        this.reportDate.set(reportDate);
    }

    public SimpleStringProperty getReport_dateProperty() {
        return reportDate;
    }

    public String getReactionDate() {
        return reactionDate.get();
    }

    public void setReactionDate(String reactionDate) {
        this.reactionDate.set(reactionDate);
    }

    public SimpleStringProperty getReactionDateProperty() {
        return reactionDate;
    }

    public Therapy getTherapy() {
        return therapy.get();
    }

    public void setTherapy(Therapy therapy) {
        this.therapy.set(therapy);
    }

    public SimpleObjectProperty<Therapy> getTherapyProperty() {
        return therapy;
    }
}
