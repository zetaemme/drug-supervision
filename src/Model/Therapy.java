package Model;

import Model.Exceptions.NullDailyFrequencyException;
import Model.Exceptions.NullDoseException;
import Model.Exceptions.NullStringException;
import Model.Utils.Date;

public class Therapy {
    private final Patient patient;
    private final Report report;
    private final String drug;
    private final double dose;
    private final int daily_frequency;
    private final Date starting_date;
    private final Date ending_date;

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

    public Patient getPatient() {
        return patient;
    }

    public Report getReport() {
        return report;
    }

    public String getDrug() {
        return drug;
    }

    public double getDose() {
        return dose;
    }

    public int getDaily_frequency() {
        return daily_frequency;
    }

    public Date getStarting_date() {
        return starting_date;
    }

    public Date getEnding_date() {
        return ending_date;
    }
}
