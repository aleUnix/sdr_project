package it.homework1.model;
import java.util.Random;

public class Noise extends GenericSignal{
	private double pot_rumore;
	
	public Noise(double snr, int length) {
		super(length);
		generateNoise(snr);
		
	}

	private void generateNoise(double snr) {
		Random campione = null;
		double snr_linearizzato = Math.pow(10, (snr/10));
		this.pot_rumore = (1/snr_linearizzato);
		
		for(int i=0; i<this.getLength(); i++) {
			campione = new Random();
			super.addParteReale(i, campione.nextGaussian() * Math.sqrt(pot_rumore/2));
		}
		
		for(int i=0; i<this.getLength(); i++) {
			campione = new Random();
			super.addParteImmaginaria(i, campione.nextGaussian() * Math.sqrt(pot_rumore/2));
		}
	}
}
