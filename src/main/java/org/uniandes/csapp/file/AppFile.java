package main.java.org.uniandes.csapp.file;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import main.java.org.uniandes.csapp.object.App;

public final class AppFile {

	//Delimiter used in CSV file
	private static final String NEW_LINE_SEPARATOR = "\n";

	CSVFormat csvFileFormat = CSVFormat.DEFAULT
                .withRecordSeparator(NEW_LINE_SEPARATOR);	
        // TODO Auto-generated method stub
	/**
         * appDataRecord.add(app.getName());                
            appDataRecord.add(app.getUrl());
                appDataRecord.add(app.getDescription());
                appDataRecord.add(app.getNumeroRatings()+"");
                appDataRecord.add(app.getRaitingProm()+"");
                appDataRecord.add(app.getRaitings4()+"");
                appDataRecord.add(app.getRaitings5()+"");
                appDataRecord.add(app.getRecentChange().toString());
                appDataRecord.add(app.getReviews().toString());
         */
        private static final Object [] FILE_HEADER = {"nombre app","url",
            "descripción","numRatings", "ratings4", "ratings5", "recentChanges",
            "reviews"};

	CSVPrinter csvFilePrinter = null;

	FileWriter fileWriter = null;		

	public AppFile(String pageName){		
		init(pageName);
	}

	public AppFile(String pageName, Set<App> appList, Set<App> app45,
                Set<App> app34, Set<App> app23, Set<App> app02) {
		try {
			init(pageName);
			for (App app : appList) {
                            writeApp(app);
			}
			closeFile();
                        
                        writeReviews(app45, pageName+"Analisis45");
			writeReviews(app34, pageName+"Analisis34");
			writeReviews(app23, pageName+"Analisis23");
			writeReviews(app23, pageName+"Analisis02");
			
		} catch (IOException e) {
			e.printStackTrace();
			closeFile();
		}
	}

	/**
	 * init
	 * @param pageName
	 */
	private void init(String pageName) {
		//Create the CSVFormat object with "\n" as a record delimiter
		CSVFormat csvFileFormat = CSVFormat.DEFAULT.
                        withRecordSeparator(NEW_LINE_SEPARATOR);

		try {
			//initialize FileWriter object
			fileWriter = new FileWriter("./data/apps"+pageName+".csv");

			//initialize CSVPrinter object 
			csvFilePrinter = new CSVPrinter(fileWriter,
                                csvFileFormat);

			//Create CSV file header
			csvFilePrinter.printRecord(FILE_HEADER);

			System.out.println("CSV file was created successfully "
                                +pageName+"!!!");

		} catch (Exception e) {
			System.out.println("Error in CsvFileWriter !!!");
			e.printStackTrace();
			closeFile();
		}
	}


	/**
	 * Write apps file
	 * @param app set if apps
	 * @throws IOException problem(?) 
	 */
	public void writeApp(App app) throws IOException{
		List<String> appDataRecord = new ArrayList<>();
		appDataRecord.add(app.getName());                
		appDataRecord.add(app.getUrl());
                appDataRecord.add(app.getDescription());
                appDataRecord.add(app.getNumeroRatings()+"");
                appDataRecord.add(app.getRaitingProm()+"");
                appDataRecord.add(app.getRaitings4()+"");
                appDataRecord.add(app.getRaitings5()+"");
                appDataRecord.add(app.getRecentChange().toString());
                appDataRecord.add(app.getReviews().toString());
                
                csvFilePrinter.printRecord(appDataRecord);
	}
        /**
	 * Write review file
         * @param apps
         * @param name
	 */
	public void writeReviews(Set<App> apps, String name){
            
                try (PrintWriter writer = new PrintWriter("data/"+name+".txt",
                        "UTF-8")) {
                    for (App app : apps){
                        for( String rev : app.getReviews())
                            writer.println(rev);
                    }
                }
             catch (IOException e) {
                   System.out.println(e.getMessage());
                   e.printStackTrace();
                 // do something
            }
	}
        
        public void closeFile(){
		try {
			fileWriter.flush();
			fileWriter.close();
			csvFilePrinter.close();
		} catch (IOException e) {
			System.out.println("Error while flushing/closing"
                                + " fileWriter/csvPrinter !!!");
                        e.printStackTrace();
		}

	}
}
