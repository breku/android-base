package com.brekol.manager;

import com.brekol.model.scene.BaseScene;
import com.brekol.model.scene.LoadingScene;
import com.brekol.model.scene.MainMenuScene;
import com.brekol.model.scene.SplashScene;
import com.brekol.util.SceneType;
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

    private void disposeSplashScene() {
        ResourcesManager.getInstance().unloadSplashScreen();
        splashScene.disposeScene();
        splashScene = null;
    }


    public BaseScene getCurrentScene() {
        return currentScene;
    }

}
