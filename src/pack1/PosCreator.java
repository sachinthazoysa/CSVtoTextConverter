package pack1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PosCreator {
	static String empRoll = "";


	public static void main(String[] args) throws IOException {
		 File file = new File("C:\\Users\\deZoysa\\Documents\\deZoysa\\h.csv");

		    List<String> lines;
		    List<String> PrintList = new ArrayList();
			try {
				lines = Files.readAllLines(file.toPath(), 
				        StandardCharsets.UTF_8);
				
				int i=0;
			
				
			    for (String line : lines) {
			    	if(i==0) {
			    		//skips the headers of csv
			    		 i++;
			    	}else {
			    	   	 String[] empList = line.split(",") ;
				           
				           if(!empList[10].isEmpty()) {
				        	   
				        	   int publications = Integer.parseInt(empList[12]);
				        	   int phdYear = Integer.parseInt(empList[11]);

				        	   int currYear = Calendar.getInstance().get(Calendar.YEAR);
				        	   int postPHDExperience = currYear - phdYear;
				        	   
				        	   int bscYear = Integer.parseInt(empList[4]);
				        	   int bscExperience = currYear - bscYear;
	
				        	   if( publications > 15) {
				        		   empRoll = "Full Proffesor";
				        	   }else if(publications > 8) {
				        		   empRoll = "Associate Proffesor";
				        	   }else if(postPHDExperience>3) {
				        		   empRoll = "Assistance Proffesor";
				        	   }else {
				        		   empRoll = "Senior Lecturer";
				        	   }
				        	   
				        	   
				           }else {
				          	   int currYear = Calendar.getInstance().get(Calendar.YEAR);
   
				        	   int bscYear = Integer.parseInt(empList[4]);
				        	   int bscExperience = currYear - bscYear;
   
				        	   if(!empList[8].isEmpty()) {
				        		   empRoll = "Senior Lecturer";
	   				        	   }else if(!empList[6].isEmpty()){
				        		   empRoll = "Lecturer";
				        	   }else if(empList[5].contains("1st") && bscExperience > 1 ) {
				        		   empRoll = "Assistant Lecturer";
				        	   }else if(empList[5].contains("1st") && bscExperience < 1 ) {
				        		   empRoll = "Instructor";
				        	   }else if(empList[5].isEmpty()) {
				        		   empRoll = "Rejected (Not hired)";
				        	   } 
				           }
				           String record = empList[0] + ":" + empRoll;       
				           PrintList.add(record);

			    	}

			    }
			    
			} catch (IOException e) {
				e.printStackTrace();
			}

	           System.out.println(PrintList);
	           
	           FileWriter writer = new FileWriter("C:\\Users\\deZoysa\\Documents\\deZoysa\\output.txt"); 
	           for(String str: PrintList) {
	             writer.write(str + System.lineSeparator());
	           }
	           writer.close();

	}

}
