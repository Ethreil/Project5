This application is designed to give a user an easy method to calculate the Hamming Distance between a certain 4 Character length String mesonet ID and other Mesonet IDs. Running this application will provide the user an easy GUI to use to do these calculations.

Elements in the GUI:

Enter / Adjust Hamming Distance: 
Use the Slider or the text field next to "Enter Hamming Distance" to enter the desired hamming distance which you want to find station IDs for.

Show Station:
Shows the related stations with a hamming distance from the chosen String. 

Compare with dropdown box:
Shows All Mesonet IDs available within the system to use for comparisons.

Calculate HD:
Shows the number of station IDs at a certain hamming distance (listed below) for the chosen Station ID>

Add Station:
Enter a 4 character length uppercase string which you wish to add to the list of Station IDs which can be compared.

To use this application:

1. Enter the desired Hamming Distance which you intend to find related Strings for. This can be done using the text field or the slider.
2. Use the "Compare With" dropdown slider to select the Mesonet ID you would like to compare against other IDs. If you wish to add a new Mesonet ID, enter a new ID into the field next to the button "Add Station". Then click "Add Station".
3. Click "Show Station' to find stations with the desired hamming distance from this Station's ID.
4. Click "Calculate HD" to find how many Station IDs have a hamming distance of 0, 1, 2, 3, or 4 from this Station ID.

MesoFrame.java

Constructs the Java Frame and the components used within the GUI. Holds private data read in from Mesonet.java

void updateList (ArrayList<String> IDs)

Updates the list of stationIDs to be shown which are at a certain hamming distance from the chosen station ID.

void updateCalculatedList (int[] sizes)

Updates the text fields to show how many Station IDs are at all hamming distances 0-4 for the chosen station ID.

Mesonet.java

Reads station IDs from Mesonet.txt to be used for calculations within Mesonet.java, which can be passed to MesoFrame.java to be shown to the user.

ArrayList<String> findSimilarIDs (String ID, int hammingDist)

Returns an arraylist of strings containing all the Station IDs at a given hamming distance (hammingDist) from the given station ID (ID).

ArrayList<String> readInIDs ()

Returns an arraylist of strings containing all the Station IDs which can be compared within the system.
