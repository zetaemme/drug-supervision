package Model.Utils.DAOs;

import Model.Therapy;

import java.util.List;

public interface TherapyDao {
    List<Therapy> getAllTherapies();
    void createTherapy(String idTherapy, String drugName, int dose, String startingDate, String endingDate, int dailyFrequency);
}
