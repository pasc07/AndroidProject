package com.pascal.sensortestapp.ui.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.work.ListenableWorker;
import androidx.work.PeriodicWorkRequest;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.pascal.sensortestapp.GithubUser;
import com.pascal.sensortestapp.HumidityData;
import com.pascal.sensortestapp.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Scheduler;

public class HomeFragment extends Fragment implements NetworkAsyncTask.Listeners, View.OnClickListener, ApiCalls.Callbacks{
    private TextView textView2 ;
    private Button getData;
    private WebPage webPage =new WebPage();
    public WebView webView;
    private WebView webView2;

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        // Il faut la connexion internet
        webView =(WebView) root.findViewById(R.id.webView);
        webView2 =(WebView) root.findViewById(R.id.webView2);
        // Enable Javascript
        webView.getSettings().setJavaScriptEnabled(true);
        webView2.getSettings().setJavaScriptEnabled(true);
        // Force links and redirects to open in the WebView instead of in a browser
        webView.setWebViewClient(new WebViewClient());
        webView2.setWebViewClient(new WebViewClient());

        //webView.loadUrl("https://google.fr");
        /* Load web page in webView
         */

          /*
        getData data = new getData();

        double[] resistance = data.getResistance();
        //Ajout des donnee reel de mesure

        for (int i = 0; i < resistance.length; i++) {
            if(resistance[i] >1) {

                 //* J'ai augmenter la dynamique

                webPage.setWeb("abscisse", resistance[i]/10000 -940, resistance[i]/10000 -900);
            }
        }
        */
        this.executeHttpRequest();
        PeriodicWorkRequest fetchData =
                new PeriodicWorkRequest.Builder(GetDataAsyncTask.class, 1000, TimeUnit.MICROSECONDS)
                // Constraints
                .build();


       //test deserialiser
        //JsonDeserialiserForTest test =new JsonDeserialiserForTest();

        TextView textView2 = root.findViewById(R.id.textView2);
        //textView2.setText(text.getFalse3());

        //--------------------------------------
        return root;
    }

    // 4 - Execute HTTP request and update UI
    private void executeHttpRequestWithRetrofit(){
        this.updateUIWhenStartingHTTPRequest();
        ApiCalls.fetchUserFollowing((ApiCalls.Callbacks) this, "JakeWharton");
    }

    // 2 - Override callback methods

    @Override
    public void onResponse(@Nullable List<GithubUser> users) {
        // 2.1 - When getting response, we update UI
        if (users != null) this.updateUIWithListOfUsers(users);
    }

    @Override
    public void onFailure() {
        // 2.2 - When getting error, we update UI
        this.updateUIWhenStopingHTTPRequest("An error happened !");
    }


            // ------------------
            //  UPDATE UI
            // ------------------


    // 3 - Update UI showing only name of users
    private void updateUIWithListOfUsers(List<GithubUser> users){
        StringBuilder stringBuilder = new StringBuilder();
        for (GithubUser user : users){
            stringBuilder.append("-"+user.getLogin()+"\n");
        }
        updateUIWhenStopingHTTPRequest(stringBuilder.toString());
    }


    //---------------------------------------------------------------------------
    // -----------------
    // ACTIONS
    // -----------------



    // ------------------
    //  HTTP REQUEST
    // ------------------

   private void executeHttpRequest(){

            new NetworkAsyncTask(this).execute("https://api.thingspeak.com/channels/1354241/fields/2.json?results=1");

    }

    @Override
    public void onPreExecute() {
        this.updateUIWhenStartingHTTPRequest();
    }

    @Override
    public void doInBackground() { }

    @Override
    public void onPostExecute(String json) {
        this.updateUIWhenStopingHTTPRequest(json);
    }

    // ------------------
    //  UPDATE UI
    // ------------------

    private void updateUIWhenStartingHTTPRequest(){
       // this.textView2.setText("Downloading...");
    }

    private void updateUIWhenStopingHTTPRequest(String response){
        String jsonFile = response;
        String[] file;
        double humidity;
        file = jsonFile.split(",");
        jsonFile = "";
        jsonFile=file[file.length-3]+","+file[file.length-2]+","+file[file.length-1];
        jsonFile=jsonFile.substring(8, jsonFile.length() - 1);

        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonArray array = parser.parse(jsonFile).getAsJsonArray();
        List<HumidityData> list = new ArrayList<HumidityData>();
        list.add(gson.fromJson(array.get(0), HumidityData.class));
        humidity = Double.parseDouble(list.get(0).getField2());
        //Mis a jour de Web
        webPage.setWeb("abscisse", humidity, humidity - 3);

        // Reconstitution complete de la page html en y ajoutant la fin
        String html = webPage.getWeb() + webPage.getWebEnd();
        //Graph 1 et 2
        webView.loadData(html, "text/html", "UTF-8");
       // this.textView2.setText(response);
    }


    @Override
    public void onClick(View v) {
        this.executeHttpRequestWithRetrofit();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        webView.destroy();
    }

//Async Task
    public class GetDataAsyncTask extends Worker {
        private WebPage webPage;
        private WebView webView;

        public GetDataAsyncTask(
                @NonNull Context context,
                @NonNull WorkerParameters workerParams, WebPage webPage) {
            super(context, workerParams);
            this.webPage = webPage;
        }

        @NonNull
        @Override
        public Result doWork() {
            jsonTest();
            return Result.success();
        }

        public void jsonTest() {
            String jsonFile = "";
            String[] file;
            double humidity;
            file = jsonFile.split(",");
            jsonFile = "";
            jsonFile=file[file.length-3]+","+file[file.length-2]+","+file[file.length-1];
            jsonFile=jsonFile.substring(8, jsonFile.length() - 1);

            Gson gson = new Gson();
            JsonParser parser = new JsonParser();
            JsonArray array = parser.parse(jsonFile).getAsJsonArray();
            List<HumidityData> list = new ArrayList<HumidityData>();
            list.add(gson.fromJson(array.get(0), HumidityData.class));
            humidity = Double.parseDouble(list.get(0).getField2());
            //Mis a jour de Web
            webPage.setWeb("abscisse", humidity, humidity - 3);

            // Reconstitution complete de la page html en y ajoutant la fin
            String html = webPage.getWeb() + webPage.getWebEnd();
            //Graph 1 et 2
            webView.loadData(html, "text/html", "UTF-8");

        }
    }


}