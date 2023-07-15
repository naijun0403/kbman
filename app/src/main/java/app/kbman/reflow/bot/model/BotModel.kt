package app.kbman.reflow.bot.model

import app.kbman.reflow.bot.BotType
import java.io.File

data class BotModel(
    val name: String,
    val type: BotType,
    val path: File,
    val dependencies: List<BotModel>,
)
