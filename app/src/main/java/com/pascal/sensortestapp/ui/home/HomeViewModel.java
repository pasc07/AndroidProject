package com.pascal.sensortestapp.ui.home;

import android.webkit.WebView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    WebView webView;
    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Graphique 2");
        //This is home fragment
    }

    public LiveData<String> getText() {
        return mText;
    }
}