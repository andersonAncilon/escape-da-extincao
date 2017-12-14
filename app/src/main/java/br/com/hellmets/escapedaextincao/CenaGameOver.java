package br.com.hellmets.escapedaextincao;

import br.com.hellmets.escapedaextincao.AndGraph.AGGameManager;
import br.com.hellmets.escapedaextincao.AndGraph.AGInputManager;
import br.com.hellmets.escapedaextincao.AndGraph.AGScene;
import br.com.hellmets.escapedaextincao.AndGraph.AGScreenManager;
import br.com.hellmets.escapedaextincao.AndGraph.AGSprite;


public class CenaGameOver extends AGScene {


    AGSprite gameOver = null;
    AGSprite dinoMorto = null;

    public CenaGameOver(AGGameManager manager) {
        super(manager);
    }

    @Override
    public void init() {
        gameOver = this.createSprite(R.mipmap.youlose, 1, 1);
        gameOver.setScreenPercent(40, 35);
        gameOver.vrPosition.setXY(AGScreenManager.iScreenWidth / 2, AGScreenManager.iScreenHeight / 1.5f);

        dinoMorto = this.createSprite(R.mipmap.pterav, 4, 4);
        dinoMorto.setScreenPercent(15, 25);
        dinoMorto.vrPosition.setXY(AGScreenManager.iScreenWidth / 2, AGScreenManager.iScreenHeight / 2);
        dinoMorto.addAnimation(5, true, 9, 15);
    }

    @Override
    public void stop() {
    }

    @Override
    public void restart() {
    }

    @Override
    public void loop() {
        if (AGInputManager.vrTouchEvents.backButtonClicked()) {
            this.vrGameManager.setCurrentScene(0);

            return;
        }
    }


}
