package com.brekol.model.scene;

import com.brekol.manager.ResourcesManager;
import com.brekol.util.ConstantsUtil;
import com.brekol.util.SceneType;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.scene.menu.item.decorator.ScaleMenuItemDecorator;
import org.andengine.entity.sprite.Sprite;

/**
 * User: Breku
 * Date: 21.09.13
 */
public class MainMenuScene extends BaseScene implements MenuScene.IOnMenuItemClickListener{
    private MenuScene menuScene;
    private final int NEW_GAME = 0;
    private final int OPTIONS = 1;
    private final int ABOUT = 2;
    private final int EXIT = 3;


    @Override
    public void createScene() {
        createBackground();
        createButtons();
    }

    private void createBackground() {
        attachChild(new Sprite(ConstantsUtil.SCREEN_WIDTH / 2, ConstantsUtil.SCREEN_HEIGHT / 2, resourcesManager.getDarkBackgroundTextureRegion(), vertexBufferObjectManager));
    }

    private void createButtons() {
        menuScene = new MenuScene(camera);
        menuScene.setPosition(0, 0);

        final IMenuItem newGameItem = new ScaleMenuItemDecorator(new SpriteMenuItem(NEW_GAME, ResourcesManager.getInstance().getButtonNewGameTextureRegion(), vertexBufferObjectManager), 1.2f, 1);
        final IMenuItem aboutItem = new ScaleMenuItemDecorator(new SpriteMenuItem(ABOUT, ResourcesManager.getInstance().getButtonAboutTextureRegion(), vertexBufferObjectManager), 1.2f, 1);
        final IMenuItem optionsItem = new ScaleMenuItemDecorator(new SpriteMenuItem(OPTIONS, ResourcesManager.getInstance().getButtonOptionsTextureRegion(), vertexBufferObjectManager), 1.2f, 1);
        final IMenuItem exitItem = new ScaleMenuItemDecorator(new SpriteMenuItem(EXIT, ResourcesManager.getInstance().getButtonExitTextureRegion(), vertexBufferObjectManager), 1.2f, 1);

        menuScene.addMenuItem(newGameItem);
        menuScene.addMenuItem(optionsItem);
        menuScene.addMenuItem(aboutItem);
        menuScene.addMenuItem(exitItem);

        menuScene.buildAnimations();
        menuScene.setBackgroundEnabled(false);

        aboutItem.setPosition(ConstantsUtil.SCREEN_WIDTH / 8, ConstantsUtil.SCREEN_HEIGHT / 3);
        newGameItem.setPosition(ConstantsUtil.SCREEN_WIDTH / 3, ConstantsUtil.SCREEN_HEIGHT * 3 / 4);
        optionsItem.setPosition(ConstantsUtil.SCREEN_WIDTH * 2 / 3, ConstantsUtil.SCREEN_HEIGHT * 3 / 4);
        exitItem.setPosition(ConstantsUtil.SCREEN_WIDTH * 7 / 8, ConstantsUtil.SCREEN_HEIGHT / 3);

        menuScene.setOnMenuItemClickListener(this);

        setChildScene(menuScene);

    }

    @Override
    public void onBackKeyPressed() {
        System.exit(0);
    }

    @Override
    public SceneType getSceneType() {
        return SceneType.MENU;
    }

    @Override
    public void disposeScene() {

    }

    @Override
    public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem, float pMenuItemLocalX, float pMenuItemLocalY) {
        switch (pMenuItem.getID()) {
            case NEW_GAME:
                return true;
            case OPTIONS:
                return true;
            case ABOUT:
                return true;
            case EXIT:
                System.exit(0);
            default:
                return false;
        }
    }
}
