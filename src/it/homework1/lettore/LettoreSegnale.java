package it.homework1.lettore;



import it.homework1.model.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * questa classe ha la responsabilità di gestire il file da leggere
 * @author alessio
 *
 */
public class LettoreSegnale {

	String percorsoFile;

	public LettoreSegnale(String percorsoFile){
		this.percorsoFile=percorsoFile;
	}

	public GenericSignal letturaFile() {
		Scanner scan;
		File file = new File(percorsoFile);
		int length = this.getLunghezzaFile();
		GenericSignal signal = new Signal(length);
		try {
			scan = new Scanner(file);
			int i=0;
			while(scan.hasNext()) {
				signal.addParteReale(i, Double.parseDouble(scan.next()));
				signal.addParteImmaginaria(i,Double.parseDouble(scan.next()));
				i++;
			}
			scan.close();
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
		return signal;
	}

	public int getLunghezzaFile(){
		BufferedReader reader;
		int righeFile = 0;
		try {
			reader = new BufferedReader(new FileReader(this.percorsoFile));
			while (reader.readLine() != null) 
				righeFile++;
			reader.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return righeFile;
	}
}
