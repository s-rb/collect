package org.odk.collect.android.feature.formentry

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith
import org.odk.collect.android.support.StubOpenRosaServer.EntityListItem
import org.odk.collect.android.support.StubOpenRosaServer.MediaFileItem
import org.odk.collect.android.support.TestDependencies
import org.odk.collect.android.support.pages.FormEntryPage
import org.odk.collect.android.support.pages.MainMenuPage
import org.odk.collect.android.support.rules.CollectTestRule
import org.odk.collect.android.support.rules.TestRuleChain

@RunWith(AndroidJUnit4::class)
class EntityFormTest {

    private val rule = CollectTestRule(useDemoProject = false)
    private val testDependencies = TestDependencies()

    @get:Rule
    val ruleChain: RuleChain = TestRuleChain.chain(testDependencies)
        .around(rule)

    @Test
    fun fillingEntityRegistrationForm_beforeCreatingEntityList_doesNotCreateEntityForFollowUpForms() {
        testDependencies.server.addForm("one-question-entity-registration.xml")
        testDependencies.server.addForm(
            "one-question-entity-update.xml",
            listOf(MediaFileItem("people.csv"))
        )

        rule.withMatchExactlyProject(testDependencies.server.url)
            .enableLocalEntitiesInForms()

            .startBlankForm("One Question Entity Registration")
            .fillOutAndFinalize(FormEntryPage.QuestionAndAnswer("Name", "Logan Roy"))

            .startBlankForm("One Question Entity Update")
            .assertQuestion("Select person")
            .assertText("Roman Roy")
            .assertTextDoesNotExist("Logan Roy")
    }

    @Test
    fun fillingEntityRegistrationForm_createsEntityForFollowUpForms() {
        testDependencies.server.addForm("one-question-entity-registration.xml")
        testDependencies.server.addForm(
            "one-question-entity-update.xml",
            listOf(EntityListItem("people.csv"))
        )

        rule.withMatchExactlyProject(testDependencies.server.url)
            .enableLocalEntitiesInForms()

            .startBlankForm("One Question Entity Registration")
            .fillOutAndFinalize(FormEntryPage.QuestionAndAnswer("Name", "Logan Roy"))

            .startBlankForm("One Question Entity Update")
            .assertQuestion("Select person")
            .assertText("Roman Roy")
            .assertText("Logan Roy")
    }

    @Test
    fun fillingEntityRegistrationForm_createsEntityForFollowUpFormsWithCachedFormDefs() {
        testDependencies.server.addForm("one-question-entity-registration.xml")
        testDependencies.server.addForm(
            "one-question-entity-update.xml",
            listOf(EntityListItem("people.csv"))
        )

        rule.withMatchExactlyProject(testDependencies.server.url)
            .enableLocalEntitiesInForms()

            .startBlankForm("One Question Entity Update") // Open to create cached form def
            .pressBackAndDiscardForm()

            .startBlankForm("One Question Entity Registration")
            .fillOutAndFinalize(FormEntryPage.QuestionAndAnswer("Name", "Logan Roy"))

            .startBlankForm("One Question Entity Update")
            .assertQuestion("Select person")
            .assertText("Roman Roy")
            .assertText("Logan Roy")
    }

    @Test
    fun fillingEntityUpdateForm_updatesEntityForFollowUpForms() {
        testDependencies.server.addForm(
            "one-question-entity-update.xml",
            listOf(EntityListItem("people.csv"))
        )

        rule.withMatchExactlyProject(testDependencies.server.url)
            .enableLocalEntitiesInForms()

            .startBlankForm("One Question Entity Update")
            .assertQuestion("Select person")
            .clickOnText("Roman Roy")
            .swipeToNextQuestion("Name")
            .answerQuestion("Name", "Romulus Roy")
            .swipeToEndScreen()
            .clickFinalize()

            .startBlankForm("One Question Entity Update")
            .assertText("Romulus Roy")
            .assertTextDoesNotExist("Roman Roy")
    }

    @Test
    fun entityListSecondaryInstancesAreConsistentBetweenFollowUpForms() {
        testDependencies.server.apply {
            addForm(
                "one-question-entity-update.xml",
                listOf(EntityListItem("people.csv"))
            )

            addForm(
                "one-question-entity-follow-up.xml",
                listOf(EntityListItem("people.csv", "updated-people.csv"))
            )
        }

        rule.withProject(testDependencies.server)
            .enableLocalEntitiesInForms()

            .clickGetBlankForm()
            .clickClearAll()
            .clickForm("One Question Entity Update")
            .clickGetSelected()
            .clickOK(MainMenuPage())
            .startBlankForm("One Question Entity Update")
            .assertText("Roman Roy")
            .pressBackAndDiscardForm()

            .clickGetBlankForm()
            .clickGetSelected() // Collect automatically only selects the un-downloaded forms
            .clickOK(MainMenuPage())
            .startBlankForm("One Question Entity Update")
            .assertText("Ro-Ro Roy")
            .assertTextDoesNotExist("Roman Roy")
    }
}
