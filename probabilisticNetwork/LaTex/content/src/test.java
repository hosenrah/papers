  //Test every cases and Compare if in first two elemensts of list
  for(Student student : datenliste){
	if(student.getQualifikation().equals("Qualifikation")) continue;

	qualifikation.finding().clear();
	schnitt.finding().clear();
 	[...]
	
	qualifikation.finding().enterState(student.getQualifikation());
	schnitt.finding().enterState(translate(student.getSchnitt()));
	[...]
 	if(!student.getSchultyp().equals("*"))schultyp.finding().enterState(student.getSchultyp());
	if(!student.getStudierfaehigkeitstest().equals("*"))studierfaehigkeitstest.finding().enterState(translate(student.getStudierfaehigkeitstest()));

	//test for match 
	studiengaenge = studiengang(studiengang);
	if(studiengaenge[0].equals(student.getStudiengang()) || studiengaenge[1].equals(student.getStudiengang()))counter ++;
	
  }
  errorrate = 100-((double) counter/ ((double) datenliste.size()-1))*100;
  
  System.out.println("Errorrate: "+ errorrate);