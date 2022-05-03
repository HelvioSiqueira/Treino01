package com.example.treino01

import android.app.Activity
import android.content.Intent
import android.widget.ListView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AbsListView

class CargoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Cria o objeto do tipo ListView
        val listView = ListView(this)

        //Seleciona o ListView como o conteudo da activity
        setContentView(listView)

        //Obtem o array de cargo na strings.xml
        val cargosList = resources.getStringArray(R.array.cargos)

        //Cria um adapter com o tipo de lista e a lista a ser adaptada
        val listAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_single_choice, cargosList)

        //Adiciona o adapter ao ListView
        listView.adapter = listAdapter

        //Seleciona o tipo de escolha da lista
        listView.choiceMode = AbsListView.CHOICE_MODE_SINGLE

        //Obtem o item selecionado anteriormente
        val cargo = intent.getStringExtra(EXTRA_CARGO)

        /*Se o cargo for diferente de nulo(já tiver sido selecionado antes)
        * seleciona apção anteriormente selecionada na lista*/
        if (cargo != null){
            val position = cargosList.indexOf(cargo)
            listView.setItemChecked(position, true)
        }

        //Quando o item for selecionado deixa disponivel o valor selecionado para outra
        //activity passando alguns parametros
        listView.setOnItemClickListener{l, _, position, _ ->

            //Obtem o texto do item que foi selecionado
            val result = l.getItemAtPosition(position) as String

            val it = Intent()

            it.putExtra(EXTRA_RESULT, result)
            setResult(Activity.RESULT_OK, it)
            finish()
        }
    }

    companion object{
        const val EXTRA_CARGO = "cargo"
        const val EXTRA_RESULT = "result"
    }
}