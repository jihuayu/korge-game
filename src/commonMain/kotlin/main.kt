import com.soywiz.korge.Korge
import com.soywiz.korge.input.onClick
import com.soywiz.korge.scene.Module
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.Container
import com.soywiz.korge.view.image
import com.soywiz.korge.view.solidRect
import com.soywiz.korge.view.text
import com.soywiz.korim.color.Colors
import com.soywiz.korinject.AsyncInjector
import com.soywiz.korio.async.launchImmediately
import scene.MyScene1
import kotlin.reflect.KClass

suspend fun main() = Korge(Korge.Config(module = MyModule))

object MyModule : Module() {
    override val mainScene: KClass<out Scene> = MainScene::class

    override suspend fun init(injector: AsyncInjector): Unit = injector.run {
        mapPrototype { MyScene1() }
        mapPrototype { MainScene() }
    }
}

class MainScene() : Scene() {
    override suspend fun Container.sceneInit() {
        solidRect(100,100, Colors.GREEN) {
            onClick {
                println(11)
                launchImmediately {
                    sceneContainer.changeTo<MyScene1>()
                }
            }
        }
    }
}