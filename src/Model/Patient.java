package Model;

import Model.Exceptions.NullStringException;
import Model.Utils.Date;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Patient {
    private final StringProperty first_name;
    private final StringProperty last_name;
    private final ObjectProperty<Date> birthday;
    private final StringProperty province;
    private final StringProperty profession;
    private final ObjectProperty<RiskFactor> risk_factor;

    private Patient(String first_name, String last_name, Date birthday, String province, String profession, RiskFactor risk_factor) throws NullStringException {
        if(province.length() == 0 || profession.length() == 0 || first_name.length() == 0 || last_name.length() == 0) {
            throw new NullStringException();
        }

        this.first_name = new SimpleStringProperty(first_name);
        this.last_name = new SimpleStringProperty(last_name);
        this.birthday = new SimpleObjectProperty<>(birthday);
        this.province = new SimpleStringProperty(province);
        this.profession = new SimpleStringProperty(profession);
        this.risk_factor = new SimpleObjectProperty<>(risk_factor);
    }

    @Override
    public int hashCode() {
        return birthday.get().hashCode() ^ province.get().hashCode() ^ profession.get().hashCode();
    }

    public String getFirstName() {
        return first_name.get();
    }

    public void setFirstName(String first_name) { 
        this.first_name.set(first_name); 
    }

    public StringProperty getFirstNameProperty() {
        return first_name;
    }

    public String getLastName() { 
        return last_name.get(); 
    }

    public void setLastName(String last_name) { 
        this.last_name.set(last_name); 
    }

    public StringProperty getLastNameProperty() {
        return last_name;
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
