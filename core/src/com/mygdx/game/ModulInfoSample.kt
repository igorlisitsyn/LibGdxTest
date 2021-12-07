package com.mygdx.game

import com.badlogic.gdx.Application
import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.mygdx.game.utils.logger

class ModulInfoSample: ApplicationAdapter() {

    companion object {
        @JvmStatic
        private val log = logger<ModulInfoSample>()
    }

    override fun create() {
        Gdx.app.logLevel = Application.LOG_DEBUG

        log.debug("приложение = ${Gdx.app}")
        log.debug("аудио = ${Gdx.audio}")
        log.debug("ввод данных = ${Gdx.input}")
        log.debug("работа с файлом = ${Gdx.files}")
        log.debug("сеть = ${Gdx.net}")
        log.debug("графика = ${Gdx.graphics}")
    }
}