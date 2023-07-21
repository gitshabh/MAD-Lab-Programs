package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Xml;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.project.myapplication.R;

import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    Button btnXmlParse,btnJsonParse;
    TextView txtXmlData,txtJsonData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BindUIData();
        btnXmlParse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ReadXmlData();
            }
        });
        btnJsonParse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ReadJsonData();
            }
        });
    }
    private void BindUIData(){
        btnXmlParse=(Button) findViewById(R.id.btnxml);
        btnJsonParse=(Button) findViewById(R.id.btnjson);
        txtXmlData=(TextView) findViewById(R.id.xm);
        txtJsonData=(TextView) findViewById(R.id.js);
    }

    private void ReadXmlData(){

        try{
            StringBuilder stringValue=new StringBuilder();
            InputStream inputStream=getAssets().open("citydetails.xml");
            XmlPullParser parser= Xml.newPullParser();
            parser.setInput(inputStream,null);

            int eventType=parser.getEventType();

            while (eventType!=XmlPullParser.END_DOCUMENT){
                String tagName;
                if(eventType==XmlPullParser.START_TAG){
                    tagName=parser.getName();
                    if(!tagName.equalsIgnoreCase("Details")) {
                        parser.next();
                        stringValue.append(tagName+" : "+parser.getText()+"\n");
                    }
                }
                eventType=parser.next();
            }
            txtXmlData.setText(stringValue);
        }catch(Exception ex){
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
    }

    private void ReadJsonData(){
        try{
            StringBuilder stringValue=new StringBuilder();
            InputStream inputStream=getAssets().open("citydetails.json");
            int size=inputStream.available();
            byte[] buffer=new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            String fileData=new String(buffer,"UTF-8");
            JSONObject jsonObject=new JSONObject(fileData);
            String cityname=jsonObject.getJSONObject("Details").getString("City_name");
            if(cityname.length()>0){
                stringValue.append("City_name : "+cityname+"\n");
            }
            String latitude=jsonObject.getJSONObject("Details").getString("Latitude");
            if(latitude.length()>0){
                stringValue.append("Latitude : "+latitude+"\n");
            }
            String longitude=jsonObject.getJSONObject("Details").getString("Longitude");
            if(longitude.length()>0){
                stringValue.append("Longitude : "+longitude+"\n");
            }
            String temperature=jsonObject.getJSONObject("Details").getString("Temperature");
            if(temperature.length()>0){
                stringValue.append("Temperature : "+temperature+"\n");
            }
            String humidity=jsonObject.getJSONObject("Details").getString("Humidity");
            if(humidity.length()>0){
                stringValue.append("Humidity : "+humidity+"\n");
            }
            txtJsonData.setText(stringValue);
        }catch (Exception ex){
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
    }
}