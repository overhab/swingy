package edu.school21.gui;

import edu.school21.app.StaticVariables;
import edu.school21.models.Hero;
import java.util.List;
import javax.swing.*;
import java.awt.*;

public class Swingy {

    private final JFrame jFrame = new JFrame("Swingy");
    private final JButton button = new JButton("BUTTON");
    private final JPanel jPanel = new JPanel();
    private JLabel mainLabel = new JLabel("");
    private final JLabel moveLabel = new JLabel("");
    private final JLabel heroLabel = new JLabel("");
    private final SpringLayout layout = new SpringLayout();
    private final JLabel wrongInput = new JLabel("");
    private final JLabel expBar = new JLabel("");
    private final JLabel levelUp = new JLabel("");

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
        jPanel.setLayout(layout);
        jPanel.setBackground(new Color(0.1f, 0.0f, 0.2f, 1.0f));
        jFrame.add(jPanel);

        layout.putConstraint(SpringLayout.NORTH, mainLabel, 50, SpringLayout.NORTH, jPanel);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, mainLabel, 0, SpringLayout.HORIZONTAL_CENTER, jPanel);

        layout.putConstraint(SpringLayout.NORTH, moveLabel, 320, SpringLayout.NORTH, jPanel);
        layout.putConstraint(SpringLayout.WEST, moveLabel, 80, SpringLayout.WEST, jPanel);

        jFrame.setVisible(true);
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
            jLabel.setBounds(100, 150 + (i * 30), 200, 20);
            jLabel.setForeground(new Color(0xFFFFFF));
            jLabel.setFont(new Font("Calibri", Font.BOLD, 18));
            jPanel.add(jLabel);
            layout.putConstraint(SpringLayout.NORTH, jLabel, 160 + (i * 30), SpringLayout.NORTH, jPanel);
            layout.putConstraint(SpringLayout.WEST, jLabel, 100, SpringLayout.WEST, jPanel);
        }

        jPanel.revalidate();

    }

    public void displayHeroes(List<Hero> heroes) {

        Hero hero;

        for (int i = 0; i < heroes.size(); i++) {
            hero = heroes.get(i);
            JLabel selectHero = new JLabel(hero.getName());
            selectHero.setBounds(100, 100 + (i * 20), 60, 20);
            selectHero.setForeground(new Color(0x04AB65));
            selectHero.setFont(new Font("Calibri", Font.BOLD, 18));
            jPanel.add(selectHero);

            JLabel levelLabel = new JLabel("LvL: " + hero.getLevel());
            levelLabel.setBounds(250, 100 + (i * 20), 20, 20);
            levelLabel.setForeground(new Color(0x04AB65));
            levelLabel.setFont(new Font("Calibri", Font.BOLD, 18));
            jPanel.add(levelLabel);

            layout.putConstraint(SpringLayout.NORTH, selectHero, 120 + (i * 20), SpringLayout.NORTH, jPanel);
            layout.putConstraint(SpringLayout.WEST, selectHero, 100, SpringLayout.WEST, jPanel);

            layout.putConstraint(SpringLayout.NORTH, levelLabel, 120 + (i * 20), SpringLayout.NORTH, jPanel);
            layout.putConstraint(SpringLayout.WEST, levelLabel, 250, SpringLayout.WEST, jPanel);
        }

        jPanel.revalidate();
    }

    public void displayMain(String text) {
        mainLabel.setText(text);
        mainLabel.setBounds(75, 50, 480, 25);
        mainLabel.setForeground(new Color(0xE1B81A));
        mainLabel.setFont(new Font("Calibri", Font.BOLD, 22));
    }

    public void setVisible(boolean b) {
        jFrame.setVisible(b);
    }

    public void refresh() {
        jPanel.removeAll();
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

    public void warning(String text) {
        heroLabel.setText(text);
        heroLabel.setBounds(80, 60, 200, 15);
        heroLabel.setForeground(new Color(0xB41E30));
        heroLabel.setFont(new Font("Calibri", Font.BOLD, 15));

        layout.putConstraint(SpringLayout.NORTH, heroLabel, 10, SpringLayout.SOUTH, mainLabel);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, heroLabel, 0, SpringLayout.HORIZONTAL_CENTER, jPanel);

        jPanel.revalidate();
    }

    public void setHero(String heroName, int level, int nextLevel, int exp, int hp) {
        levelUp.setText("");

        heroLabel.setText(heroName + "    LvL: " + level + "    HP: " + hp);
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
