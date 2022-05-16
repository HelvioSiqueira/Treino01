package com.example.treino01

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var sobre: String? = null
    var cargo: String? = null
    var local: String? = null

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

        button_cargo.setOnClickListener {
            val intent = Intent(this, CargoActivity::class.java)
            intent.putExtra(CargoActivity.EXTRA_CARGO, cargo)

            startActivityForResult(intent, REQUEST_CARGO)
        }

        button_cidade.setOnClickListener {
            val intent = Intent(this, LocalActivity::class.java)
            intent.putExtra(LocalActivity.EXTRA_LOCAL, local)

            startActivityForResult(intent, REQUEST_LOCAL)
        }

        //Checa se tem uma instancia salva, se tiver coloca o texto salvo como o anterior
        if(savedInstanceState != null){
            sobre = savedInstanceState.get(EXTRA_SOBRE) as String?
            cargo = savedInstanceState.get(EXTRA_CARGO) as String?
            local = savedInstanceState.get(EXTRA_LOCAL) as String?

            if(sobre != null){
                text_cargo.text = cargo
                text_sobrenome.text = sobre
                text_local.text = local
            }
        }
    }

    //Passa para o text_sobrenome o sobre(sobrenome) selecionado na NomeActivity
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        //Se o REQUEST(1) for igual ao requestCode passa para o text_sobrenome o
        //sobre(sobrenome) selecionado na NomeActivity
        if(resultCode == Activity.RESULT_OK && requestCode == REQUEST_SOBRE){
            sobre = data?.getStringExtra(NomeActivity.EXTRA_RESULT)

            text_sobrenome.text = sobre
        }

        //Se o REQUEST(2) for igual ao requestCode passa para o text_sobrenome o
        //sobre(sobrenome) selecionado na NomeActivity
        if(resultCode == Activity.RESULT_OK && requestCode == REQUEST_CARGO){
            cargo = data?.getStringExtra(CargoActivity.EXTRA_RESULT)

            text_cargo.text = cargo
        }

        if(resultCode == Activity.RESULT_OK && requestCode == REQUEST_LOCAL){
            local = data?.getStringExtra(CargoActivity.EXTRA_RESULT)

            text_local.text = local
        }
    }

    //Salva o estado da activity passando EXTRA_SOBRE e SOBRE(Sobrenome)
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(EXTRA_SOBRE, sobre)
        outState.putString(EXTRA_CARGO, cargo)
        outState.putString(EXTRA_LOCAL, local)
    }

    companion object{
        private const val REQUEST_SOBRE = 1
        private const val EXTRA_SOBRE = "estado"

        private const val REQUEST_CARGO = 2
        private const val EXTRA_CARGO = "cargo"

        private const val REQUEST_LOCAL = 3
        private const val EXTRA_LOCAL = "local"
    }
}