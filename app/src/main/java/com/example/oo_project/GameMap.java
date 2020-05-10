package com.example.oo_project;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.RequiresApi;

/**
 * {@link #length} 地圖邊長; {@link #map} save blocks;
 */
public class GameMap {
    int length;
    Block[] map;
    //constructor
    GameMap(int n){
        this.length = n;
    }

    //不知道會不會再用到，先留著

//    public void createMapInfo(){
//        //blocks = map size
//        int blocks = 3*(length*length)-3*length+1;
//        map = new Block[blocks];
//        //add blocks
//        for(int i=0;i<blocks;i++){
//            map[i] = new Block(i);
//        }
//        int middle = (3*(length*length)-5*length+2)/2;
//        int temp = length,l = temp,count = 0,level = 0;
//        //generate coordination of lower map nodes
//        for(int i=0;i<middle+2*length-1;i++){
//            if(i>temp-1){
//                l++;
//                temp = l + temp;
//                count = 0;
//                level++;
//            }
//            map[i].x = level;
//            map[i].y = count;
//            count++;
//        }
//        //generate coordination of upper map nodes
//        for(int i=middle+2*length-1;i<blocks;i++){
//            if(i>temp-1){
//                l--;
//                temp = l + temp;
//                count = 0;
//                level++;
//            }
//            map[i].x = level;
//            map[i].y = count;
//            count++;
//        }
//    }

    //place block into map

    //Display map
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void mapLayout(Context context,LinearLayout layout){
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
                btn.setImageResource(R.drawable.blackblock);
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
                btn.setImageResource(R.drawable.blackblock);
                secondLayout.addView(btn, buttonParams);
                count++;
            }
        }

    }
    //put all blocks into array
    public void createMapInfo(View view){
        //計算總BLOCKS數
        int blocks = 3*(length*length)-3*length+1;
        map = new Block[blocks];
        //透過ID找Layout上的BLOCK放進map
        for(int i=0;i<blocks;i++){
            map[i] = (Block)view.findViewById(i);
        }
    }
}

/**
 * 繼承AppCompatImageView
 */
class Block extends androidx.appcompat.widget.AppCompatImageView{
    int x,y,actual_x,actual_y;
    Player player;

    public Block(Context context) {
        super(context);
    }
}


