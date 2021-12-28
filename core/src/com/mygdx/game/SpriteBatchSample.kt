package com.mygdx.game

import com.badlogic.gdx.Application
import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.viewport.FitViewport
import com.badlogic.gdx.utils.viewport.Viewport
import com.mygdx.game.utils.logger
import com.mygdx.game.utils.use

class SpriteBatchSample: ApplicationAdapter() {

    companion object {
        @JvmStatic
        private val log = logger<ModulInfoSample>()
    }

    lateinit var camera: OrthographicCamera
    lateinit var viewport: Viewport
    lateinit var batch: SpriteBatch
    lateinit var texture: Texture


    override fun create() {
        Gdx.app.logLevel = Application.LOG_DEBUG
        log.debug("запуск метода create()")

        camera = OrthographicCamera()
        viewport = FitViewport(10.8f, 7.2f, camera)
        batch = SpriteBatch()
        texture = Texture("raw/character.png")


    }

    override fun resize(width: Int, height: Int) {
        viewport.update(width, height, true)
    }

    override fun render() {
        // очистка экрана
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        batch.projectionMatrix = camera.combined

        /*batch.begin() это можно переписать используя метод расширения
        dro()
        batch.end()*/

        batch.use {
            dro()
            dro1()
        }
    }


    private fun dro(){
        val widht = 1f
        val height = 1f
        batch.draw(
                texture,
                1f, 1f,
                widht / 2f, height /2f,
                widht, height,
                1f, 1f,
                0f,
                0, 0,
                texture.width, texture.height,
                false, false
                )

        /*batch.setColor(1f,0f,0f,1f)

        batch.draw(
                texture,
                4f, 2f,
                widht / 2f, height /2f,
                widht, height,
                2f, 2f,
                0f,
                0, 0,
                texture.width, texture.height,
                false, false
        )*/
    }

    private fun dro1(){
        val widht = 1f
        val height = 1f
        batch.setColor(1f,0f,0f,1f)

        batch.draw(
                texture,
                4f, 2f,
                widht / 2f, height /2f,
                widht, height,
                2f, 2f,
                0f,
                0, 0,
                texture.width, texture.height,
                false, false
        )
    }

    override fun dispose() {
        batch.dispose()

    }
}