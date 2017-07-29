package Model;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)

@JsonPropertyOrder({
        "objectid",
        "categoryname"
})
public class SpecialityModel {

    @JsonProperty("objectid")
    private String objectid;
    @JsonProperty("categoryname")
    private String categoryname;


    /**
     *
     * @return
     * The objectid
     */
    @JsonProperty("objectid")
    public String getObjectid() {
        return objectid;
    }

    /**
     *
     * @param objectid
     * The objectid
     */
    @JsonProperty("objectid")
    public void setObjectid(String objectid) {
        this.objectid = objectid;
    }

    /**
     *
     * @return
     * The categoryname
     */
    @JsonProperty("categoryname")
    public String getCategoryname() {
        return categoryname;
    }

    /**
     *
     * @param categoryname
     * The categiryname
     */
    @JsonProperty("categoryname")
    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }



}
