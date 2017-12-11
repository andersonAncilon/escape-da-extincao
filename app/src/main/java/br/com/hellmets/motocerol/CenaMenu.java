package br.com.hellmets.motocerol;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;

import java.util.ArrayList;
import java.util.Random;

import br.com.hellmets.motocerol.AndGraph.AGGameManager;
import br.com.hellmets.motocerol.AndGraph.AGInputManager;
import br.com.hellmets.motocerol.AndGraph.AGMusic;
import br.com.hellmets.motocerol.AndGraph.AGScene;
import br.com.hellmets.motocerol.AndGraph.AGScreenManager;
import br.com.hellmets.motocerol.AndGraph.AGSoundManager;
import br.com.hellmets.motocerol.AndGraph.AGSprite;
import br.com.hellmets.motocerol.AndGraph.AGTimeManager;
import br.com.hellmets.motocerol.AndGraph.AGTimer;

public class CenaMenu extends AGScene
{

    int timeValue = 0;
    int proximoMeteoro = 0;

    AGSprite[] spriteDigits;

    AGSprite dino = null;
    AGSprite background = null;
    AGSprite prBar = null;

    AGSprite cometa1 = null;
    AGSprite cometa2 = null;
    AGSprite cometa3 = null;

    AGTimer gameTimer = null;
    AGTimer tempoCometas = null;




    public CenaMenu(AGGameManager manager) {
        super(manager);
    }

    public void init()
    {
        //Inicializaçao de variaveis
        gameTimer = new AGTimer();
        tempoCometas = new AGTimer();
        spriteDigits = new AGSprite[4];
        gameTimer = new AGTimer(1000);


        //Criando os sprites e definindo os tamanhos
        background = createSprite(R.mipmap.background, 8,1);
        background.setScreenPercent(100, 100);

        dino = createSprite(R.mipmap.pterav, 4, 4);
        dino.setScreenPercent(15, 25);

        cometa1 = createSprite(R.mipmap.cometa, 1,3);
        cometa1.setScreenPercent(15,25);
        cometa2 = createSprite(R.mipmap.cometa, 1,3);
        cometa2.setScreenPercent(15,25);
        cometa3 = createSprite(R.mipmap.cometa, 1,3);
        cometa3.setScreenPercent(15,25);

        prBar = createSprite(R.mipmap.barra, 1, 1);
        prBar.setScreenPercent(100, 10);
        prBar.vrPosition.setXY((float) (AGScreenManager.iScreenWidth / 2), ((float) AGScreenManager.iScreenHeight) - (this.prBar.getSpriteHeight() / 2.0f));

        //Limpando as texturas presentes na tela.
        releaseSpritesTextures();

        //Posicionamento dos Sprites
        dino.vrPosition.setXY(AGScreenManager.iScreenWidth / 2, AGScreenManager.iScreenHeight / 4);
        background.vrPosition.setXY(AGScreenManager.iScreenWidth / 2, AGScreenManager.iScreenHeight / 2);
        cometa1.vrPosition.setXY(AGScreenManager.iScreenWidth/4, AGScreenManager.iScreenHeight + 135);
        cometa2.vrPosition.setXY(AGScreenManager.iScreenWidth/2, AGScreenManager.iScreenHeight + 135);
        cometa3.vrPosition.setXY(AGScreenManager.iScreenWidth/1.3f, AGScreenManager.iScreenHeight + 135);


        //Adicionando as animações dos Sprites
        dino.addAnimation(10, true, 0, 4);
        background.addAnimation(5, true, 0,7);
        cometa1.addAnimation(10, true, 0,2);
        cometa2.addAnimation(10, true, 0,2);
        cometa3.addAnimation(10, true, 0,2);


        AGSoundManager.vrMusic.loadMusic("tema.ogg", true);
        AGSoundManager.vrMusic.setVolume(0.6f, 0.6f);
        AGSoundManager.vrMusic.play();

        //AE MAN POE ISSO NO TEU PROJETO
        for (int iIndex = 0; iIndex < this.spriteDigits.length; iIndex++)
        {
            this.spriteDigits[iIndex] = createSprite(R.mipmap.fonte, 4, 4);
            this.spriteDigits[iIndex].setScreenPercent(10, 10);
            for (int jIndex = 0; jIndex < 10; jIndex++)
            {
                this.spriteDigits[iIndex].addAnimation(1, true, jIndex);
            }
        }
        this.spriteDigits[1].vrPosition.setXY(((float) (AGScreenManager.iScreenWidth / 2)) - (this.spriteDigits[1].getSpriteWidth() / 2.0f), this.prBar.vrPosition.fY);
        this.spriteDigits[2].vrPosition.setXY(((float) (AGScreenManager.iScreenWidth / 2)) + (this.spriteDigits[2].getSpriteWidth() / 2.0f), this.prBar.vrPosition.fY);
        this.spriteDigits[0].vrPosition.setXY(this.spriteDigits[1].vrPosition.fX - this.spriteDigits[0].getSpriteWidth(), this.prBar.vrPosition.fY);
        this.spriteDigits[3].vrPosition.setXY(this.spriteDigits[2].vrPosition.fX + this.spriteDigits[3].getSpriteWidth(), this.prBar.vrPosition.fY);
        //TA INDO BEM MAN, ACABA AQUI

    }

