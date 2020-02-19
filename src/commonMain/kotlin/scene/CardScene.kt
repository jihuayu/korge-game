package scene

import com.soywiz.korge.input.onMouseDrag
import com.soywiz.korge.particle.particleEmitter
import com.soywiz.korge.particle.readParticle
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.Container
import com.soywiz.korge.view.image
import com.soywiz.korge.view.position
import com.soywiz.korge.view.solidRect
import com.soywiz.korim.color.Colors
import com.soywiz.korim.format.readBitmap
import com.soywiz.korio.file.std.resourcesVfs

class MyScene1 : Scene() {
    var over = false;
    override suspend fun Container.sceneInit() {
        val emitter = resourcesVfs["particle/particle.pex"].readParticle()
        particleEmitter(emitter).position(views.virtualWidth * 0.5, views.virtualHeight * 0.75)
        val cardImg = resourcesVfs["card.png"].readBitmap()
        val ans = resourcesVfs["korge.png"].readBitmap()
        val rect = solidRect(100, 100, Colors.BLUE) {
            position(600, 400)
        }
        for (i in 1..4)
            image(cardImg) {
                position(i * (cardImg.width + 10), 0);
                val view = this
                var sx = 0.0
                var sy = 0.0
                onMouseDrag { info ->
                    if (over) return@onMouseDrag;
                    val ids = (rect.globalX + rect.width - globalX - width) * (rect.globalX + rect.width - globalX - width) +
                            (rect.globalY + rect.height - globalY - height) * (rect.globalY + rect.height - globalY - height)

                    if (info.start) {
                        sx = view.x
                        sy = view.y
                    } else if (info.end) {
                        if (ids > 30000) {
                            view.x = sx;
                            view.y = sy;
                        } else {
                            this@sceneInit.removeChild(this@image)
                            this@sceneInit.removeChild(rect)
                            this@sceneInit += image(ans) {
                                position(rect.pos)
                            }
                            over = true
                        }
                    } else {
                        if (ids <= 30000) {
                            view.x = sx + info.dx + kotlin.math.sin(ids) * (30000 - ids) / 3000
                            view.y = sy + info.dy + kotlin.math.cos(ids) * (30000 - ids) / 3000
                        } else {
                            view.x = sx + info.dx
                            view.y = sy + info.dy
                        }
                    }
                }
            }
    }
}