package com.example.oo_project;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

/**
 * 繼承AppCompatImageView,
 * {@link #NE,#E,#SE,#SW,#W,#NW} : directions,
 * {@link #x,#y} : coordinates
 * {@link #neighbors} : {NE,E,SE,SW,W,NW} Neighbors
 */
class Block extends androidx.appcompat.widget.AppCompatImageView {
    final static int NE = 0, E = 1, SE = 2, SW = 3, W = 4, NW = 5;
    int x, y; //coordinate
    Player player;
    Chess chess;
    private Block neighbors[] = new Block[6]; //{NE,E,SE,SW,W,NW} Neighbors

    public Block(final Context context) {
        super(context);
        player = null;
        chess = null;
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("block clicked id:("+x+","+y+")");
                GameController.setClickedBlock((Block) v);
            }
        });


    }

    /**
     * Auto find all block's neighbors, need GameMap information.
     *
     * @param map
     */
    public void AutoFindAllNeighbor(Block[][] map) {
        int max = map.length;
        if (y - 1 >= 0) neighbors[0] = map[y - 1][x];
        if (x + 1 < max) neighbors[1] = map[y][x + 1];
        if (x + 1 < max & y + 1 < max) neighbors[2] = map[y + 1][x + 1];
        if (y + 1 < max) neighbors[3] = map[y + 1][x];
        if (x - 1 >= 0) neighbors[4] = map[y][x - 1];
        if (x - 1 >= 0 & y - 1 >= 0) neighbors[5] = map[y - 1][x - 1];
    }

    /**
     * use direction to get neighborhood, ex., getNeighbor(Block.NE)
     * return null if there is no neighbor.
     */
    public Block getNeighbor(int direction) {
        return neighbors[direction];
    }


    /**
     * swap all info with another Block.
     *
     * @param block
     */
    public void swap(Block block) {
        Player tempPlayer = block.player;
        Chess tempChess = block.chess;
        block.player = this.player;
        block.chess = this.chess;
        this.chess = tempChess;
        this.player = tempPlayer;
//        if(this.chess != null)
//            this.chess.positionBlock = this;
//        if(block.chess != null)
//            block.chess.positionBlock = block;

    }

    /**
     * move the chess on this block to another block nearby.
     *
     * @param direction
     */
    public void moveChess(int direction) {
        if (isEmpty(neighbors[direction])) {
            swap(neighbors[direction]);
        }
    }

    public void moveChess(Block block) {
        if (isEmpty(block)) {
            swap(block);
        }
    }

    /**
     * 這裡我不會做，因為我看不懂block的建構，5/23開會時詢問清楚
     *
     * @param block
     */
    boolean isEmpty(Block block) {
        if (block.player == null && block.chess == null) {
            return true;
        } else {
            return false;
        }
    }

    boolean isEmpty() {
        if (player == null && chess == null) {
            return true;
        } else {
            return false;
        }
    }

}
