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
    AGSprite jogar = null;
    AGSprite creditos = null;
    AGSprite sair = null;
    AGSprite nomeJogo = null;

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

        jogar = this.createSprite(R.mipmap.play, 1, 1);
        jogar.setScreenPercent(25, 15);

        creditos = this.createSprite(R.mipmap.credits, 1, 1);
        creditos.setScreenPercent(25, 15);

        sair = this.createSprite(R.mipmap.exit, 1, 1);
        sair.setScreenPercent(25, 15);

        nomeJogo = this.createSprite(R.mipmap.nome, 1, 1);
        nomeJogo.setScreenPercent(40, 40);

        //Altura e Largura
        nomeJogo.vrPosition.setXY(AGScreenManager.iScreenWidth / 2, AGScreenManager.iScreenHeight / 1.2f);
        jogar.vrPosition.setXY(AGScreenManager.iScreenWidth / 2, AGScreenManager.iScreenHeight / 1.7f);
        creditos.vrPosition.setXY(AGScreenManager.iScreenWidth / 2, AGScreenManager.iScreenHeight / 2.4f);
        sair.vrPosition.setXY(AGScreenManager.iScreenWidth / 2, AGScreenManager.iScreenHeight / 3.9f);
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
            if (jogar.collide(AGInputManager.vrTouchEvents.getLastPosition()))
            {

                this.vrGameManager.setCurrentScene(1);
                return;
            }
        }

        if (AGInputManager.vrTouchEvents.screenClicked()) {
            if (jogar.collide(AGInputManager.vrTouchEvents.getLastPosition())) {

                this.vrGameManager.setCurrentScene(2);
                return;
            }
        }

        if (AGInputManager.vrTouchEvents.screenClicked()) {
            if (sair.collide(AGInputManager.vrTouchEvents.getLastPosition())) {

                this.vrGameManager.vrActivity.finish();
                return;
            }
        }

        if(AGInputManager.vrTouchEvents.backButtonClicked())
        {
            this.vrGameManager.release();
        }

        if (AGInputManager.vrTouchEvents.screenClicked()) {
            if (creditos.collide(AGInputManager.vrTouchEvents.getLastPosition())) {
                this.vrGameManager.setCurrentScene(3);
                return;
            }
        }
    }
}
