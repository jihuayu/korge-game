package scene

import com.soywiz.korev.Key
import com.soywiz.korge.input.onKeyDown
import com.soywiz.korge.particle.particleEmitter
import com.soywiz.korge.particle.readParticle
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.Container
import com.soywiz.korge.view.image
import com.soywiz.korge.view.position
import com.soywiz.korim.format.readBitmap
import com.soywiz.korio.file.std.resourcesVfs
import com.soywiz.korma.geom.Point

class FireScene(
) : Scene() {
    suspend override fun Container.sceneInit() {
        val cardImg = resourcesVfs["lz.png"].readBitmap()
        val fire = resourcesVfs["particle/fire/particle.pex"].readParticle()
        image(cardImg){
            position(200,200)
            particleEmitter(fire){
                position(8, 6)
                scale = 0.1
            }
            scale = 2.0;
        }
    }
}
