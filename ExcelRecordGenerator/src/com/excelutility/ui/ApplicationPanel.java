package com.excelutility.ui;

import com.excelutility.util.MessageResource;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class ApplicationPanel extends JPanel {
    private JTextField[] fields;

    // Create a form with the specified input_labels, tooltips, and sizes.
    public ApplicationPanel() {
        super(new BorderLayout());
        addAppImage();
        addApplicationInputs();
    }

    private void addAppImage() {
        ImageIcon icon = createImageIcon(MessageResource.class.getResource("/resources/app.png"), "");
        JPanel iconPanel = new JPanel(new GridLayout(1, 1));
        iconPanel.add(new JLabel("", icon, JLabel.CENTER));
        add(iconPanel, BorderLayout.NORTH);
    }

    protected ImageIcon createImageIcon(URL imgURL,
                                        String description) {
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("Couldn't find file: " + imgURL);
            return null;
        }
    }

    private void addApplicationInputs() {
        JPanel labelPanel = new JPanel(new GridLayout(ApplicationInputs.input_labels.length, 1));
        JPanel fieldPanel = new JPanel(new GridLayout(ApplicationInputs.input_labels.length, 1));
        add(labelPanel, BorderLayout.WEST);
        add(fieldPanel, BorderLayout.CENTER);

        fields = new JTextField[ApplicationInputs.input_labels.length];
        for (int i = 0; i < ApplicationInputs.input_labels.length; i += 1) {
            fields[i] = new JTextField();
            fields[i].setText(ApplicationInputs.input_default[i]);
            if (i < ApplicationInputs.input_tip.length)
                fields[i].setToolTipText(ApplicationInputs.input_tip[i]);
            if (i < ApplicationInputs.input_widths.length)
                fields[i].setColumns(ApplicationInputs.input_widths[i]);

            JLabel lab = new JLabel(ApplicationInputs.input_labels[i], JLabel.RIGHT);
            lab.setLabelFor(fields[i]);
            if (i < ApplicationInputs.input_mnemonics.length)
                lab.setDisplayedMnemonic(ApplicationInputs.input_mnemonics[i]);

            labelPanel.add(lab);
            JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));
            p.add(fields[i]);


            fieldPanel.add(p);
        }
    }

    public String getText(int i) {
        return (fields[i].getText());
    }
}
