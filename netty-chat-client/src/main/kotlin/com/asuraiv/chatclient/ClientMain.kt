package com.asuraiv.chatclient

import com.asuraiv.chatclient.view.MainView
import tornadofx.App
import tornadofx.launch

class ClientMain : App(MainView::class) {

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            launch<ClientMain>(args);
        }
    }
}