import java.awt.Color;
import java.awt.GridLayout;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;

public class MesoFrame extends JFrame {
	
	private ArrayList<String> mesoIDs = new ArrayList<String>();
	private JComboBox<String> dropdownIDs = new JComboBox<String>();
	private ArrayList<String> similarIDs = new ArrayList<String>();
	private ArrayList<String> similarIDHam1 = new ArrayList<String>();
	private ArrayList<String> similarIDHam2 = new ArrayList<String>();
	private ArrayList<String> similarIDHam3 = new ArrayList<String>();
	private ArrayList<String> similarIDHam4 = new ArrayList<String>();
	
	
	
	private JLabel hammingDistLabel = new JLabel("Enter Hamming Dist:");
	private JTextField hammingDist = new JTextField("2", 4);
	private JSlider hammingDistSlider = new JSlider(0, 4, Integer.valueOf(hammingDist.getText()));
	private JButton showStation = new JButton("Show Station");
	private JList<String> stations = new JList<String>();
	private JLabel compareIDLabel = new JLabel("Compare with:");
	private JButton calculateHD = new JButton("Calculate HD");
	private JLabel distance0Label = new JLabel("Distance 0");
	private JTextField distance0 = new JTextField(5);
	private JLabel distance1Label = new JLabel("Distance 1");
	private JTextField distance1 = new JTextField(5);
	private JLabel distance2Label = new JLabel("Distance 2");
	private JTextField distance2 = new JTextField(5);
	private JLabel distance3Label = new JLabel("Distance 3");
	private JTextField distance3 = new JTextField(5);
	private JLabel distance4Label = new JLabel("Distance 4");
	private JTextField distance4 = new JTextField(5);
	private JButton addStation = new JButton("Add Station");
	private JTextField stationToAdd = new JTextField(4);
	
	private void updateList (ArrayList<String> IDs)
	{
		String[] stationsArray = new String[IDs.size()];
		for (int i = 0; i < IDs.size(); ++i)
		{
		//stations.add(new JTextField(IDs.get(i)));
		stationsArray[i] = IDs.get(i);
		}
		stations.setListData(stationsArray);
		stations.setVisibleRowCount(IDs.size());
	}
	
	private void updateCalculatedList (int[] sizes)
	{
		distance0.setText("0");
		distance1.setText(sizes[1] + "");
		distance2.setText(sizes[2] + "");
		distance3.setText(sizes[3] + "");
		distance4.setText(sizes[4] + "");
	}

	public MesoFrame () throws FileNotFoundException
	{
		this.setTitle("Mesonet");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500, 700);
		this.setLayout(new GridLayout(8,1));
		Color turq = new Color(175,238,238);
		this.setBackground(turq);
		
		mesoIDs = Mesonet.readInIDs();
		for (int i = 0; i < mesoIDs.size(); ++i)
		{
			dropdownIDs.addItem(mesoIDs.get(i));
		}
		
		hammingDist.addActionListener((e) -> {
			hammingDistSlider.setValue(Integer.valueOf(hammingDist.getText()));
		});
		
		hammingDistSlider.addChangeListener((e) -> {
			hammingDist.setText(hammingDistSlider.getValue() + "");
		});

		showStation.addActionListener((e) -> {
			try {
				similarIDs = Mesonet.findSimilarIDs(dropdownIDs.getSelectedItem().toString(), Integer.valueOf(hammingDist.getText()));
			} catch (NumberFormatException | FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.updateList(similarIDs);
		});
		
		calculateHD.addActionListener((e) -> {
			try {
				int sizes[] = {0, 0, 0, 0};
				similarIDHam1 = Mesonet.findSimilarIDs(dropdownIDs.getSelectedItem().toString(), 1);
				similarIDHam2 = Mesonet.findSimilarIDs(dropdownIDs.getSelectedItem().toString(), 2);
				similarIDHam3 = Mesonet.findSimilarIDs(dropdownIDs.getSelectedItem().toString(), 3);
				similarIDHam4 = Mesonet.findSimilarIDs(dropdownIDs.getSelectedItem().toString(), 4);
				sizes[0] = similarIDHam1.size();
				sizes[1] = similarIDHam2.size();
				sizes[2] = similarIDHam3.size();
				sizes[3] = similarIDHam4.size();
				this.updateCalculatedList(sizes);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		JPanel hamDistPanel = new JPanel(new GridLayout(2,1));
		hamDistPanel.add(hammingDistLabel);
		hamDistPanel.add(hammingDist);
		hamDistPanel.setBackground(turq);
		this.add(hamDistPanel);
		this.add(hammingDistSlider);
		
		this.add(showStation);
		this.add(stations);
		
		JPanel compareIDPanel = new JPanel(new GridLayout(2,1));
		compareIDPanel.add(compareIDLabel);
		compareIDPanel.add(dropdownIDs);
		compareIDPanel.setBackground(turq);
		this.add(compareIDPanel);
	
		this.add(calculateHD);
		
		JPanel distanceCalculated = new JPanel(new GridLayout(6,2));
		distanceCalculated.add(distance0Label);
		distanceCalculated.add(distance0);
		distanceCalculated.add(distance1Label);
		distanceCalculated.add(distance1);
		distanceCalculated.add(distance2Label);
		distanceCalculated.add(distance2);
		distanceCalculated.add(distance3Label);
		distanceCalculated.add(distance3);
		distanceCalculated.add(distance4Label);
		distanceCalculated.add(distance4);
		distanceCalculated.add(addStation);
		distanceCalculated.add(stationToAdd);
		distanceCalculated.setBackground(turq);
		this.add(distanceCalculated);
		

		this.setVisible(true);
		
	}
	
	public static void main (String[] args) throws FileNotFoundException
	{
		new	MesoFrame();
	}
	
	

}
