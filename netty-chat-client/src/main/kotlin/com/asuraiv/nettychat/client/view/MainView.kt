package com.asuraiv.nettychat.client.view

import tornadofx.*

class MainView : View() {

    override val root = hbox {
        textfield()
        button("send")
    }
}