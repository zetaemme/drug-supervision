public class Patient {
    private final Date birthday;
    private final String province;
    private final String profession;

    public Patient(Date birthday, String province, String profession) {
        this.birthday = birthday;
        this.province = province;
        this.profession = profession;
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
