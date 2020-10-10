package edu.msu.cassett8.checkers;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.Random;

public class CheckerBoard {

    final static float SCALE_IN_VIEW = 0.9f;

    public ArrayList<CheckerPiece> pieces = new ArrayList<CheckerPiece>();
    /**
     * Completed puzzle bitmap
     */
    private Bitmap boardImage;

    public CheckerBoard(Context context){
        boardImage = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.checkersboard);

        //you will want to set initial x and y here

        for (int i=0; i<12; i++) {
            Random rand = new Random();
            //spawn white
            pieces.add(new CheckerPiece(context,
                    R.drawable.spartan_white,
                    rand.nextFloat(),
                    rand.nextFloat(), false));
        }

        for (int i=0; i<12; i++) {
            Random rand = new Random();
            //spawn white
            pieces.add(new CheckerPiece(context,
                    R.drawable.spartan_green,
                    rand.nextFloat(),
                    rand.nextFloat(), true));
        }

    }

    public void draw(Canvas canvas) {

        int wid = canvas.getHeight();
        int hit = canvas.getWidth();

        // Determine the minimum of the two dimensions
        int minDim = wid < hit ? wid : hit;

        wid = boardImage.getHeight();
        hit = boardImage.getWidth();

        int puzzleSize = (int)(minDim * SCALE_IN_VIEW);
        //int marginX = (wid - puzzleSize) / 2;

        float scaleFactor = (float)minDim /
                ((float)boardImage.getHeight());

        canvas.save();
        canvas.scale(scaleFactor, scaleFactor);
        canvas.drawBitmap(boardImage, 0, 0, null);
        canvas.restore();

        for(CheckerPiece piece : pieces) {
            piece.draw(canvas,0,0 , puzzleSize, scaleFactor);
        }

    }
}
