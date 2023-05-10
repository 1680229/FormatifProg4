package com.example.formatif4n6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.formatif4n6.databinding.ActivityMainBinding;
import com.example.formatif4n6.http.RetrofitUtil;
import com.example.formatif4n6.http.Service;
import com.example.formatif4n6.transfer.reponse;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Button btn1 = binding.buttonRoche;
        Button btn2 = binding.buttonPapier;
        Button btn3 = binding.buttonCiseaux;
        Button btn4 = binding.button4;
        Service service = RetrofitUtil.get();
        setTitle(R.string.maintitle);


        //Les boutons pour envoyer notre réponse.
        //On recoit ensuite la réponse du serveur pour savoir si on a gagné ?
        btn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Call<reponse> call = service.listRepos(1);
                Response<reponse> response = null;
                try {
                    response = call.execute();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                reponse resultat = response.body();
                Log.i("RETROFIT", String.valueOf(resultat));
                Toast.makeText(MainActivity.this, String.valueOf(resultat), Toast.LENGTH_SHORT).show();

            }
        });
        btn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Call<reponse> call = service.listRepos(2);
                Response<reponse> response = null;
                try {
                    response = call.execute();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                reponse resultat = response.body();
                Log.i("RETROFIT", String.valueOf(resultat));
                Toast.makeText(MainActivity.this, String.valueOf(resultat), Toast.LENGTH_SHORT).show();

            }
        });
        btn3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Call<reponse> call = service.listRepos(3);
                Response<reponse> response = null;
                try {
                    response = call.execute();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                reponse resultat = response.body();
                Log.i("RETROFIT", String.valueOf(resultat));
                Toast.makeText(MainActivity.this, String.valueOf(resultat), Toast.LENGTH_SHORT).show();

            }
        });
        btn4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent i = new Intent(MainActivity.this, GestionDesErreursActivity.class);
                startActivity(i);
            }
        });

    }
}