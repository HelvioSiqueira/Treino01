package com.example.treino01

//Permite usar um widget ListView na Activity
import android.widget.ListView

//Faz uma adaptação da uma lista para pode ser usada
import android.widget.ArrayAdapter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AbsListView
import android.app.Activity
import android.content.Intent

class NomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Cria uma variavel listView do tipo ListView(widget)
        val listView = ListView(this)

        //Define o conteudo da Activity como sendo o a listView
        setContentView(listView)

        //Cria uma variavel para receber o string-array de nomes do strings.xml
        val nomesList = resources.getStringArray(R.array.sobrenomes)

        //Cria uma variavel para receber um array adaptado
        val listAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_single_choice, nomesList)

        //Adiciona o array adaptado ao listView
        listView.adapter = listAdapter

        //Define o modo de escolha "single"
        listView.choiceMode = AbsListView.CHOICE_MODE_SINGLE

        val sobre = intent.getStringExtra(EXTRA_SOBRE)

        //Se "sobre" for diferente de nulo define position com a possição selecionada, e deixa-o selecionado
        if(sobre != null){
            val position = nomesList.indexOf(sobre)
            listView.setItemChecked(position, true)
        }

        //Passa o item selecionado junto com uma Intent
        listView.setOnItemClickListener { l, _, position, _ ->
            val result = l.getItemAtPosition(position) as String
            val it = Intent()
            it.putExtra(EXTRA_RESULT, result)
            setResult(Activity.RESULT_OK, it)
            finish()
        }
    }

    companion object {
        const val EXTRA_SOBRE = "sobrenome"
        const val EXTRA_RESULT = "result"
    }
}