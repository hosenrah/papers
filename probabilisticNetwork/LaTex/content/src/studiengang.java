  public static String[] studiengang(Node studiengang) throws NeticaException {
  		String[] states = {"Maschinenbau", "SozialeArbeit", "Elektrotechnik", "Wirtschaftswissenschaften", "Informatik"};
		int belive = 100;
		//compare all belives and sort the array (Bubblesort)
		String temp;
		int d=0, k, g=-1;
		k= states.length-2;
		while(g<0){
			g=0;
			for(int i=0; i<=k; i++){	
				if(studiengang.getBelief(states[i])<studiengang.getBelief(states[(i+1)])){
					temp=states[i];
					states[i]=states[(i+1)];
					states[(i+1)]=temp;
					g--;	
				}	
			}
			k--;
		}
		return states;//Da gleiche Wahrscheinlichkeiten eine Rolle spielen, geben wir ein Sortiertes Array zurueck
  }