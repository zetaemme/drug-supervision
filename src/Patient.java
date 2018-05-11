/**
 * @author Andrea Soglieri & Mattia Zorzan
 */

public class Patient {
    /**
     * Data di nascita
     */
    private final Date birthday;
    /**
     * Provincia di residenza
     */
    private final String province;
    /**
     * Professione
     */
    private final String profession;

    /**
     * Metodo costruttore
     *
     * @param birthday
     * @param province
     * @param profession
     */
    public Patient(Date birthday, String province, String profession) {
        this.birthday = birthday;
        this.province = province;
        this.profession = profession;
    }

    /**
     * Metodo hashCode
     *
     * @return L'hash del patient
     */
    @Override
    public int hashCode() {
        return birthday.hashCode() ^ province.hashCode() ^ profession.hashCode();
    }

    /**
     * Metodo getBirthday
     *
     * @return La data di nascita
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * Metodo getProvince
     *
     * @return La provincia di residenza
     */
    public String getProvince() {
        return province;
    }

    /**
     * Metodo getProfession
     *
     * @return La professione
     */
    public String getProfession() {
        return profession;
    }
}
