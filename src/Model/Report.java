package Model;

import java.util.Date;

public class Report {
    private final Patient patient;
    private final Reaction reaction;
    private final Date report_date;
    private final Date reaction_date;
    private final Therapy therapy;

    private Report(Patient patient, Reaction reaction, Date report_date, Date reaction_date, Therapy therapy) {
        this.patient = patient;
        this.reaction = reaction;
        this.report_date = report_date;
        this.reaction_date = reaction_date;
        this.therapy = therapy;
    }

    @Override
    public int hashCode() {
        return patient.hashCode() ^ reaction.hashCode() ^ report_date.hashCode() ^ reaction_date.hashCode() ^ therapy.hashCode();
    }

    public Patient getPatient() {
        return patient;
    }

    public Reaction getReaction() {
        return reaction;
    }

    public Date getReport_date() {
        return report_date;
    }

    public Date getReaction_date() {
        return reaction_date;
    }

    public Therapy getTherapy() {
        return therapy;
    }
}
