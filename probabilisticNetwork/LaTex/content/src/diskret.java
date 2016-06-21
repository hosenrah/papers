public boolean transform(){
	 this.deleteUnknown();
	 this.schnitt = this.toNote(this.schnitt);
	 this.mathe = this.toNote(this.mathe); 
	 this.physik = this.toNote(this.physik);
	 this.deutsch = this.toNote(this.deutsch);
	 this.oltMathe = this.toOltGroup(this.oltMathe);
	 this.oltDeutsch = this.toOltGroup(this.oltDeutsch);
	 this.studierfaehigkeitstest = this.toTestGroup(this.studierfaehigkeitstest);
	 //this.alter = this.toAlterGroup(this.alter);
	 this.jahreseinkommen = this.toEinkommenGroup(this.jahreseinkommen);
	 this.zwischenkalk = this.toNote(this.zwischenkalk);
	 this.abschluss = this.toNote(this.abschluss);
	 //this.abschluss = this.toAbgebrochen(this.abschluss);
	 return true;
}
public boolean deleteUnknown(){
	if(this.schultyp.equals("n.a.")) this.schultyp = "*";
	if(this.studierfaehigkeitstest.equals("n.a.")) this.studierfaehigkeitstest = "*";
	return true;
}
public String toNote(String note){
	try{
		Double x = Double.parseDouble(note);
		if(x==4) x=x-0.1;
		//map in 0.5 intervals	
		x = Math.floor(x*2)/2;
		note = x.toString();
	}catch(Exception e){
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
		einkommen= x.toString();
	}catch(Exception e){
		return einkommen; //do nothing if not a number
	}
	return einkommen;
}