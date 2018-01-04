package valdircamargo.com.br.projetofinalv001;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import gr.net.maroulis.library.EasySplashScreen;

public class ActSplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Setando os parametros para a SplashScreen
        EasySplashScreen config = new EasySplashScreen(ActSplashScreen.this)
                .withFullScreen()
                .withTargetActivity(ActMain.class)
                .withSplashTimeOut(5000)
                .withBackgroundColor(Color.parseColor("#009688"))
                .withLogo(R.drawable.xicara)
                .withHeaderText("Bem Vindo")
                .withFooterText("copyright 2017")
                .withBeforeLogoText("Projeto Final Cadastro de Pessoas")
                .withAfterLogoText("Dev. Valdir de Camargo");

        //Setando as cores
        config.getHeaderTextView().setTextColor(Color.WHITE);
        config.getFooterTextView().setTextColor(Color.WHITE);
        config.getAfterLogoTextView().setTextColor(Color.WHITE);
        config.getBeforeLogoTextView().setTextColor(Color.WHITE);

        config.getHeaderTextView().setTextSize(36);
        config.getHeaderTextView().setPadding(20,100,20,20);

        //Setando a  view
        View view = config.create();

        //Setando o content View
        setContentView(view);


    }
}
