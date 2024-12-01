package com.example.prj_zelar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MainActivity extends AppCompatActivity {
    Button btnlogin;
    EditText logintxt, senhatxt;
    Acessa objA = new Acessa();
    TextView criar_cadastro;

    CartaoFidelidade objCartao;
    preencher_pontos objPreencherPontos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        btnlogin = findViewById(R.id.btnLogin);
        logintxt = findViewById(R.id.logintxt);
        senhatxt = findViewById(R.id.senhatxt);
        criar_cadastro = findViewById(R.id.criar_cadastro);

    }

    public void entrar(View v) {
        objA.entBanco(this);
        String nome = logintxt.getText().toString();
        String senha = senhatxt.getText().toString();
        try {
            objA.RS = objA.stmt.executeQuery("select * from CadastroCliente where Email='"+nome+"' and Senha='"+senha+"'");
            if (objA.RS.next()) {
                objCartao.Nome=objA.RS.getString("Nome");
                objCartao.CodigoC=objA.RS.getString("IdCartao");
                objCartao.Pontos=objA.RS.getString("Pontos");

                objPreencherPontos.idcliente = objA.RS.getString("CodigoCliente");
                objPreencherPontos.pontocliente = objA.RS.getString("Pontos");

                Toast.makeText(getApplicationContext(), "Aprovado", 2).show();
                Intent intent = new Intent(MainActivity.this, CartaoFidelidade.class);
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "Entrada não Aprovada", 2).show();
            }

        } catch (SQLException ex) {
            Toast.makeText(getApplicationContext(), "erro no acesso", 2).show();
        }
    }

    public void teste(View V){
        objA.entBanco(this);
    }

    public void cadastrar(View v){
        Intent intent = new Intent(MainActivity.this,cadastro.class); startActivity(intent);
    }
}//ultima chave


