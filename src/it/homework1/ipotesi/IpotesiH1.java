package it.homework1.ipotesi;

import it.homework1.model.*;


/**
 * questa classe rappresenta l'ipotesi h1 del nostro sistema cognitive radio
 * @author alessio
 *
 */

public class IpotesiH1 {
	
	public IpotesiH1(){
		
	}

	
	public double calcolaDetectionFile(GenericSignal signal, int prove, double soglia){
		int cont = 0;
		int length = signal.getLength()/prove;
		for(int i=0; i<prove; i++){
			int k=0;
			GenericSignal s = new Signal(length);
			for(int j=i*length; j<(i+1)*length; j++) {
				s.addParteReale(k, signal.getParteReale(j));
				s.addParteImmaginaria(k, signal.getParteImmaginaria(j));
				k++;
			}
			if(s.evaluateEnergy() > soglia)
				cont++;
		}
		return ((double)cont/(double)prove)*100;
	}
	
	public double calcolaDetection(Signal signal, double snr, int prove, double soglia) throws Exception {
		int cont = 0;
		for(int i = 0; i<prove; i++) {
			GenericSignal signal_noise = signal.aggiungiRumore(snr);
			if(signal_noise.evaluateEnergy() > soglia)
				cont++;
		}
		return ((double)cont/(double)prove)*100;
	}


}