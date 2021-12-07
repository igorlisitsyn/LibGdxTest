package com.mygdx.game.desktop

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import com.mygdx.game.InputPolingSample
import com.mygdx.game.InputisteningSample
import com.mygdx.game.MyGdxGame

fun main() {
    val config = LwjglApplicationConfiguration()
    config.width = 1080
    config.height = 720
    LwjglApplication(InputisteningSample(), config)
}