package Model;

import Model.Exceptions.NullStringException;
import Model.Utils.Date;

public class Patient {
    private final Date birthday;
    private final String province;
    private final String profession;
    private final RiskFactor risk_factor;

    private Patient(Date birthday, String province, String profession, RiskFactor risk_factor) throws NullStringException {
        if(province.length() == 0 || profession.length() == 0) {
            throw new NullStringException();
        }

        this.birthday = birthday;
        this.province = province;
        this.profession = profession;
        this.risk_factor = risk_factor;
    }

    @Override
    public int hashCode() {
        return birthday.hashCode() ^ province.hashCode() ^ profession.hashCode();
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
