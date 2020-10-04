package org.sloubi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frame extends JFrame implements ActionListener {

    private JSpinner widthSpinner;
    private JSpinner heightSpinner;
    private final JButton aboutButton = new JButton("?");
    private final JButton newButton = new JButton("Générer");
    public final int w = 8;
    public final int h = 8;
    private JPanel mainPanel;
    private final Maze maze = new Maze(w, h);
    private final MazePanel mazePanel = new MazePanel(maze, 40, 40);

    public Frame() {
        initComponents();

        setTitle("jMaze");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();
        setVisible(true);
    }

    private void initComponents() {
        maze.gen();

        JPanel mazePanelContainer = new JPanel();
        mazePanelContainer.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        mazePanelContainer.add(mazePanel);

        mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(mazePanelContainer, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(0, 15, 15, 15));

        bottomPanel.add(new JLabel("Largeur :"));
        widthSpinner = new JSpinner(new SpinnerNumberModel(8, 1, 100, 1));
        ((JSpinner.DefaultEditor) widthSpinner.getEditor()).getTextField().setEditable(false);
        bottomPanel.add(widthSpinner);

        bottomPanel.add(new JLabel("Hauteur :"));
        heightSpinner = new JSpinner(new SpinnerNumberModel(8, 1, 100, 1));
        ((JSpinner.DefaultEditor) heightSpinner.getEditor()).getTextField().setEditable(false);
        bottomPanel.add(heightSpinner);

        bottomPanel.add(newButton);
        bottomPanel.add(aboutButton);

        mainPanel.add(bottomPanel, BorderLayout.PAGE_END);

        newButton.addActionListener(this);
        aboutButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(newButton)) {
            maze.setWidth((int)widthSpinner.getValue());
            maze.setHeight((int)heightSpinner.getValue());
            maze.gen();
            mazePanel.refresh();
            pack();
        }
        else if (e.getSource().equals(aboutButton)) {
            AboutDialog ad = new AboutDialog();
            ad.setVisible(true);
        }
    }
}
