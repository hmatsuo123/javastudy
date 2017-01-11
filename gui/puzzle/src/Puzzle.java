package puzzle;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class Puzzle  extends JFrame {
	final private int panelHeight = 300;
	final private int panelWidth = 300;

	private Puzzle puzzleFrame;
	private DigitalClockPanel digitalClockPanel;
	private SlidePuzzlePanel slidePuzzlePanel;
	private RotatePuzzlePanel rotatePuzzlePanel;
	private JPanel mainPanel;
	private DigitalClockPreferences prefs;
	private PuzzleMode puzzleMode;
	private PuzzlePanelCount puzzlePanelCount;

	private enum PuzzleMode {
		Slide,
		Rotate
	}

	private enum PuzzlePanelCount {
		Panel_3X3,
		Panel_4X4
	}

	public Puzzle() {
		super("Puzzle Game");
		this.puzzleFrame = this;
		puzzleMode = PuzzleMode.Slide;
		puzzlePanelCount = PuzzlePanelCount.Panel_3X3;

		JMenuBar menubar = new JMenuBar();
		JMenu puzzleMenu = new JMenu("Puzzle");
		menubar.add(puzzleMenu);
		JMenu modeMenu = new JMenu("Mode");
		puzzleMenu.add(modeMenu);
		JMenuItem slidePazzleMenuItem = new JMenuItem("Slide Pazzle");
		slidePazzleMenuItem.addActionListener((e) -> {
			if (puzzleMode != PuzzleMode.Slide) {
				mainPanel.remove(rotatePuzzlePanel);
				if (puzzlePanelCount == PuzzlePanelCount.Panel_3X3)
					slidePuzzlePanel = new SlidePuzzlePanel(panelWidth, panelHeight, 3, 3, digitalClockPanel);
				else if (puzzlePanelCount == PuzzlePanelCount.Panel_4X4)
					slidePuzzlePanel = new SlidePuzzlePanel(panelWidth, panelHeight, 4, 4, digitalClockPanel);
				mainPanel.add(slidePuzzlePanel, BorderLayout.EAST);
				puzzleMode = PuzzleMode.Slide;
				digitalClockPanel.digitalClockAnimation.message2 = "Slide Pazzle Game";
				repaint();
				pack();
			}
		});
		modeMenu.add(slidePazzleMenuItem);
		JMenuItem rotatePazzleMenuItem = new JMenuItem("Rotate Pazzle");
		rotatePazzleMenuItem.addActionListener((e) -> {
			if (puzzleMode != PuzzleMode.Rotate) {
				mainPanel.remove(slidePuzzlePanel);
				if (puzzlePanelCount == PuzzlePanelCount.Panel_3X3)
					rotatePuzzlePanel = new RotatePuzzlePanel(panelWidth, panelHeight, 3, 3, digitalClockPanel);
				else if (puzzlePanelCount == PuzzlePanelCount.Panel_4X4)
					rotatePuzzlePanel = new RotatePuzzlePanel(panelWidth, panelHeight, 4, 4, digitalClockPanel);
				mainPanel.add(rotatePuzzlePanel, BorderLayout.EAST);
				puzzleMode = PuzzleMode.Rotate;
				digitalClockPanel.digitalClockAnimation.message2 = "Rotate Pazzle Game";
				repaint();
				pack();
			}
		});
		modeMenu.add(rotatePazzleMenuItem);

		JMenu panelMenu = new JMenu("Panel");
		puzzleMenu.add(panelMenu);
		JMenuItem puzzle3x3MenuItem = new JMenuItem("3 X 3");
		puzzle3x3MenuItem.addActionListener((e) -> {
			if (puzzlePanelCount != PuzzlePanelCount.Panel_3X3) {
				if (puzzleMode == PuzzleMode.Slide) {
					mainPanel.remove(slidePuzzlePanel);
					slidePuzzlePanel = new SlidePuzzlePanel(panelWidth, panelHeight, 3, 3, digitalClockPanel);
					mainPanel.add(slidePuzzlePanel, BorderLayout.EAST);
				} else if (puzzleMode == PuzzleMode.Rotate) {
					mainPanel.remove(rotatePuzzlePanel);
					rotatePuzzlePanel = new RotatePuzzlePanel(panelWidth, panelHeight, 3, 3, digitalClockPanel);
					mainPanel.add(rotatePuzzlePanel, BorderLayout.EAST);
				}
				puzzlePanelCount = PuzzlePanelCount.Panel_3X3;
				repaint();
				pack();
			}
		});
		panelMenu.add(puzzle3x3MenuItem);
		JMenuItem puzzle4x4MenuItem = new JMenuItem("4 X 4");
		puzzle4x4MenuItem.addActionListener((e) -> {
			if (puzzlePanelCount != PuzzlePanelCount.Panel_4X4) {
				if (puzzleMode == PuzzleMode.Slide) {
					mainPanel.remove(slidePuzzlePanel);
					slidePuzzlePanel = new SlidePuzzlePanel(panelWidth, panelHeight, 4, 4, digitalClockPanel);
					mainPanel.add(slidePuzzlePanel, BorderLayout.EAST);
				} else if (puzzleMode == PuzzleMode.Rotate) {
					mainPanel.remove(rotatePuzzlePanel);
					rotatePuzzlePanel = new RotatePuzzlePanel(panelWidth, panelHeight, 4, 4, digitalClockPanel);
					mainPanel.add(rotatePuzzlePanel, BorderLayout.EAST);
				}
				puzzlePanelCount = PuzzlePanelCount.Panel_4X4;
				repaint();
				pack();
			}
		});
		panelMenu.add(puzzle4x4MenuItem);

		JMenu clockMenu = new JMenu("Clock");
		menubar.add(clockMenu);
		JMenuItem propertyMenuItem = new JMenuItem("Property");
		propertyMenuItem.addActionListener((e) -> {
			new ClockPropertiesDialog(puzzleFrame, digitalClockPanel);
		});
		clockMenu.add(propertyMenuItem);
		JMenuItem animationMenuItem = new JMenuItem("Animation");
		animationMenuItem.addActionListener((e) -> {
			new AnimationSettingsDialog(puzzleFrame, digitalClockPanel);
		});
		clockMenu.add(animationMenuItem);

		JMenu helpMenu = new JMenu("Help");
		menubar.add(helpMenu);
		JMenu hintMenu = new JMenu("Hint");
		helpMenu.add(hintMenu);
		JMenuItem hintOnMenuItem = new JMenuItem("ON");
		hintOnMenuItem.addActionListener((e) -> {
			slidePuzzlePanel.isHintShow = true;
			rotatePuzzlePanel.isHintShow = true;
		});
		hintMenu.add(hintOnMenuItem);
		JMenuItem hintOffMenuItem = new JMenuItem("OFF");
		hintOffMenuItem.addActionListener((e) -> {
			slidePuzzlePanel.isHintShow = false;
			rotatePuzzlePanel.isHintShow = false;
		});
		hintMenu.add(hintOffMenuItem);
		setJMenuBar(menubar);

		mainPanel = new JPanel(new BorderLayout());
		digitalClockPanel = DigitalClockPanel.newInstance(this, panelWidth, panelHeight);
		mainPanel.add(digitalClockPanel, BorderLayout.WEST);
		if (puzzleMode == PuzzleMode.Slide) {
			slidePuzzlePanel = new SlidePuzzlePanel(panelWidth, panelHeight, 3, 3, digitalClockPanel);
			mainPanel.add(slidePuzzlePanel, BorderLayout.EAST);
		} else if (puzzleMode == PuzzleMode.Rotate) {
			rotatePuzzlePanel  = new RotatePuzzlePanel(panelWidth, panelHeight, 3, 3, digitalClockPanel);
			mainPanel.add(rotatePuzzlePanel, BorderLayout.EAST);
		}

		JPanel borderPanel = new JPanel(){
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setColor(Color.WHITE);
				g.drawRect(0, 0, 10, panelHeight);
			};

		};
		borderPanel.setSize(10, panelHeight);
		borderPanel.setVisible(true);
		mainPanel.add(borderPanel, BorderLayout.CENTER);
		add(mainPanel);
		pack();
		setResizable(false);
		setLocationRelativeTo(null);
		prefs = new DigitalClockPreferences();
		addWindowListener(new ApplicationCloseAdapter());

		Insets insets = this.getInsets();
		Dimension size1 = menubar.getPreferredSize();
		setSize((panelWidth + insets.right + insets.left) * 2 + 10, panelHeight + insets.bottom + insets.top + size1.height);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	private class ApplicationCloseAdapter extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e) {
        	prefs.save(digitalClockPanel.clockFont.getName(), digitalClockPanel.clockFontColor);
            System.exit(0);
        }
    }

	public static void main(String[] args) {
		new Puzzle();
	}
}
