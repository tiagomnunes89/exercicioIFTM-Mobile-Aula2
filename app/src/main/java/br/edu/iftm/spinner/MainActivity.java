package br.edu.iftm.spinner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.edu.iftm.spinner.config.RetrofitService;
import br.edu.iftm.spinner.entities.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    Spinner spinner;
    List<User> userList;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = findViewById(R.id.spinner_users);
        textView = findViewById(R.id.text_view_user);
        userList = new ArrayList<>();
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                User user = userList.get(position);
                textView.setText("Nome: " + user.getName()+"\n\nemail: "+ user.getEmail());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        searchData();
    }

    private void searchData() {
        RetrofitService.getServico().getUser().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                userList = response.body();
                List<String> userNamesList = new ArrayList<>();
                for (User user : userList) {
                    userNamesList.add(user.getName());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(
                        MainActivity.this, android.R.layout.simple_spinner_item, userNamesList);
                spinner.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.e("aula", t.getMessage());
            }
        });
    }
}
