package com.excelutility.ui;

import static com.excelutility.util.MessageResource.message;
import static com.excelutility.util.ResourceKeys.*;
import static com.excelutility.util.ResourceKeys.SHEET_NAME_LABEL;
import static com.excelutility.util.ResourceKeys.STEP_VALUE_LABEL;

public final class ApplicationInputs {
    public static final String[] input_labels = {message(FILE_PATH_DESC), message(ROW_COUNT_DESC), message(START_VALUE_DESC), message(STEP_VALUE_DESC), message(CONSTANT_STRING_LENGTH_DESC), message(SHEET_NAME_DESC)};
    public static final String[] input_default = {message(FILE_PATH_DEFAULT), message(ROW_COUNT_DEFAULT), message(START_VALUE_DEFAULT), message(STEP_VALUE_DEFAULT), message(CONSTANT_STRING_LENGTH_DEFAULT), message(SHEET_NAME_DEFAULT)};
    public static final String[] input_tip = {message(FILE_PATH_LABEL), message(ROW_COUNT_LABEL), message(START_VALUE_LABEL), message(STEP_VALUE_LABEL), message(CONSTANT_STRING_LENGTH_LABEL), message(SHEET_NAME_LABEL)};
    public static final char[] input_mnemonics = {'F', 'R', 'B', 'D', 'C', 'S'};
    public static final int[] input_widths = {25, 15, 15, 15, 15, 25};
}
