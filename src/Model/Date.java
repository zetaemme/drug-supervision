/**
 * @author Andrea Soglieri e Mattia Zorzan
 */
import Model.Exceptions.IllegalDateException;

public class Date {
    /**
     * Data in formato numerico
     */
    private final int day, month, year;
    /**
     * Array di comodo per salvare i giorni massimi in un mese
     */
    private final int[] daysPerMonth = {31, isLeapYear() ? 29 : 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    /**
     * Metodo costruttore
     *
     * @param day
     * @param month
     * @param year
     * @throws IllegalDateException
     */
    private Date(int day, int month, int year) throws IllegalDateException {
        if(day < 1 || day > daysPerMonth[month - 1] || month < 1 || month > 12 || year < 1900 || year > 2018) {
            throw new IllegalDateException();
        }

        this.day = day;
        this.month = month;
        this.year = year;
    }

    /**
     * Metodo isLeapYear
     *
     * @return true: Anno bisestile, false: Anno normale
     */
    private boolean isLeapYear() {
        return year % 4 == 0 && (year % 100 != 0 || year % 400 == 0);
    }

    /**
     * Metodo hashCode
     *
     * @return L'hash della data
     */
    @Override
    public int hashCode() {
        return day ^ month ^ year;
    }

    /**
     * Metodo getDay
     *
     * @return Il giorno
     */
    public int getDay() {
        return day;
    }

    /**
     * Metodo getMonth
     *
     * @return Il mese
     */
    public int getMonth() {
        return month;
    }

    /**
     * Metodo getYear
     *
     * @return L'anno
      */
    public int getYear() {
        return year;
    }
}
