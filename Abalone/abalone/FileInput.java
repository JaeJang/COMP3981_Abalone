package abalone;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import boardFrame.GameFrame;

public class FileInput extends JPanel implements ActionListener{
	private BufferedReader fileInput;
	private JButton openButton, readButton;
	private File fileName;
	private int fileNumber;
	private String data;
	private String starter;
	private ArrayList<String> initPos;
	private GameFrame frame;

	/**
	 * Constructor Takes in a single value
	 * 
	 * @param s
	 */
	public FileInput(GameFrame frame) {
	    this.frame = frame;
		setBackground(Color.white);
		setLayout(new GridLayout(1, 2));

		openButton = new JButton("Open File");
		openButton.addActionListener(this);
		add(openButton);

		readButton = new JButton("Read File");
		readButton.addActionListener(this);
		readButton.setEnabled(false);
		add(readButton);
		
	}
	

	/**
	 * ActionPerformed for buttons
	 */
	public void actionPerformed(ActionEvent event) {
		if (event.getActionCommand().equals("Open File")) {
			getFileName();
		}
		if (event.getActionCommand().equals("Read File")) {
			readFile();
			frame.getBoard().setFileInputPosArray(getInitPosArray());
			
		}
	}

	/**
	 * Lets you open the file opener
	 */
	private void getFileName() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int result = fileChooser.showOpenDialog(this);
		if (result == JFileChooser.CANCEL_OPTION) {
			return;
		}
		fileName = fileChooser.getSelectedFile();
		if (checkFileName()) {
			openButton.setEnabled(false);
			readButton.setEnabled(true);
		}
	}

	/**
	 * sets the button to be enable/disable reads the file
	 */
	private void readFile() {
		readButton.setEnabled(false);
		readTheFile();
		openButton.setEnabled(true);
	}

	/**
	 * file reader This is where we parse the strings We store the first line into
	 * the variable "starter" We store the second line of string into "data" We
	 * parse the long string into an ArrayList called "initPos" The string is parsed
	 * with the delimiter specified on line 99
	 */
	private void readTheFile() {
		openFile();
		System.out.println("Read the file");
		try {
			starter = fileInput.readLine();
			System.out.println("Starter: " + starter);
		} catch (IOException ioException) {
			JOptionPane.showMessageDialog(this, "Error reading File", "Error 5: ", JOptionPane.ERROR_MESSAGE);
			closeFile();
			System.exit(1);
		}
		try {
			data = fileInput.readLine();
			System.out.println("Initial Position: " + data);
			initPos = new ArrayList<String>(Arrays.asList(data.split("/")));
			for (int i = 0; i < initPos.size(); i++) {
				System.out.println(initPos.get(i));
			}
			closeFile();
		} catch (IOException ioException) {
			JOptionPane.showMessageDialog(this, "Error reading File", "Error 5: ", JOptionPane.ERROR_MESSAGE);
			closeFile();
			System.exit(1);
		}
	}

	/**
	 * Checks if the file is ok to read
	 * 
	 * @return
	 */
	private boolean checkFileName() {
		if (fileName.exists()) {
			if (fileName.canRead()) {
				if (fileName.isFile())
					return (true);
				else
					JOptionPane.showMessageDialog(null, "ERROR 3: File is a directory");
			} else
				JOptionPane.showMessageDialog(null, "ERROR 2: Access denied");
		} else
			JOptionPane.showMessageDialog(null, "ERROR 1: No such file!");
		return (false);
	}

	/**
	 * Opens the file
	 */
	private void openFile() {
		try {
			FileReader file = new FileReader(fileName);
			fileInput = new BufferedReader(file);
			System.out.println("This is the file number " + getFileNumber(fileName));
		} catch (IOException ioException) {
			JOptionPane.showMessageDialog(this, "Error Opening File", "Error 4: ", JOptionPane.ERROR_MESSAGE);
		}
		System.out.println("File opened");
	}

	private void closeFile() {
		if (fileInput != null) {
			try {
				fileInput.close();
			} catch (IOException ioException) {
				JOptionPane.showMessageDialog(this, "Error Opening File", "Error 4: ", JOptionPane.ERROR_MESSAGE);
			}
		}
		System.out.println("File closed");
	}

	/**
	 * gets the file number to pass to the output files
	 * 
	 * @param name
	 * @return fileNumber
	 */
	public int getFileNumber(File name) {
		String filename = name.getName();
		fileNumber = Integer.parseInt(filename.replaceAll("\\D+", ""));
		return fileNumber;
	}

	/**
	 * Gets the arrayList that contains the initial position
	 * @return initPos
	 */
	public ArrayList<String> getInitPosArray() {
		return initPos;
	}
	
	/**
	 * gets the initial marble for the next play
	 * @return starter
	 */
	public String getStartingMarble() {
		return starter;
	}
}
