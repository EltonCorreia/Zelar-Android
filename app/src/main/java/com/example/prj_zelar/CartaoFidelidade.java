package com.example.prj_zelar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class CartaoFidelidade extends AppCompatActivity {
    Button btnresgatar, btnpreencher;
    EditText cliente, codcard, pontoscar;
    Acessa objA = new Acessa();
    ImageView img1, img2, img3, img4, img5, img6, img8, img7, img9;
    public static String Nome, CodigoC, Pontos;
    int pontos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartao_fidelidade);

        // Inicializando os componentes da interface
        cliente = findViewById(R.id.editxcliente);
        codcard = findViewById(R.id.editxcodcar);
        pontoscar = findViewById(R.id.editxpontos);
        btnresgatar = findViewById(R.id.btnresgatar);
        img1 = findViewById(R.id.img1);
        img2 = findViewById(R.id.img2);
        img3 = findViewById(R.id.img3);
        img4 = findViewById(R.id.img4);
        img5 = findViewById(R.id.img5);
        img6 = findViewById(R.id.img6);
        img7 = findViewById(R.id.img7);
        img8 = findViewById(R.id.img8);
        img9 = findViewById(R.id.img9);

        // Definindo as informações do cliente
        cliente.setText(Nome);
        codcard.setText(CodigoC);
        pontoscar.setText(Pontos);

        // Escondendo todas as imagens no início
        imagemDesabilitada();

        // Convertendo os pontos para int
       try{
            pontos = Integer.parseInt(Pontos);
       }catch (Exception e){
           pontos=1;
       }
    ativarImg(pontos);

        // Se os pontos forem 9, ativa o botão de resgate
        if (pontos >= 9) {
            btnresgatar.setEnabled(true); // Ativa o botão de resgate
        } else {
            btnresgatar.setEnabled(false); // Desativa o botão de resgate se não tiver 9 pontos
        }


    }

    // Esconde todas as imagens
    public void imagemDesabilitada() {
        img1.setEnabled(false);
        img2.setEnabled(false);
        img3.setEnabled(false);
        img4.setEnabled(false);
        img5.setEnabled(false);
        img6.setEnabled(false);
        img7.setEnabled(false);
        img8.setEnabled(false);
        img9.setEnabled(false);
    }

    // Ativa a imagem correspondente ao número de pontos
    public void ativarImg(int aux){
      //  hideAllImages();  // Esconde todas as imagens antes de ativar a nova

        switch (aux) {
            case 1:
                img1.setImageResource(R.drawable.img2);
                break;
            case 2:
                img1.setImageResource(R.drawable.img2);
                img2.setImageResource(R.drawable.img2);
                break;
            case 3:
                img1.setImageResource(R.drawable.img2);
                img2.setImageResource(R.drawable.img2);
                img3.setImageResource(R.drawable.img2);
                break;
            case 4:
                img1.setImageResource(R.drawable.img2);
                img2.setImageResource(R.drawable.img2);
                img3.setImageResource(R.drawable.img2);
                img4.setImageResource(R.drawable.img2);
                break;
            case 5:
                img1.setImageResource(R.drawable.img2);
                img2.setImageResource(R.drawable.img2);
                img3.setImageResource(R.drawable.img2);
                img4.setImageResource(R.drawable.img2);
                img5.setImageResource(R.drawable.img2);
                break;
            case 6:
                img1.setImageResource(R.drawable.img2);
                img2.setImageResource(R.drawable.img2);
                img3.setImageResource(R.drawable.img2);
                img4.setImageResource(R.drawable.img2);
                img5.setImageResource(R.drawable.img2);
                img6.setImageResource(R.drawable.img2);
                break;
            case 7:
                img1.setImageResource(R.drawable.img2);
                img2.setImageResource(R.drawable.img2);
                img3.setImageResource(R.drawable.img2);
                img4.setImageResource(R.drawable.img2);
                img5.setImageResource(R.drawable.img2);
                img6.setImageResource(R.drawable.img2);
                img7.setImageResource(R.drawable.img2);
                break;
            case 8:
                img1.setImageResource(R.drawable.img2);
                img2.setImageResource(R.drawable.img2);
                img3.setImageResource(R.drawable.img2);
                img4.setImageResource(R.drawable.img2);
                img5.setImageResource(R.drawable.img2);
                img6.setImageResource(R.drawable.img2);
                img7.setImageResource(R.drawable.img2);
                img8.setImageResource(R.drawable.img2);
                break;
            case 9:
                img1.setImageResource(R.drawable.img2);
                img2.setImageResource(R.drawable.img2);
                img3.setImageResource(R.drawable.img2);
                img4.setImageResource(R.drawable.img2);
                img5.setImageResource(R.drawable.img2);
                img6.setImageResource(R.drawable.img2);
                img7.setImageResource(R.drawable.img2);
                img8.setImageResource(R.drawable.img2);
                img9.setImageResource(R.drawable.img2);
                break;
        }
    }

    // Navega para a página de preencher pontos
    public void preencher(View v) {
        Intent intent = new Intent(CartaoFidelidade.this, preencher_pontos.class);
        startActivity(intent);
    }

    // Navega para a página de resgate de pontos
    public void resgatepontos(View v) {
        if (Integer.parseInt(Pontos) >= 9) {
            Intent intent = new Intent(CartaoFidelidade.this, TeladeResgate.class);
            startActivity(intent);
        } else {
            // Caso o usuário tente resgatar com menos de 9 pontos
            btnresgatar.setEnabled(false);
        }
    }
}