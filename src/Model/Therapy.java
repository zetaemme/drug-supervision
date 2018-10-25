package Model;

import Model.Exceptions.NullDailyFrequencyException;
import Model.Exceptions.NullDoseException;
import Model.Exceptions.NullStringException;

import java.util.Date;

public class Therapy {
    private final String drug;
    private final double dose;
    private final int daily_frequency;
    private final String starting_date;
    private final String ending_date;

    public Therapy(String drug, double dose, int daily_frequency, String starting_date, String ending_date) throws NullStringException, NullDoseException, NullDailyFrequencyException {
        if(drug.length() == 0) {
            throw new NullStringException();
        } else if(dose < 1) {
            throw new NullDoseException();
        } else if(daily_frequency < 1) {
            throw new NullDailyFrequencyException();
        }

        this.drug = drug;
        this.dose = dose;
        this.daily_frequency = daily_frequency;
        this.starting_date = starting_date;
        this.ending_date = ending_date;
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

    public String getStarting_date() {
        return starting_date;
    }

    public String getEnding_date() {
        return ending_date;
    }
}
