package ru.devyatov.htc_test;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity
         implements LoaderManager.LoaderCallbacks<JSONObject>{

    private RecyclerView listOfEmployees;
    private ListOfEmployeesAdapter listOfEmployeesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();

        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;

        if (connMgr != null) {
            networkInfo = connMgr.getActiveNetworkInfo();
        }

        boolean isLoaded = !(getSupportLoaderManager().getLoader(0) == null);
        if (networkInfo != null && networkInfo.isConnected() || isLoaded) {
            getSupportLoaderManager().initLoader(0,Bundle.EMPTY,this);
        }else{
            Toast.makeText(this, "Нет подключения к сети",Toast.LENGTH_LONG).show();
        }
    }

    @NonNull
    @Override
    public Loader<JSONObject> onCreateLoader(int id, @Nullable Bundle args) {
        return new ListOfEmployeesLoader(this);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<JSONObject> loader, JSONObject data) {
        Gson gson = new Gson();
        Company company = gson.fromJson(data.toString(), Company.class);
        List<Employee> employees = company.getEmployees();
        Collections.sort(employees);
        List<Object> list = new ArrayList<>();
        list.add(company);
        list.addAll(employees);
        listOfEmployeesAdapter = new ListOfEmployeesAdapter(list);
        listOfEmployees = findViewById(R.id.rv_list_of_employees);
        listOfEmployees.setLayoutManager(new LinearLayoutManager(this));
        listOfEmployees.setAdapter(listOfEmployeesAdapter);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<JSONObject> loader) {

    }
}