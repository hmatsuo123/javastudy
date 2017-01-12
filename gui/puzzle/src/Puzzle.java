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
	private static final int PANEL_HEIGHT = 300;
	private static final int PANEL_WIDTH = 300;

	private Puzzle puzzleFrame;
	private DigitalClockPanel digitalClockPanel;
	private SlidePuzzlePanel slidePuzzlePanel;
	private RotatePuzzlePanel rotatePuzzlePanel;
	private JPanel mainPanel;
	private DigitalClockPreferences prefs;
	private PuzzleGameMode puzzleGameMode;
	private PuzzlePanelCount puzzlePanelCount;
	private int boardRowNum;
	private int boardColNum;

	private enum PuzzleGameMode {
		SLIDE,
		ROTATE
	}

	private enum PuzzlePanelCount {
		PANEL3X3,
		PANEL4X4
	}

	public Puzzle() {
		super("Digital Clock Puzzle Game");
		this.puzzleFrame = this;
		puzzleGameMode = PuzzleGameMode.SLIDE;
		puzzlePanelCount = PuzzlePanelCount.PANEL3X3;
		boardRowNum = 3;
		boardColNum = 3;
		prefs = new DigitalClockPreferences();

		JMenuBar menuBar = new JMenuBar();

		JMenu puzzleMenu = new JMenu("Puzzle");
		menuBar.add(puzzleMenu);
		JMenu modeMenu = new JMenu("Mode");
		puzzleMenu.add(modeMenu);
		JMenuItem slidePazzleMenuItem = new JMenuItem("Slide Pazzle");
		slidePazzleMenuItem.addActionListener((e) -> {
			if (puzzleGameMode != PuzzleGameMode.SLIDE) {
				mainPanel.remove(rotatePuzzlePanel);
				slidePuzzlePanel = new SlidePuzzlePanel(PANEL_WIDTH, PANEL_HEIGHT, boardColNum, boardRowNum, digitalClockPanel);
				mainPanel.add(slidePuzzlePanel, BorderLayout.EAST);
				puzzleGameMode = PuzzleGameMode.SLIDE;
				digitalClockPanel.digitalClockAnimation.message2 = "Slide Pazzle Game";
				repaint();
				pack();
			}
		});
		modeMenu.add(slidePazzleMenuItem);
		JMenuItem rotatePazzleMenuItem = new JMenuItem("Rotate Pazzle");
		rotatePazzleMenuItem.addActionListener((e) -> {
			if (puzzleGameMode != PuzzleGameMode.ROTATE) {
				mainPanel.remove(slidePuzzlePanel);
				rotatePuzzlePanel = new RotatePuzzlePanel(PANEL_WIDTH, PANEL_HEIGHT, boardColNum, boardRowNum, digitalClockPanel);
				mainPanel.add(rotatePuzzlePanel, BorderLayout.EAST);
				puzzleGameMode = PuzzleGameMode.ROTATE;
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
			if (puzzlePanelCount != PuzzlePanelCount.PANEL3X3) {
				puzzlePanelCount = PuzzlePanelCount.PANEL3X3;
				boardRowNum = 3;
				boardColNum = 3;
				if (puzzleGameMode == PuzzleGameMode.SLIDE) {
					mainPanel.remove(slidePuzzlePanel);
					slidePuzzlePanel = new SlidePuzzlePanel(PANEL_WIDTH, PANEL_HEIGHT, boardColNum, boardRowNum, digitalClockPanel);
					mainPanel.add(slidePuzzlePanel, BorderLayout.EAST);
				} else if (puzzleGameMode == PuzzleGameMode.ROTATE) {
					mainPanel.remove(rotatePuzzlePanel);
					rotatePuzzlePanel = new RotatePuzzlePanel(PANEL_WIDTH, PANEL_HEIGHT, boardColNum, boardRowNum, digitalClockPanel);
					mainPanel.add(rotatePuzzlePanel, BorderLayout.EAST);
				}
				repaint();
				pack();
			}
		});
		panelMenu.add(puzzle3x3MenuItem);
		JMenuItem puzzle4x4MenuItem = new JMenuItem("4 X 4");
		puzzle4x4MenuItem.addActionListener((e) -> {
			if (puzzlePanelCount != PuzzlePanelCount.PANEL4X4) {
				puzzlePanelCount = PuzzlePanelCount.PANEL4X4;
				boardRowNum = 4;
				boardColNum = 4;
				if (puzzleGameMode == PuzzleGameMode.SLIDE) {
					mainPanel.remove(slidePuzzlePanel);
					slidePuzzlePanel = new SlidePuzzlePanel(PANEL_WIDTH, PANEL_HEIGHT, boardColNum, boardRowNum, digitalClockPanel);
					mainPanel.add(slidePuzzlePanel, BorderLayout.EAST);
				} else if (puzzleGameMode == PuzzleGameMode.ROTATE) {
					mainPanel.remove(rotatePuzzlePanel);
					rotatePuzzlePanel = new RotatePuzzlePanel(PANEL_WIDTH, PANEL_HEIGHT, boardColNum, boardRowNum, digitalClockPanel);
					mainPanel.add(rotatePuzzlePanel, BorderLayout.EAST);
				}
				repaint();
				pack();
			}
		});
		panelMenu.add(puzzle4x4MenuItem);

		JMenu clockMenu = new JMenu("Clock");
		menuBar.add(clockMenu);
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
		menuBar.add(helpMenu);
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
		setJMenuBar(menuBar);

		mainPanel = new JPanel(new BorderLayout());
		digitalClockPanel = DigitalClockPanel.newInstance(this, PANEL_WIDTH, PANEL_HEIGHT);
		mainPanel.add(digitalClockPanel, BorderLayout.WEST);
		if (puzzleGameMode == PuzzleGameMode.SLIDE) {
			slidePuzzlePanel = new SlidePuzzlePanel(PANEL_WIDTH, PANEL_HEIGHT, 3, 3, digitalClockPanel);
			mainPanel.add(slidePuzzlePanel, BorderLayout.EAST);
		} else if (puzzleGameMode == PuzzleGameMode.ROTATE) {
			rotatePuzzlePanel  = new RotatePuzzlePanel(PANEL_WIDTH, PANEL_HEIGHT, 3, 3, digitalClockPanel);
			mainPanel.add(rotatePuzzlePanel, BorderLayout.EAST);
		}

		JPanel borderPanel = new JPanel(){
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setColor(Color.WHITE);
				g.drawRect(0, 0, 10, PANEL_HEIGHT);
			};

		};
		borderPanel.setSize(10, PANEL_HEIGHT);
		borderPanel.setVisible(true);
		mainPanel.add(borderPanel, BorderLayout.CENTER);
		add(mainPanel);
		pack();
		setResizable(false);
		setLocationRelativeTo(null);
		addWindowListener(new ApplicationCloseAdapter());

		Insets insets = this.getInsets();
		Dimension size1 = menuBar.getPreferredSize();
		setSize((PANEL_WIDTH + insets.right + insets.left) * 2 + 10, PANEL_HEIGHT + insets.bottom + insets.top + size1.height);
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
