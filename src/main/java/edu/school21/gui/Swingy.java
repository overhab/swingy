package edu.school21.gui;

import edu.school21.app.StaticVariables;
import edu.school21.models.Artifact;
import edu.school21.models.Hero;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.*;

public class Swingy {

    private final JFrame jFrame = new JFrame("Swingy");
    private final JPanel jPanel = new JPanel();
    private JLabel mainLabel = new JLabel("");
    private final JLabel moveLabel = new JLabel("");
    private final JLabel heroLabel = new JLabel("");
    private final SpringLayout layout = new SpringLayout();
    private final JLabel wrongInput = new JLabel("");
    private final JLabel expBar = new JLabel("");
    private final JLabel levelUp = new JLabel("");
    private final JLabel items = new JLabel("");
    private final JLabel message = new JLabel("");
    private static final Color BACKGROUND_COLOR = new Color(0x070917);

    public Swingy() {
    }

    private void setup() {
        jFrame.setSize(600, 480);
        jPanel.setSize(600, 480);
        jPanel.add(mainLabel);
        jPanel.add(moveLabel);
        jPanel.add(heroLabel);
        jPanel.add(expBar);
        jPanel.add(levelUp);
        jPanel.add(items);
        jPanel.setLayout(layout);
        jPanel.add(message);
        jPanel.setBackground(BACKGROUND_COLOR);
        jFrame.add(jPanel);

        layout.putConstraint(SpringLayout.NORTH, mainLabel, 50, SpringLayout.NORTH, jPanel);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, mainLabel, 0, SpringLayout.HORIZONTAL_CENTER, jPanel);

        layout.putConstraint(SpringLayout.NORTH, moveLabel, 320, SpringLayout.NORTH, jPanel);
        layout.putConstraint(SpringLayout.WEST, moveLabel, 80, SpringLayout.WEST, jPanel);


        jFrame.setVisible(true);
        jFrame.setResizable(false);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void start() {
        setup();
    }

    public JPanel getjPanel() {
        return jPanel;
    }

    public void displayOptions(String text) {
        String[] labels = text.split("\n");

        for (int i = 0; i < labels.length; i++) {
            JLabel jLabel = new JLabel(labels[i]);
            jLabel.setBounds(100, 150 + (i * 30), 200, 22);
            jLabel.setForeground(new Color(0xFFFFFF));
            jLabel.setFont(new Font("Calibri", Font.BOLD, 22));
            jPanel.add(jLabel);
            layout.putConstraint(SpringLayout.NORTH, jLabel, 160 + (i * 30), SpringLayout.NORTH, jPanel);
            layout.putConstraint(SpringLayout.WEST, jLabel, 100, SpringLayout.WEST, jPanel);
        }

        jPanel.revalidate();

    }

    public void displayHeroes(List<Hero> heroes) {
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.setColumnCount(6);

        String[] colNames = {"Name", "Class", "LvL", "Attack", "Defence", "HP"};
        tableModel.setColumnIdentifiers(colNames);

        for (Hero hero : heroes) {
            tableModel.addRow(hero.toArray());
        }

        JTable table = new JTable(tableModel);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( SwingConstants.CENTER );
        table.setForeground(new Color(0x04AB65));
        table.setBackground(BACKGROUND_COLOR);
        table.setFont(new Font("Calibri", Font.BOLD, 18));
        table.setRowHeight(20);
        table.getColumn("LvL").setMaxWidth(45);
        table.getColumn("Attack").setMaxWidth(65);
        table.getColumn("Defence").setMaxWidth(75);
        table.getColumn("HP").setMaxWidth(40);
        table.getColumn("Name").setCellRenderer(centerRenderer);
        table.getColumn("Class").setCellRenderer(centerRenderer);
        table.getColumn("LvL").setCellRenderer(centerRenderer);
        table.getColumn("Attack").setCellRenderer(centerRenderer);
        table.getColumn("Defence").setCellRenderer(centerRenderer);
        table.getColumn("HP").setCellRenderer(centerRenderer);
        table.getTableHeader().setBackground(new Color(0x0E112D));
        table.getTableHeader().setForeground(new Color(0x04AB65));
        table.getTableHeader().setFont(new Font("Calibri", Font.BOLD, 20));

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(40, 130, 100, 100);
        scroll.setPreferredSize(new Dimension(500, 210));
        scroll.setBackground(BACKGROUND_COLOR);
        scroll.getViewport().setBackground(BACKGROUND_COLOR);
        scroll.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        scroll.getViewport().setBorder(null);
        scroll.setViewportBorder(null);
        scroll.setBorder(null);

        jPanel.add(scroll);

        layout.putConstraint(SpringLayout.NORTH, scroll, 130, SpringLayout.NORTH, jPanel);
        layout.putConstraint(SpringLayout.WEST, scroll, 40, SpringLayout.WEST, jPanel);

        jPanel.revalidate();
    }

