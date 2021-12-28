package com.mygdx.game.desktop

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import com.mygdx.game.MyGdxGame
import com.mygdx.game.ShapeRendererSample
import com.mygdx.game.SpriteBatchSample


fun main() {
    LwjglApplication(ShapeRendererSample(), LwjglApplicationConfiguration())
}