package Model.Utils;

import Model.Exceptions.IllegalDateException;

public class Date {
    private final int day, month, year;
    private final int[] daysPerMonth = {31, isLeapYear() ? 29 : 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    private Date(int day, int month, int year) throws IllegalDateException {
        if(day < 1 || day > daysPerMonth[month - 1] || month < 1 || month > 12 || year < 1900 || year > 2018) {
            throw new IllegalDateException();
        }

        this.day = day;
        this.month = month;
        this.year = year;
    }

    private boolean isLeapYear() {
        return year % 4 == 0 && (year % 100 != 0 || year % 400 == 0);
    }

    @Override
    public int hashCode() {
        return day ^ month ^ year;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }
}
