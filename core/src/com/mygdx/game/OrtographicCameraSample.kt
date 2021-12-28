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
import com.mygdx.game.utils.isKeyPressed
import com.mygdx.game.utils.logger

class OrtographicCameraSample: ApplicationAdapter() {

    companion object {
        @JvmStatic
        private val log = logger<ModulInfoSample>()

        private const val WORLD_WIDTH = 10.0f
        private const val WORLD_HEIGTH = 7.2f
        private const val CAMERA_SPEED = 2.0f
        private const val CAMERA_ZOOM_SPEED = 2.0f
    }

    private lateinit var camera: OrthographicCamera
    private lateinit var viewport: Viewport
    private lateinit var batch: SpriteBatch
    private lateinit var texture: Texture

    override fun create() {
        Gdx.app.logLevel = Application.LOG_DEBUG
        log.debug("запуск метода create()")

        camera = OrthographicCamera()
        camera.setToOrtho(true) //поворот изображения на 180 ( смена начальной точки координат с нижнего левого на верхний левый)
        viewport = FitViewport(WORLD_WIDTH, WORLD_HEIGTH, camera)
        batch = SpriteBatch()
        texture = Texture("raw/level-bg.png")

    }

    override fun resize(width: Int, height: Int) {
        viewport.update(width, height, true)
    }

    override fun render() {
        // очистка экрана
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        queryInput()

        batch.projectionMatrix = camera.combined

        batch.begin()
        dro()
        batch.end()
    }


    fun queryInput() {
        val deltaTime = Gdx.graphics.deltaTime
        //Gdx.input.isKeyPressed(Input.Keys.LEFT)
        when {
            Input.Keys.LEFT.isKeyPressed() -> camera.position.x -= CAMERA_SPEED * deltaTime
            Input.Keys.RIGHT.isKeyPressed() -> camera.position.x += CAMERA_SPEED * deltaTime
            Input.Keys.UP.isKeyPressed() -> camera.position.y += CAMERA_SPEED * deltaTime
            Input.Keys.DOWN.isKeyPressed() -> camera.position.y -= CAMERA_SPEED * deltaTime
            Input.Keys.PAGE_UP.isKeyPressed() -> camera.zoom -= CAMERA_ZOOM_SPEED * deltaTime
            Input.Keys.PAGE_DOWN.isKeyPressed() -> camera.zoom += CAMERA_ZOOM_SPEED * deltaTime
            Input.Keys.ENTER.isKeyPressed() -> log.debug("position camera = ${camera.position}")
            //else -> log.debug("")
        }

        camera.update()

    }

    private fun dro(){

      batch.draw(texture, 0f, 0f, WORLD_WIDTH, WORLD_HEIGTH)
    }

    override fun dispose() {
        batch.dispose()
        texture.dispose()
    }
}