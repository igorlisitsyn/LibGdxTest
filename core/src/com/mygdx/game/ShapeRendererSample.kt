package com.mygdx.game
// рисуем графические примитивы и координатнцю сетку по перврначальным координатам экрана

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.utils.viewport.FitViewport
import com.badlogic.gdx.utils.viewport.Viewport
import com.mygdx.game.sampler.SampleBase

import com.mygdx.game.utils.clearScreen
import com.mygdx.game.utils.isKeyPressed
import com.mygdx.game.utils.logger

class ShapeRendererSample: SampleBase() {

    companion object {
        @JvmStatic
        private val log = logger<ShapeRendererSample>()

        private const val WORLD_WIDTH = 40f
        private const val WORLD_HEIGHT = 20f
    }

    private lateinit var camera: OrthographicCamera
    private lateinit var viewport: Viewport
    private lateinit var renderer: ShapeRenderer

    private var drawGrid = true
    private var drawCircles = true
    private var drawRectangles = true
    private var drawPoints = true

    override fun create() {
        camera = OrthographicCamera()
        viewport = FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera)
        renderer = ShapeRenderer()

        Gdx.input.inputProcessor = this
    }


    override fun render() {
        clearScreen()

        renderer.projectionMatrix = camera.combined


        if (drawGrid) {
            drawGrid()
        }

        if (drawCircles) {
            drawCircles()
        }

        if (drawRectangles) {
            drawRectangles()
        }

        if (drawPoints) {
            drawPoints()
        }

    }

    private fun drawPoints() {
        renderer.begin(ShapeRenderer.ShapeType.Filled)
        renderer.color = Color.BLUE
        renderer.point(5f, 0f, 0f)
        renderer.point(5f, -3f, 1f)
        renderer.end()

        renderer.begin(ShapeRenderer.ShapeType.Line)
        renderer.x(-10f, 0f, 0.25f)
        renderer.end()
    }

    private fun drawRectangles() {
        renderer.begin(ShapeRenderer.ShapeType.Filled)

        renderer.color = Color.CORAL
        renderer.rect(-8f, 4f, 4f, 2f)

        renderer.end()
    }

    private fun drawCircles() {
        renderer.begin(ShapeRenderer.ShapeType.Filled)
        renderer.color = Color.GREEN

        renderer.circle(2f, 2f, 2f, 30)
        renderer.circle(-5f, -5f, 1f)

        renderer.end()
    }

    private fun drawGrid() {
        renderer.begin(ShapeRenderer.ShapeType.Line)
        renderer.color = Color.WHITE

        val worldeWidth = WORLD_WIDTH.toInt()
        val worldHight = WORLD_HEIGHT.toInt()
        // рисуем горизонтальные линии
        for (y in -worldeWidth until worldeWidth) {
            renderer.line(-worldeWidth.toFloat(), y.toFloat(), worldeWidth.toFloat(), y.toFloat())
        }

        //рисуем вертикальные линии
        for (x in -worldHight until worldHight) {
            renderer.line(x.toFloat(),-worldHight.toFloat(), x.toFloat(), worldHight.toFloat() )
        }

        renderer.color = Color.BLUE
        //renderer.line(-worldeWidth.toFloat(), 0.0f, worldeWidth.toFloat(), 0f, Color.BLUE, Color.BLUE)
        renderer.line(-worldeWidth.toFloat(), 0.0f, worldeWidth.toFloat(), 0f)
        renderer.line(0f, -worldHight.toFloat(), 0f, worldHight.toFloat())

        renderer.end()
    }

    override fun keyDown(keycode: Int): Boolean {
        when(keycode) {
           Input.Keys.G -> drawGrid = !drawGrid
            Input.Keys.C -> drawCircles = !drawCircles
            Input.Keys.R -> drawRectangles = !drawRectangles
            Input.Keys.F -> drawPoints = !drawPoints
        }

        return true
    }

    override fun resize(width: Int, height: Int) {
       // камера спозиционирована в центре координатной сетке
        viewport.update(width, height)
        // камера спозиционирована в нижний левый угол
       // viewport.update(width, height, true)
    }

    override fun dispose() {
        renderer.dispose()
    }
}