package com.example.treino01

import android.app.Activity
import android.content.Intent
import android.widget.ListView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AbsListView

class LocalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Criei um objeto do tipo listView coloquei-o como conteudo da activity
        val listView = ListView(this)
        setContentView(listView)

        //Obtive o array de strings do strings.xml
        val localList = resources.getStringArray(R.array.locais)

        //Cria um objeto ArrayAdapter, seleciona o tipo de lista e a lista
        val listAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_single_choice, localList)

        //Adiciona a lista adaptada ao listView
        listView.adapter = listAdapter

        //Seleciona o tipo de seleção
        listView.choiceMode = AbsListView.CHOICE_MODE_SINGLE

        //Obtem o local selecionado(caso já tenha, senão retorna null)
        val local = intent.getStringExtra(EXTRA_LOCAL)

        //Se local não for null obtem-se a posição dele o marca-o na list
        if(local != null){
            val position = localList.indexOf(local)
            listView.setItemChecked(position, true)
        }

        //Quando o listView for clicado obtem-se a posição do item clicado
        //e disponibiliza-o para ser obtido(passa a Intent)
        listView.setOnItemClickListener{l, _, position, _ ->
            val result = l.getItemAtPosition(position) as String

            val it = Intent()

            it.putExtra(EXTRA_RESULT, result)
            setResult(Activity.RESULT_OK, it)
            finish()
        }

    }

    companion object{
        const val EXTRA_LOCAL = "local"
        const val EXTRA_RESULT = "result"
    }
}