package com.example.oo_project;


import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.BaseExpandableListAdapter;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Game {
    public Fountain[] fountainList = new Fountain[6];
    int step = 1;
    int deathChess = 0;
    Player player1, player2;

    GameMap GM;
    final public static int TransferTowerId = 0, RhinoId = 1, RockId = 2, ClipId = 3, HerculesId = 4, JetId = 5, HorseId = 6, SpyId = 7;

    Game(GameMap map) {
        GM = map;
        player1 = new Player(true, "blue");//讓玩家1先手
        player2 = new Player(false, "red");//玩家2後手

        int temp = 0;
        for (int i = 0; i < map.map.length; i++) {
            for (int j = 0; j < map.map[i].length; j++) {
                if (GM.map[i][j] != null && map.map[i][j] instanceof Fountain) {
                    fountainList[temp++] = (Fountain) map.map[i][j];
                }
            }
        }
    }

    public void ChangeRound() {//交換回合，待新增泉與城後做修正
        System.out.println("ChangeRound");
        player1.myRound = !player1.myRound;
        player2.myRound = !player2.myRound;

        for (int i = 0; i < GM.map.length; i++) {
            for (int j = 0; j < GM.map[i].length; j++) {

                if (GM.map[i][j] != null && GM.map[i][j].chess != null && GM.map[i][j].chess.team != null) {
                    GM.map[i][j].chess.setClickAvail(GM.map[i][j].chess.team.myRound);
                }
            }
        }
        /* skill point & move point initialize*/
        {
            player1.movePoint = Player.baseMovePoint;
            player1.skillPoint = Player.baseSkillPoint;
            player2.movePoint = Player.baseMovePoint;
            player2.skillPoint = Player.baseSkillPoint;
        }

        /* fountain 提供雙方額外技能點和移動步數 */
        {
            int fountainOccupy_player1 = 0, fountainOccupy_player2 = 0;
            for (int i = 0; i < fountainList.length; i++) {
                if (fountainList[i].player == player1) {
                    fountainOccupy_player1++;
                }
                if (fountainList[i].player == player2) {
                    fountainOccupy_player2++;
                }
            }
            player1.skillPoint += fountainOccupy_player1;
            player2.skillPoint += fountainOccupy_player2;
            if (fountainOccupy_player1 >= 4) {
                player1.movePoint++;
            }
            if (fountainOccupy_player2 >= 4) {
                player2.movePoint++;
            }
        }
        /* 缺換底色 */
        if (whoseRound() == player1) {
            GM.lowestLayout.setBackgroundColor(Color.parseColor("#ff33b5e5")); //blue player backgound (holo_blue_light)

        }
        if (whoseRound() == player2) {
            GM.lowestLayout.setBackgroundColor(Color.parseColor("#ffff4444")
                    //blue player backgound (holo_blue_light)
            );
        }
        //reset timer
        NewGame.customTimer.stopTimer();
        NewGame.customTimer.timeLeftMS = 30000;
        NewGame.customTimer.startTimer();

        System.out.println("Change Round complete. whose round:" + whoseRound().ID);
        GameController.movementFinish();

    }

    /**
     * return true if any chess die
     *
     * @return
     */
    public boolean checkAllDeath() {
        int count = 0;
        boolean flag = false;
        for (int i = 0; i < GM.map.length - 1; i++) {
            for (int j = 0; j < GM.map[i].length - 1; j++) {
                if (GM.map[i][j] != null) {
                    Chess chess = GM.map[i][j].chess;
                    if (chess != null && chess.team != null) {
                        for (int k = 0; k < 6; k++) {
                            Chess nei_chess = chess.positionBlock.getNeighbor(k).chess;
                            if (nei_chess != null && nei_chess.team != null) {
                                if (chess.deathTeam){
                                    if(chess.team==nei_chess.team){
                                        count++;
                                        flag = true;
                                    }
                                }else{
                                    if(chess.team!=nei_chess.team){
                                        count++;
                                        flag = true;
                                    }
                                }
                            }
                        }
                        if (count >= chess.deathNum) {
                            chess.positionBlock.chess = null;
                            chess.positionBlock.player = null;
                            GameView.removeChess_View(chess);
                            flag = true;
                        }
                        count = 0;
                    }
                }
            }
        }
        return flag;
    }


    public Player whoseRound() {//找出當回合玩家的方法
        Player playerNow;
        if (this.player1.myRound == true)
            playerNow = player1;
        else
            playerNow = player2;

        if (playerNow == null) {
            Log.i("Game.whoseRound", "null return 117");
        }
        return playerNow;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void saveGame(Context context) throws JSONException, IOException {
        //紀錄whoseRound、Map、ControllerState

        String save = "";
        //save player info : playerID, movePoint, skillPoint, whoseRound
        save = save + player1.ID + ":" + player1.movePoint + ":"
                + player1.skillPoint + ",";
        save = save + player2.ID + ":" + player2.movePoint + ":"
                + player2.skillPoint;
        save += "=" + whoseRound().ID + "=";

        //save map info
        for (Block[] array : GM.map) {
            for (Block block : array) {
                if (block != null) {
                    if (block.chess != null && block.player != null) {
                        save = save + block.x + ":" + block.y + ":" + block.chess.chessName
                                + ":" + block.player.ID + ",";
                    }
                }
            }
        }
        save = save.substring(0, save.length() - 1);
        System.out.println(save);
        FileOutputStream fos = null;
        try {
            fos = context.openFileOutput("save.txt", Context.MODE_PRIVATE);
            fos.write(save.getBytes());
            System.out.println("save file to: " + context.getFilesDir());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("save file not found error");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("save other error");
        } finally {
            if (fos != null) {
                fos.close();
            }
        }


    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void loadGame(Context context) throws IOException {
        String contents = null;
        FileInputStream fis = context.openFileInput("save.txt");
        InputStreamReader inputStreamReader =
                new InputStreamReader(fis);
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(inputStreamReader)) {
            String line = reader.readLine();
            while (line != null) {
                stringBuilder.append(line).append('\n');
                line = reader.readLine();
            }

        } catch (IOException e) {
            // Error occurred when opening raw file for reading.
        } finally {
            if (fis != null) {
                contents = stringBuilder.toString();
                System.out.println(contents);
                fis.close();
            }

        }
        if (contents != null) {
            String[] all_info = contents.split("=");
            //load player information
            String[] temp_info = all_info[0].split(",");
            String[] p1_info = temp_info[0].split(":");
            String[] p2_info = temp_info[1].split(":");
            player1.ID = p1_info[0];
            player1.movePoint = Short.parseShort(p1_info[1]);
            player1.skillPoint = Short.parseShort(p1_info[2]);
            player2.ID = p2_info[0];
            player2.movePoint = Short.parseShort(p2_info[1]);
            player2.skillPoint = Short.parseShort(p2_info[2]);
            //load whoseRound
            if (all_info[1].equals(player1.ID)) {
                player1.myRound = true;
                player2.myRound = false;
            } else if (all_info[1] == player2.ID) {
                player1.myRound = false;
                player2.myRound = true;
            } else {
                System.out.println("load whoseRound error");
            }
            //load map information
            if (all_info.length < 3) {
                Toast toast = Toast.makeText(context, "no saved map", Toast.LENGTH_SHORT);
                toast.show();
            } else {
                temp_info = all_info[2].split(",");
                for (int i = 0; i < temp_info.length; i++) {
                    String[] block_info = temp_info[i].split(":");
                    int x = Integer.parseInt(block_info[0]), y = Integer.parseInt(block_info[1]);
                    String name = block_info[2];
                    Chess chess;
                    if (i == temp_info.length - 1)
                        block_info[3] = block_info[3].substring(0, block_info[3].length() - 1);
                    for (int j = 0; j < block_info[3].length(); j++) {
                        System.out.println("" + j + ":" + block_info[3].charAt(j));
                    }

                    if (block_info[3].contentEquals(player1.ID)) {
                        switch (name) {
                            case "clip":
                                chess = new Clip(context, name, player1, GM.map[y][x]);
                                break;
                            case "horse":
                                chess = new Horse(context, name, player1, GM.map[y][x]);
                                break;
                            case "rhino":
                                chess = new Rhino(context, name, player1, GM.map[y][x]);
                                break;
                            case "rock":
                                chess = new Rock(context, name, player1, GM.map[y][x]);
                                break;
                            case "spy":
                                chess = new Spy(context, name, player1, GM.map[y][x]);
                                break;
                            case "transfertower":
                                chess = new TransferTower(context, name, player1, GM.map[y][x]);
                                break;
                            case "jet":
                                chess = new Jet(context, name, player1, GM.map[y][x]);
                                break;
                            default:
                                chess = new Chess(context, name, player1, GM.map[y][x]);
                        }
                        GameView.chessView_Blue(chess);
                        System.out.println(chess.team.ID);
                        System.out.println(chess.chessName);
                    } else {
                        switch (name) {
                            case "clip":
                                chess = new Clip(context, name, player2, GM.map[y][x]);
                                break;
                            case "horse":
                                chess = new Horse(context, name, player2, GM.map[y][x]);
                                break;
                            case "rhino":
                                chess = new Rhino(context, name, player2, GM.map[y][x]);
                                break;
                            case "rock":
                                chess = new Rock(context, name, player2, GM.map[y][x]);
                                break;
                            case "spy":
                                chess = new Spy(context, name, player2, GM.map[y][x]);
                                break;
                            case "transfertower":
                                chess = new TransferTower(context, name, player2, GM.map[y][x]);
                                break;
                            case "jet":
                                chess = new Jet(context, name, player2, GM.map[y][x]);
                                break;
                            default:
                                chess = new Chess(context, name, player2, GM.map[y][x]);
                        }
                        GameView.chessView_Red(chess);
                        System.out.println(chess.team.ID);
                        System.out.println(chess.chessName);
                    }

                }
            }
        } else {
            System.out.println("save no contents");
        }

    }


}