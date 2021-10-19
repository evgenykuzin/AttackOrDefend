package com.mygdx.game.models.blocks;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.models.blocks.Block.TYPE;
import com.mygdx.game.models.hud.Hud;
import com.mygdx.game.models.player.Fluffy;
import com.mygdx.game.resources.RConst;
import com.mygdx.game.resources.RDim;
import com.mygdx.game.screens.GameScreen;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.Stack;

public class BlockManager {
    private GameScreen gs;
    public ArrayList<Block> blockList;
    private Fluffy fluffy;
    private Hud hud;
    private ArrayList<Block> deadList;
    private ArrayList<Set<Block>> deadFamilyList;
    private Stack<Block> towerStack;
    private Block topBlock;
    private float towerSpeed;
    private boolean firstWallInited;
    private boolean secondWallInited;
    //private boolean wallInited;
    private ArrayList<Set<Block>> families;
    float wallX;
    float wallY;
    int wallCounter;

    public BlockManager(GameScreen gs) {
        this.gs = gs;
        blockList = new ArrayList<Block>();
        deadList = new ArrayList<Block>();
        deadFamilyList = new ArrayList<Set<Block>>();
        towerStack = new Stack<Block>();
        towerSpeed = 10;
        wallX = 0;
        wallY = 1;
        wallCounter = 0;
        towerSpeed = RDim.BLOCK_WIDTH + 2;
        fluffy = gs.getFluffy();
        hud = gs.getHud();
        topBlock = null;
        firstWallInited = false;
        secondWallInited = false;
        families = new ArrayList<Set<Block>>();
        initRims();
    }

    public boolean isWallInited() {
        return firstWallInited && secondWallInited;
    }

    public boolean isFirstWallInited() {
        return firstWallInited;
    }

    public boolean isSecondWallInited() {
        return secondWallInited;
    }

    public Block getTopBlock() {
        return topBlock;
    }

    public void drawBlocks(SpriteBatch batch) {
        if (!blockList.isEmpty()) {
            for (Block block : blockList) {
                block.drawBlock(batch);
            }
        }
    }

    public void allFalls() {
        ArrayList<Block> deadList = new ArrayList<Block>();
        if (!blockList.isEmpty()) {
            for (Block block : blockList) {
                if (block.downDir()) {
                    if (block.getPosition().y <= RDim.FLORE_Y) {
                        block.stop();
                        block.getPosition().y = RDim.FLORE_Y;
                    } else block.fall();
                } else if (block.upDir()) {
                    if (block.getPosition().y >= RDim.ROOF_Y) {
                        block.stop();
                        block.getPosition().y = RDim.ROOF_Y;
                    } else block.fall();
                }

            }
        }

        if (topBlock != null) {
            topBlock.fall();
            if (topBlock.downDir()) {
                if (topBlock.getPosition().y <= RDim.FLORE_Y) {
                    topBlock.stop();
                    topBlock.getPosition().y = RDim.FLORE_Y;
                    blockList.add(topBlock); // get new

                }
            } else if (topBlock.upDir()) {
                if (topBlock.getPosition().y >= RDim.ROOF_Y) {
                    topBlock.stop();
                    topBlock.getPosition().y = RDim.ROOF_Y;
                    blockList.add(topBlock); // get new
                }
            }
        }
//            blockList.removeAll(deadList);
//            deadList.clear();
    }


    public void drawTopBlock(SpriteBatch batch) {
        if (topBlock != null) {
            topBlock.drawBlock(batch);
        }
    }

    public Block initBlock(int team, int direction, float x) {
        Random random = new Random();
        float ry;
        if (team == RConst.PLAYER_TEAM) {
            ry = RDim.DEVICE_HEIGHT - RDim.DEVICE_HEIGHT / 3;
        } else {
            ry = RDim.BLOCK_HEIGHT + RDim.DEVICE_HEIGHT / 3;
        }
        x = x - x % (RDim.BLOCK_WIDTH + RDim.B_SPACE);
        Block block;
        Vector2 vector = new Vector2(x, ry);
        if (random.nextInt(2) == 1) {
            // block = new BombBlock(vector, dirrection, team);
            block = new FireBoll(vector, direction, team);
        } else {
            //block = new CasualBlock(vector, dirrection, team);
            block = new Shield(vector, direction, team);
        }
        topBlock = block;
        //blockList.add(block);
        return block;
    }

