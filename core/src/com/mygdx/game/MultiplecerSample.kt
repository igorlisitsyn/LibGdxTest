package com.mygdx.game

import com.badlogic.gdx.*
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.mygdx.game.utils.logger

class MultiplecerSample: ApplicationAdapter() {

        companion object {
            @JvmStatic
            private val log = logger<MultiplecerSample>()
        }

    override fun create() {
        Gdx.app.logLevel = Application.LOG_DEBUG

       // val multiplexer = InputMultiplexer()

        val firstProcessor = object : InputAdapter() {
            override fun keyDown(keycode: Int): Boolean {
                log.debug("first keyDown keykode = $keycode")
                return true
            }

            override fun keyUp(keycode: Int): Boolean {
                log.debug("first keyUp keykode = $keycode")
                return false
            }
        }

        val secondProcessor = object : InputAdapter() {
            override fun keyDown(keycode: Int): Boolean {
                log.debug("second keyDown keykode = $keycode")
                return true
            }
            override fun keyUp(keycode: Int): Boolean {
                log.debug("second keyUp keykode = $keycode")
                return false
            }

        }

      //  multiplexer.addProcessor(firstProcessor)
      //  multiplexer.addProcessor(secondProcessor)

       // Gdx.input.inputProcessor = multiplexer
        // тоже самое
        Gdx.input.inputProcessor = InputMultiplexer(firstProcessor, secondProcessor)
    }
}