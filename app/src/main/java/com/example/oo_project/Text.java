package com.example.oo_project;

import android.widget.TextView;

public class Text {
    public static class PutChess {
        static TextView chessNameBlock, messageBlock;
        static TextView timer;
        static TextView round;
        static final String TransferTower =
                "傳送塔\n-死亡條件：周圍沒有友軍時死亡\n" +
                        "-移動距離：1\n" +
                        "-技能範圍：8\n" +
                        "-技能耗點：1\n" +
                        "-技能說明：\n" +
                        " 點選一名任意友軍，傳送塔將與其互換位置\n";
        static final String Jet =
                "氣場\n-死亡條件：周圍3名敵軍時死亡\n" +
                        "-移動距離：1\n" +
                        "-技能範圍：1\n" +
                        "-技能耗點：1\n" +
                        "-技能說明：\n" +
                        "將周圍所有人外推一格(連同與被推棋子同一直線且相連的目標)，施放完技能後可額外移動一次\n";
        static final String Rhino =
                "犀牛\n-死亡條件：周圍5名敵軍時死亡\n" +
                        "-移動距離：1\n" +
                        "-技能範圍：1\n" +
                        "-技能耗點：1\n" +
                        "-技能說明：\n" +
                        "點選一名敵軍，犀牛會將其衝撞至底部，並將沿途遇到的棋子一併撞擊到底\n";
        static final String Rock =
                "巨石\n-死亡條件：周圍4名敵軍時死亡\n" +
                        "-移動距離：1\n" +
                        "-技能範圍：0\n" +
                        "-技能耗點：0\n" +
                        "-技能說明：\n" +
                        "無法被任何技能控制或移動\n";
        static final String Clip =
                "夾子\n-死亡條件：周圍4名敵軍時死亡\n" +
                        "-移動距離：1\n" +
                        "-技能範圍：1\n" +
                        "-技能耗點：1\n" +
                        "-技能說明：\n" +
                        "點選一名敵軍，之後將其拉至撞到任意目標為止\n";
        static final String Hercules =
                "力士\n-死亡條件：周圍4名敵軍時死亡\n" +
                        "-移動距離：1\n" +
                        "-技能範圍：1\n" +
                        "-技能耗點：1\n" +
                        "-技能說明：\n" +
                        "點選周圍一名敵軍，並點選周圍一個空格，力士將會把它抓舉至該處\n";
        static final String Horse =
                "馬\n-死亡條件：周圍3名敵軍時死亡\n" +
                        "-移動距離：1\n" +
                        "-技能範圍：1\n" +
                        "-技能耗點：1\n" +
                        "-技能說明：\n" +
                        "1. 點選敵軍時，將馬的位置和目標敵軍位置互換\n" +
                        "2. 點選友軍時，將友軍踢飛兩格遠(撞到任意目標將停下)\n";
        static final String Spy =
                "間諜\n-死亡條件：周圍3名敵軍時死亡\n" +
                        "-移動距離：4\n" +
                        "-技能範圍：0\n" +
                        "-技能耗點：2\n" +
                        "-技能說明：\n" +
                        "1. 開場可於場上任意位置(除中線、泉、城以外)布陣\n" +
                        "2. 移動時消耗2技能點而非移動點\n";
        static final String runOutChess = "你所能放置的這顆棋子已用完";
        static final String noSelectedChess = "請先選取棋子";
        static final String notAvailableBlock_red = "紅方只能放置棋子於上半部(間諜除外)", notAvailableBlock_blue = "藍方只能放置棋子於下半部(間諜除外)";
        static final String notAvailableBlockOnFountain = "不可放置棋子於泉上";
        static final String notAvailableBlockSpy = "間諜不可放置棋子於中間線、泉、城上";
    }

