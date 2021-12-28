package com.mygdx.game.desktop

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import com.mygdx.game.MyGdxGame
import com.mygdx.game.OrtographicCameraSample
import com.mygdx.game.ReflactionSample
import com.mygdx.game.ViewPortSample

fun main() {
    //LwjglApplication(OrtographicCameraSample(), LwjglApplicationConfiguration())
    LwjglApplication(ViewPortSample(), LwjglApplicationConfiguration())
}