package Model;

import javafx.beans.property.SimpleObjectProperty;

import java.util.Date;

public class Report {
    private final SimpleObjectProperty<Patient> patient;
    private final SimpleObjectProperty<Reaction> reaction;
    private final SimpleObjectProperty<Date> reportDate;
    private final SimpleObjectProperty<Date> reactionDate;
    private final SimpleObjectProperty<Therapy> therapy;

    private Report(Patient patient, Reaction reaction, Date reportDate, Date reactionDate, Therapy therapy) {
        this.patient = new SimpleObjectProperty<>(patient);
        this.reaction = new SimpleObjectProperty<>(reaction);
        this.reportDate = new SimpleObjectProperty<>(reportDate);
        this.reactionDate = new SimpleObjectProperty<>(reactionDate);
        this.therapy = new SimpleObjectProperty<>(therapy);
    }

    @Override
    public int hashCode() {
        return patient.hashCode() ^ reaction.hashCode() ^ reportDate.hashCode() ^ reactionDate.hashCode() ^ therapy.hashCode();
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

    public Date getReportDate() {
        return reportDate.get();
    }

    public void setReportDate(Date reportDate) {
        this.reportDate.set(reportDate);
    }

    public SimpleObjectProperty<Date> getReport_dateProperty() {
        return reportDate;
    }

    public Date getReactionDate() {
        return reactionDate.get();
    }

    public void setReactionDate(Date reactionDate) {
        this.reactionDate.set(reactionDate);
    }

    public SimpleObjectProperty<Date> getReactionDateProperty() {
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