    public void restart() { }

    public void stop() { }


    private void updateTime()
    {

            gameTimer.update();

            if (this.gameTimer.isTimeEnded())
            {
                this.timeValue++;
                this.gameTimer.restart();
            }

            if (this.timeValue <= 9999)
            {
                spriteDigits[3].setCurrentAnimation(timeValue % 10);
                spriteDigits[2].setCurrentAnimation((timeValue % 100) / 10);
                spriteDigits[1].setCurrentAnimation((timeValue % 1000) / 100);
                spriteDigits[0].setCurrentAnimation((timeValue % 10000) / 1000);
            }

    }


    public void loop()
    {
        updateTime();

        tempoCometas.update();

        proximoMeteoro = 0;
        if(tempoCometas.isTimeEnded())
        {
            do
            {
                double rand = Math.random();
                if(rand >= 0.8f )
                {
                    proximoMeteoro = 1;
                }

                else if(rand >= 0.4f && rand < 0.8f)
                {
                    proximoMeteoro = 2;
                }
                else if(rand >= 0.0f && rand < 0.4f)
                {
                    proximoMeteoro = 3;
                }

            }while(proximoMeteoro == 0);

            tempoCometas.restart();
        }





        if (proximoMeteoro == 1)
        {
            cometa1.moveTo(1500, cometa1.vrPosition.getX(), (AGScreenManager.iScreenHeight - AGScreenManager.iScreenHeight));
            cometa1.vrPosition.setXY(AGScreenManager.iScreenWidth/4, AGScreenManager.iScreenHeight + 135);
        }

        if (proximoMeteoro == 2)
        {
            cometa2.moveTo(1500, cometa2.vrPosition.getX(), (AGScreenManager.iScreenHeight - AGScreenManager.iScreenHeight));
            cometa2.vrPosition.setXY(AGScreenManager.iScreenWidth/2, AGScreenManager.iScreenHeight + 135);
        }

        if (proximoMeteoro == 3)
        {
            cometa3.moveTo(1500, cometa3.vrPosition.getX(), (AGScreenManager.iScreenHeight - AGScreenManager.iScreenHeight));
            cometa3.vrPosition.setXY(AGScreenManager.iScreenWidth/1.3f, AGScreenManager.iScreenHeight + 135);
        }

        if(AGInputManager.vrTouchEvents.backButtonClicked())
        {
            this.vrGameManager.setCurrentScene(0);
        }

        //Trata a movimentação para a direita, quando o acelerômetro tem valores positivos, sempre rotacionando o Sprite para .NONE
        if (AGInputManager.vrAccelerometer.getAccelX() > 1.0f && dino.vrPosition.getX() <= ((float) AGScreenManager.iScreenWidth))
        {
            dino.iMirror = AGSprite.NONE;
            dino.vrPosition.setX(dino.vrPosition.getX() + (AGInputManager.vrAccelerometer.getAccelX() * 5.0f));
        }

        //Trata a movimentação para a esquerda, quando o acelerômetro tem valores negativos
        else if (AGInputManager.vrAccelerometer.getAccelX() < - 1.0f && dino.vrPosition.getX() > ((float) (AGScreenManager.iScreenWidth - AGScreenManager.iScreenWidth)))
        {
            dino.iMirror = AGSprite.HORIZONTAL;
            dino.vrPosition.setX(dino.vrPosition.getX() + (AGInputManager.vrAccelerometer.getAccelX() * 5.0f));
        }

        //Trata a movimentação para cima
        if (AGInputManager.vrAccelerometer.getAccelY() > 0.5f && dino.vrPosition.getY() <= ((float) AGScreenManager.iScreenHeight))
        {
            dino.vrPosition.setY(dino.vrPosition.getY() + (AGInputManager.vrAccelerometer.getAccelZ() * 3.0f));
        }

        //Trata a movimentação para a baixo, quando o acelerômetro tem valores negativos
        else if (AGInputManager.vrAccelerometer.getAccelY() <  -5.0f && dino.vrPosition.getY() >= ((float) (AGScreenManager.iScreenHeight - AGScreenManager.iScreenHeight)))
        {
            dino.vrPosition.setY(dino.vrPosition.getY() + (AGInputManager.vrAccelerometer.getAccelY() * 3.0f));
        }



    }


}