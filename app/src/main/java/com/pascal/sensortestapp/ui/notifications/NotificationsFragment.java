package com.pascal.sensortestapp.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.pascal.sensortestapp.R;
import com.pascal.sensortestapp.ui.home.ApiCalls;
import com.pascal.sensortestapp.ui.home.NetworkAsyncTask;

public class NotificationsFragment extends Fragment implements NetworkAsyncTask.Listeners, View.OnClickListener{

    private NotificationsViewModel notificationsViewModel;
    private TextView textView2;
    private Button getDataNotif;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        final TextView textView = root.findViewById(R.id.text_notifications);
        notificationsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        textView2 = root.findViewById(R.id.text_notifications2);
        getDataNotif =root.findViewById(R.id.getDataNotif);
        getDataNotif.setOnClickListener(this);
        return root;
    }


    private void executeHttpRequest(){
        new NetworkAsyncTask(this).execute("https://api.thingspeak.com/channels/1354241/fields/2.json?results=2");
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
        this.textView2.setText("Downloading...");
    }

    private void updateUIWhenStopingHTTPRequest(String response){
        this.textView2.setText(response);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        this.executeHttpRequest();
    }
}