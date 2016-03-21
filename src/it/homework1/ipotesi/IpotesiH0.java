package it.homework1.ipotesi;

import it.homework1.funzioni.Functions;
import it.homework1.model.*;

/**
 * questa classe rappresenta l'ipotesi h0 del nostro sistema cognitive radio
 * @author alessio
 *
 */

public class IpotesiH0 {

	public IpotesiH0(){
	}

	/**
	 * metodo che prende in input snr, lunghezza del segnale, il numero di prove da effettuare e la probabilita di falso allarme
	 * e restituisce la soglia calcolata offline
	 * @param snr
	 * @param length
	 * @param prove
	 * @param pfa
	 * @return soglia
	 */
	public double calcoloSoglia(double snr, int length, int prove, double pfa) {
		double[] energia = this.valutaEnergie(snr, length, prove);
		double th=0;
		double media = Functions.media(energia);
		double varianza = Functions.varianza(energia);
		try {
			th = media + (Math.sqrt(2*varianza) * Functions.invErf(1-2*pfa));
		} catch (Exception e){
			System.out.println(e.getMessage());
		}
		return th;
	}
	
	private double[] valutaEnergie(double snr, int length, int prove){
		double[] energia= new double[prove];

		for(int i=0; i<prove; i++){
			GenericSignal noise = new Noise(snr, length);
			energia[i] = noise.evaluateEnergy(); 
		}
		return energia;
	}

	
}
