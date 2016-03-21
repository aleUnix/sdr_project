package it.homework1.energyDetector;


import it.homework1.lettore.LettoreSegnale;
import it.homework1.model.*;


import java.util.Scanner;

public class Main {

	private final static String PATHFILE = "/media/alessio/Data/documenti università/SDR/HW1/Sequenza_3/output_4.dat";

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		System.out.println("Simulatore: ");
		int risp;
		int tipoSimulatore=0;

		System.out.print("\nPer la simulazione da file digitare 1, per la simulazione con segnale randomico digitare 2: ");
		tipoSimulatore = scanner.nextInt();
		if(tipoSimulatore==1 || tipoSimulatore == 2){
			do {
				if (tipoSimulatore==1) {
					do{
						Simulatore s = new Simulatore();
						LettoreSegnale l = new LettoreSegnale(PATHFILE);
						System.out.print("\nSIMULATORE: SEGNALE LETTO DA FILE: ");
						System.out.print("\nInserire il  numero di prove nelle quali viene generato di "
								+ "volta in volta un nuovo rumore (H0): ");
						int numeroProveh0 = scanner.nextInt();
						System.out.print("\nInserire il numero di prove da effettuare per H1: ");
						int numeroProveh1 = scanner.nextInt();



						GenericSignal segnale = l.letturaFile();
						s.runSimulatoreFile(segnale, numeroProveh0, numeroProveh1);

						System.out.print("Premere '1' per effettuare un'altra simulazione, '0' per uscire: ");
						risp = scanner.nextInt();

					}	while (risp != 0);

				}
				else if (tipoSimulatore==2) {
					do {
						System.out.print("\nSIMULATORE: SEGNALE RANDOMICO: ");
						System.out.print("\nInserire il  numero di prove da effettuare online: ");
						int numeroProveOn = scanner.nextInt();
						System.out.print("\nInserire il numero di prove da effettuare offline: ");
						int numeroProveOff = scanner.nextInt();
						System.out.print("\nInserire la lunghezza del segnale randomico: ");
						int lunghezza = scanner.nextInt();
						System.out.print("\nInserire il valore di SNR in db: ");
						int snr = scanner.nextInt();

						Simulatore s = new Simulatore();
						s.runSimulatore(snr, lunghezza,numeroProveOff, numeroProveOn);

						System.out.print("Premere '1' per effettuare un'altra simulazione, '0' per uscire: ");
						risp = scanner.nextInt();

					} while (risp != 0);

				}

				System.out.print("\nPer uscire digitare 0; per la simulazione da file digitare 1, per la simulazione con segnale randomico digitare 2: ");
				tipoSimulatore = scanner.nextInt();

			}
			while (tipoSimulatore!=0);
		}
		else{

			System.out.println("errore inserimento");

		}
	}
}