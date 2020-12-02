package com.example.colortiles;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
//свойства нашей плитки, прописываем как объект
class Tile {
    int color;
    int left;
    int right;
    int bottom;
    int top;

    Tile(int l, int t, int r, int b, int c) {
        color = c;
        left = l;
        right = r;
        bottom = b;
        top = t;
    }

    int getColor() {
        return color;
    }

    void setColor(int c) {
        color = c;
    }
}

public class TilesView extends View {

    boolean flag = true; //отрисовали ли мы наши плитки, это нужно для того, чтоб они не отрисовывались после каждого нажатия по новой
    int col = 2, row = 2; //количество рядов и строк (определяем количество плиток)
    int outline = 10; //отступы, промежуточное расстояние
    int width, height; //высота и ширина игрового поля

    Tile[][] tiles = new Tile[col][row]; //опысываем массив наших плиток


    int darkColor = Color.GRAY, lightColor = Color.YELLOW;//заранее определяем цвета

    public TilesView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        //получаем параметры экрана
        width = canvas.getWidth();
        height = canvas.getHeight();

        //находим высоту и ширину области нашей плитки
        int t_width = width / col;
        int t_height = height / row;

        //задаём наши цвета
        Paint light = new Paint();
        light.setColor(lightColor);

        Paint dark = new Paint();
        dark.setColor(darkColor);

        //стили плиток
        light.setStyle(Paint.Style.FILL);
        dark.setStyle(Paint.Style.FILL);

        //задаём координаты
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {

                int left = j * t_width;
                int top = i * t_height;
                int right = left + t_width;
                int bottom = top + t_height;

                //создаём пустой прямоугольник
                Rect tile = new Rect();

                tile.set(left + outline, top + outline, right - outline, bottom - outline);

                int color;

                //рандомно распределяем цвета наших плиток
                if (flag) {
                    if (Math.random() > 0.5) {
                        canvas.drawRect(tile, light);
                        color = 1;
                    } else {
                        canvas.drawRect(tile, dark);
                        color = 0;
                    }
                    //передаём данные в наш объект
                    tiles[i][j] = new Tile(left, top, right, bottom, color);

                } else {
                    color = tiles[i][j].getColor();
                    if (color == 0) {
                        canvas.drawRect(tile, light);
                        color = 1;
                    } else {
                        canvas.drawRect(tile, dark);
                        color = 0;
                    }
                }
            }
        }
        //ставим "флаг" для того, чтоб при каждом нажатии наши плитки не отрисовывались заново
        if (flag) flag = false;
        //отрисовываем наши плитки
        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //получаем координаты нажатия
        int x = (int) event.getX();
        int y = (int) event.getY();

        //определяем событие
        if (event.getAction() == MotionEvent.ACTION_DOWN) {

            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {

                    //определяем какой плитки коснулся пользователь
                    if (tiles[i][j].left < x && tiles[i][j].right > x &&
                            tiles[i][j].top < y && tiles[i][j].bottom > y) {

                        int k = i, m = j;

                        for (int ii = 0; ii < row; ii++) {
                            for (int jj = 0; jj < col; jj++) {

                                //меняем цвет плитки на противоположный
                                if (ii == k || jj == m) {
                                    if (tiles[ii][jj].getColor() == 1)
                                        tiles[ii][jj].setColor(0);
                                    else
                                        tiles[ii][jj].setColor(1);
                                }
                            }
                        }
                        break;
                    }
                }
            }

            //заготавливаем счётчик, в который мы будем сумировать наши "цвета"
            int sum = 0;

            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    //проходим по массиву и получаем сумму
                    sum += tiles[i][j].getColor();
                }
            }
            //если сумма равна нулю (всё плитки тёмного цвета) или равна количуству плиток (все плитки светлого цвета), то значит, что пользователь выиграл
            if (sum == 0 || sum == col * row) {

                Toast toast = Toast.makeText(getContext(),
                        "Cool, you win!", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
        invalidate();//перерисовываем экран
        return true;
    }

}