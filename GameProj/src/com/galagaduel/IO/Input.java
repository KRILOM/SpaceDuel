package com.galagaduel.IO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Arrays;

public class Input extends JComponent {

    private boolean[] map;//это массив с аски значениями(индексы = равны аски значениям кнопок)

    public Input() {

        map = new boolean[256];

        for (int i = 0; i < map.length; i++) {//пробегаемся по всем аски значениям

            final int KEY_CODE = i;

            //случай когда кнопка нажимается          //нажати кнопки которая ноль//какой-то объект , который мы будем потом использовать когда эта кнопка будет нажиматься(false, когда кнопка нажата )
            getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(i, 0, false), i * 2);//возвращает наш инпут мап, карту в которую мы можем добавлять какие-то значения на каждую кнопку и давать им какие-то названия
            //нажатие кнопок нужно ловить тогда, когда наше окно игры в фокусе
            getActionMap().put(i * 2, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {//эта функция работает когда KeyStroke.getKeyStroke(i,0,false) эта кнопка нажата
                    map[KEY_CODE] = true;//изначально когда кнопка нажата она имеет значение фолс, когда мы ее нажимаем мы передаем ей значение тру
                }
            });

            //случай когда кнопка отпускается
            getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(i, 0, true), i * 2 + 1);//возвращает наш инпут мап, карту в которую мы можем добавлять какие-то значения на каждую кнопку и давать им какие-то названия
            //нажатие кнопок нужно ловить тогда, когда наше окно игры в фокусе
            getActionMap().put(i * 2 + 1, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {//эта функция работает когда KeyStroke.getKeyStroke(i,0,true ) эта кнопка нажата
                    map[KEY_CODE] = false;
                }
            });
        }
    }

    public boolean[] getMap() {
        return Arrays.copyOf(map, map.length);//функция возвращающая всю нашу мапу
    }

    public boolean getKey(int keyCode){//метод который проверяет нажата наша кнопка или нет
        return map[keyCode];//аски значения наших кнопок
    }
}
