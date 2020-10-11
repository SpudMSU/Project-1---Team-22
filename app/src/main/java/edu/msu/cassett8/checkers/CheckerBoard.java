package edu.msu.cassett8.checkers;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.Random;

public class CheckerBoard {

    final static float SCALE_IN_VIEW = .95f;

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

        int wid = canvas.getWidth();
        int hit = canvas.getHeight();

        // Determine the minimum of the two dimensions
        int minDim = wid < hit ? wid : hit;

        int puzzleSize = (int)(minDim * SCALE_IN_VIEW);

        // Compute the margins so we center the puzzle
        int marginX = (wid - puzzleSize) / 2;
        int marginY = (hit - puzzleSize) / 2;

        //
        // Draw the outline of the puzzle
        //

        float scaleFactor = (float)puzzleSize / (float)boardImage.getWidth();

        canvas.save();
        canvas.translate(marginX, marginY);
        canvas.scale(scaleFactor, scaleFactor);
        canvas.drawBitmap(boardImage, 0, 0, null);
        canvas.restore();

        for(CheckerPiece piece : pieces) {
            piece.draw( canvas, marginX, marginY, puzzleSize, scaleFactor);
        }

    }
}
