package API;

import retrofit.Callback;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.mime.TypedFile;
import retrofit.mime.TypedString;

/**
 * Created by gajendrasingh on 11/20/2015.
 */
public interface AgentPicRestEndPoint {
    @Multipart
    @POST("/agent/agentpic/upload")
    void uploadFile(@Part("emailId") TypedString emailId,
                                    @Part("photoFile") TypedFile photoFile, Callback<String> callback);
}
