/*
 * Copyright 2020 Daniel Scherf & Michael Rittmeister & Julian König
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

package com.github.devcordde.devcordbot.util

import kotlinx.coroutines.future.await
import mu.KotlinLogging
import net.dv8tion.jda.api.entities.Member
import net.dv8tion.jda.api.requests.RestAction
import net.dv8tion.jda.api.utils.data.DataObject

private val httpLogger = KotlinLogging.logger("HttpClient")

/**
 * Checks whether a string is numeric or not.
 */
fun String.isNumeric(): Boolean = all(Char::isDigit)

/**
 * Checks whether a string is not numeric or not
 * @see isNumeric
 */
@Suppress("unused")
fun String.isNotNumeric(): Boolean = !isNumeric()

/**
 * @see net.dv8tion.jda.api.entities.IMentionable.getAsMention
 */
fun Member.asMention(): Regex = "<@!?$id>\\s?".toRegex()

/**
 * Limits the length of a string by [amount] and adds [contraction] at the end.
 */
fun String.limit(amount: Int, contraction: String = "..."): String =
    if (length < amount) this else "${substring(0, amount - contraction.length)}$contraction"

/**
 * Public map constructor of [DataObject].
 */
class MapJsonObject(map: Map<String, Any>) : DataObject(map)

/**
 * **Only use in coroutines**
 * Awaits the [RestAction] to finish
 */
suspend fun <T> RestAction<T>.await(): T = submit().await()
