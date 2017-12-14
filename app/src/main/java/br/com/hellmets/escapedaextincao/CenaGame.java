package br.com.hellmets.escapedaextincao;

import br.com.hellmets.escapedaextincao.AndGraph.AGGameManager;
import br.com.hellmets.escapedaextincao.AndGraph.AGScreenManager;
import br.com.hellmets.escapedaextincao.AndGraph.AGSoundManager;
import br.com.hellmets.escapedaextincao.AndGraph.AGSprite;
import br.com.hellmets.escapedaextincao.AndGraph.AGTimer;
import br.com.hellmets.escapedaextincao.AndGraph.AGInputManager;
import br.com.hellmets.escapedaextincao.AndGraph.AGScene;

public class CenaGame extends AGScene {


    int i = 0;
    private int timeValue = 0;
    private int[] proximosMeteoros = null;


    private AGSprite[] spriteDigits;

    private AGSprite dino = null;
    private AGSprite background = null;
    private AGSprite barraScore = null;

    private AGSprite[] cometas = null;
    private AGTimer gameTimer = null;
    private AGTimer tempoCometas = null;


    public CenaGame(AGGameManager manager) {
        super(manager);
    }

    public void init() {

        //Inicializaçao de variaveis
        proximosMeteoros = new int[3];

        gameTimer = new AGTimer();
        tempoCometas = new AGTimer();
        spriteDigits = new AGSprite[4];
        gameTimer = new AGTimer(1000);

        cometas = new AGSprite[5];


        //Criando os sprites e definindo os tamanhos
        background = createSprite(R.mipmap.background, 8, 1);
        background.setScreenPercent(100, 100);

        dino = createSprite(R.mipmap.pterav, 4, 4);
        dino.setScreenPercent(15, 25);

        for (int i = 0; i < 5; i++) {
            cometas[i] = createSprite(R.mipmap.cometa, 1, 3);
            cometas[i].setScreenPercent(10, 20);
        }


        barraScore = createSprite(R.mipmap.barra, 1, 1);
        barraScore.setScreenPercent(100, 10);
        barraScore.vrPosition.setXY((float) (AGScreenManager.iScreenWidth / 2), ((float) AGScreenManager.iScreenHeight) - (this.barraScore.getSpriteHeight() / 2.0f));

        //Limpando as texturas presentes na tela.
        releaseSpritesTextures();

        //Posicionamento dos Sprites
        dino.vrPosition.setXY(AGScreenManager.iScreenWidth / 2, AGScreenManager.iScreenHeight / 4);
        background.vrPosition.setXY(AGScreenManager.iScreenWidth / 2, AGScreenManager.iScreenHeight / 2);


        cometas[0].vrPosition.setXY(AGScreenManager.iScreenWidth / 3.65f, AGScreenManager.iScreenHeight + 135);
        cometas[1].vrPosition.setXY(AGScreenManager.iScreenWidth / 2.0f, AGScreenManager.iScreenHeight + 135);
        cometas[2].vrPosition.setXY(AGScreenManager.iScreenWidth / 1.35f, AGScreenManager.iScreenHeight + 135);
        cometas[3].vrPosition.setXY(AGScreenManager.iScreenWidth / 1.05f, AGScreenManager.iScreenHeight + 1355);
        cometas[4].vrPosition.setXY(AGScreenManager.iScreenWidth / 16.5f, AGScreenManager.iScreenHeight + 135);


        //Adicionando as animações dos Sprites
        dino.addAnimation(10, true, 0, 4);
        background.addAnimation(5, true, 0, 7);

        for (int i = 0; i < 5; i++) {
            cometas[i].addAnimation(10, true, 0, 2);
        }


        AGSoundManager.vrMusic.loadMusic("tema.ogg", true);
        AGSoundManager.vrMusic.setVolume(0.6f, 0.6f);
        AGSoundManager.vrMusic.play();

        for (int i = 0; i < this.spriteDigits.length; i++) {
            this.spriteDigits[i] = createSprite(R.mipmap.fonte, 4, 4);
            this.spriteDigits[i].setScreenPercent(10, 10);
            for (int j = 0; j < 10; j++) {
                this.spriteDigits[i].addAnimation(1, true, j);
            }
        }
        this.spriteDigits[1].vrPosition.setXY(((float) (AGScreenManager.iScreenWidth / 2)) - (this.spriteDigits[1].getSpriteWidth() / 2.0f), this.barraScore.vrPosition.fY);
        this.spriteDigits[2].vrPosition.setXY(((float) (AGScreenManager.iScreenWidth / 2)) + (this.spriteDigits[2].getSpriteWidth() / 2.0f), this.barraScore.vrPosition.fY);
        this.spriteDigits[0].vrPosition.setXY(this.spriteDigits[1].vrPosition.fX - this.spriteDigits[0].getSpriteWidth(), this.barraScore.vrPosition.fY);
        this.spriteDigits[3].vrPosition.setXY(this.spriteDigits[2].vrPosition.fX + this.spriteDigits[3].getSpriteWidth(), this.barraScore.vrPosition.fY);


    }

    public void restart() {
        init();
        loop();
    }

    public void stop() {
    }


