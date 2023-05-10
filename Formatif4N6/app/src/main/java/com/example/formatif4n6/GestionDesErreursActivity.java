package com.example.formatif4n6;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.formatif4n6.databinding.ActivityGestionBinding;
import com.example.formatif4n6.http.RetrofitUtil;
import com.example.formatif4n6.http.Service;
import com.example.formatif4n6.transfer.reponse;
import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GestionDesErreursActivity extends AppCompatActivity {
    private ActivityGestionBinding binding;
    protected void onCreate(Bundle savedInstanceState) {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion);
        binding = ActivityGestionBinding .inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setTitle(R.string.gestion_name);
        EditText et = binding.ET;
        Button button = binding.buttonTest;
        Service service = RetrofitUtil.get();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Call<String> call = service.motDePasse(String.valueOf(et.getText()));
//                Response<String> response = null;
//                try {
//                    response = call.execute();
//                } catch (IOException e) {
//                    Toast.makeText(GestionDesErreursActivity.this, "Le serveur n'a pas répondu à temps. Veuillez réessayer.", Toast.LENGTH_SHORT).show();
//                    throw new RuntimeException(e);
//                }
//                if (response.isSuccessful() || response.code() == 200) {
//                    Toast.makeText(GestionDesErreursActivity.this, response.message(), Toast.LENGTH_SHORT).show();
//                    Log.i("RETROFIT", response.raw() + "");
//                }
//                //Si mot de passe est pas bon
//                else {
//                    Snackbar.make(binding.getRoot(), "Mot de passe incorrect : " + response.message(), Snackbar.LENGTH_SHORT).show();
//                    Log.i("RETROFIT", response.raw() + response.headers().toString() + "");
//                }
//            }
                service.motDePasse(et.getText().toString()).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        //Si mot de passe est bon
                        String nouveau = et.getText().toString();
                        if (response.isSuccessful() || response.code() == 200) {
                            Toast.makeText(GestionDesErreursActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                            Log.i("RETROFIT", response.code() + response.headers().toString() + "");
                        }
                        //Si mot de passe est pas bon
                        else
                        {
                            //Faudrait probablement tester pour voir les messages d'erreurs vu que la réponse n'en envoie pas
                            //ainsi que faire des if/else avec le mot de passe et ses critères
                            Snackbar.make(binding.getRoot(), "Mot de passe incorrect : " + response.toString(), Snackbar.LENGTH_SHORT).show();
                            Log.i("RETROFIT", response.code() + response.headers().toString() +"");
                        }
                    }
                    //Si serveur n'est pas rejoins à temps
                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(GestionDesErreursActivity.this, "Le serveur n'a pas répondu à temps. Veuillez réessayer.",Toast.LENGTH_SHORT).show();
                    }

        });
    }
});
    }
}