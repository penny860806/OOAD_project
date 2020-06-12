package com.example.oo_project;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.annotation.RequiresApi;

import java.util.concurrent.ScheduledExecutorService;

/**
 * {@link #length} 地圖邊長; {@link #map} save blocks;
 */
public class GameMap {
    private int length, blocks;
    Block[][] map;
    Context context;
    LinearLayout lowestLayout;

    //constructor
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    GameMap(int n, Context context, LinearLayout layout) {
        this.length = n;
        this.context = context;
        this.blocks = 3 * (length * length) - 3 * length + 1;
        this.map = new Block[2 * length - 1][2 * length - 1];
        mapLayout(context, layout);
        createBlocksArray(layout, map);
        //find neighbors and set onClick
        Block temp;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                temp = map[i][j];
                if (temp != null) {
                    temp.AutoFindAllNeighbor(map);
                }
            }
        }
        setMapEdge();

    }

    //Display map
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void mapLayout(Context context, LinearLayout layout) {
        /**新增第一層的Layout當容器，設定他的參數
         * 長寬與PARENT相同
         * 排列方式:垂直
         * 底色:黑色
         * 子物件置中
         */
        lowestLayout = new LinearLayout(context);
        LinearLayout.LayoutParams lowestparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        lowestLayout.setLayoutParams(lowestparams);
        lowestLayout.setOrientation(LinearLayout.VERTICAL);
        lowestLayout.setBackgroundColor(Color.parseColor("#ff33b5e5"));// blue
        lowestLayout.setGravity(Gravity.CENTER);
        layout.addView(lowestLayout);
        /**
         * 新增第二層Layout參數物件
         */
        FrameLayout.LayoutParams secondlayoutParams = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                110);
        /**
         * 新增Block的參數物件
         * */
        LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(
                100,
                100);
        /**
         * 產生上部分BLOCKS的XML
         * 每次迴圈產生一排Layout容器，加入BLOCKS
         * */
        int count = 0;
        for (int i = 0; i < length; i++) {
            FrameLayout secondLayout = new FrameLayout(lowestLayout.getContext());
            secondLayout.setBackgroundColor(Color.TRANSPARENT); //底色同PARENT
            secondLayout.setLayoutParams(secondlayoutParams);
            lowestLayout.addView(secondLayout);

            for (int j = 0; j < i + length; j++) {
                Block btn;
                if((i==1 && (j==1||j==length-1))||(i==5&&(j==1||j==2*(length-1)-1))){
                    btn = new Fountain(secondLayout.getContext());//產生泉
                }else if(i==length-1&&j==length-1){
                    btn = new Castle(secondLayout.getContext());//產生城
                    Castle.theCastle = (Castle) btn;
                }else {
                    btn = new Block(secondLayout.getContext()); //產生normal block
                    btn.setImageResource(R.drawable.block);
                }
                buttonParams.leftMargin = 110 * j;
                btn.setId(count);
                btn.setBackgroundColor(Color.TRANSPARENT); //設定顏色
                secondLayout.addView(btn, buttonParams);
                count++;
            }
        }
        /**
         * 產生下半部BLOCKS的XML
         * 每次迴圈產生一排Layout容器，加入BLOCKS
         * */
        for (int i = length - 1; i > 0; i--) {
            FrameLayout secondLayout = new FrameLayout(lowestLayout.getContext());
            secondLayout.setBackgroundColor(Color.TRANSPARENT); //底色同PARENT
            secondLayout.setLayoutParams(secondlayoutParams);
            lowestLayout.addView(secondLayout);

            for (int j = 0; j < i + length - 1; j++) {
                Block btn;
                if((i==2 && (j==1||j==length-1))){
                    btn = new Fountain(secondLayout.getContext());//產生泉
                }else {
                    btn = new Block(secondLayout.getContext()); //產生normal block
                    btn.setImageResource(R.drawable.block);
                }
                buttonParams.leftMargin = 110 * j;
                btn.setId(count);
                btn.setBackgroundColor(Color.TRANSPARENT); //設定顏色

                btn.setLayoutParams(buttonParams);
                secondLayout.addView(btn);
                count++;
            }
        }

    }

    //put all blocks into array
    private void createBlocksArray(View view, Block[][] finalmap) {
        Block[] tempMap = new Block[blocks];
        //透過ID找Layout上的BLOCK放進map
        for (int i = 0; i < blocks; i++) {
            tempMap[i] = (Block) view.findViewById(i);
        }

        int middle = (3 * (length * length) - 5 * length + 2) / 2;
        int temp = length, l = temp, count = 0, level = 0;
        //generate coordination of upper map nodes
        for (int i = 0; i < middle + 2 * length - 1; i++) {
            if (i > temp - 1) {
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
        for (int i = middle + 2 * length - 1; i < blocks; i++) {
            if (i > temp - 1) {
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

    private void setMapEdge() {
        Block begin = map[0][0];
        Block now = begin;
        int dir = Block.E;
        do {
            genEdgeRock(now);
            if (now.getNeighbor(dir) == null) {
                dir++;
                if (dir > 5) dir = 0;
            }
            now = now.getNeighbor(dir);
        } while (now != begin);
    }

    private void genEdgeRock(Block block) {
        block.setVisibility(View.INVISIBLE);
        block.chess = new Rock(block.getContext(), "rock", null, block);
    }


    public void genSampleBoard(Player player1, Player player2) {
        Block temp;
        Chess chess;
        FrameLayout.LayoutParams chessParams;
        int i;
        for (i = 0; i < 5; i++) {
            temp = map[0][i];
            chess = new Chess(temp.getContext(), player1.ID + ":chess:" + i, player1, temp);
            chessParams = (FrameLayout.LayoutParams) temp.getLayoutParams();
            chess.setLayoutParams(chessParams);
            chess.setImageResource(R.drawable.clip_blue);
            FrameLayout layout = (FrameLayout) temp.getParent();
            temp.chess = chess;
            layout.addView(chess);
        }
        for (i = 4; i < 9; i++) {
            temp = map[8][i];
            chess = new Chess(temp.getContext(), player2.ID + ":chess:" + i, player2, temp);
            chessParams = (FrameLayout.LayoutParams) temp.getLayoutParams();
            chess.setLayoutParams(chessParams);
            chess.setImageResource(R.drawable.rock_red);
            FrameLayout layout = (FrameLayout) temp.getParent();
            temp.chess = chess;
            layout.addView(chess);
        }
    }
}



