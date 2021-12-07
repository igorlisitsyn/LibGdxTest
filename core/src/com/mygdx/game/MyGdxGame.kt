package com.mygdx.game

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch

class MyGdxGame: ApplicationAdapter() {

    lateinit var bath: SpriteBatch
    lateinit var img: Texture

    override fun create() {
        bath = SpriteBatch()
        img = Texture("badlogic.jpg")
    }

    override fun render() {
        Gdx.gl.glClearColor(1.0f, 0.0f, 0.0f, 1.0f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        bath.begin()
        bath.draw(img, 0.0f, 0.0f)
        bath.end()
    }

    override fun dispose() {
        bath.dispose()
        img.dispose()
    }

}