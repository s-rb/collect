package org.odk.collect.entities.javarosa

import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.odk.collect.entities.browser.EntityItemElement
import org.odk.collect.entities.javarosa.intance.LocalEntitiesFileInstanceParser
import org.odk.collect.entities.storage.Entity
import org.odk.collect.entities.storage.InMemEntitiesRepository

class LocalEntitiesFileInstanceParserTest {

    private val entitiesRepository = InMemEntitiesRepository()

    @Test
    fun `includes properties in local entity elements`() {
        val entity =
            Entity(
                "people",
                "1",
                "Shiv Roy",
                properties = listOf(Pair("age", "35"), Pair("born", "England"))
            )
        entitiesRepository.save(entity)

        val parser = LocalEntitiesFileInstanceParser { entitiesRepository }
        val instance = parser.parse("people", "people.csv")
        assertThat(instance.numChildren, equalTo(1))

        val item = instance.getChildAt(0)!!
        assertThat(item.numChildren, equalTo(5))
        assertThat(item.getFirstChild("age")?.value?.value, equalTo("35"))
        assertThat(item.getFirstChild("born")?.value?.value, equalTo("England"))
    }

    @Test
    fun `includes version in local entity elements`() {
        val entity =
            Entity(
                "people",
                "1",
                "Shiv Roy",
                version = 1
            )
        entitiesRepository.save(entity)

        val parser = LocalEntitiesFileInstanceParser { entitiesRepository }
        val instance = parser.parse("people", "people.csv")
        assertThat(instance.numChildren, equalTo(1))

        val item = instance.getChildAt(0)!!
        assertThat(item.numChildren, equalTo(3))
        assertThat(item.getFirstChild(EntityItemElement.VERSION)?.value?.value, equalTo("1"))
    }

    @Test
    fun `partial parse returns elements without values`() {
        val entity =
            Entity(
                "people",
                "1",
                "Shiv Roy",
                properties = listOf(Pair("age", "35")),
                version = 1
            )
        entitiesRepository.save(entity)

        val parser = LocalEntitiesFileInstanceParser { entitiesRepository }
        val instance = parser.parse("people", "people.csv", true)
        assertThat(instance.numChildren, equalTo(1))

        val item = instance.getChildAt(0)!!
        assertThat(item.isPartial, equalTo(true))
        assertThat(item.numChildren, equalTo(4))
        0.until(item.numChildren).forEach {
            assertThat(item.getChildAt(it).value?.value, equalTo(null))
        }
    }
}