    public Block initBotBlock(int team, int direction, float x) {
        Random random = new Random();
        float ry;
        if (team == RConst.PLAYER_TEAM) {
            ry = RDim.DEVICE_HEIGHT - RDim.DEVICE_HEIGHT / 3;
        } else {
            ry = RDim.BLOCK_HEIGHT + RDim.DEVICE_HEIGHT / 3;
        }
        x = x - x % (RDim.BLOCK_WIDTH + RDim.B_SPACE);
        Block block;
        Vector2 vector = new Vector2(x, ry);
        if (random.nextInt(2) == 1) {
            //block = new BLock(vector);
            block = new FireBoll(vector, direction, team);
        } else {
            block = new Shield(vector, direction, team);
        }
        //blockList.add(block);
        return block;
    }

    public void initRims() {
        for (int i = 0; i < 19; i++) {
            Block floreB = new RimBlock(new Vector2(i * RDim.BLOCK_WIDTH, RDim.FLORE_Y), RConst.DOWN_DIR, RConst.PLAYER_TEAM);
            blockList.add(floreB);
        }
        for (int i = 0; i < 19; i++) {
            Block roofB = new RimBlock(new Vector2(i * RDim.BLOCK_WIDTH, RDim.ROOF_Y), RConst.UP_DIR, RConst.ROBOT_TEAM);
            blockList.add(roofB);
        }
    }

    public void initWall(int team, int direction) {
        wallCounter++;
        Random random = new Random();
        float rx = random.nextInt(10) * (RDim.BLOCK_WIDTH / 2 + 2) + RDim.BLOCK_WIDTH * 3;
        rx = random.nextInt(20) * (RDim.BLOCK_WIDTH + 2);
        rx = wallX * (RDim.BLOCK_WIDTH + 2);
        float ry;
        if (direction == 1) {
            ry = wallY * (RDim.DEVICE_HEIGHT + RDim.BLOCK_HEIGHT);
            ry = RDim.DEVICE_HEIGHT / 2 - RDim.BLOCK_HEIGHT - RDim.B_SPACE;
        } else {
            ry = 1;
            ry = RDim.DEVICE_HEIGHT / 2 + RDim.BLOCK_HEIGHT + RDim.B_SPACE;
        }
        if (wallX == RDim.maxBlockLength) {
            wallX = 0;
        } else {
            wallX += 1;
        }
        //dy = dy == RDim.BLOCK_HEIGHT/10 ? RDim.BLOCK_HEIGHT + RDim.BLOCK_HEIGHT/10 : RDim.BLOCK_HEIGHT/10;
        Block b = new Shield(new Vector2(rx, ry), direction, team);
        b.go(5, 0.5f);
        blockList.add(b);
        if (wallCounter >= 180) {
            wallCounter = 0;
            System.out.println("wall inited");
            if (!secondWallInited && firstWallInited && team == RConst.PLAYER_TEAM) {
                secondWallInited = true;
            }
            if (!firstWallInited && !secondWallInited && team == RConst.ROBOT_TEAM) {
                firstWallInited = true;
            }
        }

    }


    public void destroyBlock(Block b) {
        //blockList.remove(b);
        deadList.add(b);

    }

    public void destroyBlocks(ArrayList<Block> blocks) {
        deadList.addAll(blocks);
    }

    public void sendToHell() {
        int i = 0;
        for (Block block : deadList) {
            System.out.println(i++);
            if (block.getTeam() == RConst.PLAYER_TEAM) {
                hud.missPlayerHealthBlocks(1);
            } else {
                hud.missBotHealthBlocks(1);
            }
//            Set<Block> family = getFamilyOf(block);
//            if (family != null) {
//                family.remove(block);
//            }
        }
        blockList.removeAll(deadList);
        deadList = new ArrayList<Block>();
    }

    public ArrayList<Block> getPlayerWall() {
        ArrayList<Block> list = new ArrayList<Block>();
        for (Block block : blockList) {
            if (block.getTeam() == RConst.PLAYER_TEAM) {
                list.add(block);
            }
        }
        return list;
    }

    public ArrayList<Block> getBotWall() {
        ArrayList<Block> list = new ArrayList<Block>();
        for (Block block : blockList) {
            if (block.getTeam() == RConst.ROBOT_TEAM) {
                list.add(block);
            }
        }

        return list;
    }

    public static boolean onUp(Block b1, Block b2, int length) {
        return b2.getPosition().y >= b1.getPosition().y + b1.getHeight() / 1.5
                && b2.getPosition().y <= b1.getPosition().y + b1.getHeight()
                && b2.getPosition().x <= b1.getPosition().x + b1.getWidth()
                && b2.getPosition().x + b2.getWidth() >= b1.getPosition().x;
    }

