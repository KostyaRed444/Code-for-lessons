package com.example.memory;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

class Card{
    Paint p=new Paint();
    public Card(int x, int y, int w, int h, int c){
        this.color=color;
        this.cx=x;
        this.cy=y;
        this.width=w;
        this.height=h;
        this.color=c;
    }

    int color, backColor= Color.DKGRAY;
    boolean isOpen=false;
    boolean isVis=true;
    int cx, cy;
    int width, height;
    public void draw(Canvas c){
        if (isOpen){
            p.setColor(color);
        }else{p.setColor(backColor);}

        c.drawRect(cx, cy, cx+width, cy+height, p);
    }
    public boolean flip(float tup_x, float tup_y){
        if (tup_x>=cx && tup_x<=cx+width&&tup_y>=cy&&tup_y<=cy+height){
            isOpen=!isOpen;
            return true;
        } else{return false;}
    }
}

public class TilesView extends View{
    final int PAUSE_LENGHT=2;
    boolean isOnPauseNow=false;
    int openedCard=0;
    int couple=2;
    int width, height;
    int padding=10;
    Card[][] cards= new Card[couple][couple];


    public TilesView(Context context){ super(context);}

//    public TilesView(Context context, @Nullable AttributeSet attrs){
//        super (context, attrs);
//        for (int i=0; i<couple; i++){
//            for (int j=0; j<couple; j++){
//
//            }
//        }
////        cards.add(new Card(0,0, 200, 150, Color.YELLOW));
////        cards.add(new Card(200+50, 0, 200 + 200 + 50, 150, Color.YELLOW));
////
////        cards.add(new Card(0,200, 200, 150 + 200, Color.RED));
////        cards.add(new Card(200+50, 200, 200 + 200 + 50, 150+200, Color.RED));
//    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        width=canvas.getWidth();
        height=canvas.getHeight();

        int cw=width/couple;
        int ch=height/couple;

        Paint p= new Paint();
        for (int i=0; i<couple; i++){
            for (int j=0; j<couple;j++){
              int cx= i;
              int cy=j+(int)sqrt();
            }

        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        int x=(int) event.getX();
        int y=(int) event.getY();

        if (event.getAction()==MotionEvent.ACTION_DOWN&&!isOnPauseNow){
            for (Card c: cards){
                if (openedCard==0){
                    if(c.flip(x,y)){
                        Log.d("mytag", "card flipped: "+ openedCard);
                        openedCard++;
                        invalidate();
                        return true;
                    }
                }
                if (openedCard==1){
                    if (c.flip(x, y)){
                        openedCard++;
                        invalidate();;
                        PauseTask task= new PauseTask();
                        task.execute(PAUSE_LENGHT);
                        isOnPauseNow=true;
                        return true;
                    }
                }
            }
        }
        return true;
    }
    public void newGame(){

    }
    class PauseTask extends AsyncTask<Integer, Void, Void>{
        @Override
        protected Void doInBackground(Integer... integers){
            Log.d("mytag", "Pause started");
            try{
                Thread.sleep(integers[0*1000]);
            } catch(InterruptedException e){}
            Log.d("mytag", "Pause finished");
            return null;
        }
        @Override
        protected  void onPostExecute(Void aVoid){
            for (Card c: cards){
                if (c.isOpen){
                    c.isOpen=false;
                }
            }
            openedCard=0;
            isOnPauseNow=false;
            invalidate();
        }
    }
}

