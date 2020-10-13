package edu.msu.cassett8.checkers;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.Random;

public class CheckerBoard {

    int mPuzzleSize = -1;
    boolean initialized = false;
    final static float SCALE_IN_VIEW = .95f;

    public ArrayList<CheckerPiece> whitePieces = new ArrayList<CheckerPiece>();
    public ArrayList<CheckerPiece> greenPieces = new ArrayList<CheckerPiece>();
    /**
     * Completed puzzle bitmap
     */
    private Bitmap boardImage;

    public CheckerBoard(Context context){
        boardImage = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.checkersboard);

        //spawn white checkers
        for (int i=0; i<12; i++) {
            whitePieces.add(new WhiteChecker(context,
                    R.drawable.spartan_white,
                    0));
        }

        //spawn green checkers
        for (int i=0; i<12; i++) {
            greenPieces.add(new GreenChecker(context,
                    R.drawable.spartan_green,
                    0));

        }

    }

    public void draw(Canvas canvas) {

        int wid = canvas.getWidth();
        int hit = canvas.getHeight();

        if(!initialized)
        {
            setInitialPos(wid, hit);
            initialized=true;
        }

        // Determine the minimum of the two dimensions
        int minDim = wid < hit ? wid : hit;

        int puzzleSize = (int)(minDim * SCALE_IN_VIEW);

        int mPuzzleSize = puzzleSize;

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

        /*//draw all green checker pieces
        for(CheckerPiece piece : greenPieces) {
            piece.draw( canvas, marginX, marginY, puzzleSize, scaleFactor);
        }*/

        //draw all white checker pieces
        for(CheckerPiece piece : whitePieces) {
            piece.draw( canvas, marginX, marginY, puzzleSize, scaleFactor);
        }

    }

    public void setInitialPos(int wid, int hit)
    {
        //code for setting initial positions of checkers

        //first 12 are white and first 12 are green. May also be other way around. I dont know. x and y range from 0-.95f.

        //example of setting their locations (randomly)
        Random rand = new Random();//remove
        float shift = 0;
        int count = 0;

        for(CheckerPiece piece : greenPieces) {
            piece.setCords(rand.nextFloat()*SCALE_IN_VIEW, rand.nextFloat()*SCALE_IN_VIEW);
        }

        for(CheckerPiece piece : whitePieces) {

            if (count <= 3) {
                piece.setCords((0.123f * SCALE_IN_VIEW) + shift, (0.123f * SCALE_IN_VIEW));
                shift = (shift + 0.218f);
                count++;
            }
            if (count > 3 && count <= 7){
                break;
            }


        }
    }
}
