import com.soywiz.korge.Korge
import com.soywiz.korge.input.onClick
import com.soywiz.korge.scene.Module
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.Container
import com.soywiz.korge.view.position
import com.soywiz.korge.view.solidRect
import com.soywiz.korge.view.text
import com.soywiz.korim.color.Colors
import com.soywiz.korinject.AsyncInjector
import com.soywiz.korio.async.launchImmediately
import kotlin.reflect.KClass

suspend fun main() = Korge(Korge.Config(module = MyModule))

object MyModule : Module() {
    override val mainScene: KClass<out Scene> = MainScene::class

	override suspend fun init(injector: AsyncInjector): Unit =injector.run  {
        mapInstance(MyDependency("HELLO WORLD"))
        mapPrototype { MyScene1(get()) }
        mapPrototype { MainScene() }
    }
}

class MainScene() : Scene() {
    override suspend fun Container.sceneInit() {
        solidRect(100, 100, Colors.BLUE).onClick {
			launchImmediately {
				sceneContainer.changeTo<MyScene1>()
			}
		}
    }
}

class MyDependency(val value: String)

class MyScene1(val myDependency: MyDependency) : Scene() {
    override suspend fun Container.sceneInit() {
        solidRect(100, 100, Colors.RED).position(100, 100).onClick {
			launchImmediately {
				sceneContainer.changeTo<MainScene>()
			}
		}
    }
}