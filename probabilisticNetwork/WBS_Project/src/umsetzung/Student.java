package umsetzung;

import java.util.ArrayList;

public class Student{
	
	private String qualifikation;
	private String schnitt;
	private String bundesland;
	private String mathe;
	private String physik;	
	private String deutsch;	
	private String schultyp;	
	private String oltMathe;	
	private String oltDeutsch;	
	private String studierfaehigkeitstest;	
	private String alter;	
	private String qeschlecht;	
	private String jahreseinkommen; 
	private String staatsbuergerschaft; 
	private String studiengang;	
	private String zwischenkalk; 
	private String abschluss;
	
	public Student(){
		
	}
	public Student(String qualifikation, String schnitt, String bundesland,
				String mathe, String physik, String deutsch, String schultyp,
				String oltMathe, String oltDeutsch, String studierfaehigkeitstest,
				String alter, String qeschlecht, String jahreseinkommen,
				String staatsbuergerschaft) {
			super();
			this.qualifikation = qualifikation;
			this.schnitt = schnitt;
			this.bundesland = bundesland;
			this.mathe = mathe;
			this.physik = physik;
			this.deutsch = deutsch;
			this.schultyp = schultyp;
			this.oltMathe = oltMathe;
			this.oltDeutsch = oltDeutsch;
			this.studierfaehigkeitstest = studierfaehigkeitstest;
			this.alter = alter;
			this.qeschlecht = qeschlecht;
			this.jahreseinkommen = jahreseinkommen;
			this.staatsbuergerschaft = staatsbuergerschaft;
		}
		
	public Student(String qualifikation, String schnitt, String bundesland,
				String mathe, String physik, String deutsch, String schultyp,
				String oltMathe, String oltDeutsch, String studierfaehigkeitstest,
				String alter, String qeschlecht, String jahreseinkommen,
				String staatsbuergerschaft, String studiengang,
				String zwischenkalk, String abschluss) {
			super();
			this.qualifikation = qualifikation;
			this.schnitt = schnitt;
			this.bundesland = bundesland;
			this.mathe = mathe;
			this.physik = physik;
			this.deutsch = deutsch;
			this.schultyp = schultyp;
			this.oltMathe = oltMathe;
			this.oltDeutsch = oltDeutsch;
			this.studierfaehigkeitstest = studierfaehigkeitstest;
			this.alter = alter;
			this.qeschlecht = qeschlecht;
			this.jahreseinkommen = jahreseinkommen;
			this.staatsbuergerschaft = staatsbuergerschaft;
			this.studiengang = studiengang;
			this.zwischenkalk = zwischenkalk;
			this.abschluss = abschluss;
		}
	/*Get all*/
	public ArrayList<String> getAll(){
		ArrayList<String> student = new ArrayList<String>();
		student.add(this.qualifikation);
		student.add(this.schnitt);
		student.add(this.bundesland);
		student.add(this.mathe);
		//student.add(this.physik);
		student.add(this.deutsch);
		student.add(this.schultyp);
		student.add(this.oltMathe);
		student.add(this.oltDeutsch);
		student.add(this.studierfaehigkeitstest);
		student.add(this.alter);
		student.add(this.qeschlecht);
		student.add(this.jahreseinkommen);
		student.add(this.staatsbuergerschaft);
		student.add(this.studiengang);
		//student.add(this.zwischenkalk);
		student.add(this.abschluss);
		return student;
	}
	/*transform*/
	public boolean transform(){
		 // this.qualifikation = this.qualifikation Bleibt gleich
		 this.schnitt = this.toNote(this.schnitt);
		 //this.bundesland =  this.bundesland; 
		 this.mathe = this.toNote(this.mathe); //'keine' wird durch toNote() unterstützt
		 this.physik = this.toNote(this.physik);
		 this.deutsch = this.toNote(this.deutsch);
		 //this.schultyp = this.schultyp;
		 this.oltMathe = this.toOltGroup(this.oltMathe);
		 this.oltDeutsch = this.toOltGroup(this.oltDeutsch);
		 this.studierfaehigkeitstest = this.toTestGroup(this.studierfaehigkeitstest);
		 this.alter = this.toAlterGroup(this.alter);
		 //this.qeschlecht = this.geschlecht;
		 this.jahreseinkommen = this.toEinkommenGroup(this.jahreseinkommen);
		 //this.staatsbuergerschaft = this.staatsbuergerschaft;
		 //this.studiengang = this.studiengang
		 this.zwischenkalk = this.toNote(this.zwischenkalk);
		 //this.abschluss = this.toNote(this.abschluss);
		 this.abschluss = this.toAbgebrochen(this.abschluss);
		 
		 return true;
	}
	public boolean deleteUnknown(){
		if(this.qualifikation.equals("n.a.")) this.qualifikation = "*";
		if(this.schnitt.equals("n.a.")) this.schnitt = "*";
		if(this.bundesland.equals("n.a.")) this.bundesland = "*";
		if(this.mathe.equals("n.a.")) this.mathe = "*";
		if(this.physik.equals("n.a.")) this.physik  = "*";
		if(this.deutsch.equals("n.a.")) this.deutsch = "*";
		if(this.schultyp.equals("n.a."))  this.schultyp = "*";
		if(this.oltMathe.equals("n.a.")) this.oltMathe = "*";
		if(this.oltDeutsch.equals("n.a.")) this.oltDeutsch = "*";
		if(this.studierfaehigkeitstest.equals("n.a.")) this.studierfaehigkeitstest = "*";
		if(this.alter.equals("n.a.")) this.alter = "*";
		if(this.qeschlecht.equals("n.a.")) this.qeschlecht = "*";
		if(this.jahreseinkommen.equals("n.a.")) this.jahreseinkommen = "*";
		if(this.staatsbuergerschaft.equals("n.a.")) this.staatsbuergerschaft = "*";
		if(this.studiengang.equals("n.a.")) this.studiengang = "*";
		if(this.zwischenkalk.equals("n.a.")) this.zwischenkalk = "*";
		if(this.abschluss.equals("n.a.")) this.abschluss = "*";
		
		return true;
	}
	public String toNote(String note){
		try{
			Double x = Double.parseDouble(note);
			if(x==4) x=x-0.1;
			//map in 0.5 intervals
			x = Math.floor(x*2)/2;
			note = x.toString();
		}
		catch(Exception e){
			return note; //do nothing if not a number
		}
		return note;
	}
	public String toOltGroup(String olt){
		try{
			Double x = Double.parseDouble(olt);
			//map 0-100 into 10 Groups
			if(x==100) x--;
			x = Math.floor(x/10);
			olt= x.toString();
		}catch(Exception e){
			return olt; //do nothing if not a number
		}
		return olt;
	}
	public String toAlterGroup(String alter){
		try{
			Double x = Double.parseDouble(alter);
			//map into 2 Groups
			if(x<18){ 
				alter="jung";
			}else if(x<20){
				alter="teen";
			}else{
				if(x>=20) alter="teeth";
			}
			
		}catch(Exception e){
			return alter; //do nothing if not a number
		}
		return alter;
	}
	public String toTestGroup(String test){
		try{
			Double x = Double.parseDouble(test);
			//map 0-1000 into 10 Groups
			if(x==1000) x--;
			x = Math.floor(x/100);
			test= x.toString();
		}catch(Exception e){
			return test; //do nothing if not a number
		}
		return test;
	}
	public String toEinkommenGroup(String einkommen){
		try{
			Double x = Double.parseDouble(einkommen);
			//map 0-inf k into 10 Groups
			if(x<50000) return "0.0"; //in the first group
			if(x>130000) return "9.0"; //in the 10th group
			x = x/1000;
			x = Math.floor((x-50)/10)+1; //Split everithing in between into 8 Groups
			// ((51-50)/10) +1 = 1,1
			// ((60-50)/10) +1 = 2
			// ((61-50)/10) +1 = 2,1
			// ((130-50)/10) +1 = 9
			einkommen= x.toString();
		}catch(Exception e){
			return einkommen; //do nothing if not a number
		}
		return einkommen;
	}
	public String toAbgebrochen(String abgebrochen){
		if(!abgebrochen.equals("abgebrochen")&&!abgebrochen.equals("Abschluss")){
			return "nichtAbgebrochen";
		}
		return abgebrochen;
	}
		
	
	/*Geter and Setter*/
	public String getQualifikation() {
		return qualifikation;
	}

