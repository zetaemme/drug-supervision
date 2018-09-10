package Model; /**
 * @author Andrea Soglieri e Mattia Zorzan
 */
import Model.Exceptions.NullDailyFrequencyException;
import Model.Exceptions.NullDoseException;
import Model.Exceptions.NullStringException;

public class Therapy {
    /**
     * Il paziente a cui si riferisce
     */
    private final Patient patient;
    /**
     * La segnalazione a cui è legata
     */
    private final Report report;
    /**
     * Il farmaco somministrato
     */
    private final String drug;
    /**
     * La dose del farmaco
     */
    private final double dose;
    /**
     * La frequenza giornaliera
     */
    private final int daily_frequency;
    /**
     * Data inizio terapia
     */
    private final Date starting_date;
    /**
     * Data fine trapia
     */
    private final Date ending_date;

    /**
     * Metodo costruttore
     *
     * @param patient
     * @param report
     * @param drug
     * @param dose
     * @param daily_frequency
     * @param starting_date
     * @param ending_date
     */
    private Therapy(Patient patient, Report report, String drug, double dose, int daily_frequency, Date starting_date, Date ending_date) throws NullStringException, NullDoseException, NullDailyFrequencyException {
        if(drug.length() == 0) {
            throw new NullStringException();
        } else if(dose < 1) {
            throw new NullDoseException();
        } else if(daily_frequency < 1) {
            throw new NullDailyFrequencyException();
        }

        this.patient = patient;
        this.report = report;
        this.drug = drug;
        this.dose = dose;
        this.daily_frequency = daily_frequency;
        this.starting_date = starting_date;
        this.ending_date = ending_date;
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
     * Metodo getReport
     *
     * @return Ritorna i dati della segnalazione
     */
    public Report getReport() {
        return report;
    }

    /**
     * Metodo getDrug
     *
     * @return Il farmaco somministrato
     */
    public String getDrug() {
        return drug;
    }

    /**
     * Metodo getDose
     *
     * @return La dose del farmaco
     */
    public double getDose() {
        return dose;
    }

    /**
     * Metodo getDaily_frequency
     *
     * @return La frequenza giornaliera con cui viene assunto il farmaco
     */
    public int getDaily_frequency() {
        return daily_frequency;
    }

    /**
     * Metodo getStarting_date
     *
     * @return La data di inizio terapia
     */
    public Date getStarting_date() {
        return starting_date;
    }

    /**
     * Metodo getEnding_date
     *
     * @return La data di fine terapia
     */
    public Date getEnding_date() {
        return ending_date;
    }
}
