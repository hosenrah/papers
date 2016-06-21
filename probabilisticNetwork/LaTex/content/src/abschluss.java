public static String abgebrochen(Node abschluss) throws NeticaException {
  if(abschluss.getBelief("abgebrochen")>abschluss.getBelief("nichtAbgebrochen"){
  	  return "abgebrochen";
  }
  return "nichtAbgebrochen";
}