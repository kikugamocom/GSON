package th.vu.cs.nectec_gson;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.google.gson.Gson;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static  final  String URL =  "http://samples.openweathermap.org/data/2.5/weather?q=London%2Cuk&appid=b1b15e88fa797225412429c1c50c122a1";
    private ListView mListView;
    private CustomerAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView)findViewById(R.id.liseview1);


        new SimpleTask().execute(URL);


    }


    private void showData(String jsonString){

        Gson gson = new Gson();
        Blog blog = gson.fromJson(jsonString, Blog.class);



        List<Post> posts = blog.getWeather();

        mAdapter = new CustomerAdapter(this,posts);
        mListView.setAdapter(mAdapter);





    }

    private  class  SimpleTask extends AsyncTask<String,Void,String>{

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected void onPostExecute(String jsonString) {
            showData(jsonString);
        }


        @Override
        protected String doInBackground(String... urls) {
            String result = "";

            try {
                HttpGet httpGet = new HttpGet(urls[0]);
                HttpClient client = new DefaultHttpClient();

                HttpResponse response = client.execute(httpGet);

                int statusCode = response.getStatusLine().getStatusCode();

                if(statusCode == 200){
                    InputStream inputStream = response.getEntity().getContent();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    String line;
                    while ((line = reader.readLine()) != null ){
                       result += line;


                    }

                }


            } catch (IOException e) {
                e.printStackTrace();
            }


            return result;


        }


    }
}
