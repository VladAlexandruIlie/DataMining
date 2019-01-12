package data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataLoader {

	public static ArrayList<SurveyAnswer> LoadData() {
		ArrayList<SurveyAnswer> data = new ArrayList<>();
		int j=0;
		try {
			List<List<String>> dataOrig = CSVFileReader.readDataFile("data/Data Mining - Spring 2018.csv","\",\"","",true);

			for (int i=1; i<dataOrig.size(); i+=1) {
				String timeStamp = dataOrig.get(i).get(0).substring(1);
				String age = dataOrig.get(i).get(1);
				String gender = dataOrig.get(i).get(2);
				String shoeSize = dataOrig.get(i).get(3);
				String height = dataOrig.get(i).get(4);
				String degree = dataOrig.get(i).get(5);
				String motivation = dataOrig.get(i).get(6);
				String programmingLanguages = dataOrig.get(i).get(7);
				String phoneOS = dataOrig.get(i).get(8);

				String interest1_efficientDatabases = dataOrig.get(i).get(9);
				String interest2_predicativeModels = dataOrig.get(i).get(10);
				String interest3_similarObjects = dataOrig.get(i).get(11);
				String interest4_dataVisualization = dataOrig.get(i).get(12);
				String interest5_setPatterns = dataOrig.get(i).get(13);
				String interest6_sequencePatterns = dataOrig.get(i).get(14);
				String interest7_graphPatterns = dataOrig.get(i).get(15);
				String interest8_textPatterns = dataOrig.get(i).get(16);
				String interest9_imagePatterns = dataOrig.get(i).get(17);
				String interest10_algorithms = dataOrig.get(i).get(18);
				String interest11_tools = dataOrig.get(i).get(19);

				String gamesPlayed = dataOrig.get(i).get(20);
				String commute = dataOrig.get(i).get(21);

				String checkpoint1 = dataOrig.get(i).get(22);
				String checkpoint2 = dataOrig.get(i).get(23);
				String checkpoint3 = dataOrig.get(i).get(24);
				String checkpoint4 = dataOrig.get(i).get(25);
				String checkpoint5 = dataOrig.get(i).get(26);
				String checkpoint6 = dataOrig.get(i).get(27);
				String checkpoint7 = dataOrig.get(i).get(28);
				String checkpoint8 = dataOrig.get(i).get(29);
				String checkpoint9 = dataOrig.get(i).get(30);
				String checkpoint10 = dataOrig.get(i).get(31);
				String checkpoint11 = dataOrig.get(i).get(32);
				String checkpoint12 = dataOrig.get(i).get(33);
				String checkpoint13 = dataOrig.get(i).get(34);
				String checkpoint14 = dataOrig.get(i).get(35);
				String checkpoint15 = dataOrig.get(i).get(36);

				String randomNo = dataOrig.get(i).get(37);
				String wutIsThis = dataOrig.get(i).get(38);
				String pickedNo = dataOrig.get(i).get(39);

				String favFilm = dataOrig.get(i).get(40);
				String favTVShow = dataOrig.get(i).get(41);
				String favGame = dataOrig.get(i).get(42);

				String seatRow = dataOrig.get(i).get(43);
				String seatNo = dataOrig.get(i).get(44).substring(0,dataOrig.get(i).get(44).length()-1);


				SurveyAnswer newSurveyAnswer = new SurveyAnswer(timeStamp, age, gender, shoeSize, height, degree, motivation, programmingLanguages, phoneOS,
						interest1_efficientDatabases, interest2_predicativeModels, interest3_similarObjects, interest4_dataVisualization, interest5_setPatterns,
						interest6_sequencePatterns, interest7_graphPatterns, interest8_textPatterns, interest9_imagePatterns, interest10_algorithms, interest11_tools,
						gamesPlayed, commute,
						checkpoint1, checkpoint2, checkpoint3, checkpoint4, checkpoint5, checkpoint6, checkpoint7, checkpoint8, checkpoint9, checkpoint10, checkpoint11, checkpoint12, checkpoint13, checkpoint14, checkpoint15,
						randomNo, wutIsThis, pickedNo, favFilm, favTVShow, favGame,
						seatRow, seatNo);

				//System.out.println(j + " " + newSurveyAnswer);
				j++;
				data.add(newSurveyAnswer);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;

	}

}
