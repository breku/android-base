package com.brekol.manager;

import com.brekol.model.scene.*;
import com.brekol.util.ConstantsUtil;
import com.brekol.util.SceneType;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.ui.IGameInterface;

/**
 * User: Breku
 * Date: 21.09.13
 */
public class SceneManager {

    private static final SceneManager INSTANCE = new SceneManager();

    private SceneType currentSceneType = SceneType.SPLASH;
    private BaseScene gameScene, menuScene, loadingScene, splashScene, currentScene, aboutScene, optionsScene;

    public static SceneManager getInstance() {
        return INSTANCE;
    }

    public void setScene(BaseScene scene) {
        ResourcesManager.getInstance().getEngine().setScene(scene);
        currentScene = scene;
        currentSceneType = scene.getSceneType();
    }


    public void setScene(SceneType sceneType) {
        switch (sceneType) {
            case MENU:
                setScene(menuScene);
                break;
            case GAME:
                setScene(gameScene);
                break;
            case SPLASH:
                setScene(splashScene);
                break;
            case LOADING:
                setScene(loadingScene);
                break;
            case ABOUT:
                setScene(aboutScene);
                break;
            case OPTIONS:
                setScene(optionsScene);
                break;
            default:
                break;
        }
    }

    public void createSplashScene(IGameInterface.OnCreateSceneCallback onCreateSceneCallback) {
        ResourcesManager.getInstance().loadSplashScreen();
        splashScene = new SplashScene();
        currentScene = splashScene;
        onCreateSceneCallback.onCreateSceneFinished(splashScene);
    }


    public void createMainMenuScene() {
        ResourcesManager.getInstance().loadMainMenuResources();
        menuScene = new MainMenuScene();
        loadingScene = new LoadingScene();
        setScene(menuScene);
        disposeSplashScene();
    }

    public void loadMenuScene() {
        setScene(loadingScene);
        gameScene.disposeScene();
        ResourcesManager.getInstance().unloadGameTextures();
        ResourcesManager.getInstance().getEngine().registerUpdateHandler(new TimerHandler(ConstantsUtil.LOADING_SCENE_TIME, new ITimerCallback() {
            @Override
            public void onTimePassed(TimerHandler pTimerHandler) {
                ResourcesManager.getInstance().getEngine().unregisterUpdateHandler(pTimerHandler);
                ResourcesManager.getInstance().loadMenuTextures();
                setScene(menuScene);
            }
        }));
    }

    public void loadGameScene() {
        setScene(loadingScene);
        ResourcesManager.getInstance().unloadMenuTextures();
        ResourcesManager.getInstance().getEngine().registerUpdateHandler(new TimerHandler(ConstantsUtil.LOADING_SCENE_TIME, new ITimerCallback() {
            @Override
            public void onTimePassed(TimerHandler pTimerHandler) {
                ResourcesManager.getInstance().getEngine().unregisterUpdateHandler(pTimerHandler);
                ResourcesManager.getInstance().loadGameResources();
                gameScene = new GameScene();
                setScene(gameScene);
            }
        }));
    }

    private void disposeSplashScene() {
        ResourcesManager.getInstance().unloadSplashScreen();
        splashScene.disposeScene();
        splashScene = null;
    }


    public BaseScene getCurrentScene() {
        return currentScene;
    }

}
