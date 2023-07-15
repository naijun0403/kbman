package app.kbman.reflow.constant

import android.os.Environment
import app.kbman.reflow.bot.BotType

object Constant {

    val SDCARD_PATH: String = Environment.getExternalStorageDirectory().absolutePath

    val BOT_DEFAULT_DIR_MAPPER = mutableMapOf(
        BotType.MESSENGERBOTR to "msgbot",
        BotType.GREENBOT to "ChatBot"
    )

}