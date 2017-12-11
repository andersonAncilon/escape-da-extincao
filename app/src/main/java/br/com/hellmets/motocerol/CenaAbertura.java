package br.com.hellmets.motocerol;

import br.com.hellmets.motocerol.AndGraph.AGGameManager;
import br.com.hellmets.motocerol.AndGraph.AGInputManager;
import br.com.hellmets.motocerol.AndGraph.AGScene;
import br.com.hellmets.motocerol.AndGraph.AGScreenManager;
import br.com.hellmets.motocerol.AndGraph.AGSprite;
import br.com.hellmets.motocerol.AndGraph.AGTimeManager;
import br.com.hellmets.motocerol.AndGraph.AGTimer;

/**
 * Created by breno on 16/11/2017.
 */

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
