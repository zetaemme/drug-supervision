package Model;

import Model.Exceptions.NullStringException;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class Patient {
    private final SimpleStringProperty idPatient;
    private final SimpleStringProperty birthday;
    private final SimpleStringProperty province;
    private final SimpleStringProperty profession;
    private final SimpleObjectProperty<RiskFactor> risk_factor;

    public Patient(String idPatient, String birthday, String province, String profession, RiskFactor risk_factor) throws NullStringException {
        if(province.length() == 0 || profession.length() == 0 || idPatient.length() == 0) {
            throw new NullStringException();
        }

        this.idPatient = new SimpleStringProperty(province + birthday.replace("-", "") +
                                                    profession.substring(0 , 3).toUpperCase());
        this.birthday = new SimpleStringProperty(birthday);
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

    public SimpleStringProperty getIdProperty() {
        return idPatient;
    }

    public String getBirthday() {
        return birthday.get(); 
    }

    public void setBirthday(String birthday) {
        this.birthday.set(birthday);
    }
    
    public SimpleStringProperty getBirthdayProperty() {
        return birthday;
    }

    public String getProvince() {
        return province.get();
    }

    public void setProvince(String province) {
        this.province.set(province);
    }

    public SimpleStringProperty getProvinceProperty() {
        return province;
    }

    public String getProfession() {
        return profession.get();
    }

    public void setProfession(String profession) {
        this.profession.set(profession);
    }

    public SimpleStringProperty getProfessionProperty() {
        return profession;
    }

    public RiskFactor getRisk_factor() { 
        return risk_factor.get(); 
    }

    public void setRiskFactor(RiskFactor risk_factor) {
        this.risk_factor.set(risk_factor);
    }

    public SimpleObjectProperty<RiskFactor> getRiskFactorProperty() {
        return risk_factor;
    }
}
