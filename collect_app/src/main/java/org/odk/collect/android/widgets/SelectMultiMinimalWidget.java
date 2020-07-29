package org.odk.collect.android.widgets;

import android.content.Context;

import org.javarosa.core.model.data.IAnswerData;
import org.javarosa.core.model.data.SelectMultiData;
import org.javarosa.core.model.data.helper.Selection;
import org.odk.collect.android.R;
import org.odk.collect.android.activities.FormEntryActivity;
import org.odk.collect.android.formentry.questions.QuestionDetails;
import org.odk.collect.android.fragments.dialogs.SelectMinimalDialog;
import org.odk.collect.android.fragments.dialogs.SelectMultiMinimalDialog;
import org.odk.collect.android.utilities.StringUtils;
import org.odk.collect.android.utilities.WidgetAppearanceUtils;

import java.util.ArrayList;
import java.util.List;

import static org.odk.collect.android.formentry.media.FormMediaUtils.getPlayColor;

public class SelectMultiMinimalWidget extends SelectMinimalWidget {
    private List<Selection> selectedItems;

    public SelectMultiMinimalWidget(Context context, QuestionDetails prompt) {
        super(context, prompt);
        selectedItems = getFormEntryPrompt().getAnswerValue() == null
                ? new ArrayList<>() :
                (List<Selection>) getFormEntryPrompt().getAnswerValue().getValue();
        updateAnswerLabel();
    }

    @Override
    protected void showDialog() {
        SelectMultiMinimalDialog dialog = new SelectMultiMinimalDialog(items, getSavedSelectedItems(), getFormEntryPrompt(), getReferenceManager(), getAudioHelper(), getPlayColor(getFormEntryPrompt(), themeUtils), getContext(), WidgetAppearanceUtils.isFlexAppearance(getFormEntryPrompt()), WidgetAppearanceUtils.isAutocomplete(getFormEntryPrompt()));
        dialog.show(((FormEntryActivity) getContext()).getSupportFragmentManager(), SelectMinimalDialog.class.getName());
    }

    @Override
    public IAnswerData getAnswer() {
        return selectedItems.isEmpty()
                ? null
                : new SelectMultiData(selectedItems);
    }

    @Override
    public void clearAnswer() {
        selectedItems.clear();
        super.clearAnswer();
    }

    @Override
    public void setBinaryData(Object answer) {
        selectedItems = (List<Selection>) answer;
        updateAnswerLabel();
        widgetValueChanged();
    }

    @Override
    public void setChoiceSelected(int choiceIndex, boolean isSelected) {
        if (isSelected) {
            selectedItems.add(items.get(choiceIndex).selection());
        } else {
            selectedItems.remove(items.get(choiceIndex).selection());
        }
    }

    private void updateAnswerLabel() {
        if (selectedItems.isEmpty()) {
            binding.choicesSearchBox.setText(R.string.select_answer);
        } else {
            StringBuilder builder = new StringBuilder();
            for (Selection selectedItem : selectedItems) {
                builder.append(StringUtils.textToHtml(getFormEntryPrompt().getSelectItemText(selectedItem)));
                if (selectedItems.size() - 1 > selectedItems.indexOf(selectedItem)) {
                    builder.append(", ");
                }
            }
            binding.choicesSearchBox.setText(builder.toString());
        }
    }

    private List<Selection> getSavedSelectedItems() {
        return selectedItems;
    }
}
