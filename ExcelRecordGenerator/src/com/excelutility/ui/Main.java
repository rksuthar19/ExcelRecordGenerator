package com.excelutility.ui;

import com.excelutility.util.MessageResource;
import com.excelutility.writer.RecordWriter;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import static com.excelutility.util.MessageResource.message;
import static com.excelutility.util.ResourceKeys.*;
import static com.excelutility.util.ResourceKeys.HEADING_LABEL;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.PLAIN_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;

public class Main {
    public static void main(String[] args) {

        final JButton submit_btn = new JButton(message(GENERATE_RECORDS_LABEL));

        //adding application panel on j-frame
        final ApplicationPanel component = new ApplicationPanel();
        JFrame f = new JFrame(message(HEADING_LABEL));
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().add(component, BorderLayout.NORTH);
        JPanel p = new JPanel();
        p.add(submit_btn);
        f.getContentPane().add(p, BorderLayout.SOUTH);
        f.pack();
        f.setVisible(true);

        //setting up app-icon
        Toolkit kit = Toolkit.getDefaultToolkit();
        Image img = kit.createImage(MessageResource.class.getResource("/resources/icon.png"));
        f.setIconImage(img);

        specifyActionOnSubmit(component, submit_btn);
    }

    private static void specifyActionOnSubmit(final ApplicationPanel form, final JButton submit) {
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                submit.setEnabled(false);
                Integer noOfRows;
                Integer startValue;
                Integer stepValue;
                Integer constantStringLength;

                try {
                    noOfRows = Integer.valueOf(form.getText(1));
                } catch (NumberFormatException ex) {
                    showMessageDialog(new JFrame(), message(INVALID_ROWS_VALUE), message(ERROR_LOG_LABEL),
                            ERROR_MESSAGE);
                    submit.setEnabled(true);
                    return;
                }

                try {
                    startValue = Integer.valueOf(form.getText(2));
                } catch (NumberFormatException ex) {
                    showMessageDialog(new JFrame(), message(INVALID_STAT_VALUE), message(ERROR_LOG_LABEL),
                            ERROR_MESSAGE);
                    submit.setEnabled(true);
                    return;
                }

                try {
                    stepValue = Integer.valueOf(form.getText(3));
                } catch (NumberFormatException ex) {
                    showMessageDialog(new JFrame(), message(INVALID_STEP_VALUE), message(ERROR_LOG_LABEL),
                            ERROR_MESSAGE);
                    submit.setEnabled(true);
                    return;
                }

                try {
                    constantStringLength = Integer.valueOf(form.getText(4));
                } catch (NumberFormatException ex) {
                    showMessageDialog(new JFrame(), message(INVALID_CONSTANT_STRING_LENGTH), message(ERROR_LOG_LABEL),
                            ERROR_MESSAGE);
                    submit.setEnabled(true);
                    return;
                }

                File parentDirectory = new File(form.getText(0));
                File parentFile = parentDirectory.getParentFile();
                if (!parentFile.exists()) {
                    boolean created = parentFile.mkdir();
                    if (!created) {
                        showMessageDialog(new JFrame(), message(UNABLE_TO_CREATE_DIRECTORY_MESSAGE, parentFile.getName()), message(ERROR_LOG_LABEL), ERROR_MESSAGE);
                        return;
                    }

                }

                //workbook creation starts here
                XSSFWorkbook wb = new XSSFWorkbook();
                RecordWriter recordWriter = new RecordWriter(new SXSSFWorkbook(wb, 2, false), form.getText(4), parentDirectory);
                recordWriter.addSequentialNumbersWithConstantString(startValue, stepValue, noOfRows, constantStringLength);
                try {
                    showMessageDialog(new JFrame(), message(WRITING_STARTED_MESSAGE), message(LOGS_LABEL), PLAIN_MESSAGE);
                    recordWriter.write();
                    showMessageDialog(new JFrame(), message(WRITING_DONE_MESSAGE), message(LOGS_LABEL), PLAIN_MESSAGE);
                } catch (IOException e1) {
                    showMessageDialog(new JFrame(), e1.getMessage(), message(ERROR_LOG_LABEL), ERROR_MESSAGE);
                }
                submit.setEnabled(true);

            }
        });
    }
}
