package API;

import Model.AgentModel;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Created by gajendrasingh on 11/17/2015.
 */
public interface AgentServiceAPI {
    @POST("/agent/agent/entry")
    void saveAgentCall(@Body AgentModel model, Callback<AgentModel> callback);
}
