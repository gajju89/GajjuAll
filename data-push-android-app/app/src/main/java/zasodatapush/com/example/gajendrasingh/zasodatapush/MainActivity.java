package zasodatapush.com.example.gajendrasingh.zasodatapush;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import API.AgentPicRestEndPoint;
import API.AgentServiceAPI;
import API.ICategoryRestEndPoint;
import GPS.GPSTracker;
import Model.AgentModel;
import Model.SpecialityModel;

import retrofit.Callback;

import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.JacksonConverter;

import retrofit.mime.TypedFile;
import retrofit.mime.TypedString;

public class MainActivity extends AppCompatActivity {
    String API = "http://192.168.0.108:8081";
    private static final int CAMERA_REQUEST = 1888;
    private Button btnAddPhoto, btnAdSpeciality,btnSend,btnLocation;
    private EditText firstNameEditText,lastNameEditText,emailEditText,mobileEditText,addressEditText;
    TextView textViewLocation1,textViewLocation2;
    MultiAutoCompleteTextView multiAutoCompleteTextView;
    private ImageView imageView;
    private LinearLayout linearLayout;
    private LinearLayout linearSpeciality;
    protected Double latitude,longitude;

    GPSTracker gpsTracker ;

  // LinkedList<MultiAutoCompleteTextView> multiAutoCompleteTextViewLinkedList;
  // LinkedList<ArrayAdapter> multiAutoCompleteTextViewLinkedList;
  LinkedList<MultiAutoCompleteTextView> multiAutoCompleteTextViewLinkedList=new LinkedList<MultiAutoCompleteTextView>();
    LinkedList<Bitmap> bitmapLinkedList=new LinkedList<Bitmap>();

     ImageView newImageView ;





    RestAdapter retrofit = new RestAdapter.Builder()
            .setEndpoint(API).setConverter(new JacksonConverter()).build();

    ICategoryRestEndPoint git = retrofit.create(ICategoryRestEndPoint.class);
    AgentServiceAPI agentServiceAPI=retrofit.create(AgentServiceAPI.class);
    private List<SpecialityModel> specialityModels;
    AgentPicRestEndPoint agentPicRestEndPoint=retrofit.create(AgentPicRestEndPoint.class);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        btnAdSpeciality = (Button) findViewById(R.id.buttonAddSpeciality);
        linearSpeciality=(LinearLayout)findViewById(R.id.specialityLinearId);

        btnSend=(Button)findViewById(R.id.butttonSendId);



        /*btnLocation =(Button)findViewById(R.id.appButtonId);
        textViewLocation1 = (TextView) findViewById(R.id.location1TextViewId);
        textViewLocation2 = (TextView) findViewById(R.id.location2TextViewId);*/


        btnSend=(Button)findViewById(R.id.butttonSendId);


        firstNameEditText=(EditText)findViewById(R.id.editTextFirstNameId);
        lastNameEditText=(EditText)findViewById(R.id.editTextLastNameId);
        emailEditText=(EditText)findViewById(R.id.editTextEmailId);
        mobileEditText=(EditText)findViewById(R.id.editTextMobileNumberId);
        addressEditText=(EditText) findViewById(R.id.editTextAddressId);
        imageView=(ImageView)findViewById(R.id.imageViewId);










                gpsTracker = new GPSTracker(MainActivity.this);
                // check if GPS enabled
                if(gpsTracker.canGetLocation()){

                     latitude = gpsTracker.getLatitude();
                     longitude = gpsTracker.getLongitude();
                    /*textViewLocation1.setText(String.valueOf(latitude));
                    textViewLocation2.setText(String.valueOf(longitude));*/

                    // \n is for new line
                   // Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
                }else{
                    // can't get location
                    // GPS or Network is not enabled
                    // Ask user to enable GPS/network in settings
                    gpsTracker.showSettingsAlert();
                }











        addPhoto();
        addSpeciality();
        agentSend();





    }

    public void sendPic(){
        Iterator<Bitmap>  itr=bitmapLinkedList.iterator();
        while(itr.hasNext()) {

            String bitMapFile=itr.next().toString();
            File photoFile=new File(bitMapFile);
            TypedFile tpFile=new TypedFile("image/jpeg",photoFile);
            TypedString emailId = new TypedString(emailEditText.getText().toString());

            agentPicRestEndPoint.uploadFile(emailId, tpFile, new Callback<String>() {
                @Override
                public void success(String s, Response response) {

                }

                @Override
                public void failure(RetrofitError error) {

                }
            });
        }

    }


