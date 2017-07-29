package API;

import java.util.List;

import Model.SpecialityModel;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by gajendrasingh on 11/15/2015.
 */
public interface ICategoryRestEndPoint {

    @GET("/agent/category/all")
   // Call<List<SpecialityModel>> getSpeciality();
    void  getSpeciality(Callback<List<SpecialityModel>> callback);
}
