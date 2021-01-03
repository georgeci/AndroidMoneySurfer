package com.georgeci.moneysurfer.mvi.nesting

import com.georgeci.moneysurfer.mvi.Store
import kotlinx.coroutines.CoroutineScope

class NestedFeatureController {
    private var scope: CoroutineScope? = null

    fun setup(scope: CoroutineScope) {
        this.scope = scope
    }

    fun <T : Store<*, *, *>> initFeature(block: () -> T): T {
        val block1 = block()
        block1.launch(scope!!)
        return block1
    }

    fun <T : Store<*, *, *>> disposeFeature(block: () -> T): T {
        val block1 = block()
        block1.dispose()
        return block1
    }
}