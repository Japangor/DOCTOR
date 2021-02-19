package com.doctorjiyo.app.activity.BookAppointment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.doctorjiyo.app.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class doctorselect extends AppCompatActivity {


    private ListView l1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctorselect);

        l1 = (ListView) findViewById(R.id.l1);

        JsonWork jsonWork = new JsonWork();
        jsonWork.execute();
    }


    private class JsonWork extends AsyncTask<String,String,List<doctormodel>>{


        HttpURLConnection httpURLConnection = null;
        BufferedReader bufferedReader = null;
        String  FullJsonData;
        @Override
        protected List<doctormodel> doInBackground(String... strings) {

            try {
                URL url = new URL("http://manage.doctorjiyo.com/api/DoctorsListAPI");
                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.connect();
                InputStream inputStream = httpURLConnection.getInputStream();
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuffer stringBuffer= new StringBuffer();
                String line="";

                while ((line= bufferedReader.readLine())!= null){

                    stringBuffer.append(line);

                }
                FullJsonData =  stringBuffer.toString();
                List<doctormodel> jsonModelList = new ArrayList<>();

                JSONObject jsonStartingObject = new JSONObject(FullJsonData);
                JSONArray  jsonStudentArray = jsonStartingObject.getJSONArray("result");


                for(int i=0; i<jsonStudentArray.length(); i++) {

                    JSONObject jsonUnderArrayObject = jsonStudentArray.getJSONObject(i);

                    doctormodel jsonModel = new doctormodel();
                    jsonModel.setName(jsonUnderArrayObject.getString("DoctorID"));
                    jsonModel.setGrade(jsonUnderArrayObject.getString("DoctorName"));
                    jsonModel.setSection(jsonUnderArrayObject.getString("DoctorName"));
                    jsonModelList.add(jsonModel);
                }

                return jsonModelList;


            } catch (JSONException | IOException e) {
                e.printStackTrace();
            } finally{

                httpURLConnection.disconnect();
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            return null;
        }

        @Override
        protected void onPostExecute(List<doctormodel> jsonModels) {
            super.onPostExecute(jsonModels);
            CustomAdapter adapter = new CustomAdapter(getApplicationContext(),R.layout.sample,jsonModels);
            l1.setAdapter(adapter);


        }
    }

}