package Model;

import Model.Exceptions.NullStringException;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Date;

public class Patient {
    private final StringProperty idPatient;
    private final ObjectProperty<Date> birthday;
    private final StringProperty province;
    private final StringProperty profession;
    private final ObjectProperty<RiskFactor> risk_factor;

    public Patient(String idPatient, Date birthday, String province, String profession, RiskFactor risk_factor) throws NullStringException {
        if(province.length() == 0 || profession.length() == 0 || idPatient.length() == 0) {
            throw new NullStringException();
        }

        this.idPatient = new SimpleStringProperty(idPatient);
        this.birthday = new SimpleObjectProperty<>(birthday);
        this.province = new SimpleStringProperty(province);
        this.profession = new SimpleStringProperty(profession);
        this.risk_factor = new SimpleObjectProperty<>(risk_factor);
    }

    @Override
    public int hashCode() {
        return birthday.get().hashCode() ^ province.get().hashCode() ^ profession.get().hashCode();
    }

    public String getId() {
        return idPatient.get();
    }

    public void setId(String first_name) {
        this.idPatient.set(first_name);
    }

    public StringProperty getIdProperty() {
        return idPatient;
    }

    public Date getBirthday() { 
        return birthday.get(); 
    }

    public void setBirthday(Date birthday) {
        this.birthday.set(birthday);
    }
    
    public ObjectProperty<Date> getBirthdayProperty() {
        return birthday;
    }

    public String getProvince() {
        return province.get();
    }

    public void setProvince(String province) {
        this.province.set(province);
    }

    public StringProperty getProvinceProperty() {
        return province;
    }

    public String getProfession() {
        return profession.get();
    }

    public void setProfession(String profession) {
        this.profession.set(profession);
    }

    public StringProperty getProfessionProperty() {
        return profession;
    }

    public RiskFactor getRisk_factor() { 
        return risk_factor.get(); 
    }

    public void setRiskFactor(RiskFactor risk_factor) {
        this.risk_factor.set(risk_factor);
    }

    public ObjectProperty<RiskFactor> getRiskFactorProperty() {
        return risk_factor;
    }
}
