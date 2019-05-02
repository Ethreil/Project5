import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Mesonet {

	public static ArrayList<String> findSimilarIDs (String ID, int hammingDist) throws FileNotFoundException
	{
		ArrayList<String> similarIDs = new ArrayList<String>();
		File mesonetIDs = new File("Mesonet.txt");
		Scanner mesoScan = new Scanner(mesonetIDs);
		char[] idCharArray = ID.toCharArray();
		
		String tempID = "";
		char[] tempIDArray = new char[3];
		int countIncorrect = 0;
		while (mesoScan.hasNext())
		{
			tempID = mesoScan.next();
			tempIDArray = tempID.toCharArray();
			for (int i = 0; i < 4; ++i)
			{
				if (!(tempIDArray[i] == idCharArray[i]))
				{
					++countIncorrect;
				}
			}
			if (countIncorrect == hammingDist)
			{
				similarIDs.add(tempID);
			}		
		}
		mesoScan.close();
		return similarIDs;
	}
	
	public static ArrayList<String> readInIDs () throws FileNotFoundException
	{
		ArrayList<String> IDs = new ArrayList<String>();
		File mesonetIDs = new File("Mesonet.txt");
		Scanner mesoScan = new Scanner(mesonetIDs);
		
		while(mesoScan.hasNext())
		{
			IDs.add(mesoScan.next());
		}
		
		return IDs;
	}
	
}
