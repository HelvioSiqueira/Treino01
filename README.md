# Passagem de Intents

Esse app é uma exemplificação da passagem de Intents(Informações) entre entre Activitys(Janelas).

## O app

Basicamente é um layout com algumas informações editaveis. De inicio parece ser algo bem simples e que não precisa de muito pra ser feito,
mas tem mais nele do que os olhos podem ver.

## Impedindo que dados desapareçam quando rodar a tela

Se você fazer uma mudança de orientação de tela em qualquer layout com informações no seu app vai perceber que os dados não somem, o android não trata isso por padrão,
por isso é preciso salvar o estado dos dados toda vez que haver uma mudança de orientação na tela.

###### Esse parte do código indica quais os dados serão salvos e passa um key de acesso a eles
```
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(EXTRA_SOBRE, sobre)
        outState.putString(EXTRA_CARGO, cargo)
        outState.putString(EXTRA_LOCAL, local)
    }
```
###### Aqui é recuperado os dados salvos usando a key
```
        if(savedInstanceState != null){
            sobre = savedInstanceState.get(EXTRA_SOBRE) as String?
            cargo = savedInstanceState.get(EXTRA_CARGO) as String?
            local = savedInstanceState.get(EXTRA_LOCAL) as String?
            ...
            ...
        }
```

## Passando Intents(dados) entre Activitys

Os dados devem ser passados entre activitys para poderem ser mostrados na pagina inicial 

###### Na tela de selecionar a informação(Ex: NomeActivity) o dado(nesse caso, o sobrenome) é deixado disponivel assim que ele é clicado
```
        listView.setOnItemClickListener { l, _, position, _ ->
            val result = l.getItemAtPosition(position) as String
            val it = Intent()
            it.putExtra(EXTRA_RESULT, result)
            setResult(Activity.RESULT_OK, it)
            finish()
        }
```
###### Nessa parte(no Main) é o obtida a Intent(sobrenome) se o REQUEST(1) for igual ao requestCode e mostra o sobre(sobrenome) selecionado na NomeActivity
```
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == Activity.RESULT_OK && requestCode == REQUEST_SOBRE){
            sobre = data?.getStringExtra(NomeActivity.EXTRA_RESULT)

            text_sobrenome.text = sobre
        }
        ...
        ...
    }
```
Essas são algumas das principais funcionalidades não implicitas no app, ainda poderia falar sobre o código para deixar a opção sempre selecionada
(Até isso é preciso ser feito kkkkkkk), mas não acho tão relevante pra esse app.
