package edu.school21.controllers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class UserInput {
    private final Scanner SCANNER = new Scanner(System.in);
    private JPanel jPanel;
    private JTextField textField;
    private boolean MODE = true; // true - console, false - GUI
    private String INPUT;
    private volatile boolean enter;
    private SpringLayout layout;

    private final Action ACTION = new AbstractAction()
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            INPUT = textField.getText();
//            System.out.println(INPUT);
            enter = true;
        }
    };

    public UserInput(JPanel jPanel, SpringLayout layout) {
        this.jPanel = jPanel;
        this.layout = layout;
    }

    public UserInput() {
    }

    public String getInput() {
        if (MODE) {
            INPUT = SCANNER.nextLine();
            System.out.println("You chose: [" + INPUT + "]");
        } else {
            enter = false;
            textField = new JTextField(15);
            textField.setBackground(new Color(0x1D265D));
            textField.setBounds(80, 350, 200, 40);
            textField.setForeground(new Color(0xFFFFFF));
            textField.setFont(new Font("Calibri", Font.BOLD, 17));
            textField.addActionListener(ACTION);
            jPanel.add(textField);
            layout.putConstraint(SpringLayout.NORTH, textField, 350, SpringLayout.NORTH, jPanel);
            layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, textField, 0, SpringLayout.HORIZONTAL_CENTER, jPanel);
            jPanel.revalidate();
            while (!enter) {
                Thread.onSpinWait();
            }
            jPanel.remove(textField);
        }
        return INPUT;
    }

    public void changeMode(boolean mode) {
        MODE = mode;
    }

    public void close() {
        SCANNER.close();
    }

    public JTextField getTextField() {
        return textField;
    }

    public boolean isMODE() {
        return MODE;
    }

    public void setMODE(boolean MODE) {
        this.MODE = MODE;
    }

    public void setSwing(JPanel jPanel, SpringLayout layout) {
        this.jPanel = jPanel;
        this.layout = layout;
    }

    public JPanel getjPanel() {
        return jPanel;
    }
}