    private void updateTime() {

        gameTimer.update();

        if (this.gameTimer.isTimeEnded()) {
            this.timeValue++;
            this.gameTimer.restart();
        }

        {
            spriteDigits[3].setCurrentAnimation(timeValue % 10);
            spriteDigits[2].setCurrentAnimation((timeValue % 100) / 10);
            spriteDigits[1].setCurrentAnimation((timeValue % 1000) / 100);
            spriteDigits[0].setCurrentAnimation((timeValue % 10000) / 1000);
        }

    }

    private boolean colisao(AGSprite spriteColidido, AGSprite spriteColisor) {
        if (spriteColidido.collide(spriteColisor) && spriteColisor.collide(spriteColidido.vrPosition.getX(), spriteColidido.vrPosition.getY())) {
            return true;
        }
        return false;
    }

    private void determinaCometas() {
        int i = 0;
        do {
            double rand = Math.random();
            if (rand >= 0.8f && cometas[0].vrPosition.getY() >= AGScreenManager.iScreenHeight) {
                cometas[0].moveTo(1500, cometas[0].vrPosition.getX(), (AGScreenManager.iScreenHeight - AGScreenManager.iScreenHeight) - 130);
                i++;
            } else if (rand >= 0.6f && rand < 0.8f && cometas[1].vrPosition.getY() >= AGScreenManager.iScreenHeight) {
                cometas[1].moveTo(1500, cometas[1].vrPosition.getX(), (AGScreenManager.iScreenHeight - AGScreenManager.iScreenHeight) - 130);
                i++;
            } else if (rand >= 0.4f && rand < 0.6f && cometas[2].vrPosition.getY() >= AGScreenManager.iScreenHeight) {
                cometas[2].moveTo(1500, cometas[2].vrPosition.getX(), (AGScreenManager.iScreenHeight - AGScreenManager.iScreenHeight) - 130);
                i++;
            } else if (rand >= 0.2f && rand < 0.4f && cometas[3].vrPosition.getY() >= AGScreenManager.iScreenHeight) {
                cometas[3].moveTo(1500, cometas[3].vrPosition.getX(), (AGScreenManager.iScreenHeight - AGScreenManager.iScreenHeight) - 130);
                i++;
            } else if (rand >= 0.0f && rand < 0.4f && cometas[4].vrPosition.getY() >= AGScreenManager.iScreenHeight) {
                cometas[4].moveTo(1500, cometas[4].vrPosition.getX(), (AGScreenManager.iScreenHeight - AGScreenManager.iScreenHeight) - 130);
                i++;
            }

        } while (i < 3);

        tempoCometas.restart();
    }


    public void loop() {

        updateTime();

        if (AGInputManager.vrTouchEvents.backButtonClicked()) {
            this.vrGameManager.setCurrentScene(0);
            return;
        }

        tempoCometas.update();

        if (tempoCometas.isTimeEnded()) {
            determinaCometas();
        }


        //Detector de colisões
        if (colisao(dino, cometas[0]) || colisao(dino, cometas[1]) || colisao(dino, cometas[2]) || colisao(dino, cometas[3]) || colisao(dino, cometas[4]))
        {
            dino.bVisible = false;
            timeValue = 0;
            this.vrGameManager.setCurrentScene(2);
            return ;
        }


        //Trata a movimentação para a direita, quando o acelerômetro tem valores positivos, sempre rotacionando o Sprite para .NONE
        if (AGInputManager.vrAccelerometer.getAccelX() > 1.0f && dino.vrPosition.getX() <= ((float) AGScreenManager.iScreenWidth) - 75) {
            //Sempre nessa orientação
            dino.iMirror = AGSprite.NONE;
            dino.vrPosition.setX(dino.vrPosition.getX() + (AGInputManager.vrAccelerometer.getAccelX() * 10.0f));
        }

        //Trata a movimentação para a esquerda, quando o acelerômetro tem valores negativos
        else if (AGInputManager.vrAccelerometer.getAccelX() < -1.0f && dino.vrPosition.getX() > ((float) (AGScreenManager.iScreenWidth - AGScreenManager.iScreenWidth)) + 75) {
            //Sempre nessa orientação
            dino.iMirror = AGSprite.HORIZONTAL;
            dino.vrPosition.setX(dino.vrPosition.getX() + (AGInputManager.vrAccelerometer.getAccelX() * 10.0f));
            //background.restartAnimation();
        }

        //Reposiciono os meteoros para fora da tela, parte superior acima do limite da altura
        cometas[0].vrPosition.setXY(AGScreenManager.iScreenWidth / 4, AGScreenManager.iScreenHeight + 135);
        cometas[1].vrPosition.setXY(AGScreenManager.iScreenWidth / 2, AGScreenManager.iScreenHeight + 135);
        cometas[2].vrPosition.setXY(AGScreenManager.iScreenWidth / 1.3f, AGScreenManager.iScreenHeight + 135);
        cometas[3].vrPosition.setXY(AGScreenManager.iScreenWidth / 1.05f, AGScreenManager.iScreenHeight + 135);
        cometas[4].vrPosition.setXY(AGScreenManager.iScreenWidth / 16.5f, AGScreenManager.iScreenHeight + 135);
    }


}