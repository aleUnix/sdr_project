package it.homework1.model;

public class Signal extends GenericSignal{
	
	public Signal(int length){
		super(length);
	}
	
	public void generateRandomSignal() {
		for(int i = 0; i < this.getLength(); i++) {
			double v = Math.random();
			if(v < 0.5)
				super.addParteReale(i, 1/Math.sqrt(2));
			else
				super.addParteReale(i, -1/Math.sqrt(2));
			
			double p = Math.random();
			if(p < 0.5)
				super.addParteImmaginaria(i, 1/Math.sqrt(2));
			else
				super.addParteImmaginaria(i, -1/Math.sqrt(2));
		}
	}
	
	public GenericSignal aggiungiRumore(double snr){
		GenericSignal noise = new Noise(snr, this.getLength());
		
		GenericSignal signal_noise = new Signal(this.getLength());
		for(int i=0; i<this.getLength(); i++){
			double sommaReale = this.getParteReale(i)+noise.getParteReale(i);
			signal_noise.addParteReale(i, sommaReale);
			double sommaImmaginaria = this.getParteImmaginaria(i)+noise.getParteImmaginaria(i);
			signal_noise.addParteImmaginaria(i, sommaImmaginaria);
		}
		return signal_noise;
	}
}

