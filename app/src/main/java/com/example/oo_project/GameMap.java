package com.example.oo_project;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

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
    public int getLength(){
        return length;
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


    public void genSampleBoard() {
        String contents = "blue:1:1:15,red:1:1:15=blue=0=2:1:jet:red,3:1:transfertower:red,4:1:jet:red,1:2:spy:blue,2:2:rhino:red,3:2:clip:red,4:2:clip:red,5:2:rhino:red,6:2:spy:blue,1:3:horse:red,2:3:rock:red,3:3:hercules:red,5:3:hercules:red,6:3:rock:red,7:3:horse:red,3:7:horse:blue,4:7:rock:blue,5:7:hercules:blue,7:7:hercules:blue,8:7:rock:blue,9:7:horse:blue,4:8:spy:red,5:8:rhino:blue,6:8:clip:blue,7:8:clip:blue,8:8:rhino:blue,9:8:spy:red,6:9:jet:blue,7:9:transfertower:blue,8:9:jet:blue";
        String[] all_info = contents.split("=");
        //load player information
        String[] temp_info = all_info[0].split(",");
        String[] p1_info = temp_info[0].split(":");
        String[] p2_info = temp_info[1].split(":");
        Game.player1.ID = p1_info[0];
        Game.player1.movePoint = Short.parseShort(p1_info[1]);
        Game.player1.skillPoint = Short.parseShort(p1_info[2]);
        Game.player1.chessNum = Integer.parseInt(p1_info[3]);
        Game.player2.ID = p2_info[0];
        Game.player2.movePoint = Short.parseShort(p2_info[1]);
        Game.player2.skillPoint = Short.parseShort(p2_info[2]);
        Game.player2.chessNum = Integer.parseInt(p2_info[3]);
        //load whoseRound
        if (all_info[1].equals(Game.player1.ID)) {
            Game.player1.myRound = true;
            Game.player2.myRound = false;
        } else if (all_info[1].equals(Game.player2.ID)) {
            Game.player1.myRound = false;
            Game.player2.myRound = true;
        } else {
            System.out.println("load whoseRound error");
        }
        if (all_info.length < 4) {
            //no map info
            Toast toast = Toast.makeText(context, "no saved map, back please", Toast.LENGTH_SHORT);
            toast.show();
        } else {
            //load castle's occupied_round
            Castle castle = (Castle) map[getLength() - 1][getLength() - 1];
            castle.setOccupiedRound(Integer.parseInt(all_info[2]));
            //load map information
            temp_info = all_info[3].split(",");
            for (int i = 0; i < temp_info.length; i++) {
                String[] block_info = temp_info[i].split(":");
                int x = Integer.parseInt(block_info[0]), y = Integer.parseInt(block_info[1]);
                String name = block_info[2];
                Chess chess;
//                if (i == temp_info.length - 1)
//                    block_info[3] = block_info[3].substring(0, block_info[3].length() - 1);
                for (int j = 0; j < block_info[3].length(); j++) {
                    System.out.println("" + j + ":" + block_info[3].charAt(j));
                }

                if (block_info[3].contentEquals(Game.player1.ID)) {
                    switch (name) {
                        case "clip":
                            chess = new Clip(context, name, Game.player1, map[y][x]);
                            GameView.chessView_Clip(chess);
                            break;
                        case "horse":
                            chess = new Horse(context, name, Game.player1, map[y][x]);
                            GameView.chessView_Horse(chess);
                            break;
                        case "rhino":
                            chess = new Rhino(context, name, Game.player1, map[y][x]);
                            GameView.chessView_Rhino(chess);
                            break;
                        case "rock":
                            chess = new Rock(context, name, Game.player1, map[y][x]);
                            GameView.chessView_Rock(chess);
                            break;
                        case "spy":
                            chess = new Spy(context, name, Game.player1, map[y][x]);
                            GameView.chessView_Spy(chess);
                            break;
                        case "transfertower":
                            chess = new TransferTower(context, name, Game.player1, map[y][x]);
                            GameView.chessView_TransferTower(chess);
                            break;
                        case "jet":
                            chess = new Jet(context, name, Game.player1, map[y][x]);
                            GameView.chessView_Jet(chess);
                            break;
                        case "hercules":
                            chess = new Hercules(context, name, Game.player1, map[y][x]);
                            GameView.chessView_Hercules(chess);
                            break;
                        default:
                            chess = new Chess(context, name, Game.player1, map[y][x]);
                    }
                    System.out.println(chess.team.ID);
                    System.out.println(chess.chessName);
                } else {
                    switch (name) {
                        case "clip":
                            chess = new Clip(context, name, Game.player2, map[y][x]);
                            GameView.chessView_Clip(chess);
                            break;
                        case "horse":
                            chess = new Horse(context, name, Game.player2, map[y][x]);
                            GameView.chessView_Horse(chess);
                            break;
                        case "rhino":
                            chess = new Rhino(context, name, Game.player2, map[y][x]);
                            GameView.chessView_Rhino(chess);
                            break;
                        case "rock":
                            chess = new Rock(context, name, Game.player2, map[y][x]);
                            GameView.chessView_Rock(chess);
                            break;
                        case "spy":
                            chess = new Spy(context, name, Game.player2, map[y][x]);
                            GameView.chessView_Spy(chess);
                            break;
                        case "transfertower":
                            chess = new TransferTower(context, name, Game.player2, map[y][x]);
                            GameView.chessView_TransferTower(chess);
                            break;
                        case "jet":
                            chess = new Jet(context, name, Game.player2, map[y][x]);
                            GameView.chessView_Jet(chess);
                            break;
                        case "hercules":
                            chess = new Hercules(context, name, Game.player2, map[y][x]);
                            GameView.chessView_Hercules(chess);
                            break;
                        default:
                            chess = new Chess(context, name, Game.player2, map[y][x]);
                    }
                    System.out.println(chess.team.ID);
                    System.out.println(chess.chessName);
                }

            }
        }
    }
}