    public static class PlayChess {
        static TextView messageBlock, chessNameBlock;
        static TextView skillPoint_red, movePoint_red, skillPoint_blue, movePoint_blue;
        static TextView timer;
        static final String TransferTower =
                "傳送塔\n-死亡條件：周圍沒有友軍時死亡\n" +
                        "-移動距離：1\n" +
                        "-技能範圍：8\n" +
                        "-技能耗點：1\n" +
                        "-技能說明：\n" +
                        " 點選一名任意友軍，傳送塔將與其互換位置\n";
        static final String Jet =
                "氣場\n-死亡條件：周圍3名敵軍時死亡\n" +
                        "-移動距離：1\n" +
                        "-技能範圍：1\n" +
                        "-技能耗點：1\n" +
                        "-技能說明：\n" +
                        "將周圍所有人外推一格(連同與被推棋子同一直線且相連的目標)，施放完技能後可額外移動一次\n";
        static final String Rhino =
                "犀牛\n-死亡條件：周圍5名敵軍時死亡\n" +
                        "-移動距離：1\n" +
                        "-技能範圍：1\n" +
                        "-技能耗點：1\n" +
                        "-技能說明：\n" +
                        "點選一名敵軍，犀牛會將其衝撞至底部，並將沿途遇到的棋子一併撞擊到底\n";
        static final String Rock =
                "巨石\n-死亡條件：周圍4名敵軍時死亡\n" +
                        "-移動距離：1\n" +
                        "-技能範圍：0\n" +
                        "-技能耗點：0\n" +
                        "-技能說明：\n" +
                        "無法被任何技能控制或移動\n";
        static final String Clip =
                "夾子\n-死亡條件：周圍4名敵軍時死亡\n" +
                        "-移動距離：1\n" +
                        "-技能範圍：1\n" +
                        "-技能耗點：1\n" +
                        "-技能說明：\n" +
                        "點選一名敵軍，之後將其拉至撞到任意目標為止\n";
        static final String Hercules =
                "力士\n-死亡條件：周圍4名敵軍時死亡\n" +
                        "-移動距離：1\n" +
                        "-技能範圍：1\n" +
                        "-技能耗點：1\n" +
                        "-技能說明：\n" +
                        "點選周圍一名敵軍，並點選周圍一個空格，力士將會把它抓舉至該處\n";
        static final String Horse =
                "馬\n-死亡條件：周圍3名敵軍時死亡\n" +
                        "-移動距離：1\n" +
                        "-技能範圍：1\n" +
                        "-技能耗點：1\n" +
                        "-技能說明：\n" +
                        "1. 點選敵軍時，將馬的位置和目標敵軍位置互換\n" +
                        "2. 點選友軍時，將友軍踢飛兩格遠(撞到任意目標將停下)\n";
        static final String Spy =
                "間諜\n-死亡條件：周圍3名敵軍時死亡\n" +
                        "-移動距離：4\n" +
                        "-技能範圍：0\n" +
                        "-技能耗點：2\n" +
                        "-技能說明：\n" +
                        "1. 開場可於場上任意位置(除中線、泉、城以外)布陣\n" +
                        "2. 移動時消耗2技能點而非移動點\n";
        static final String clickBlock = "請點選一顆格子";
        static final String clickChess = "請點選一顆棋子";
        static final String clickChessAround = "請點選一顆該棋子周圍的棋子";
        static final String clickBlockAround = "請點選一個該棋子周圍的格子";
        static final String notClickRock = "巨石不能被選為技能對象，請選擇另一顆有效棋子";
        static final String notClickSameTeam = "屬於你的棋子不能被選為技能對象，請選擇另一顆有效棋子";
        static final String notClickEnemyTeam = "對手的棋子不能被選為技能對象，請選擇另一顆有效棋子";
        static final String notClickChessOuter = "不屬於周圍的棋子不能被選為技能對象，請選擇另一顆有效棋子";
        static final String notClickBlockOuter = "不屬於周圍的格子不能被選為技能對象，請選擇另一顆有效格子";
        static final String notClickEnemy = "敵對棋子不能被選為技能對象，請選擇另一顆有效棋子";
        static final String notAvailTarget = "選取對象無效，請選擇另一個有效的對象";
        static void chessInfo(Chess chess) {
            if (GameController.clickChess instanceof TransferTower) {
                chessNameBlock.setText("已選取: 傳送塔");
            } else if (GameController.clickChess instanceof Jet) {
                chessNameBlock.setText("已選取: 氣場");
            } else if (GameController.clickChess instanceof Rhino) {
                chessNameBlock.setText("已選取: 犀牛");
            } else if (GameController.clickChess instanceof Rock) {
                chessNameBlock.setText("已選取: 巨石");
            } else if (GameController.clickChess instanceof Clip) {
                chessNameBlock.setText("已選取: 夾子");
            } else if (GameController.clickChess instanceof Hercules) {
                chessNameBlock.setText("已選取: 力士");
            } else if (GameController.clickChess instanceof Horse) {
                chessNameBlock.setText("已選取: 馬");
            } else if (GameController.clickChess instanceof Spy) {
                chessNameBlock.setText("已選取: 間諜");
            } else if(GameController.clickChess == null){
                Text.PlayChess.chessNameBlock.setText("請選擇一顆我方棋子");
            }


            if (chess instanceof TransferTower) {
                messageBlock.setText(TransferTower);
            } else if (chess instanceof Jet) {
                messageBlock.setText(Jet);
            } else if (chess instanceof Rhino) {
                messageBlock.setText(Rhino);
            } else if (chess instanceof Rock) {
                messageBlock.setText(Rock);
            } else if (chess instanceof Clip) {
                messageBlock.setText(Clip);
            } else if (chess instanceof Hercules) {
                messageBlock.setText(Hercules);
            } else if (chess instanceof Horse) {
                messageBlock.setText(Horse);
            } else if (chess instanceof Spy) {
                messageBlock.setText(Spy);
            }
        }
    }
}
