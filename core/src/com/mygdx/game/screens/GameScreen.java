package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.AI.Bot;
import com.mygdx.game.MyGame;
import com.mygdx.game.controllers.DeathMenuInputProcessor;
import com.mygdx.game.controllers.PlayerInputProcessor;
import com.mygdx.game.models.blocks.BlockManager;
import com.mygdx.game.models.hud.Hud;
import com.mygdx.game.models.player.Fluffy;
import com.mygdx.game.models.player.MindCloud;
import com.mygdx.game.renderers.DeathMenuRenderer;
import com.mygdx.game.renderers.HudRenderer;
import com.mygdx.game.renderers.ScenRenderer;
import com.mygdx.game.resources.RDim;

public class GameScreen implements Screen {
    public MyGame game;
    private OrthographicCamera camera;
    private ScenRenderer scenRenderer;
    private PlayerInputProcessor playerInputProcessor;
    private HudRenderer hudRenderer;
    private DeathMenuRenderer deathMenuRenderer;
    private Fluffy fluffy;
    private Hud hud;
    private BlockManager blockManager;
    private MindCloud mindCloud;
    public int deadLine;
    public int nextLevelLine;
    private ShapeRenderer shapeRenderer;
    private Bot bot;
    public GameScreen(MyGame game) {
        this.game = game;
        camera = new OrthographicCamera(RDim.DEVICE_WIDTH, RDim.DEVICE_HEIGHT);
        setCamera(RDim.DEVICE_WIDTH / 2f, RDim.DEVICE_HEIGHT / 2f);
        fluffy = new Fluffy(new Vector2().set(RDim.DEVICE_WIDTH / 2 - RDim.BLOCK_WIDTH / 2, 64));
        mindCloud = new MindCloud(fluffy);
        hud = new Hud(this);
        blockManager = new BlockManager(this);
        scenRenderer = new ScenRenderer(game, camera, this);
        hudRenderer = new HudRenderer(game, this);
        deathMenuRenderer = new DeathMenuRenderer(game);
        deathMenuRenderer.isGameOver = false;
        deadLine = (int) RDim.DEVICE_HEIGHT - 100;
        deadLine = 150;
        nextLevelLine = 0;
        shapeRenderer = new ShapeRenderer();

        bot = new Bot(game, this);
        bot.startGame();
    }

    public void incDeadLine(int y) {
        deadLine += y;
    }

    public ScenRenderer getScenRenderer() {
        return scenRenderer;
    }

    public void setCamera(float x, float y) {
        this.camera.position.set(x, y, 0);
        this.camera.update();
    }

    public void endGame() {
        if (hud.getPlayerHealthBlocks() <= 0) {
            game.setScreen(new MainMenuScreen(game));
            bot.stopGame();
        }
    }

    public void onDeadLine() {
        if (deadLine < 350) deadLine+=1;
        if (blockManager.getHigherBlockY() + RDim.BLOCK_HEIGHT >= deadLine && blockManager.isWallInited()
                && !deathMenuRenderer.isGameOver) {
            deadLine += 100;
            deathMenuRenderer.isGameOver = true;
            Gdx.input.setInputProcessor(new DeathMenuInputProcessor(deathMenuRenderer, game));
            System.out.println("on dead line");
        }
    }

    public void onNextLevelLine() {
        if (nextLevelLine < 180)  nextLevelLine+=1;
        if (blockManager.getHigherBlockY() + RDim.BLOCK_HEIGHT <= nextLevelLine &&
                blockManager.isWallInited()) {
            System.out.println("next level");
            hud.nextLevel();
            this.dispose();
            game.setScreen(new GameScreen(game));
        }

    }

    public Bot getBot(){
        if (bot == null) bot = new Bot(game, game.gameScreen);
        return bot;
    }

    public Fluffy getFluffy() {
        return fluffy;
    }

    public Hud getHud() {
        return hud;
    }

    public MindCloud getMindCloud() {
        return mindCloud;
    }

    public BlockManager getBlockManager() {
        return blockManager;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        scenRenderer.render();
        hudRenderer.render();
        //shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        //shapeRenderer.line(0, deadLine, RDim.DEVICE_WIDTH, deadLine);
        //shapeRenderer.line(0, nextLevelLine, RDim.DEVICE_WIDTH, nextLevelLine);
        bot.playGame();
        //shapeRenderer.end();
        //onDeadLine();
        //onNextLevelLine();
        //deathMenuRenderer.render();
        //endGame();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        scenRenderer.dispose();
        hudRenderer.dispose();
    }
}
