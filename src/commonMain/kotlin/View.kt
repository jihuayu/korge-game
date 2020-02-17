import com.soywiz.klock.seconds
import com.soywiz.korge.input.onMouseDrag
import com.soywiz.korge.tween.moveTo
import com.soywiz.korge.tween.tween
import com.soywiz.korge.view.Container
import com.soywiz.korge.view.Image
import com.soywiz.korge.view.ViewsDslMarker
import com.soywiz.korge.view.addTo
import com.soywiz.korim.bitmap.Bitmap
import com.soywiz.korim.bitmap.BmpSlice

inline fun Container.dragImage(
        texture: BmpSlice, anchorX: Double = 0.0, anchorY: Double = 0.0, callback: @ViewsDslMarker Image.() -> Unit = {}
): Image = Image(texture, anchorX, anchorY).addTo(this).apply{
    val view = this
    var sx = 0.0
    var sy = 0.0
    onMouseDrag { info ->
        if (info.start) {
            sx = view.x
            sy = view.y
        }
        if (info.end){
            view.x = sx;
            view.y = sy;
        }
        view.x = sx + info.dx
        view.y = sy + info.dy
    }
    callback()
}

inline fun Container.dragImage(
        texture: Bitmap, anchorX: Double = 0.0, anchorY: Double = 0.0, callback: @ViewsDslMarker Image.() -> Unit = {}
): Image = Image(texture, anchorX, anchorY).addTo(this).apply{

    callback()
}
