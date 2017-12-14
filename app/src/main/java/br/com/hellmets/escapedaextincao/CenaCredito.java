package br.com.hellmets.escapedaextincao;

import br.com.hellmets.escapedaextincao.AndGraph.AGGameManager;
import br.com.hellmets.escapedaextincao.AndGraph.AGInputManager;
import br.com.hellmets.escapedaextincao.AndGraph.AGScene;
import br.com.hellmets.escapedaextincao.AndGraph.AGScreenManager;
import br.com.hellmets.escapedaextincao.AndGraph.AGSprite;

/**
 * Created by splatter on 14/12/2017.
 */

public class CenaCredito extends AGScene {

    AGSprite credito = null;

    public CenaCredito(AGGameManager pManager) {
        super(pManager);
    }

    @Override
    public void init() {
        credito = this.createSprite(R.mipmap.creditos, 1, 1);
        credito.setScreenPercent(50, 50);

        credito.vrPosition.setXY(AGScreenManager.iScreenWidth / 2, AGScreenManager.iScreenHeight / 2);
    }

    @Override
    public void restart() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void loop() {
        if (AGInputManager.vrTouchEvents.backButtonClicked()) {
            this.vrGameManager.setCurrentScene(0);
            return;
        }

        if (AGInputManager.vrTouchEvents.screenClicked()) {
            this.vrGameManager.setCurrentScene(0);
            return;
        }

    }
}
