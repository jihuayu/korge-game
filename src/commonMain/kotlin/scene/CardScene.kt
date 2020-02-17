package scene

import com.soywiz.korge.input.onMouseDrag
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.*
import com.soywiz.korim.format.readBitmap
import com.soywiz.korio.file.std.resourcesVfs
import com.soywiz.korma.geom.Point
import dragImage

class MyScene1 : Scene() {
    override suspend fun Container.sceneInit() {
        val cardImg = resourcesVfs["card.png"].readBitmap()
        dragImage(cardImg).position(cardImg.width+10,0);
        dragImage(cardImg).position(2*(cardImg.width+10),0);
        dragImage(cardImg).position(3*(cardImg.width+10),0);
        dragImage(cardImg).position(4*(cardImg.width+10),0);
    }
}