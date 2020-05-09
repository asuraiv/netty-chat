package com.asuraiv.chatclient.view

import tornadofx.*

class MainView : View() {

    override val root = hbox {
        textfield()
        button("send")
    }
}