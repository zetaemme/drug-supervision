package Model;

import Model.Exceptions.NullStringException;
import Model.Utils.Date;

public class Patient {
    private final String first_name;
    private final String last_name;
    private final Date birthday;
    private final String province;
    private final String profession;
    private final RiskFactor risk_factor;

    private Patient(String first_name, String last_name, Date birthday, String province, String profession, RiskFactor risk_factor) throws NullStringException {
        if(province.length() == 0 || profession.length() == 0 || first_name.length() == 0 || last_name.length() == 0) {
            throw new NullStringException();
        }

        this.first_name = first_name;
        this.last_name = last_name;
        this.birthday = birthday;
        this.province = province;
        this.profession = profession;
        this.risk_factor = risk_factor;
    }

    @Override
    public int hashCode() {
        return birthday.hashCode() ^ province.hashCode() ^ profession.hashCode();
    }

    public String getFirstName() {
        return first_name;
    }

    public String getLastName() {
        return last_name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getProvince() {
        return province;
    }

    public String getProfession() {
        return profession;
    }
}
