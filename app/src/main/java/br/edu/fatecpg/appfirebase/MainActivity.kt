package br.edu.fatecpg.appfirebase

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.edu.fatecpg.appfirebase.databinding.ActivityMainBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private lateinit var auth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.btnLogin.setOnClickListener{
            val email = binding.edtEmail.text.toString()
            val senha = binding.edtSenha.text.toString()
            auth.signInWithEmailAndPassword(email,senha)
                .addOnCompleteListener{task ->
                    if(task.isSuccessful){
                        val intent = Intent(this, UserActivity::class.java)
                        intent.putExtra("nome", "Amanda")
                        startActivity(intent)
                    }else{
                        Toast.makeText(this, "Verifique as informações!", Toast.LENGTH_SHORT).show()
                    }
                }

        }
        binding.txvEsqueciSenha.setOnClickListener{
            val email = binding.edtEmail.text.toString()
        }

    }
}