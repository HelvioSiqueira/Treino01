package com.example.treino01

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var sobre: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Quando o botão é clicado é iniciada uma nova Intent com NomeActivity
        //É passado o sobrenome que foi clicado anteriormente(caso tenha) e deixa-o selecionado
        button_nome.setOnClickListener {
            val intent = Intent(this, NomeActivity::class.java)
            intent.putExtra(NomeActivity.EXTRA_SOBRE, sobre)

            startActivityForResult(intent, REQUEST_SOBRE)
        }

        //Checa se tem uma instancia salva, se tiver coloca o texto salvo como o anterior
        if(savedInstanceState != null){
            sobre = savedInstanceState.get(EXTRA_SOBRE) as String?

            if(sobre != null){
                text_sobrenome.text = sobre
            }
        }
    }

    //Passa para o text_sobrenome o sobre(sobrenome) selecionado na NomeActivity
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == Activity.RESULT_OK && requestCode == REQUEST_SOBRE){
            sobre = data?.getStringExtra(NomeActivity.EXTRA_RESULT)
            text_sobrenome.text = sobre
        }
    }

    //Salva o estado da activity passando EXTRA_SOBRE e SOBRE(Sobrenome)
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(EXTRA_SOBRE, sobre)
    }

    companion object{
        private const val REQUEST_SOBRE = 1
        private const val EXTRA_SOBRE = "estado"
    }
}