package com.brekol.model.scene;

import com.brekol.manager.SceneManager;
import com.brekol.util.ConstantsUtil;
import com.brekol.util.SceneType;
import org.andengine.entity.sprite.Sprite;

/**
 * User: Breku
 * Date: 21.09.13
 */
public class AboutScene extends BaseScene {

    @Override
    public void createScene() {
        createBackground();
    }

    private void createBackground() {
        attachChild(new Sprite(ConstantsUtil.SCREEN_WIDTH / 2, ConstantsUtil.SCREEN_HEIGHT / 2, resourcesManager.getAboutBackgroundtTextureRegion(), vertexBufferObjectManager));
        attachChild(new Sprite(ConstantsUtil.SCREEN_WIDTH / 2, ConstantsUtil.SCREEN_HEIGHT / 2, resourcesManager.getAboutTextureRegion(), vertexBufferObjectManager));
    }

    @Override
    public void onBackKeyPressed() {
        SceneManager.getInstance().loadMenuSceneFrom(SceneType.ABOUT);
    }

    @Override
    public SceneType getSceneType() {
        return SceneType.ABOUT;
    }

    @Override
    public void disposeScene() {
    }
}
