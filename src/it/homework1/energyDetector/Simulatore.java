package it.homework1.energyDetector;

import it.homework1.ipotesi.*;
import it.homework1.model.*;

/**
 * la classe simulatore ha la responsabilità di rappresentare una simulazione di esecuzione, sia che sia da file che da input
 * 
 * @author alessio
 *
 */

public class Simulatore {
	
	private static final double PFA = Math.pow(10, -3);

	IpotesiH0 h0;
	IpotesiH1 h1;
	
	private double snr;
	private double soglia;
	private double detection;

	public Simulatore(){
		this.h0 = new IpotesiH0();
		this.h1 = new IpotesiH1();
	}
	
	/**
	 * gestisece la responsabilita di far partire la simulazione dal file
	 * @param signal_noise
	 * @param proveOffLine
	 * @param proveOnLine
	 */
	public void runSimulatoreFile(GenericSignal signal_noise, int proveOffLine, int proveOnLine){
		try{
			this.snr = signal_noise.getSNR();
			this.soglia = this.h0.calcoloSoglia(this.snr, signal_noise.getLength()/proveOnLine, proveOffLine, PFA);
			this.detection = this.h1.calcolaDetectionFile(signal_noise, proveOnLine, soglia);
			System.out.println("l'snr e': " + this.snr + "\nla soglie e': " + this.soglia + "\nla detection è: " + this.detection +"\n");
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
	}
	
	/**
	 * gestisce la responsabilità di far partire la simulazione da input
	 * @param snr
	 * @param length
	 * @param proveOffLine
	 * @param proveOnLine
	 */
	public void runSimulatore(double snr, int length, int proveOffLine, int proveOnLine) {
		try{
			this.snr = snr;
			Signal signal = new Signal(length);
			signal.generateRandomSignal();
			this.soglia = this.h0.calcoloSoglia(snr, signal.getLength(), proveOffLine, PFA);
			this.detection = this.h1.calcolaDetection(signal, snr, proveOnLine, soglia);
			System.out.println("snr: " + this.snr + "\nsoglia: " + this.soglia + "\ndetection: " + this.detection);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	

	public IpotesiH0 getH0() {
		return h0;
	}

	public void setH0(IpotesiH0 h0) {
		this.h0 = h0;
	}

	public IpotesiH1 getH1() {
		return h1;
	}

	public void setH1(IpotesiH1 h1) {
		this.h1 = h1;
	}
	
	
}
