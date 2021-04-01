/*
 * Copyright 2021 Daniel Scherf & Michael Rittmeister & Julian König
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.github.devcordde.devcordbot.command

import com.github.devcordde.devcordbot.command.context.Context
import com.github.devcordde.devcordbot.command.permission.Permission
import com.github.devcordde.devcordbot.command.slashcommands.permissions.PermissiveCommandData
import net.dv8tion.jda.api.requests.restaction.CommandUpdateAction

/**
 * Abstract single command (without sub commands).
 */
abstract class AbstractSingleCommand : AbstractCommand() {

    /**
     * A List of [CommandUpdateAction.OptionData] required for slash command registration.
     */
    open val options: List<CommandUpdateAction.OptionData> = emptyList()

    /**
     * Invokes the command.
     * @param context the [Context] in which the command is invoked
     */
    abstract suspend fun execute(context: Context)

    fun toSlashCommand(): CommandUpdateAction.CommandData {
        try {
            val command = PermissiveCommandData(
                name, description
            )
            command.defaultPermission = permission == Permission.ANY
            options.forEach(command::addOption)

            return command
        } catch (e: Exception) {
            throw IllegalStateException(
                "Could not process command with name $name",
                e
            )
        }
    }
}
