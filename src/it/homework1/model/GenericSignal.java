package it.homework1.model;

import java.util.Arrays;

public abstract class GenericSignal {
	private int length;
	private double[] parteReale;
	private double[] parteImmaginaria;

	public GenericSignal(int length){
		this.length = length;
		this.parteReale = new double[length];
		this.parteImmaginaria = new double[length];
	}

	/**
	 * @param index indice
	 * @param value
	 */
	public void addParteReale(int index, double value){
		this.parteReale[index]=value;
	}

	public void addParteImmaginaria(int index, double value){
		this.parteImmaginaria[index]=value;
	}

	public int getLength(){
		return this.length;
	}

	public double getParteReale(int index){
		return this.parteReale[index];
	}

	public double getParteImmaginaria(int index){
		return this.parteImmaginaria[index];
	}

	/**
	 * Calcola l'energia del segnale
	 * @param GenericSignal signal
	 * @return energia del segnale
	 */
	public double evaluateEnergy() {
		double z;
		double somma = 0;
		for(int i=0; i<this.length; i++){
			double moduloQuadro = Math.pow(this.getParteReale(i), 2) + Math.pow(this.getParteImmaginaria(i), 2);
			somma = somma + moduloQuadro;
		}
		z=somma/this.getLength();
		return z;
	}

	/**
	 * Calcola SNR del segnale
	 * @return il valore in dB del Signal-to-noise-ratio
	 * @exception lancia l'eccezione se l'energia del rumore è negativa
	 */
	public double getSNR() throws Exception{
		double energiaRumore = this.evaluateEnergy() - 1;
		if(energiaRumore<0) {
			throw new Exception("Energia negativa");
		}
		if(energiaRumore > 0)
			return 10*Math.log10(1/energiaRumore);
		return Double.POSITIVE_INFINITY;
	}

	@Override
	public String toString() {
		return "GenericSignal [length=" + length + ", parteReale="
				+ Arrays.toString(parteReale) + ", parteImmaginaria="
				+ Arrays.toString(parteImmaginaria) + "]";
	}
	
	
}
