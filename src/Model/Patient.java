/**
 * @author Andrea Soglieri e Mattia Zorzan
 */
import Model.Exceptions.NullStringException;

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
     * Il fattore di rischio
     */
    private final RiskFactor risk_factor;

    /**
     * Metodo costruttore
     *
     * @param birthday
     * @param province
     * @param profession
     * @param risk_factor
     */
    private Patient(Date birthday, String province, String profession, RiskFactor risk_factor) throws NullStringException {
        if(province.length() == 0 || profession.length() == 0) {
            throw new NullStringException();
        }

        this.birthday = birthday;
        this.province = province;
        this.profession = profession;
        this.risk_factor = risk_factor;
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
