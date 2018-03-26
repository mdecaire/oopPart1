/**
 * File Name: GUI.java
 * Due Date: 3/25/2018
 * Author: Michelle Decaire
 * Pupose: to build a interface and initializes any searches 
 * for any data that the text file may want related to ports and such.
 * 
 */
import java.awt.*;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class GUI extends JFrame {


	private static final long serialVersionUID = 1L;
	private static JFrame background;
	private World shipWorld=null;
	boolean fileLoaded=true;
	
	public static void main(String[] args) {
		GUI driver = new GUI();
		driver.buildGUI();

	}


	//builds a GUI and calls different events
	private void buildGUI() {
		background = new JFrame("Port Information Tool");
		background.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel contentPane = new JPanel();
		contentPane.setLayout(new GridBagLayout());

		JLabel topLabel= new JLabel("Select a file from your system to begin:");
		topLabel.setFont (new java.awt.Font ("Dialog", 0, 18));
		
		//panel to read file in
		JPanel filePanel= new JPanel((new GridLayout(4,1)));
		topLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 5));
		JButton fileButton= new JButton("Select File");
		JPanel fileButtonPanel= new JPanel(new GridLayout(1,3));
		fileButtonPanel.add(new JLabel());
		fileButtonPanel.add(fileButton);
		fileButtonPanel.add(new JLabel());
		fileButtonPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		filePanel.add(new JLabel());
		filePanel.add(topLabel);
		filePanel.add(fileButtonPanel);
		filePanel.add(new JLabel());
		filePanel.setBorder(BorderFactory.createEtchedBorder());
		
		//panel to label buttons
		JLabel selectFromStructure= new JLabel("Select from the categories below to view information on that item:");
		selectFromStructure.setFont (new java.awt.Font ("Dialog", 0, 18));
		JPanel labelPanel= new JPanel(new GridLayout(2,2));
		labelPanel.add(selectFromStructure);
		labelPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 0, 5));
		
		//panel for buttons
		JButton portButton= new JButton("Ports");
		JButton dockButton= new JButton("Docks");
		JButton allShipButton= new JButton ("All Ships");
		JButton queButton= new JButton ("Ships in Que");
		JButton personButton= new JButton ("All Personnel");
		JButton jobButton = new JButton ("Jobs");
		JPanel buttonPanel= new JPanel( new GridLayout(3,2,5,5));
		buttonPanel.add(portButton);
		buttonPanel.add(dockButton);
		buttonPanel.add(allShipButton);
		buttonPanel.add(personButton);
		buttonPanel.add(queButton);
		buttonPanel.add(jobButton);
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
		
		//panel to combine btn and label
		JPanel combBtnPan= new JPanel(new GridLayout(3,1));
		combBtnPan.add(labelPanel);
		combBtnPan.add(buttonPanel);
		combBtnPan.setBorder(BorderFactory.createEtchedBorder());

		//radio Button functionality
		JPanel radioPanel = new JPanel(new GridLayout (2,1));
		radioPanel.add(new JLabel ("Select a category option."));	
		JRadioButton name= new JRadioButton("Name", true);
		JRadioButton index=new JRadioButton("Index");
		JRadioButton skill= new JRadioButton("Skill");
		JRadioButton weight= new JRadioButton("Weight");
		JRadioButton length = new JRadioButton("Length");
		JRadioButton numPassenger= new JRadioButton("Number of Passengers");
		name.setActionCommand("name");
		index.setActionCommand("index");
		skill.setActionCommand("skill");
		weight.setActionCommand("weight");
		length.setActionCommand("length");
		numPassenger.setActionCommand("passengers");
	
		ButtonGroup bGroup= new ButtonGroup();
		bGroup.add(name);
		bGroup.add(index);
		bGroup.add(skill);
		bGroup.add(weight);
		bGroup.add(length);
		bGroup.add(numPassenger);
			
		JPanel rPanel= new JPanel(new GridLayout(2,3));
		rPanel.add(name);
		rPanel.add(index);
		rPanel.add(skill);
		rPanel.add(weight);
		rPanel.add(length);
		rPanel.add(numPassenger);
		radioPanel.add(rPanel);

		// panel to get searching information

		JTextField searchString = new JTextField("", 50);
		JButton searchButton = new JButton("Search ");
		JPanel textPanel = new JPanel(new BorderLayout());
		textPanel.add(searchString, BorderLayout.WEST);
		textPanel.add(searchButton, BorderLayout.EAST);
		JLabel searchLabel = new JLabel(
				"To Perform Search: Enter the information in the text field and select a radio button below.");
		searchLabel.setFont(new java.awt.Font("Dialog", 0, 18));
		JPanel searchPanel = new JPanel(new GridLayout(3, 1, 1, 10));
		searchPanel.add(searchLabel);
		searchPanel
				.add(new JLabel("To Search for a weight, length, or number of passengers enter the minimum target."));
		searchPanel.add(textPanel);
		searchPanel.setBorder(BorderFactory.createEtchedBorder());
		
		
		// gridbag layout
		GridBagConstraints c = new GridBagConstraints();

		c.anchor = GridBagConstraints.PAGE_START;
		c.fill = GridBagConstraints.BOTH;

		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0.5;
		c.weighty = 1.0;
		contentPane.add(filePanel, c);

		c.gridx = 0;
		c.gridy = 2;
		contentPane.add(labelPanel, c);

		c.gridx = 0;
		c.gridy = 3;
		contentPane.add(buttonPanel, c);

		c.insets = new Insets(20, 0, 0, 0);
		c.gridx = 0;
		c.gridy = 5;
		contentPane.add(searchPanel, c);

		c.insets = new Insets(0, 0, 10, 0);
		c.gridx = 0;
		c.gridy = 7;
		contentPane.add(radioPanel, c);

		background.setContentPane(contentPane);
		background.setLocationByPlatform(true);
		background.pack();
		background.setResizable(true);
		background.setLocationRelativeTo(null);
		background.setVisible(true);

		// action lambda's
		fileButton.addActionListener(e -> getFile(background));
        
		// makes sure there is a file before proceeding to get searches
		if (fileLoaded) {
			allShipButton.addActionListener(e -> getStructures("allShips"));
			portButton.addActionListener(e -> getStructures("ports"));
			queButton.addActionListener(e -> getStructures("que"));
			personButton.addActionListener(e -> getStructures("persons"));
			dockButton.addActionListener(e -> getStructures("dock"));
			jobButton.addActionListener(e -> getStructures("job"));
			searchButton.addActionListener(
					e -> getSearchResults(searchString.getText(), bGroup.getSelection().getActionCommand()));
		} else {
			showError("File Not Found", "Please enter a file to continue!");
		}
       
	}

	// for the search section of the GUI. Case statement is for
	// different radio buttons.
	private void getSearchResults(String text1, String actionCommand) {
		String text = text1.trim();
		String textDisplay = "";
		try {
			switch (actionCommand) {
			case "name":
				textDisplay = shipWorld.searchName(text);
				if (textDisplay.isEmpty()) {
					showMessage("Name Not Found", "Please check the spelling and try again!");
				} else {
					showResults("Result of Name Search", ("\n" + textDisplay));
				}
				break;

			case "index":
				int num = Integer.parseInt(text);
				textDisplay = shipWorld.searchByIndex(num);
				if (textDisplay.isEmpty()) {
					showMessage("Index Not Found", "Please check the number entered and try again!");
				} else {
					showResults("Result of Index Search", ("\n" + textDisplay));
				}
				break;

			case "skill":
				textDisplay = shipWorld.searchSkill(text);
				if (textDisplay.isEmpty()) {
					showMessage("Person with that skill not Found", "Please check the spelling and try again!");
				} else {
					showResults("Person with skill" + text, ("\n" + textDisplay));
				}
				break;

			case "weight":
				double wgt = Double.parseDouble(text);
				textDisplay = shipWorld.searchByDouble(wgt, false);
				if (textDisplay.isEmpty()) {
					showMessage("No ship with that minimum weight found",
							"Please check the number entered and try again!");
				} else {
					showResults("Result of Weight Search", ("\n" + textDisplay));
				}
				break;
			case "length":
				double length = Double.parseDouble(text);
				textDisplay = shipWorld.searchByDouble(length, true);
				if (textDisplay.isEmpty()) {
					showMessage("No ships with that minimum length found",
							"Please check the number entered and try again!");
				} else {
					showResults("Result of Length Search", ("\n" + textDisplay));
				}
				break;
			case "passengers":
				int numPass = Integer.parseInt(text);
				textDisplay = shipWorld.searchByNumberOfPassengers(numPass);
				if (textDisplay.isEmpty()) {
					showMessage("No ships with that minimum passengers found",
							"Please check the number entered and try again!");
				} else {
					showResults("Result of Index Search", ("\n" + textDisplay));
				}

				break;
			default:
				break;

			}
		} catch (NullPointerException e) {
			showError("File Not Found", "Please enter a file to continue!");
			return;
		} catch (NumberFormatException nfe) {
			showError("Not a number",
					"Please enter a valid number to continue" + "\n Note: Indexes should not have a decimal");
		}

	}

	// informational messages like no name found
	private void showMessage(String title, String message) {

		JOptionPane.showMessageDialog(background, message, title, JOptionPane.PLAIN_MESSAGE);

	}

	// error messages such as no file loaded
	private void showError(String title, String message) {
		JOptionPane.showMessageDialog(background, message, title, JOptionPane.ERROR_MESSAGE);

	}

	// For the Structure buttons. Each case statement corresponds
	// to one of the buttons.
	private void getStructures(String searchCriteria) {
		String textToDisplay = "\n";
		try {
			switch (searchCriteria) {
			case "allShips":
				textToDisplay += shipWorld.getShips();
				showResults("All Ships", textToDisplay);
				break;
			case "ports":
				textToDisplay += shipWorld.getPorts();
				showResults("List of Ports in the World", textToDisplay);
				break;
			case "que":
				textToDisplay += shipWorld.getQue();
				showResults("List Ships in Que", textToDisplay);
				break;
			case "persons":
				textToDisplay += shipWorld.getPerson();
				showResults("List of Personnel", textToDisplay);
				break;
			case "dock":
				textToDisplay += shipWorld.getDock();
				showResults("List of Docks", textToDisplay);
				break;
			case "job":
				textToDisplay += shipWorld.getJob();
				showResults("List of Jobs", textToDisplay);
				break;
			default:
				break;

			}
		} catch (NullPointerException e) {
			showError("File Not Found", "Please enter a file to continue!");
			return;
		}
	}

	/**
	 * Window to show the results of all information
	 * 
	 * @param title
	 * @param information
	 */

	private void showResults(String title, String information) {

		JFrame resultFrame = new JFrame(title);
		JPanel searchTextPanel = new JPanel(new BorderLayout());
		JTextArea results = new JTextArea();
		results.setFont(new java.awt.Font("Monospaced", 1, 14));
		results.setEditable(false);
		results.setText(information);

		searchTextPanel.add(results);
		JScrollPane searchScroll = new JScrollPane(results, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		searchTextPanel.add(searchScroll, BorderLayout.CENTER);

		// code to set scroll bar to the beginning of text area
		int x;
		results.selectAll();
		x = results.getSelectionStart();
		results.select(x, x);

		JPanel framPanel = new JPanel(new BorderLayout());
		framPanel.add(searchTextPanel, BorderLayout.CENTER);
		resultFrame.getContentPane().add(framPanel);
		resultFrame.pack();
		resultFrame.setSize(400, 800);
		resultFrame.setResizable(true);
		resultFrame.setLocationRelativeTo(null);
		resultFrame.setVisible(true);
		resultFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

	}

	/**
	 * Action event for file button
	 * 
	 * @param background2
	 */
	private void getFile(JFrame background2) {
		File file = null;

		JFileChooser fc = new JFileChooser(".");
		fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		int returnValue = fc.showOpenDialog(background2);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			file = fc.getSelectedFile();
			fileLoaded = true;
			shipWorld = new World();
			shipWorld.processFile(file);
			showResults("File Information", shipWorld.toString());

		} else {
			fileLoaded = false;
		}

	}

}
