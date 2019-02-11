package com.galagaduel.main;

import com.galagaduel.game.Game;


public class Main {
    public static void main(String[] args) {



        Game galaga = new Game();
        galaga.start();
    }
}












































//0123456789abcdef (int 0xffffffff)

//        Display.Create(800, 600, " GALAGA",0xff00ff00,3 );//(int ffff - green )все пиксели не прозрачныые(ff)red no(00)green yes(ff)blue no(00)
/*        Timer t = new Timer(1000 / 60, new AbstractAction() {//экран будет обнавляться каждую 1/60 секунды, второй параметр - что мы хоти делать каждый этот интервал времени
    @Override
    public void actionPerformed(ActionEvent e) {//анонимный метод который будет вызываться каждый этот интервал времени
        Display.clear();
        //Display.render();
        Display.swapBuffers();
        }
    });
    t.setRepeats(true);//эта функция говорит нашему таймеру повторяться(без нее таймер просто проработает один раз (1/60))
    t.start();*/
