package com.dubreuia.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.dubreuia.integration.ActionTestFile.Reformat_KO_Import_KO;
import static com.dubreuia.integration.ActionTestFile.Reformat_KO_Import_OK;
import static com.dubreuia.integration.ActionTestFile.Reformat_KO_Rearrange_KO;
import static com.dubreuia.integration.ActionTestFile.Reformat_KO_Rearrange_OK;
import static com.dubreuia.integration.ActionTestFile.Reformat_OK_Import_KO;
import static com.dubreuia.integration.ActionTestFile.Reformat_OK_Import_OK;
import static com.dubreuia.integration.ActionTestFile.Reformat_OK_Rearrange_OK;
import static com.dubreuia.model.Action.activate;
import static com.dubreuia.model.Action.activateOnBatch;
import static com.dubreuia.model.Action.activateOnShortcut;
import static com.dubreuia.model.Action.organizeImports;
import static com.dubreuia.model.Action.rearrange;
import static com.dubreuia.model.Action.reformat;
import static com.dubreuia.model.Action.useGlobalConfiguration;

class GlobalIntegrationTest extends IntegrationTest {

    @BeforeEach
    void useGlobalConfiguration() {
        projectStorage.setEnabled(useGlobalConfiguration, true);
    }

    @Test
    void should_reformat_without_activation_produces_same_file() {
        globalStorage.setEnabled(reformat, true);
        assertSaveAction(Reformat_KO_Import_KO, Reformat_KO_Import_KO);
    }

    @Test
    void should_reformat_with_activation_produces_indented_file() {
        globalStorage.setEnabled(activate, true);
        globalStorage.setEnabled(reformat, true);
        assertSaveAction(Reformat_KO_Import_KO, Reformat_OK_Import_KO);
    }

    @Test
    void should_reformat_with_shortcut_produces_same_file() {
        globalStorage.setEnabled(activateOnShortcut, true);
        globalStorage.setEnabled(reformat, true);
        assertSaveAction(Reformat_KO_Import_KO, Reformat_KO_Import_KO);
    }

    @Test
    void should_reformat_with_shortcut_produces_indented_file_on_shortcut() {
        globalStorage.setEnabled(activateOnShortcut, true);
        globalStorage.setEnabled(reformat, true);
        assertSaveActionShortcut(Reformat_KO_Import_KO, Reformat_OK_Import_KO);
    }

    @Test
    void should_reformat_as_batch_produces_indented_file() {
        globalStorage.setEnabled(activateOnBatch, true);
        globalStorage.setEnabled(reformat, true);
        assertSaveActionBatch(Reformat_KO_Import_KO, Reformat_OK_Import_KO);
    }

    @Test
    void should_reformat_as_batch_on_shortcut_produces_same_file() {
        globalStorage.setEnabled(activateOnShortcut, true);
        globalStorage.setEnabled(reformat, true);
        assertSaveActionBatch(Reformat_KO_Import_KO, Reformat_KO_Import_KO);
    }

    @Test
    void should_import_without_activation_produces_same_file() {
        globalStorage.setEnabled(organizeImports, true);
        assertSaveAction(Reformat_KO_Import_KO, Reformat_KO_Import_KO);
    }

    @Test
    void should_import_with_activation_produces_cleaned_import_file() {
        globalStorage.setEnabled(activate, true);
        globalStorage.setEnabled(organizeImports, true);
        assertSaveAction(Reformat_KO_Import_KO, Reformat_KO_Import_OK);
    }

    @Test
    void should_import_and_format_with_activation_produces_cleaned_import_and_formated_file() {
        globalStorage.setEnabled(activate, true);
        globalStorage.setEnabled(organizeImports, true);
        globalStorage.setEnabled(reformat, true);
        assertSaveAction(Reformat_KO_Import_KO, Reformat_OK_Import_OK);
    }

    @Test
    void should_rearrange_without_activation_produces_same_file() {
        globalStorage.setEnabled(rearrange, true);
        assertSaveAction(Reformat_KO_Import_KO, Reformat_KO_Import_KO);
    }

    @Test
    void should_rearrange_with_activation_produces_ordered_file() {
        globalStorage.setEnabled(activate, true);
        globalStorage.setEnabled(rearrange, true);
        assertSaveAction(Reformat_KO_Rearrange_KO, Reformat_KO_Rearrange_OK);
    }

    @Test
    void should_rearrange_and_format_with_activation_produces_ordered_file_and_formated_file() {
        globalStorage.setEnabled(activate, true);
        globalStorage.setEnabled(reformat, true);
        globalStorage.setEnabled(rearrange, true);
        assertSaveAction(Reformat_KO_Rearrange_KO, Reformat_OK_Rearrange_OK);
    }

}
