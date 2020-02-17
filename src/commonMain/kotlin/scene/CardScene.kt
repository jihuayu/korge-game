package scene

import com.soywiz.klock.seconds
import com.soywiz.korge.input.onMouseDrag
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.tween.moveTo
import com.soywiz.korge.view.*
import com.soywiz.korim.color.Colors
import com.soywiz.korim.format.readBitmap
import com.soywiz.korio.file.std.resourcesVfs
import com.soywiz.korma.geom.Point
import com.soywiz.korma.geom.shape.Shape2d
import dragImage

class MyScene1 : Scene() {
    var over = false;
    override suspend fun Container.sceneInit() {
        val cardImg = resourcesVfs["card.png"].readBitmap()
        val ans = resourcesVfs["korge.png"].readBitmap()
        val rect = solidRect(100,100, Colors.BLUE){
            position(600,400)
        }
        for (i in 1..4)
        dragImage(cardImg){
            position(i*(cardImg.width+10),0);
            val view = this
            var sx = 0.0
            var sy = 0.0
            onMouseDrag { info ->
                if (over) return@onMouseDrag;
                if (info.start) {
                    sx = view.x
                    sy = view.y
                }
                else if (info.end){
                    println((rect.globalX+rect.width-globalX-width)*(rect.globalX+rect.width-globalX-width)+
                            (rect.globalY+rect.height-globalY-height)*(rect.globalY+rect.height-globalY-height))
                    if ((rect.globalX+rect.width-globalX-width)*(rect.globalX+rect.width-globalX-width)+
                            (rect.globalY+rect.height-globalY-height)*(rect.globalY+rect.height-globalY-height)>30000){
                        view.x = sx;
                        view.y = sy;

                    }
                    else{
                        this@sceneInit.removeChild(this@dragImage)
                        this@sceneInit.removeChild(rect)
                        this@sceneInit+=image(ans){
                            position(rect.pos)
                        }
                        over = true
                    }
                }
                else{
                    view.x = sx + info.dx
                    view.y = sy + info.dy
                }
            }
        }
    }
}