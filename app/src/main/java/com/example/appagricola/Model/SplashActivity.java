package com.example.appagricola.Model;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appagricola.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Cargar el ImageView y asignar la animación
        ImageView androidMonkey = findViewById(R.id.android_monkey);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_scale_rotate);
        androidMonkey.startAnimation(animation);

        // Iniciar MainActivity después de 3 segundos
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }, 3000); // Duración de la pantalla de carga en milisegundos
    }
}