    public void displayMain(String text) {
        mainLabel.setText(text);
        mainLabel.setBounds(75, 50, 480, 27);
        mainLabel.setForeground(new Color(0xE1B81A));
        mainLabel.setFont(new Font("Calibri", Font.BOLD, 27));
    }

    public void setVisible(boolean b) {
        jFrame.setVisible(b);
    }

    public void refresh() {
        jPanel.removeAll();
        jPanel.revalidate();
        jPanel.repaint();
        jPanel.add(mainLabel);
        layout.putConstraint(SpringLayout.NORTH, mainLabel, 50, SpringLayout.NORTH, jPanel);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, mainLabel, 0, SpringLayout.HORIZONTAL_CENTER, jPanel);

        jPanel.add(moveLabel);
        layout.putConstraint(SpringLayout.NORTH, moveLabel, 320, SpringLayout.NORTH, jPanel);
        layout.putConstraint(SpringLayout.WEST, moveLabel, 80, SpringLayout.WEST, jPanel);

        jPanel.add(heroLabel);
        layout.putConstraint(SpringLayout.NORTH, heroLabel, 10, SpringLayout.SOUTH, mainLabel);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, heroLabel, 0, SpringLayout.HORIZONTAL_CENTER, jPanel);

        jPanel.add(wrongInput);
        layout.putConstraint(SpringLayout.NORTH, wrongInput, 320, SpringLayout.NORTH, jPanel);
        layout.putConstraint(SpringLayout.WEST, wrongInput, 80, SpringLayout.WEST, jPanel);

        jPanel.add(expBar);
        layout.putConstraint(SpringLayout.SOUTH, expBar, 0, SpringLayout.SOUTH, jPanel);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, expBar, 0, SpringLayout.HORIZONTAL_CENTER, jPanel);

        jPanel.add(levelUp);
        layout.putConstraint(SpringLayout.SOUTH, levelUp, 5, SpringLayout.NORTH, expBar);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, levelUp, 0, SpringLayout.HORIZONTAL_CENTER, jPanel);

        jPanel.add(items);
        layout.putConstraint(SpringLayout.NORTH, items, 160, SpringLayout.NORTH, jPanel);
        layout.putConstraint(SpringLayout.WEST, items, 300, SpringLayout.WEST, jPanel);

        jPanel.add(message);
        message.setText("");
        layout.putConstraint(SpringLayout.NORTH, message, 50, SpringLayout.SOUTH, mainLabel);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, message, 0, SpringLayout.HORIZONTAL_CENTER, jPanel);

        jPanel.revalidate();
    }

    public JLabel getMainLabel() {
        return mainLabel;
    }

    public void setMainLabel(JLabel mainLabel) {
        this.mainLabel = mainLabel;
    }

    public void levelUp() {
        levelUp.setText("LEVEL UP! ! !");
        levelUp.setForeground(new Color(0xFFFFD500, true));
        levelUp.setFont(new Font("Calibri", Font.BOLD, 14));

        layout.putConstraint(SpringLayout.SOUTH, levelUp, 5, SpringLayout.NORTH, expBar);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, levelUp, 0, SpringLayout.HORIZONTAL_CENTER, jPanel);

        jPanel.revalidate();
    }

    public void message(String text) {
        message.setText(text);

        message.setBounds(80, 60, 200, 16);
        message.setForeground(new Color(0xEAC01A));
        message.setFont(new Font("Calibri", Font.BOLD, 16));

        layout.putConstraint(SpringLayout.NORTH, message, 50, SpringLayout.SOUTH, mainLabel);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, message, 0, SpringLayout.HORIZONTAL_CENTER, jPanel);

        jPanel.revalidate();
    }

    public void warning(String text) {
        heroLabel.setText(text);
        heroLabel.setBounds(80, 60, 200, 15);
        heroLabel.setForeground(new Color(0xB41E30));
        heroLabel.setFont(new Font("Calibri", Font.BOLD, 15));

        layout.putConstraint(SpringLayout.NORTH, heroLabel, 10, SpringLayout.SOUTH, mainLabel);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, heroLabel, 0, SpringLayout.HORIZONTAL_CENTER, jPanel);

        jPanel.revalidate();
    }

    public void showInventory(HashMap<String, Artifact> artifacts) {
        items.setText("Inventory:");
        items.setBounds(400, 160, 200, 22);
        items.setForeground(new Color(0xFFC908));
        items.setFont(new Font("Calibri", Font.BOLD, 22));

        int i = 35;
        for (String key : artifacts.keySet()) {
            JLabel item = new JLabel(key + ": " + artifacts.get(key).getName());
            item.setForeground(new Color(0xFFC908));
            item.setBounds(380, 160 + i, 200, 14);
            item.setFont(new Font("Calibri", Font.BOLD, 14));
            jPanel.add(item);
            layout.putConstraint(SpringLayout.NORTH, item, 160 + i, SpringLayout.NORTH, jPanel);
            layout.putConstraint(SpringLayout.WEST, item, 380, SpringLayout.WEST, jPanel);
            i += 25;
        }

        layout.putConstraint(SpringLayout.NORTH, items, 160, SpringLayout.NORTH, jPanel);
        layout.putConstraint(SpringLayout.WEST, items, 400, SpringLayout.WEST, jPanel);

        jPanel.revalidate();
    }

    public void setHero(String heroName, int level, int nextLevel, int exp, int hp, int attack, int defence) {
        levelUp.setText("");

        heroLabel.setText(heroName + "  LvL: " + level + "  HP: " + hp + "  Attack: " + attack + "  Defence: " + defence);
        heroLabel.setBounds(80, 60, 200, 15);
        heroLabel.setForeground(new Color(0xFFC908));
        heroLabel.setFont(new Font("Calibri", Font.BOLD, 14));

        layout.putConstraint(SpringLayout.NORTH, heroLabel, 10, SpringLayout.SOUTH, mainLabel);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, heroLabel, 0, SpringLayout.HORIZONTAL_CENTER, jPanel);

        expBar.setText("*EXPERIENCE [" + exp + "/" + nextLevel + "]*");
        expBar.setForeground(new Color(0xFFC908));
        expBar.setFont(new Font("Calibri", Font.BOLD, 14));

        layout.putConstraint(SpringLayout.SOUTH, expBar, 0, SpringLayout.SOUTH, jPanel);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, expBar, 0, SpringLayout.HORIZONTAL_CENTER, jPanel);

        jPanel.revalidate();
    }

    public void changeMain() {
        mainLabel.setForeground(new Color(0xFF0707));
        mainLabel.setFont(new Font("Calibri", Font.BOLD, 22));
    }

    public void displayMove(String text) {
        wrongInput.setText("");

        moveLabel.setText(text);
        moveLabel.setBounds(80, 420, 150, 20);
        moveLabel.setForeground(new Color(0xBB9501));
        moveLabel.setFont(new Font("Calibri", Font.PLAIN, 14));

        jPanel.revalidate();
    }

    public void wrongInput() {
        moveLabel.setText("");

        wrongInput.setText(StaticVariables.WRONG_INPUT);
        wrongInput.setBounds(80, 450, 150, 20);
        wrongInput.setForeground(new Color(0x8D0C0C));
        wrongInput.setFont(new Font("Calibri", Font.BOLD, 14));

        layout.putConstraint(SpringLayout.NORTH, wrongInput, 320, SpringLayout.NORTH, jPanel);
        layout.putConstraint(SpringLayout.WEST, wrongInput, 80, SpringLayout.WEST, jPanel);

        jPanel.revalidate();
    }

    public SpringLayout getLayout() {
        return layout;
    }

    public void exit() {
        jFrame.setVisible(false);
        jFrame.dispose();
    }
}
