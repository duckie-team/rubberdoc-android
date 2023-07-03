/*
 * Designed and developed by Duckie Team, 2022
 *
 * Licensed under the MIT.
 * Please see full license: https://github.com/duckie-team/duckie-android/blob/develop/LICENSE
 */

package team.duckie.rubberdoc.json

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

private data class Pojo(
  val string: String?,
  val list: List<Any>?,
  val nested: Pojo? = null,
)

class JsonBuilderTest : StringSpec() {
  init {
    "single json" {
      val actual = buildJson {
        "int" withInt 1
        "float" withFloat 1f
        "boolean" withBoolean true
        "string" withString "string"
        "pojo" withPojo Pojo("1", listOf("2"))
      }
      val expected =
        "{" +
          "\"int\":1," +
          "\"float\":1.0," +
          "\"boolean\":true," +
          "\"string\":\"string\"," +
          "\"pojo\":{\"string\":\"1\",\"list\":[\"2\"]," +
          "\"nested\":null}" +
          "}"

      actual shouldBe expected
    }

    "double json" {
      val actual = buildJson {
        "ints" withInts listOf(1, 2)
        "floats" withFloats listOf(1f, 2f)
        "booleans" withBooleans listOf(true, false)
        "strings" withStrings listOf("string", "string2")
        "pojos" withPojos listOf(
          Pojo("1", listOf("2")),
          Pojo("3", listOf("4")),
        )
      }
      val expected =
        "{" +
          "\"ints\":[1,2]," +
          "\"floats\":[1.0,2.0]," +
          "\"booleans\":[true,false]," +
          "\"strings\":[\"string\",\"string2\"]," +
          "\"pojos\":[" +
          "{\"string\":\"1\",\"list\":[\"2\"],\"nested\":null}," +
          "{\"string\":\"3\",\"list\":[\"4\"],\"nested\":null}" +
          "]" +
          "}"

      actual shouldBe expected
    }

    "nested pojo json" {
      val actual = buildJson {
        "int" withInt 1
        "float" withFloat 1f
        "boolean" withBoolean true
        "string" withString "string"
        "pojo" withPojo Pojo(
          "1",
          listOf("2"),
          Pojo(
            "3",
            listOf("4"),
          ),
        )
      }
      val expected =
        "{" +
          "\"int\":1," +
          "\"float\":1.0," +
          "\"boolean\":true," +
          "\"string\":\"string\"," +
          "\"pojo\":{" +
          "\"string\":\"1\"," +
          "\"list\":[\"2\"]," +
          "\"nested\":{" +
          "\"string\":\"3\"," +
          "\"list\":[\"4\"]," +
          "\"nested\":null" +
          "}" +
          "}" +
          "}"

      actual shouldBe expected
    }
  }
}
