package umsetzung;

import java.util.ArrayList;
import norsys.netica.*;

public class Hochschulqualifikation {

	public static String inputPath;
	public static String outputPath = "output.csv";
	
	public static void main(String[] args) {

		// get input parameters
		String param = "";
		String dnePath = "";
		try {
			if(args[0].equals("-test")){
				param = args[0];
				dnePath = args[1];
				inputPath = args[2];
			}else{
				dnePath = args[0];
				inputPath = args[1];
			}

		} catch (Exception e) {
			System.out.println("Usage: ");
			System.out.println("java -Djava.library.path=/YOUR/PATH/TO/NETICA/NeticaJ_504/bin -jar Hochschulqualifikation.jar net_file.dne source_file.csv");
			System.exit(-1);
		}

		Float belief;

		try {

			System.out.println("\nPrognose der Hochschulqualifikation:");

			Environ env = new Environ(null);

			// einlesen der mit Netica vorbereiteten Netzdatei
			Net net = new Net(new Streamer(dnePath));

			// extrahieren von Knotenobjekten aus der gegebenen Datei
			// um vom Programm aus auf diese Ereignisse zugreifen zu
			// koennen

			Node qualifikation = net.getNode("Qualifikation");
			Node schnitt = net.getNode("Schnitt");
			Node bundesland = net.getNode("Bundesland");
			Node mathe = net.getNode("Mathe");
			// Node physik = net.getNode("Physik");
			Node deutsch = net.getNode("Deutsch");
			Node schultyp = net.getNode("Schultyp");
			Node oltMathe = net.getNode("OltMathe");
			Node oltDeutsch = net.getNode("OltDeutsch");
			Node studierfaehigkeitstest = net
					.getNode("Studierfaehigkeitstest");
			Node alter = net.getNode("Alter");
			Node qeschlecht = net.getNode("Geschlecht");
			Node jahreseinkommen = net.getNode("Jahreseinkommen");
			Node staatsbuergerschaft = net.getNode("Staatsbuergerschaft");
			Node studiengang = net.getNode("Studiengang");
			Node abschluss = net.getNode("Abschluss");

			// erzeugen eines ausfuehrbaren Bayesschen Netzes aus den
			// Angaben in der Datei

			net.compile();
		
			String[] studiengaenge = studiengang(studiengang);
			
			ArrayList<Student> datenliste;
			// Read cases and store as students
			Discretize obj = new Discretize();
			datenliste = obj.read();
			datenliste = obj.transform(datenliste);
			datenliste = obj.deleteUnknown(datenliste);
			
			
			// Test every cases and Compare if in first two elemensts of
			double errorrate = 0;
			int counter = 0;
		
			for (Student student : datenliste) {
				if (student.getQualifikation().equals("Qualifikation"))
					continue;

				qualifikation.finding().clear();
				schnitt.finding().clear();
				bundesland.finding().clear();
				mathe.finding().clear();
				deutsch.finding().clear();
				schultyp.finding().clear();
				oltMathe.finding().clear();
				oltDeutsch.finding().clear();
				studierfaehigkeitstest.finding().clear();
				alter.finding().clear();
				qeschlecht.finding().clear();
				jahreseinkommen.finding().clear();
				staatsbuergerschaft.finding().clear();

				if (!student.getQualifikation().equals("*"))
					qualifikation.finding().enterState(student.getQualifikation());
				if (!student.getSchnitt().equals("*"))
					schnitt.finding().enterState(translate(student.getSchnitt()));
				if (!student.getBundesland().equals("*"))
					bundesland.finding().enterState(translate(student.getBundesland()));
				if (!student.getMathe().equals("*"))
					mathe.finding().enterState(translate(student.getMathe()));
				if (!student.getDeutsch().equals("*"))
					deutsch.finding().enterState(translate(student.getDeutsch()));
				if (!student.getSchultyp().equals("*"))
					schultyp.finding().enterState(student.getSchultyp());
				if (!student.getOltMathe().equals("*"))
					oltMathe.finding().enterState(translate(student.getOltMathe()));
				if (!student.getOltDeutsch().equals("*"))
					oltDeutsch.finding().enterState(translate(student.getOltDeutsch()));
				if (!student.getStudierfaehigkeitstest().equals("*"))
					studierfaehigkeitstest.finding().enterState(translate(student.getStudierfaehigkeitstest()));
				if (!student.getAlter().equals("*"))
					alter.finding().enterState(student.getAlter());
				if (!student.getQeschlecht().equals("*"))
					qeschlecht.finding().enterState(student.getQeschlecht());
				if (!student.getJahreseinkommen().equals("*"))
					jahreseinkommen.finding().enterState(translate(student.getJahreseinkommen()));
				if (!student.getStaatsbuergerschaft().equals("*"))
					staatsbuergerschaft.finding().enterState(student.getStaatsbuergerschaft());
				
				
				studiengaenge = studiengang(studiengang);
				
				if (param.equals("-test")) {
				// test for match
					if (studiengaenge[0].equals(student.getStudiengang())|| studiengaenge[1].equals(student.getStudiengang()))
						counter++;
				}else{
					//abgebrochen oder nicht? und 2 Studiengänge
					System.out.println(abgebrochen(abschluss) + " mit " + abschluss.getBelief(abgebrochen(abschluss)) + ";\t\t" + studiengaenge[0] + " oder " + studiengaenge[1] + ";");
				}

			}
		
			if (param.equals("-test")) {
				errorrate = 100 - ((double) counter / ((double) datenliste.size() - 1)) * 100;
				System.out.println("Studiengang Errorrate: " + errorrate);
			}else{
				System.out.println("Done");
			}
	


		} catch (Exception e) {
			e.printStackTrace();
		}
		
			
	}
	
	
	/*
	 * +++++++++++++++ACHTUNG aenerungen auch in Latex übertragen!!+++++++++++++++
	 */
	public static String abgebrochen(Node abschluss) throws NeticaException {
		if (abschluss.getBelief("abgebrochen") > abschluss
				.getBelief("nichtAbgebrochen")) {
			return "abgebrochen";
		}
		return "nichtAbgebrochen";
	}

	public static String[] studiengang(Node studiengang) throws NeticaException {
		String[] states = { "Maschinenbau", "SozialeArbeit", "Elektrotechnik",
				"Wirtschaftswissenschaften", "Informatik" };
		int belive = 100;
		// compare all belives and sort the array (Bubblesort)
		String temp;
		int d = 0, k, g = -1;
		k = states.length - 2;
		while (g < 0) {
			g = 0;
			for (int i = 0; i <= k; i++) {
				if (studiengang.getBelief(states[i]) < studiengang
						.getBelief(states[(i + 1)])) {
					temp = states[i];
					states[i] = states[(i + 1)];
					states[(i + 1)] = temp;
					g--;
				}
			}
			k--;
		}
		return states;// Da gleiche Wahrschinlichkeiten eine Rolle spielen,
						// geben wir ein Sortiertes Array zurück
	}

	public static String translate(String name) {
		try {
			Double x = Double.parseDouble(name);
			char[] newName = new char[4];
			char[] oldName = name.toCharArray();
			newName[0] = 's';
			newName[1] = oldName[0];
			newName[2] = 'o';
			newName[3] = oldName[2];
			name = String.valueOf(newName);
			name = name.replace("-", "_");
		} catch (Exception e) {
			return name.replace("-", "_"); // remove -
		}
		return name;
	}
}
