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

class InputPolingSample: ApplicationAdapter() {

    companion object {
        @JvmStatic
        private val log = logger<ModulInfoSample>()
    }

    lateinit var camera: OrthographicCamera
    lateinit var viewport: Viewport
    lateinit var batch: SpriteBatch
    lateinit var font: BitmapFont

    override fun create() {
        Gdx.app.logLevel = Application.LOG_DEBUG
        log.debug("запуск метода create()")

        camera = OrthographicCamera()
        viewport = FitViewport(1080f, 720f, camera)
        batch = SpriteBatch()
        font = BitmapFont(Gdx.files.internal("fonts/oswald-32.fnt"))

    }

    override fun resize(width: Int, height: Int) {
        viewport.update(width, height, true)
    }

    override fun render() {
        // очистка экрана
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        batch.projectionMatrix = camera.combined

        batch.begin()
        dro()
        batch.end()
    }


    private fun dro(){
        val mousex = Gdx.input.x
        val mousey = Gdx.input.y

        val leftPressed = Gdx.input.isButtonPressed(Input.Buttons.LEFT)
        val rightPressed = Gdx.input.isButtonPressed(Input.Buttons.RIGHT)
        font.draw(batch, "Mouse x = $mousex  y = $mousey", 20f, 720f - 20f)

        val leftPressedString = if (leftPressed) {
            "Left key pressed"
        } else{
            "Left key NOT pressed"
        }

        font.draw(batch,leftPressedString, 20f, 720f - 50f)

        // обработка клавиатуры
        val wPressed = Gdx.input.isKeyPressed(Input.Keys.W)
        val sPressed = Gdx.input.isKeyPressed(Input.Keys.S)

        val wPressedString = if (wPressed) {
            "W keyboad pressed"
        } else{
            "W key NOT pressed"
        }

        val sPressedString = if (sPressed) {
            "S keyboad pressed"
        } else{
            "S key NOT pressed"
        }

        font.draw(batch,wPressedString, 20f, 720f - 80f)
        font.draw(batch,sPressedString, 20f, 720f - 110f)

    }

    override fun dispose() {
        batch.dispose()
        font.dispose()
    }
}