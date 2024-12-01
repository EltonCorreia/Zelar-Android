package com.example.prj_zelar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;

import java.sql.SQLException;


public class cadastro extends AppCompatActivity {

    EditText etNome, etEndereco, etTelefone, etEmail, etSenha, etCodigoLoja;
    Button btnCadastrar;
    Acessa objA = new Acessa();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        etNome = findViewById(R.id.editNomeCliente);
        etEndereco = findViewById(R.id.editEnderecoCliente);
        etTelefone = findViewById(R.id.editFoneCliente);
        etEmail = findViewById(R.id.editEmailCliente);
        etSenha = findViewById(R.id.editSenhaCliente);
        etCodigoLoja = findViewById(R.id.editCodigoCadastro);

        TextWatcher criarMascara = AlciMask.aplicarMascara(etTelefone, "(##) #####-####");
        //cria a máscara
        etTelefone.addTextChangedListener(criarMascara);
    }
    public void ClickSalvar(View v){
        salvar();
    }

    // Método que salva os dados no banco
    public void salvar() {
        // Estabelece conexão com o banco de dados
        objA.entBanco(this);

        // Captura os dados inseridos nos campos de texto
        String nomeIn = etNome.getText().toString();
        String enderecoIn = etEndereco.getText().toString();
        String telefoneIn = etTelefone.getText().toString();
        String emailIn = etEmail.getText().toString();
        String senhaIn = etSenha.getText().toString();
        String codigolojaTxt = etCodigoLoja.getText().toString();


         //Verifica se algum campo está vazio antes de prosseguir
        if (nomeIn.isEmpty() || enderecoIn.isEmpty() || telefoneIn.isEmpty() || emailIn.isEmpty() || senhaIn.isEmpty() || codigolojaTxt.isEmpty()) {
            Toast.makeText(this, "Todos os campos são obrigatórios", Toast.LENGTH_LONG).show();
        } else{

            int codigolojaIn = Integer.parseInt(codigolojaTxt);
            try {
                // Monta a query SQL para inserir os dados
                String sql = "INSERT INTO CadastroCliente (Nome, Endereco, Telefone, IdCartao, Email, Senha,Pontos) VALUES ('" + nomeIn + "', '" + enderecoIn + "', '" + telefoneIn + "', " + codigolojaIn + ", '" + emailIn + "', '" + senhaIn + "','0')";


                int resultado = objA.stmt.executeUpdate(sql);

                // Verifica o resultado da inserção e exibe uma mensagem apropriada
                if (resultado == 1) {
                    Toast.makeText(this, "Dados cadastrados com sucesso", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Erro ao cadastrar os dados", Toast.LENGTH_LONG).show();
                }
            } catch (SQLException e) {

                Toast.makeText(this, e + "", Toast.LENGTH_SHORT).show();
            }
            Intent intent = new Intent(cadastro.this,MainActivity.class); startActivity(intent);
        }
    }


}
