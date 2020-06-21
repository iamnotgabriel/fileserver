package model;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class CSVHandler {
	private String filePath;
	public String[] cols;
	
	public CSVHandler(String filePath, String... cols) {
		this.filePath = filePath;
		this.cols = cols;
	}
	
	public void write(String[] line) {
		CSVWriter writer;
		try {
			writer = new CSVWriter( new FileWriter(filePath, true) );
			writer.writeNext(line);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Falha em escrver no arquivo" + filePath);
		}
	}
	
	public int getCol(String colName) {
		for(int i=0; i<cols.length; i++) {
			if( colName.equals(cols[i]) ) {
				return i;
			}
		}
		return -1;
	}
	
	public List<String[]> readLines(){
		CSVReader reader;
		List<String[]> lines = null;
		try {
			reader = new CSVReader( new FileReader(filePath) );
			lines = reader.readAll();
			lines.remove(0);
			
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Falha em escrver no arquivo" + filePath);
		}
		return lines;
	}
	
}
