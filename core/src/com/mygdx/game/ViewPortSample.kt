package com.mygdx.game

import com.badlogic.gdx.*
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.ArrayMap
import com.badlogic.gdx.utils.viewport.*
import com.mygdx.game.sampler.SampleBase
import com.mygdx.game.utils.isKeyPressed
import com.mygdx.game.utils.logger
import com.mygdx.game.utils.toInternalFile

class ViewPortSample: SampleBase() {

    companion object {
        @JvmStatic
        private val log = logger<ModulInfoSample>()

        private const val WORLD_WIDTH = 1080f
        private const val WORLD_HEIGTH = 720f
        //private const val CAMERA_SPEED = 2.0f
        //private const val CAMERA_ZOOM_SPEED = 2.0f

        private val viewports = ArrayMap<String, Viewport>()
        private var currentViewportIndex = -1
        private var currentViewportName = ""

    }

    private lateinit var camera: OrthographicCamera
    private lateinit var currentViewport: Viewport
    private lateinit var batch: SpriteBatch
    private lateinit var texture: Texture
    private lateinit var font: BitmapFont

    override fun create() {
        Gdx.app.logLevel = Application.LOG_DEBUG
        log.debug("запуск метода create()")

        camera = OrthographicCamera()
        /*camera.setToOrtho(true) //поворот изображения на 180 ( смена начальной точки координат с нижнего левого на верхний левый)
        currentViewport = FitViewport(WORLD_WIDTH, WORLD_HEIGTH, camera)*/

        batch = SpriteBatch()
        texture = Texture("raw/level-bg.png")
        font = BitmapFont("fonts/oswald-32.fnt".toInternalFile())

        createViewports()
        selectNextViewport()

        Gdx.input.inputProcessor = this

    }

    fun createViewports() {
        viewports.put(StretchViewport::class.java.simpleName, StretchViewport(WORLD_WIDTH, WORLD_HEIGTH, camera))
        viewports.put(FitViewport::class.java.simpleName, FitViewport(WORLD_WIDTH, WORLD_HEIGTH, camera))
        viewports.put(FillViewport::class.java.simpleName, FitViewport(WORLD_WIDTH, WORLD_HEIGTH, camera))
        viewports.put(ScreenViewport::class.java.simpleName, ScreenViewport( camera))
        viewports.put(ExtendViewport::class.java.simpleName, ExtendViewport(WORLD_WIDTH, WORLD_HEIGTH, camera))
    }

    fun selectNextViewport() {
        currentViewportIndex = (currentViewportIndex + 1) % viewports.size
        currentViewport = viewports.getValueAt(currentViewportIndex)
        resize(Gdx.graphics.width, Gdx.graphics.height)
        currentViewportName = viewports.getKeyAt(currentViewportIndex)
        log.debug("select viewports = $currentViewportName")
    }

    override fun resize(width: Int, height: Int) {
        currentViewport.update(width, height, true)

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

      batch.draw(texture, 0f, 0f, WORLD_WIDTH, WORLD_HEIGTH)
        font.draw(batch, currentViewportName, 50f, 100f)

    }

    override fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        selectNextViewport()
        return false
    }

    override fun dispose() {
        batch.dispose()
        texture.dispose()
    }
}