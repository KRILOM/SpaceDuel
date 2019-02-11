package com.galagaduel.display;

import com.galagaduel.IO.Input;


import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Arrays;

public abstract class Display {//абстрактный потому что в любом момент времени нужен только 1 окно
    private static boolean created = false;// проверка на то создано окно уже или нет
    private static JFrame window;//рамка
    private static Canvas content;//лист который добавим в нашу рамку, и на котором будем рисовать

    private static BufferedImage buffer;//просто класс который содержит изображения в джаве
    private static int[] bufferData;//массив содеражащий всю информацию составляющая весь имедж ктороый мы создали
    private static Graphics bufferGraphics;//создаем объект изображения (в руную вытаскиваем)
    private static int clearColor;//просто какой-то цвет который мы будем использовать чтобы очищать имеджбаффер(

    private static BufferStrategy bufferStrategy;//дополнительные бафферы спомощью класса бафферстратеджи


    public static void Create(int width, int height, String title, int _clearColor, int numBuffers){// метод создания окна(title - имя окна)
        if(created){//если окно уже создано то просто возвращаем значение окна
            return;
        }
        //если еще не создано то создаем его
        else {
            window = new JFrame(title);
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//передаем окну, что должно происходить когда нажимаем на крестик


            //когда созадется наш Канвас, автоматические создается метод пейнт и в него передается графический объект с помощью которого можно рисовать много всяких вещей
            content = new Canvas();     //создаем лист
           /* {@Override
            public void paint(Graphics graphics){//эта функция вызывается с каим-то графическим объектом
                super.paint(graphics);//перед тем как мы хотим добавить свои изменения в эту функцию, мы должны сначала запустить оригинальную поскольку закулисами она делает важные вещи
                render(graphics);//когда созадется наш Канвас, автоматические создается метод пейнт и в него передается графический объект с помощью которого можно рисовать много всяких вещей

            }
            };*/
            Dimension size = new Dimension(width, height);//создаем размер листа через объект(размер нашего листа(контента ))
            content.setPreferredSize(size);//(говорим контенту что дименшен это наш размер имеет вигхт и хайгхт )
            //content.setBackground(Color.BLACK);

            window.setResizable(false);//нашему окну юзер не сможет менять размер
            window.getContentPane().add(content);//возвращает  нам внутреннюю часть самого окна(content)
            window.pack();//изменяет размер окна так, что бы наш размер подходил точно под размер контента
            window.setLocationRelativeTo(null);// при открытии игры, чтобы окно открывалось по середине
            window.setVisible(true);//видимость окна(дисплея без тру оно невидимое)

            buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);//информацию храним с помощью одного длинного ареея интеджеров
            bufferData = ((DataBufferInt) buffer.getRaster().getDataBuffer()).getData();//вытаскиваем арей из нашего имеджа
            bufferGraphics = buffer.getGraphics();
            ((Graphics2D) bufferGraphics).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);//сглаживание
            clearColor = _clearColor;//инициализируем цвет который был передан в констурктор

            content.createBufferStrategy(numBuffers);//сколько бафферов мы хотим имплементировать(с помощью контента создаем стратегию буфферизации и передаюм туда колличсетво буфферов которое мы хотим )
            bufferStrategy = content.getBufferStrategy();//вытащили

            created = true;
        }
    }

    public static void clear(){
        Arrays.fill(bufferData,clearColor);//принимает любой арей и заполняет его какими-то любыми значениями (заполняем цветами )//заполняет эрей в котором хранится вся информация о нашем имедже buffer
    }

/*
    public static void render(){
        bufferGraphics.setColor(new Color(0xff0000ff));//установка цвета курга(синяя)
        bufferGraphics.fillOval((int) (350 + (Math.sin(delta)* 200)),250,100,100);//(размещение курга без сглаживания )
        ((Graphics2D)bufferGraphics).setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);//(включение сглаживания )
        bufferGraphics.fillOval((int) (500 + (Math.sin(delta)* 200)),250,100,100);//дорисовка второго круга со сглаживанием
        ((Graphics2D)bufferGraphics).setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_OFF);// выключение сглаживания

        delta += 0.02f;

    }
*/
    public static void swapBuffers(){//поменять бафферы(баффер на который мы смотрим в данный момент(это наш контент, у него есть поле на котором мы рисуем)
        // и вот эта функция меняет то что мы видим изнутри нашего канваса на то что мы создавли(на новую сцену которая будет находиться в нашем имедже ))
        Graphics g = bufferStrategy.getDrawGraphics();//из бафера который нам нужен
        g.drawImage(buffer, 0 , 0 , null );
        bufferStrategy.show();//
    }
    public static Graphics2D getGraphics(){//возвращает объект из класса Graphics
        return (Graphics2D) bufferGraphics;
    }
    public static void Destroy(){//уничножить наше окно
        if(!created){
            return;
        }
        else{
            window.dispose();// на освобождение ресурсов окна
        }
    }
    public static void setTitle(String title){
        window.setTitle(title);
    }
    public static void addInputListener(Input inputListener){
        window.add(inputListener);
    }


}
