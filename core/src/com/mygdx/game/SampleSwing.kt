package com.mygdx.game

import java.awt.FlowLayout
import java.awt.GridLayout
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import javax.swing.JButton
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.JPanel

class SampleSwing {

    private lateinit var mainFrame: JFrame
    private lateinit var headerLabel: JLabel
    private lateinit var statusLabel: JLabel
    private lateinit var controlPanel: JPanel

    init {
        prepareGUI()
    }

    private fun prepareGUI() {
        headerLabel = JLabel("", JLabel.CENTER)
        statusLabel = JLabel("", JLabel.CENTER).apply { setSize(350, 100) }

        controlPanel = JPanel().apply { layout = FlowLayout() }

        mainFrame = JFrame("Java SWING Examples").apply {
            setSize(400, 400)
            layout = GridLayout(3, 1)
            addWindowListener(object : WindowAdapter() {
                override fun windowClosing(windowEvent: WindowEvent?) {
                    System.exit(0)
                }
            })
            add(headerLabel)
            add(controlPanel)
            add(statusLabel)
            isVisible = true
        }
    }

    internal fun showEventDemo() {
        headerLabel.text = "Control in action: Button"

        val okButton = JButton("OK").apply {
            actionCommand = "OK"
            addActionListener(ButtonClickListener())
        }
        val submitButton = JButton("Submit").apply {
            actionCommand = "Submit"
            addActionListener(ButtonClickListener())
        }
        val cancelButton = JButton("Cancel").apply {
            actionCommand = "Cancel"
            addActionListener(ButtonClickListener())
        }

        controlPanel.add(okButton)
        controlPanel.add(submitButton)
        controlPanel.add(cancelButton)

        mainFrame.isVisible = true
    }

    private inner class ButtonClickListener : ActionListener {
        override fun actionPerformed(e: ActionEvent) {
            statusLabel.text = when (e.actionCommand) {
                "OK" -> "Ok Button clicked."
                "Submit" -> "Submit Button clicked."
                else -> "Cancel Button clicked."
            }
        }
    }
}

fun main(args: Array<String>) {
    val swingControlDemo = SampleSwing()
    swingControlDemo.showEventDemo()
}