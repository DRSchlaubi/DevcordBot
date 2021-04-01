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

package com.github.devcordde.devcordbot.command.slashcommands

import com.github.devcordde.devcordbot.command.context.Arguments
import net.dv8tion.jda.api.entities.Command
import net.dv8tion.jda.api.entities.Member
import net.dv8tion.jda.api.entities.User
import net.dv8tion.jda.api.requests.restaction.CommandUpdateAction

/**
 * Builder for slash command options.
 */
class OptionsBuilder(private val options: MutableList<CommandUpdateAction.OptionData>) {

    /**
     * Adds an option of [type] with [name] and [description] and applied [builder] to id.
     */
    fun option(
        type: Command.OptionType,
        name: String,
        description: String,
        builder: CommandUpdateAction.OptionData.() -> Unit = {}
    ) {
        options.add(CommandUpdateAction.OptionData(type, name, description).apply(builder))
    }

    /**
     * Adds an option of type [Command.OptionType.STRING] with [name] and [description] and applied [builder] to id.
     */
    fun string(
        name: String,
        description: String,
        builder: CommandUpdateAction.OptionData.() -> Unit = {}
    ): Unit = option(Command.OptionType.STRING, name, description, builder)

    /**
     * Adds an option of type [Command.OptionType.INTEGER] with [name] and [description] and applied [builder] to id.
     *
     * This covers both [Int] and [Long] (see [Arguments.long] and [Arguments.int])
     */
    fun int(
        name: String,
        description: String,
        builder: CommandUpdateAction.OptionData.() -> Unit = {}
    ): Unit = option(Command.OptionType.INTEGER, name, description, builder)

    /**
     * Adds an option of type [Command.OptionType.BOOLEAN] with [name] and [description] and applied [builder] to id.
     */
    fun boolean(
        name: String,
        description: String,
        builder: CommandUpdateAction.OptionData.() -> Unit = {}
    ): Unit = option(Command.OptionType.BOOLEAN, name, description, builder)

    /**
     * Adds an option of type [Command.OptionType.STRING] with [name] and [description] and applied [builder] to id.
     *
     * This covers both [User] and [Member] (see [Arguments.user] and [Arguments.member])
     */
    fun user(
        name: String,
        description: String,
        builder: CommandUpdateAction.OptionData.() -> Unit = {}
    ): Unit = option(Command.OptionType.USER, name, description, builder)

    /**
     * Adds an option of type [Command.OptionType.CHANNEL] with [name] and [description] and applied [builder] to id.
     */
    fun channel(
        name: String,
        description: String,
        builder: CommandUpdateAction.OptionData.() -> Unit = {}
    ): Unit = option(Command.OptionType.CHANNEL, name, description, builder)

    /**
     * Adds an option of type [Command.OptionType.ROLE] with [name] and [description] and applied [builder] to id.
     */
    fun role(
        name: String,
        description: String,
        builder: CommandUpdateAction.OptionData.() -> Unit = {}
    ): Unit = option(Command.OptionType.ROLE, name, description, builder)
}
