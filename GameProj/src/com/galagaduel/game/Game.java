package com.galagaduel.game;

import com.galagaduel.IO.Input;
import com.galagaduel.display.Display;
import com.galagaduel.utils.Time;
import com.galagaduel.graphics.TextureAtlas;

import java.awt.*;

public class Game implements Runnable{
    //int width, int height, String title, int _clearColor, int numBuffers
    /**ЗАКИНУТЬ  В КЛАСС ПРОПЕРТИС*/
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final String TITEL = "GALAGA DUEL";
    public static final int CLEAR_COLOR = 0xff000000;//очищаем экран в черный цвет (00 для краснаого 00 для зел 00 для синего )
    public static final int NUM_BUFFERS = 3;//кол-во бафферов которое будет имплементироваться с помощью канваса который находится в дисплее


    public static final float UPDATE_RATE = 60.0f;  //сколько раз в секунду мы хотим считать нашу физику
    public static final float UPDATE_INTERVAL = Time.SECOND/ UPDATE_RATE;   //сколько времени должно проходить между каждым апдейтом
    public static final long IDLE_TIME = 1; //

    public static final String ATLAS_FILE_NAME = "NES-Galaga-Galaga.png";//"NES-Galaga-Galaga.png texture_atlas.png";

    private boolean isRunning;
    private Thread gameThread;
    private Graphics2D graphics;
    private Input input;
    private TextureAtlas atlas;
    private Player player;
    private EnemyPlayer enemyPlayer;


    public Game() {
        isRunning = false;
        Display.Create(WIDTH,HEIGHT,TITEL,CLEAR_COLOR,NUM_BUFFERS);
        graphics = Display.getGraphics();//вытаскиваем графику
        input = new Input();
        Display.addInputListener(input);
        atlas = new TextureAtlas(ATLAS_FILE_NAME);
        player = new Player(375,500,atlas, 2 , 3 );
        enemyPlayer = new EnemyPlayer(375, 100, atlas,2,3);
    }
    public synchronized void start(){//start game //нельзя вызывать одновременно из двух разных процессов(потоков)
        if(isRunning){//эта функция работает только с одним потоком
            return;
        }
        else{
            isRunning = true;//запускаем метод run()
            gameThread = new Thread(this);
            gameThread.start();
        }
    }
    public synchronized void stop(){//stop game (чтобы одновременно 2 треда не могл иначинать или заканчивать нашу игру)
    if(!isRunning){
    return;
    }else{
    isRunning = false;

    try {
        gameThread.join();//ждет пока наш процесс закончит свою работу
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
    cleanUp();
    }
    }
    private void update(){//считаем физику и расчеты игры
        player.update(input);
        enemyPlayer.update(input);
        }

    private void render(){//рисуем сцены которые должны показываться на экране
    Display.clear();//очищаем баффер,(цвет который был передан в конструктор )

    player.render(graphics);
    enemyPlayer.render(graphics);

    Display.swapBuffers();//поменять наш баффер
    }
    private void cleanUp(){// любые ресурсы которые мы будем создавать, здесь мы их будем закрывать
    Display.Destroy();
    }//любые ресурсы которые желательно закрывать, это будет происходить именно в этой функции


    @Override
    public void run() {//ядро игры(бежит спомощью бесконечного цикла , до того как мы не остоновим игру  )


    int fps = 0;//считывать фпс сколько фпс
    int upd = 0;//считвание сколько апдейтов мы делаем в секунду
    int updloop = 0;//скулько раз мы повторили наш апдейт подряд

    long count = 0;

    float delta = 0;// здесь храним колличество апдейтов которое нам нужно сделать

    long lastTime = Time.get();// держит в себе просто время, настоящее время которое сейчас есть  на компьютере
    while (isRunning){//цикл работает пока isRunning = true
        long now = Time.get();//нынешнее время на итерации (оно всегда больше чем ласттайм )
        long elapsedTime = now - lastTime;//колличество времени которое прошло после прошлой итерации цикла
        lastTime = now;//делаем так, что ласттайм становится значением нау, чтобы на следующей итерации значения нау и ласттайм отличались (латсайм становится нау, а нау увеличивается)

        count += elapsedTime; //считается время которое проходит по ходу нашей игры

        boolean render = false;//если изменений не произошло то не перерисовываем экран
        delta += (elapsedTime / UPDATE_INTERVAL);
        while (delta > 1 ){//если дельта становится больше единицы
            update();//то мы делаем апдейт
            upd++;
            delta--;//и уменьшаем нашу дельту на 1
            if(render){//если наш рендер равен тру то это значить что наш вайллуп бежит не в первый раз, и если это не в первый раз, то мы делаем апдейтлуп++
                updloop++;
            }else{
                render = true;
            }
            render = true;//если сделали апдейт, то нужно перерисовать экран
        }
        if(render){//проверяем, если были какие-то изменения и нужно что-то рисовать то вызываем функцию рендер
            render();//и она уже перерисовывает польностью весь экран
            fps++;
        }
        else{
            try {
                Thread.sleep(IDLE_TIME);//иначе же засыпаем(ничего не делаем)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    if(count >= Time.SECOND){//если прошла 1 секунда с тех пор как мы включили нашу игру мы увидим наше название игры, фпс, сколько мы сделали апдейтов и сколько раз нам пришлось догонять апдейт чтобы получить желаемый результат
        Display.setTitle(TITEL + " || Fps:" + fps + "|Upd: " + upd + "|Updloop: " + updloop);
        upd = 0;// как только одна секунда прошла мы снова обновляем наши счетчики
        fps = 0;
        updloop = 0;
        count = 0;
    }
    }
  }

}
