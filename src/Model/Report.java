package Model;

/**
 * @author Andrea Soglieri e Mattia Zorzan
 *
 * Classe che gestisce la segnalazione
 */

public class Report {
    /**
     * Dati del paziente
     */
    private final Patient patient;
    /**
     * Dati della reazione avversa
     */
    private final Reaction reaction;
    /**
     * Data della segnalazione
     */
    private final Date report_date;
    /**
     * Data della reazione avversa
     */
    private final Date reaction_date;
    /**
     * Dati della terapia farmacologica
     */
    private final Therapy therapy;

    /**
     * Metodo costruttore
     *
     * @param patient
     * @param reaction
     * @param report_date
     * @param reaction_date
     * @param therapy
     */
    private Report(Patient patient, Reaction reaction, Date report_date, Date reaction_date, Therapy therapy) {
        this.patient = patient;
        this.reaction = reaction;
        this.report_date = report_date;
        this.reaction_date = reaction_date;
        this.therapy = therapy;
    }

    /**
     * Metodo hashCode
     *
     * @return L'hash del report
     */
    @Override
    public int hashCode() {
        return patient.hashCode() ^ reaction.hashCode() ^ report_date.hashCode() ^ reaction_date.hashCode() ^ therapy.hashCode();
    }

    /**
     * Metodo getPatient
     *
     * @return I dati del paziente
     */
    public Patient getPatient() {
        return patient;
    }

    /**
     * Metodo getReaction
     *
     * @return I dati della reazione
     */
    public Reaction getReaction() {
        return reaction;
    }

    /**
     * Metodo getReport_date
     *
     * @return La data della segnalazione
     */
    public Date getReport_date() {
        return report_date;
    }

    /**
     * Metodo getReaction_date
     *
     * @return La data della reazione
     */
    public Date getReaction_date() {
        return reaction_date;
    }

    /**
     * Metodo getTherapy
     *
     * @return La terapia farmacologica
     */
    public Therapy getTherapy() {
        return therapy;
    }
}
