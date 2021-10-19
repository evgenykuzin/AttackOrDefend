package com.mygdx.game.AI;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.MyGame;
import com.mygdx.game.controllers.PlayerInputProcessor;
import com.mygdx.game.models.blocks.Block;
import com.mygdx.game.models.blocks.BlockManager;
import com.mygdx.game.models.blocks.TestBlock;
import com.mygdx.game.models.hud.Hud;
import com.mygdx.game.renderers.ScenRenderer;
import com.mygdx.game.resources.RConst;
import com.mygdx.game.screens.GameScreen;

import java.util.ArrayList;
import java.util.Random;

public class Bot {
    private boolean pause = true;
    private MyGame game;
    private GameScreen gameScreen;
    private ScenRenderer scenRenderer;
    private PlayerInputProcessor playerInputProcessor;
    private BlockManager blockManager;
    private Hud hud;
    private Random random;
    private Block topBlock;
    private float blockSpeed = 1;
    private float blockAcc = 0.5f;

    public Bot(MyGame game, GameScreen gs) {
        this.game = game;
        gameScreen = game.gameScreen;
        gameScreen = gs;
        scenRenderer = gameScreen.getScenRenderer();
        playerInputProcessor = scenRenderer.playerInputProcessor;
        blockManager = gameScreen.getBlockManager();
        hud = gameScreen.getHud();
        random = new Random();
    }


    private long sec = 0;
    private int t = 0;

    // time 0 = infinity
    private void run(int times) {
        if (t < times || times == 0) {

            sec++;
            if (sec >= 100) {
                throwBlock();
                sec = 0;
                t++;

            }
        }
    }

    public void playGame() {
        //topBlock.fall();
        run(0);
//        if (!pause && !playerInputProcessor.isPlayerTurn()){
//            Block playerBlock = blockManager.getTopBlock();
//            boolean c1 = false;
//            if (playerBlock == null) c1 = true;
//            else if (!playerBlock.isCanFall()) c1 = true;
//            boolean c2 = false;
//            if (topBlock == null) c2 = true;
//            else if (!topBlock.isCanFall()) c2 = true;
//
//                if (c1 && c2) {
//                    System.out.println("throw");
//                    throwBlock();
//                    playerInputProcessor.setTurn(true);
//                }
//
//        }
    }

    public void startGame() {
        pause = false;
    }

    public void stopGame() {
        pause = true;
    }

    private ArrayList<Block> getTopLayer() {
        ArrayList<Block> layer = new ArrayList<Block>();
        for (Block block : blockManager.blockList) {
            if (block.getTeam() != RConst.ROBOT_TEAM && block.getY() == blockManager.getHigherBlockY()) {
                layer.add(block);
            }
        }
        return layer;
    }

    public static ArrayList<ArrayList<Block>> splitByLayers(ArrayList<Block> blocks) {
        ArrayList<ArrayList<Block>> layers = new ArrayList<ArrayList<Block>>();
        ArrayList<Block> lastLayer = new ArrayList<Block>();
//        for (int i = 0; i < blockList.size(); i++) {
        int counter = 0;
        while (true) {
            ArrayList<Block> layer = new ArrayList<Block>();
            for (int i = 0; i < blocks.size(); i++) {
                Block b1 = blocks.get(i);
                boolean canAdd = false;
                for (int j = 0; j < blocks.size(); j++) {
                    Block b2 = blocks.get(j);
                    if (b1.getPosition().x == b2.getPosition().x && b1.getPosition().y == 0 && b2.getPosition().y == 34)
                        System.out.println(b1.getPosition().x + " " + b2.getPosition().x + " " + b1.getPosition().y + " " + b2.getPosition().y);
                    if (b1.equals(b2)) break;
                    boolean onUp = BlockManager.onUp(b1, b2, 1);
                    onUp = b1.getPosition().x == b2.getPosition().x &&
                            b1.getPosition().y == b2.getPosition().y - b1.getHeight();
                    boolean onDown = BlockManager.onDown(b1, b2, 1);
                    canAdd = !onUp && lastLayer.isEmpty() || onUp && lastLayer.contains(b2);
                    canAdd = !onUp;
                    if (!canAdd) {
                        break;
                    }
                }
                if (canAdd) {
                    layer.add(b1);
                }
            }
            if (!layer.isEmpty()) {
                counter += layer.size();
                layers.add(layer);
                lastLayer.addAll(layer);
                //lastLayer = new ArrayList<Block>(layer);
            }
            if (counter >= layer.size()) break;
        }

//        StringBuilder sb = new StringBuilder();
//        for (ArrayList<Block> layer : layers){
//            for (Block block : layer){
//                sb.append("|").append(block.getDigit()).append("|");
//            }
//            sb.append("\n");
//        }
//        System.out.println(sb);
        return layers;
    }

