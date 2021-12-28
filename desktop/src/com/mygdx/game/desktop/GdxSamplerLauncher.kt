package com.mygdx.game.desktop

import com.badlogic.gdx.ApplicationListener
import com.badlogic.gdx.backends.lwjgl.LwjglAWTCanvas
import com.badlogic.gdx.utils.reflect.ClassReflection
import com.mygdx.game.ModulInfoSample
import com.mygdx.game.utils.logger
import java.awt.*
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import javax.swing.*

class GdxSamplerLauncher: JFrame() {

    private val windowWidth = 1080
    private val cellWidth = 200
    private val windowHigth = 720
    private val windowsSize = Dimension(windowWidth, windowHigth)
    private val canvasWidth = windowWidth - cellWidth
    private var lwjglAWTCanvas: LwjglAWTCanvas? = null
    private lateinit var sampleList: JList<String>

    init {

        title = GdxSamplerLauncher::class.java.simpleName
        minimumSize = windowsSize
        size = windowsSize
        isResizable = false
        defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE

        createControlPanel()

        addWindowListener(object : WindowAdapter() {
            override fun windowClosing(e: WindowEvent?) {
                println("window closing")
                lwjglAWTCanvas?.stop()
            }
        })

        laumchSample("com.mygdx.game.InputPolingSample")
        pack()
            isVisible = true
    }

    private fun createControlPanel() {
        val controlPanel = JPanel(GridBagLayout()) // общий контейнер в который добавляются компоненты кнопки и.т.д
        val c = GridBagConstraints()
        /*c.gridx = 0
        c.gridy = 0
        c.fill = GridBagConstraints.VERTICAL
        c.weighty = 1.0*/
        c.apply {
            gridx = 0
            gridy = 0
            fill = GridBagConstraints.VERTICAL
            weighty = 1.0
        }
        sampleList = JList(arrayOf("com.mygdx.game.InputPolingSample"))
        sampleList.fixedCellWidth = cellWidth
        sampleList.selectionMode = ListSelectionModel.SINGLE_SELECTION

        // добавляем прослушку нажатия клавиши мыши
        sampleList.addMouseListener(object : MouseAdapter() {
            override fun mouseClicked(e: MouseEvent?) {
                if (e?.clickCount == 2) {
                    launchSelectedSample()
                }
            }
        })

        val scrollPane = JScrollPane(sampleList)
        controlPanel.add(scrollPane, c)
        //controlPanel.add(controlPanel, BorderLayout.WEST)
    }

    private fun launchSelectedSample() {

    }

    private fun laumchSample(name: String) {
        //println("launching name = ")
//       очистка перед выводом нового изображения
        lwjglAWTCanvas?.stop()
        if (lwjglAWTCanvas != null) {
            contentPane.remove(lwjglAWTCanvas?.canvas)
        }

        val sampleClass = ClassReflection.forName(name)

        val sample = ClassReflection.newInstance(sampleClass) as ApplicationListener

        lwjglAWTCanvas = LwjglAWTCanvas(sample)
        lwjglAWTCanvas?.canvas?.size = Dimension(canvasWidth,windowHigth)
        contentPane.add(lwjglAWTCanvas!!.canvas, BorderLayout.CENTER)
    }
}



fun main() {
    SwingUtilities.invokeLater {
        GdxSamplerLauncher()
    }

//    SwingUtilities.invokeLater { Runnable { GdxSamplerLauncher() } }
    //GdxTest().init()
   // GdxSamplerLauncher()
}
