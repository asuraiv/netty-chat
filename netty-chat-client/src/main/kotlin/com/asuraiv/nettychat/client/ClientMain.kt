package com.asuraiv.nettychat.client

import com.asuraiv.nettychat.client.view.MainView
import tornadofx.*

class ClientMain : App(MainView::class) {

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            launch<ClientMain>(args);
        }
    }
}