    public ArrayList<Block> getFirstLayer(ArrayList<Block> blocks) {
        ArrayList<Block> layer = new ArrayList<Block>();
        for (int i = 0; i < blocks.size(); i++) {
            Block b1 = blocks.get(i);
            boolean canAdd = true;
            for (int j = 0; j < blocks.size(); j++) {

                Block b2 = blocks.get(j);
                if (b1.getPosition().x == b2.getPosition().x && b1.getPosition().y == b2.getPosition().y - b1.getHeight()) {
                    canAdd = false;
                    break;
                }
            }
            if (canAdd) {
                layer.add(b1);
            }
        }
        return layer;
    }

    public static void main(String[] args) {
        ArrayList<Block> blocks = new ArrayList<Block>();
        int x = 0;
        int y = 0;
        for (int i = 0; i < 20; i++) {
           // blocks.add(new TestBlock(new Vector2(x, y), RConst.DOWN_DIR, RConst.PLAYER_TEAM));
            if (i == 10) {
                y += 32;
            } else x += 32;
        }

        splitByLayers(blocks);
    }

//    private float chooseX() {
//        float x = 0.1f;
//        //ArrayList<Block> firstLayer = splitByLayers(blockManager.getPlayerWall()).get(0);
//        ArrayList<Block> firstLayer = getFirstLayer(blockManager.getPlayerWall());
//        do {
//            for (Block block : firstLayer) {
//                if (chance(10)) {
//                    x = block.getX();
//                    break;
//                }
//            }
//        } while (x == 0.1f);
//        if (topBlock.blockType != Block.TYPE.SPECIAL) {
//            for (Block block : firstLayer) {
//                //block.setDigit(block.getTeam());
//                if (topBlock.blockType.equals(block.blockType)) {
//                    x = block.getX();
//                    BlockManager.Neighbors neighbors = new BlockManager.Neighbors(block, blockManager.getPlayerWall(), 1);
//                    neighbors.findNeighbors(1);
//                    boolean canPutLeft = neighbors.downLeft != null && neighbors.left == null;
//                    boolean canPutRight = neighbors.downRight != null && neighbors.right == null;
//                    if (canPutLeft && canPutRight) {
//                        if (chance(50)) {
//                            System.out.println(neighbors.downLeft.toString());
//                            System.out.println("put left");
//                            x = neighbors.downLeft.get(0).getX();
//                        } else {
//                            System.out.println(neighbors.downRight.toString());
//                            System.out.println("put right");
//                            x = neighbors.downRight.get(0).getX();
//                        }
//
//                    } else if (canPutLeft) {
//                        System.out.println(neighbors.downLeft.toString());
//                        System.out.println("put left");
//                        x = neighbors.downLeft.get(0).getX();
//                    } else if (canPutRight) {
//                        System.out.println(neighbors.downRight.toString());
//                        System.out.println("put right");
//                        x = neighbors.downRight.get(0).getX();
//                    }
//
//                    break;
//                }
//            }
//        }
//        return x;
//    }

    private void throwBlock() {
        topBlock = blockManager.initBotBlock(RConst.PLAYER_TEAM, RConst.DOWN_DIR, 0);
        topBlock.stop();
        blockManager.blockList.add(topBlock);
        //hud.incPlayerHealthBlocks(1);
        //float x = chooseX();
        float x = 0;
        topBlock.setX(x);
        topBlock.go(blockSpeed, blockAcc);
    }

    private static boolean chance(int percent) {
        Random random = new Random();
        int r = 100 / percent;
        return random.nextInt(r) == 0;
    }

    public Block getRobotTopBlock() {
        return topBlock;
    }

    public void drawRobotTopBlock(SpriteBatch batch) {
        if (topBlock != null) topBlock.drawBlock(batch);
    }
}
