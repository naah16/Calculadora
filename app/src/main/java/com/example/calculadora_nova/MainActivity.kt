package com.example.calculadora_nova

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder
import net.objecthunter.exp4j.operator.Operator


class MainActivity : AppCompatActivity() {
    var isPonto = 0;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar!!.hide()

        //Números
        n_zero.setOnClickListener{acrescentarExpressao("0",true,"num")}
        n_um.setOnClickListener{acrescentarExpressao("1",true,"num")}
        n_dois.setOnClickListener{acrescentarExpressao("2",true,"num")}
        n_tres.setOnClickListener{acrescentarExpressao("3",true,"num")}
        n_quatro.setOnClickListener{acrescentarExpressao("4",true,"num")}
        n_cinco.setOnClickListener{acrescentarExpressao("5",true,"num")}
        n_seis.setOnClickListener{acrescentarExpressao("6",true,"num")}
        n_sete.setOnClickListener{acrescentarExpressao("7",true,"num")}
        n_oito.setOnClickListener{acrescentarExpressao("8",true,"num")}
        n_nove.setOnClickListener{acrescentarExpressao("9",true,"num")}
        ponto.setOnClickListener{acrescentarExpressao(".",true,"pont")}

        //Operadores
        soma.setOnClickListener{acrescentarExpressao("+",false,"ope")}
        subtracao.setOnClickListener{acrescentarExpressao("-",false,"ope")}
        divisao.setOnClickListener{acrescentarExpressao("/",false,"ope")}
        multiplicacao.setOnClickListener{acrescentarExpressao("*",false,"ope")}

        //Clear
        limpar.setOnClickListener{
            expressao.text=""
            txt_resultado.text = ""
            isPonto = 0;
        }

        //Igual
        igual.setOnClickListener{
            try{
                val expressao = ExpressionBuilder(expressao.text.toString()).build()
                val resultado = expressao.evaluate()
                val longResult = resultado.toLong()

                if(resultado == longResult.toDouble()){
                    txt_resultado.text = longResult.toString()
                }else{
                    txt_resultado.text = resultado.toString()
                }
            }catch(e: Exception){ }
        }
    }

    private fun acrescentarExpressao(string: String, limpar_dados: Boolean, opcao: String){
        print("valor é: $opcao")
        if(opcao == "ope"){
            isPonto = 0
        }
        if(opcao == "pont"){
            isPonto += 1
        }
        if(isPonto<2||opcao != "pont")
        {
            if(limpar_dados){
                txt_resultado.text = ""
                expressao.append(string)
            }else{
                expressao.append(txt_resultado.text)
                expressao.append(string)
                txt_resultado.text = ""
            }
        }
        if(txt_resultado.text.isNotEmpty()){
            expressao.text = ""
        }
    }
}

