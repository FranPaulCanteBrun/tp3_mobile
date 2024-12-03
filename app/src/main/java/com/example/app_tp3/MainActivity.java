package com.example.app_tp3;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import java.util.List;

import com.example.app_tp3.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private UserViewModelActivity userVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        userVM = new ViewModelProvider(this).get(UserViewModelActivity.class);
        tarea();
    }

    public void tarea() {
        binding.btGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = binding.teNombre.getText().toString();
                int edad;
                if (!nombre.isEmpty() && !binding.teEdad.getText().toString().isEmpty()) {
                    try {
                        edad = Integer.parseInt(binding.teEdad.getText().toString());
                        Usuario usuario = new Usuario(nombre, edad);
                        userVM.addUser(usuario);
                        binding.teNombre.setText("");
                        binding.teEdad.setText("");
                    } catch (NumberFormatException e) {
                        binding.tvVerUsuario.setText("Error de formato en la edad");
                    }
                } else {
                    binding.tvVerUsuario.setText("ERROR");
                }
                binding.teNombre.setText("");
                binding.teEdad.setText("");
            }
        });
        binding.btVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Usuario> lista = userVM.getUserList();
                StringBuilder texto = new StringBuilder();
                for (Usuario u : lista) {
                    texto.append("Nombre: ").append(u.getNombre()).append(" | Edad: ").append(u.getEdad()).append("\n");;
                }
                if (texto.length() > 0) {
                    binding.tvVerUsuario.setText(texto.toString());
                } else {
                    binding.tvVerUsuario.setText("No hay usuarios guardados.");
                }
            }
        });
    }
}