package Model.Utils.DAOs;

public interface TherapyDao {
    void createTherapy(String idTherapy, String drugName, int dose, String startingDate, String endingDate, int dailyFrequency);
}