    public static boolean onDown(Block b1, Block b2, int length) {
        return b1.getPosition().y >= b2.getPosition().y - b2.getHeight()
                && b1.getPosition().y <= b2.getPosition().y + b2.getHeight()
                && b1.getPosition().x <= b2.getPosition().x + b2.getWidth()
                && b1.getPosition().x + b1.getWidth() >= b2.getPosition().x;
    }

    public static boolean onLeft(Block b1, Block b2, int length) {
        return b1.getPosition().x == b2.getPosition().x + b2.getWidth() * length + RDim.B_SPACE
                && b1.getPosition().y == b2.getPosition().y;
    }

    public static boolean onRight(Block b1, Block b2, int length) {
        return b1.getPosition().x + b1.getWidth() * length + RDim.B_SPACE == b2.getPosition().x
                && b1.getPosition().y == b2.getPosition().y;
    }

    public void gravitation() {
        for (Block b1 : blockList) {
            boolean mustFall = true;
            for (Block b2 : blockList) {
                if (onUp(b1, b2, 1) && b1.upDir() || onDown(b1, b2, 1) && b1.downDir()) {
                    mustFall = false;
                    break;
                }
            }
            if (mustFall) {
                b1.go(5, 0.1f);
            }
        }
    }

    private void overlaps(Block b1, Block b2) {
        boolean onDown = b1.getPosition().y >= b2.getPosition().y + b2.getHeight() / 1.5
                && b1.getPosition().y <= b2.getPosition().y + b2.getHeight()
                && b1.getPosition().x <= b2.getPosition().x + b2.getWidth()
                && b1.getPosition().x + b1.getWidth() >= b2.getPosition().x;
        boolean onLeft = b1.getPosition().x == b2.getPosition().x + b2.getWidth() + RDim.B_SPACE
                && b1.getPosition().y == b2.getPosition().y;
        boolean onRight = b1.getPosition().x + b1.getWidth() + RDim.B_SPACE == b2.getPosition().x
                && b1.getPosition().y == b2.getPosition().y;
        boolean onUp = b2.getPosition().y >= b1.getPosition().y + b1.getHeight() / 1.5
                && b2.getPosition().y <= b1.getPosition().y + b1.getHeight()
                && b2.getPosition().x <= b1.getPosition().x + b1.getWidth()
                && b2.getPosition().x + b2.getWidth() >= b1.getPosition().x;
        if ((onDown || onLeft || onRight || onUp) && !b2.isCanFall()) {
            if (onUp && b1.upDir() || onDown && b1.downDir()) {
                if (b1.equals(topBlock)) {   //  || b1.isTopBlock
                    if (b1.equals(topBlock)) {
                        b1 = topBlock; // get new
                        topBlock = null;
                    }
                    //b1.isTopBlock = false;
                    blockList.add(b1);
                    //hud.missHealth(-1);
                    if (b1.downDir() && !onUp) {
                        b1.getPosition().y = b2.getPosition().y + b2.getHeight();
                    } else if (b1.upDir() && !onDown) {
                        b1.getPosition().y = b2.getPosition().y - b1.getHeight();
                    }
                } else {
                    if (b1.downDir() && !onUp) {
                        b1.getPosition().y = b2.getPosition().y + b2.getHeight();
                    } else if (b1.upDir() && !onDown) {
                        b1.getPosition().y = b2.getPosition().y - b1.getHeight();
                    }
                }
                if (b1.isCanFall()) {
                    if (b1.getTeam() == RConst.PLAYER_TEAM) {
                        hud.incPlayerHealthBlocks(1);
                    } else {
                        hud.incBotHealthBlocks(1);
                    }
                }
                b1.stop();
                b1.setSpeed(0);
                b1.setAcceleration(0);

            }
            b1.specialBlockAction(this, b2);
        }

    }

    public void overlapsBlocks() {
        //hud.setPlayerHealthBlocks(getPlayerWall().size());
        //hud.setBotHealthBlocks(getBotWall().size());
        for (int i = 0; i < blockList.size(); i++) {
            Block b1 = blockList.get(i);
            if (topBlock != null) {
                //if (b1.getTeam() != topBlock.getTeam()) {
                    overlaps(topBlock, b1);
               // } else {
                    //overlaps(topBlock, b1);
               // }
            }
            for (int j = 0; j < blockList.size(); j++) {
                //if (blockList.size() <= i) break;
                Block b2 = blockList.get(j);
                overlaps(b1, b2);
            }
        }
    }

    public float getHigherBlockY() {
        float higher = RDim.FLORE_Y + RDim.BLOCK_HEIGHT;
        for (Block block : blockList) {
            if (block.getPosition().y > higher && block != topBlock
                    && !block.isCanFall() && block.getSpeed() == 0 && block.getTeam() == RConst.PLAYER_TEAM)
                higher = block.getPosition().y;
        }
        return higher;
    }