	public void setQualifikation(String qualifikation) {
		this.qualifikation = qualifikation;
	}

	public String getSchnitt() {
		return schnitt;
	}

	public void setSchnitt(String schnitt) {
		this.schnitt = schnitt;
	}

	public String getBundesland() {
		return bundesland;
	}

	public void setBundesland(String bundesland) {
		this.bundesland = bundesland;
	}

	public String getMathe() {
		return mathe;
	}

	public void setMathe(String mathe) {
		this.mathe = mathe;
	}

	public String getPhysik() {
		return physik;
	}

	public void setPhysik(String physik) {
		this.physik = physik;
	}

	public String getDeutsch() {
		return deutsch;
	}

	public void setDeutsch(String deutsch) {
		this.deutsch = deutsch;
	}

	public String getSchultyp() {
		return schultyp;
	}

	public void setSchultyp(String schultyp) {
		this.schultyp = schultyp;
	}

	public String getOltMathe() {
		return oltMathe;
	}

	public void setOltMathe(String oltMathe) {
		this.oltMathe = oltMathe;
	}

	public String getOltDeutsch() {
		return oltDeutsch;
	}

	public void setOltDeutsch(String oltDeutsch) {
		this.oltDeutsch = oltDeutsch;
	}

	public String getStudierfaehigkeitstest() {
		return studierfaehigkeitstest;
	}

	public void setStudierfaehigkeitstest(String studierfaehigkeitstest) {
		this.studierfaehigkeitstest = studierfaehigkeitstest;
	}

	public String getAlter() {
		return alter;
	}

	public void setAlter(String alter) {
		this.alter = alter;
	}

	public String getQeschlecht() {
		return qeschlecht;
	}

	public void setQeschlecht(String qeschlecht) {
		this.qeschlecht = qeschlecht;
	}

	public String getJahreseinkommen() {
		return jahreseinkommen;
	}

	public void setJahreseinkommen(String jahreseinkommen) {
		this.jahreseinkommen = jahreseinkommen;
	}

	public String getStaatsbuergerschaft() {
		return staatsbuergerschaft;
	}

	public void setStaatsbuergerschaft(String staatsbuergerschaft) {
		this.staatsbuergerschaft = staatsbuergerschaft;
	}

	public String getStudiengang() {
		return studiengang;
	}

	public void setStudiengang(String studiengang) {
		this.studiengang = studiengang;
	}

	public String getZwischenkalk() {
		return zwischenkalk;
	}

	public void setZwischenkalk(String zwischenkalk) {
		this.zwischenkalk = zwischenkalk;
	}

	public String getAbschluss() {
		return abschluss;
	}

	public void setAbschluss(String abschluss) {
		this.abschluss = abschluss;
	}
	

}