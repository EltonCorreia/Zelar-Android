package com.example.prj_zelar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.SQLException;

public class preencher_pontos extends AppCompatActivity {
    EditText dataCompra, valorCompra,codCupom;
    Acessa objA = new Acessa();
    public static String idcliente, pontocliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preencher_pontos);

        dataCompra = findViewById(R.id.editData);
        valorCompra = findViewById(R.id.editValor);
        codCupom = findViewById(R.id.editNum);

        TextWatcher criarMascara = AlciMask.aplicarMascara(dataCompra, "##/##/#####");
        //cria a máscara
        dataCompra.addTextChangedListener(criarMascara);

        TextWatcher criarMascara2 = AlciMask.aplicarMascara(valorCompra, "R$ ");
        //cria a máscara
        dataCompra.addTextChangedListener(criarMascara2);
    }

    public void preencher(View v)
    {
        // Estabelece conexão com o banco de dados
        objA.entBanco(this);

        // Captura os dados inseridos nos campos de texto
        String dataIn = dataCompra.getText().toString();
        String valorIn = valorCompra.getText().toString();
        String codIn = codCupom.getText().toString();

        String pontos = (pontocliente)+"";
        int p = Integer.parseInt(pontos)+1;
        pontos = p+"";
        CartaoFidelidade.Pontos=pontos;
        int x;
        try {
            x=objA.stmt.executeUpdate("UPDATE CadastroCliente SET Pontos = '"+pontos+"', Datadacompra = '"+dataIn+"', ValorCompra = '"+valorIn+"', CodCupom = '"+codIn+"' WHERE CodigoCliente = '"+idcliente+"'");
            if (x!=0){
                Toast.makeText(this, "Pontuado com sucesso!", Toast.LENGTH_SHORT).show();
            }
        } catch (SQLException e) {
            Toast.makeText(this, ""+e, Toast.LENGTH_SHORT).show();
        }
        Intent intent = new Intent(preencher_pontos.this,CartaoFidelidade.class); startActivity(intent);
    }
}