    public void moveLeft() {
//        for (BLock block : towerStack) {
//            topBlock.moveLeft(towerSpeed);
//        }
        topBlock.moveLeft(towerSpeed);
    }

    public void moveRight() {
//        for (BLock block : towerStack) {
//            topBlock.moveRight(towerSpeed);
//        }
        topBlock.moveRight(towerSpeed);
    }

    public static class Neighbors {
        Block mainBlock;
        private int radius;
        private ArrayList<Block> blocks;
        private ArrayList<Block> neighbors;
        public ArrayList<Block> up;
        public ArrayList<Block> down;
        public ArrayList<Block> left;
        public ArrayList<Block> right;
        public ArrayList<Block> upLeft;
        public ArrayList<Block> upRight;
        public ArrayList<Block> downLeft;
        public ArrayList<Block> downRight;

        public Neighbors(Block mainBlock, ArrayList<Block> blocks, int radius) {
            this.mainBlock = mainBlock;
            this.blocks = blocks;
            this.radius = radius;
        }

        public ArrayList<Block> getNeighbors() {
            return neighbors;
        }

        public void findNeighbors(int countOfLayers) {
            //ArrayList<Block> neighbors = new ArrayList<Block>();
            int rad = radius;
            neighbors = new ArrayList<Block>();
            for (int i = 1; i <= countOfLayers; i++) {
                for (Block b2 : blocks) {
                    if (!mainBlock.equals(b2) && !neighbors.contains(b2)) {
                        boolean onUp = onUp(mainBlock, b2, rad);
                        boolean onDown = onDown(mainBlock, b2, rad);
                        boolean onLeft = onLeft(mainBlock, b2, rad);
                        boolean onRight = onRight(mainBlock, b2, rad);
                        boolean onUpLeft = b2.getPosition().y >= mainBlock.getPosition().y + mainBlock.getHeight() * rad - mainBlock.getHeight() / 1.5
                                && b2.getPosition().y <= mainBlock.getPosition().y + mainBlock.getHeight() * rad
                                && mainBlock.getPosition().x == b2.getPosition().x + b2.getWidth() + 2;
                        boolean onUpRight = b2.getPosition().y >= mainBlock.getPosition().y + mainBlock.getHeight() * rad - mainBlock.getHeight() / 1.5
                                && b2.getPosition().y <= mainBlock.getPosition().y + mainBlock.getHeight() * rad
                                && mainBlock.getPosition().x + mainBlock.getWidth() + 2 == b2.getPosition().x;
                        boolean onDownLeft = mainBlock.getPosition().y >= b2.getPosition().y + b2.getHeight() * rad - b2.getHeight() / 1.5
                                && mainBlock.getPosition().y <= b2.getPosition().y + b2.getHeight() * rad
                                && mainBlock.getPosition().x == b2.getPosition().x + b2.getWidth() + 2;
                        boolean onDownRight = mainBlock.getPosition().y >= b2.getPosition().y + b2.getHeight() * rad - b2.getHeight() / 1.5
                                && mainBlock.getPosition().y <= b2.getPosition().y + b2.getHeight()
                                && mainBlock.getPosition().x + mainBlock.getWidth() + 2 == b2.getPosition().x;
                        if (onUp || onDown || onLeft || onRight || onUpLeft || onUpRight || onDownLeft || onDownRight) {
                            if (onUp) {
                                if (up == null) up = new ArrayList<Block>();
                                up.add(b2);
                            }
                            if (onDown) {
                                if (down == null) down = new ArrayList<Block>();
                                down.add(b2);
                            }
                            if (onLeft) {
                                if (left == null) left = new ArrayList<Block>();
                                left.add(b2);
                            }
                            if (onRight) {
                                if (right == null) right = new ArrayList<Block>();
                                right.add(b2);
                            }
                            if (onUpLeft) {
                                if (upLeft == null) upLeft = new ArrayList<Block>();
                                upLeft.add(b2);
                            }
                            if (onUpRight) {
                                if (upRight == null) upRight = new ArrayList<Block>();
                                upRight.add(b2);
                            }
                            if (onDownLeft) {
                                if (downLeft == null) downLeft = new ArrayList<Block>();
                                downLeft.add(b2);
                            }
                            if (onDownRight) {
                                if (downRight == null) downRight = new ArrayList<Block>();
                                downRight.add(b2);
                            }
                            neighbors.add(b2);
                        }
                    }
                }
                rad--;
                if (rad < 0)
                    throw new IllegalArgumentException("'count of layers' must be less or equals radius");
            }
            //return neighbors;
        }
    }


}
