package com.mygdx.game

import com.badlogic.gdx.Application
import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.ApplicationListener
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.Logger
import com.mygdx.game.utils.logger

class ApplicationListenerSample: ApplicationListener {

    companion object {
        @JvmStatic
        private val log = logger<ApplicationListenerSample>()
        //private val log = logger(ApplicationListenerSample::class.java)
         //private val log = Logger(ApplicationListenerSample::class.java.simpleName, Logger.DEBUG)
    }


    private var renderInterrupter = true

    override fun create() {
       // Gdx.app.debug("ApplicationListenerSample","create")
        Gdx.app.logLevel = Application.LOG_DEBUG
        log.debug("create()")
    }

    override fun resize(width: Int, height: Int) {
        log.debug("resize()")
    }

    override fun render() {
        if (renderInterrupter) {
            log.debug("render()")
            renderInterrupter = false
        }
    }

    override fun pause() {
        log.debug("pause()")
        renderInterrupter = true
    }

    override fun resume() {
        log.debug("resume()")
        renderInterrupter = true
    }


    override fun dispose() {
        log.debug("dispose()")
    }


}