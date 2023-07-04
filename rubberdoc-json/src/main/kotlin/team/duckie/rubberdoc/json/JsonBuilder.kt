/*
 * Designed and developed by Duckie Team 2023.
 *
 * Licensed under the MIT.
 * Please see full license: https://github.com/duckie-team/rubberdoc-android/blob/main/LICENSE
 */

package team.duckie.rubberdoc.json

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import team.duckie.rubberdoc.utils.fastForEach

private val jsonMapper =
  ObjectMapper()
    .disable(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES)
    .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)

@RubberdocDsl
public interface JsonBuilder {
  public infix fun String.withInt(value: Int)
  public infix fun String.withFloat(value: Float)
  public infix fun String.withBoolean(value: Boolean)
  public infix fun String.withString(value: String)
  public infix fun String.withPojo(value: Any)

  public infix fun String.withInts(value: List<Int>)
  public infix fun String.withFloats(value: List<Float>)
  public infix fun String.withBooleans(value: List<Boolean>)
  public infix fun String.withStrings(value: List<String>)
  public infix fun String.withPojos(value: List<Any>)

  public fun build(): String
}

// 매 DSL 마다 `builder` 인스턴스를 새로 만들어야 함
// -> 싱글톤 불가
@PublishedApi
internal class JsonBuilderInstance : JsonBuilder {
  private val builder = jsonMapper.createObjectNode()

  override infix fun String.withInt(value: Int) {
    builder.put(this, value)
  }

  override infix fun String.withFloat(value: Float) {
    builder.put(this, value)
  }

  override infix fun String.withBoolean(value: Boolean) {
    builder.put(this, value)
  }

  override infix fun String.withString(value: String) {
    builder.put(this, value)
  }

  override infix fun String.withPojo(value: Any) {
    builder.putPOJO(this, value)
  }

  override fun String.withInts(value: List<Int>) {
    builder.putArray(this).also { array ->
      value.fastForEach(array::add)
    }
  }

  override fun String.withFloats(value: List<Float>) {
    builder.putArray(this).also { array ->
      value.fastForEach(array::add)
    }
  }

  override fun String.withBooleans(value: List<Boolean>) {
    builder.putArray(this).also { array ->
      value.fastForEach(array::add)
    }
  }

  override fun String.withStrings(value: List<String>) {
    builder.putArray(this).also { array ->
      value.fastForEach(array::add)
    }
  }

  override fun String.withPojos(value: List<Any>) {
    builder.putArray(this).also { array ->
      value.fastForEach(array::addPOJO)
    }
  }

  override fun build() = builder.toString()
}

public inline fun buildJson(builder: JsonBuilder.() -> Unit): String =
  with(JsonBuilderInstance()) {
    builder()
    build()
  }
