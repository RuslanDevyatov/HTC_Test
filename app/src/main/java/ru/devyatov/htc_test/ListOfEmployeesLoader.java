package ru.devyatov.htc_test;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import org.json.JSONObject;

public class ListOfEmployeesLoader extends AsyncTaskLoader<JSONObject> {

    private boolean isItLoaded = false;

    public ListOfEmployeesLoader(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading(){
        super.onStartLoading();

        if (isItLoaded) {
            return;
        }

        forceLoad();
    }

    @Nullable
    @Override
    public JSONObject loadInBackground() {

        JSONObject result = URLConnector.getJSONObject();

        if (result != null) {
            isItLoaded = true;
        }
        return result;
    }
}
