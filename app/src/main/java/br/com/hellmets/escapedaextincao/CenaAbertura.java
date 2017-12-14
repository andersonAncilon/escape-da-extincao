package br.com.hellmets.escapedaextincao;

import br.com.hellmets.escapedaextincao.AndGraph.AGGameManager;
import br.com.hellmets.escapedaextincao.AndGraph.AGInputManager;
import br.com.hellmets.escapedaextincao.AndGraph.AGScreenManager;
import br.com.hellmets.escapedaextincao.AndGraph.AGSoundManager;
import br.com.hellmets.escapedaextincao.AndGraph.AGSprite;
import br.com.hellmets.escapedaextincao.AndGraph.AGTimer;
import br.com.hellmets.escapedaextincao.AndGraph.AGScene;


public class CenaAbertura extends AGScene
{
    AGTimer tempo = null;
    AGSprite botao = null;

    public CenaAbertura(AGGameManager manager)
    {
        super(manager);
    }

    @Override
    public void init()
    {
        tempo = new AGTimer(4000);
        setSceneBackgroundColor(1,0,0);

        AGSoundManager.vrMusic.loadMusic("tema.ogg", true);
        AGSoundManager.vrMusic.setVolume(0.6f, 0.6f);
        AGSoundManager.vrMusic.play();

        botao = this.createSprite(R.mipmap.btnjogar, 1,1);
        botao.setScreenPercent(25,15);

        //Altura e Largura
        botao.vrPosition.setX(AGScreenManager.iScreenWidth/2);
        botao.vrPosition.setY(AGScreenManager.iScreenHeight/2);
    }

    @Override
    public void restart()
    {

    }

    @Override
    public void stop()
    {

    }

    @Override
    public void loop()
    {
        if (AGInputManager.vrTouchEvents.screenClicked())
        {
            if (botao.collide(AGInputManager.vrTouchEvents.getLastPosition()))
            {

                this.vrGameManager.setCurrentScene(1);
                return;
            }
        }

        if(AGInputManager.vrTouchEvents.backButtonClicked())
        {
            this.vrGameManager.release();
        }
    }
}