public void agentSend() {
    btnSend.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Iterator<MultiAutoCompleteTextView> iterable = multiAutoCompleteTextViewLinkedList.iterator();
           // Iterator<ArrayAdapter> iterable = multiAutoCompleteTextViewLinkedList.iterator();
            while (iterable.hasNext()) {
                String speciality = iterable.next().getText().toString()
                        ;


                AgentModel agentModel = new AgentModel();
                agentModel.setFname(firstNameEditText.getText().toString());
                agentModel.setLname(lastNameEditText.getText().toString());
                agentModel.setEmail(emailEditText.getText().toString());
                agentModel.setMobile(mobileEditText.getText().toString());
                agentModel.setAddress(addressEditText.getText().toString());
                agentModel.setLattitude(String.valueOf(latitude));
                agentModel.setLongitude(String.valueOf(longitude));
                agentModel.setSpeciality(speciality);

                    /*//Call<AgentModel> call = agentServiceAPI.saveAgentCall(agentModel);
                    call.enqueue(new Callback<AgentModel>() {

                        @Override
                        public void onResponse(Response<AgentModel> response, Retrofit retrofit) {

                        }

                        @Override
                        public void onFailure(Throwable t) {

                        }
                    });*/

                agentServiceAPI.saveAgentCall(agentModel, new Callback<AgentModel>() {

                    @Override
                    public void success(AgentModel model, Response response) {

                    }

                    @Override
                    public void failure(RetrofitError error) {

                    }


                });


            }
            sendPic();
            Toast.makeText(MainActivity.this,"Data Successfully sent",Toast.LENGTH_LONG).show();
        }
    });
}




    public void addPhoto() {
        btnAddPhoto = (Button) findViewById(R.id.butttonTakePhoto);
        imageView = (ImageView) findViewById(R.id.imageViewId);
        linearLayout = (LinearLayout) findViewById(R.id.linearLayoutPhoto);




        btnAddPhoto.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {


                Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, CAMERA_REQUEST);
            }
        });




    }

    public void addSpeciality()
    {



        /*Call<List<SpecialityModel>> call= git.getSpeciality();
        call.enqueue(new Callback<List<SpecialityModel>>(){
            @Override
            public void onResponse(Response<List<SpecialityModel>> response, Retrofit retrofit) {
                specialityModels= response.body();
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });*/

        git.getSpeciality(new Callback<List<SpecialityModel>>() {


            @Override
            public void success(List<SpecialityModel> specialityModels, Response response) {
                MainActivity.this.specialityModels=specialityModels;
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });


        btnAdSpeciality.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        multiAutoCompleteTextView = new MultiAutoCompleteTextView(MainActivity.this);
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,
                                android.R.layout.simple_dropdown_item_1line, getSpecialityNames());
                        adapter.areAllItemsEnabled();
                        multiAutoCompleteTextView.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
                        multiAutoCompleteTextView.setThreshold(1);
                        multiAutoCompleteTextView.setAdapter(adapter);

                        multiAutoCompleteTextViewLinkedList.add(multiAutoCompleteTextView);
                       // multiAutoCompleteTextViewLinkedList.add(adapter);
                        linearSpeciality.addView(multiAutoCompleteTextView);



                    }

                });
    }








    public List<String> getSpecialityNames()
    {
        List<String> specialityName=new ArrayList<>();
        for(SpecialityModel model:specialityModels)
        {
            if(model.getCategoryname()!=null) {
                specialityName.add(model.getCategoryname());
            }
    }
        return specialityName;
    }


    private static Bitmap codec(Bitmap src, Bitmap.CompressFormat format,
                                int quality) {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        src.compress(format, quality, os);

        byte[] array = os.toByteArray();
        return BitmapFactory.decodeByteArray(array, 0, array.length);
    }




    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {

            Bitmap photo = (Bitmap) data.getExtras().get("data");

            Bitmap bJPGcompress = codec(photo, Bitmap.CompressFormat.JPEG, 80);



            linearLayout.addView(createImageView(photo));
            bitmapLinkedList.add(bJPGcompress);




        }
    }
    public ImageView createImageView(Bitmap bitmap)
    {
      final ImageView  newImageView =new ImageView(this);
        final LinearLayout.LayoutParams params=new LinearLayout.LayoutParams((int)convertDpToPixel(200f,this), LinearLayout.LayoutParams.MATCH_PARENT);
        params.setMargins((int) convertDpToPixel(10f, this), 0, (int) convertDpToPixel(10f, this), 0);
        newImageView.setLayoutParams(params);
        newImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        newImageView.setImageBitmap(bitmap);
        newImageView.setId(bitmapLinkedList.size());
       // imageViewHashSet.add(bitmap);

        newImageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
             linearLayout.removeView(newImageView);
                return true;
            }
        });


        //Bitmap bJPGcompress = codec(bitmap, Bitmap.CompressFormat.JPEG, 80);
        // newImageView.setId(1);
       // imageViewHashSet.add(newImageView);
        return newImageView;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public float convertDpToPixel(float dp, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return px;
    }


    public float convertPixelsToDp(float px, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float dp = px / (metrics.densityDpi / 160f);
        return dp;
    }





}
