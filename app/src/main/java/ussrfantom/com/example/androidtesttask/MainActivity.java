package ussrfantom.com.example.androidtesttask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private EditText editTextName;
    private EditText editTextPassword;
    private Button buttonInput;
    private TextView textViewInfo;
    private String name;
    private String password;
    private String BASE_URL = "https://www.alarstudios.com/test/auth.cgi?";
    private String BASE_NAME = "username=";
    private String BASE_PASSWORD = "&password=";
    private String jsonURL;
    public static String code1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextName = findViewById(R.id.editTextTextLogin);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonInput = findViewById(R.id.buttonInput);
        textViewInfo = findViewById(R.id.textViewInfo);
    }

    public void buttonClick(View view) {
        name = editTextName.getText().toString();
        password = editTextPassword.getText().toString();
        jsonURL = createLink(name, password);
        DownloadJSONTask task = new DownloadJSONTask();
        task.execute(jsonURL);


    }

    public String createLink(String name, String password){
        String endUrl = BASE_URL + BASE_NAME + name + BASE_PASSWORD + password;
    return endUrl;
    }



        private class DownloadJSONTask extends AsyncTask<String, Void, String>{
            @Override
            protected String doInBackground(String... strings) {
                URL url = null;
             HttpsURLConnection httpsURLConnection = null;
             StringBuilder result = new StringBuilder();
                try {
                    url = new URL(strings[0]);
                    httpsURLConnection = (HttpsURLConnection) url.openConnection();
                    InputStream inputStream = httpsURLConnection.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    BufferedReader reader = new BufferedReader(inputStreamReader);
                    String line = reader.readLine();
                    while (line != null){
                        result.append(line);
                        line = reader.readLine();
                    }
                    return result.toString();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (httpsURLConnection != null){
                        httpsURLConnection.disconnect();
                    }
                }

                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    String name = jsonObject.getString("status");
                    String code = jsonObject.getString("code");

                    if (name.equals("ok")){
                        Intent intent = new Intent(MainActivity.this, Users.class);
                        intent.putExtra("code", code);
                        startActivity(intent);
                    }else{
                        textViewInfo.setText("Ошибка! Неверный логин или пароль!");
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

}