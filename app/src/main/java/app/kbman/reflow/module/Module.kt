package app.kbman.reflow.module

import app.kbman.reflow.engine.EngineType

data class Module(
    val name: String,
    val type: List<EngineType>,
    val path: String,
    val dependencies: List<Module>,
)
