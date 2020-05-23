package com.example.oo_project;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.RequiresApi;

import java.util.concurrent.ScheduledExecutorService;

/**
 * {@link #length} 地圖邊長; {@link #map} save blocks;
 */
public class GameMap {
    private int length,blocks;
    Block[][] map;
    Context context;
    //constructor
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    GameMap(int n,Context context,LinearLayout layout){
        this.length = n;
        this.context = context;
        this.blocks = 3*(length*length)-3*length+1;
        this.map = new Block[2*length-1][2*length-1];
        mapLayout(context,layout);
        createBlocksArray(layout,map);
    }

    //Display map
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void mapLayout(Context context,LinearLayout layout){
        /**新增第一層的Layout當容器，設定他的參數
         * 長寬與PARENT相同
         * 排列方式:垂直
         * 底色:黑色
         * 子物件置中
         */
        LinearLayout lowestLayout = new LinearLayout(context);
        LinearLayout.LayoutParams lowestparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        lowestLayout.setLayoutParams(lowestparams);
        lowestLayout.setOrientation(LinearLayout.VERTICAL);
        lowestLayout.setBackgroundColor(Color.GRAY);
        lowestLayout.setGravity(Gravity.CENTER);
        layout.addView(lowestLayout);
        /**
         * 新增第二層Layout參數物件
         */
        LinearLayout.LayoutParams secondlayoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                110);
        /**
         * 新增Block的參數物件
         * */
        LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(
                100,
                100);
        buttonParams.setMargins(10,10,10,10);//設定BLOCKS間的距離
        /**
         * 產生上部分BLOCKS的XML
         * 每次迴圈產生一排Layout容器，加入BLOCKS
         * */
        int count = 0;
        for(int i=0;i<length;i++){
            LinearLayout secondLayout = new LinearLayout(lowestLayout.getContext());
            secondLayout.setOrientation(LinearLayout.HORIZONTAL); //水平排列
            secondLayout.setBackgroundColor(Color.TRANSPARENT); //底色同PARENT
            secondLayout.setGravity(Gravity.CENTER); //子物件置中
            secondLayout.setPaddingRelative(10,10,10,10); //padding
            secondLayout.setLayoutParams(secondlayoutParams);
            lowestLayout.addView(secondLayout);

            for(int j=0;j<i+length;j++){
                Block btn = new Block(secondLayout.getContext()); //產生Button
                btn.setId(count);
                btn.setImageResource(R.drawable.block);
                btn.setBackgroundColor(Color.TRANSPARENT); //設定顏色
                secondLayout.addView(btn, buttonParams);
                count++;
            }
        }
        /**
         * 產生下半部BLOCKS的XML
         * 每次迴圈產生一排Layout容器，加入BLOCKS
         * */
        for(int i=length-1;i>0;i--){
            LinearLayout secondLayout = new LinearLayout(lowestLayout.getContext());
            secondLayout.setOrientation(LinearLayout.HORIZONTAL); //水平排列
            secondLayout.setBackgroundColor(Color.TRANSPARENT); //底色同PARENT
            secondLayout.setGravity(Gravity.CENTER); //子物件置中
            secondLayout.setPaddingRelative(10,10,10,10); //padding
            secondLayout.setLayoutParams(secondlayoutParams);
            lowestLayout.addView(secondLayout);

            for(int j=0;j<i+length-1;j++){
                Block btn = new Block(secondLayout.getContext()); //產生Block
                btn.setId(count);
                btn.setBackgroundColor(Color.TRANSPARENT); //設定顏色
                btn.setImageResource(R.drawable.block);
                secondLayout.addView(btn, buttonParams);
                count++;
            }
        }

    }
    //put all blocks into array
    private void createBlocksArray(View view,Block[][] finalmap){
        Block[] tempMap = new Block[blocks];
        //透過ID找Layout上的BLOCK放進map
        for(int i=0;i<blocks;i++){
            tempMap[i] = (Block)view.findViewById(i);
        }

        int middle = (3*(length*length)-5*length+2)/2;
        int temp = length,l = temp,count = 0,level = 0;
        //generate coordination of upper map nodes
        for(int i=0;i<middle+2*length-1;i++){
            if(i>temp-1){
                l++;
                temp = l + temp;
                count = 0;
                level++;
            }
            tempMap[i].x = count;
            tempMap[i].y = level;
            finalmap[level][count] = tempMap[i];
            count++;
        }
        //generate coordination of bottom map nodes
        int count2 = 1;
        for(int i=middle+2*length-1;i<blocks;i++){
            if(i>temp-1){
                l--;
                temp = l + temp;
                count = count2;
                count2++;
                level++;
            }
            tempMap[i].x = count;
            tempMap[i].y = level;
            finalmap[level][count] = tempMap[i];
            count++;
        }

    }


    public void genSampleBoard(Player player1,Player player2){
        Block temp;
        Chess chess;
        int i;
        for(i=0;i<5;i++){
            temp = map[0][i];
            chess = new Chess(temp.getContext(),player1.ID+":chess:"+i,1,player1);
            chess.setTop(temp.getTop());
            chess.setLeft(temp.getLeft());
            chess.setImageResource(R.drawable.chess_blue);
            LinearLayout layout = (LinearLayout) temp.getParent();
            layout.addView(chess);
        }
        for(i=4;i<9;i++){
            temp = map[8][i];
            temp.chess = new Chess(temp.getContext(),player2.ID+":chess:"+i,1,player2);
        }
    }
}

/**
 * 繼承AppCompatImageView,
 * {@link #NE,#E,#SE,#SW,#W,#NW} : directions,
 * {@link #x,#y} : coordinates
 * {@link #neighbors} : {NE,E,SE,SW,W,NW} Neighbors
 */
class Block extends androidx.appcompat.widget.AppCompatImageView{
    final static int NE=0,E=1,SE=2,SW=3,W=4,NW=5;
    int x,y; //coordinate
    Player player;
    Chess chess;
    private Block neighbors[] = new Block[6]; //{NE,E,SE,SW,W,NW} Neighbors

    public Block(Context context) {
        super(context);
    }

    /**
     * Auto find all block's neighbors, need GameMap information.
     * @param map
     */
    public void AutoFindAllNeighbor(Block[][] map){
        int max = map.length;
        if(y-1>=0) neighbors[0] = map[y - 1][x];
        if(x+1<max) neighbors[1] = map[y][x + 1];
        if(x+1<max & y+1<max) neighbors[2] = map[y + 1][x + 1];
        if(y+1<max) neighbors[3] = map[y + 1][x];
        if(x-1>=0) neighbors[4] = map[y][x - 1];
        if(x-1>=0 & y-1>=0) neighbors[5] = map[y - 1][x - 1];
    }
    /**
     * use direction to get neighborhood, ex., getNeighbor(Block.NE)
     * return null if there is no neighbor.
     */
    public Block getNeighbor(int direction){
        return neighbors[direction];
    }



}


