package umsetzung;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
 
public class Discretize{
 
  public static void main(String[] args) {
 	
	Discretize obj = new Discretize();
	ArrayList<Student> datenliste;
	
	datenliste = obj.read();
	obj.write(datenliste);
	
	datenliste = obj.transform(datenliste);
	datenliste = obj.deleteUnknown(datenliste);
	obj.writeNetica(datenliste);
	
 
  }
 
  public ArrayList<Student> read() {
	  //auf länge des arrays prüfen, bei eingabe test länge 16 bei parameter -test
	  //ohne -test länge 0 bis 14
 
	ArrayList<Student> datenliste = new ArrayList<Student>();
	
	String csv = Hochschulqualifikation.inputPath;
	BufferedReader br = null;
	String line = "";
	String split = ";";
 
	try {
 
		br = new BufferedReader(new FileReader(csv));
		while ((line = br.readLine()) != null) {
 		   	//Replace all Special characters of german language Netica does not understand
			
			line = line.replaceAll(",", ".").replaceAll("ä", "ae").replaceAll("Ä", "Ae").replaceAll("ö", "oe").replaceAll("Ö", "Oe").replaceAll("ü", "ue").replaceAll("Ü", "Ue").replaceAll("ß", "ss").replaceAll(" ", "");
		    // use comma as separator
			String[] data = line.split(split);
 		   	
			/*	The Array contains the information in the Order: 			
			*	Qualifikation	Schnitt	Bundesland	Mathe	Physik	Deutsch	Schultyp	OLT-Mathe	OLT-Deutsch	
			*	Studierfähigkeitstest	Alter	Geschlecht	Jahreseinkommen der Eltern	Staatsbürgerschaft	
			* 	Studiengang	Zwischenkalk	Abschluss
			*/
			
			//create an ArrayList containing all Student Objects
			datenliste.add(new Student(data[0],  data[1],  data[2],  data[3],  data[4],  data[5],  data[6],  data[7],  data[8],  data[9],  data[10],  data[11],  data[12],  data[13], data[14], data[15], data[16])); 
		}
 
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} finally {
		if (br != null) {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
 
	System.out.println("Read done");
	return datenliste;
  }
  
  public ArrayList<Student> transform(ArrayList<Student> datenliste){
	  for(Student student : datenliste){
		  student.transform();
	  }
	  System.out.println("Transform to netica states done");
	  return datenliste;
  }
  public ArrayList<Student> deleteUnknown(ArrayList<Student> datenliste){
	  for(Student student : datenliste){
		  student.deleteUnknown();
	  }
	  System.out.println("Replacement of 'n.a.'  to '*' done");
	  return datenliste;
  }
  
  public boolean write(ArrayList<Student> datenliste){
	  String csv = "Ausgabe.csv";
	  BufferedWriter bw = null;
	  
	  try{
		  bw = new BufferedWriter(new FileWriter(csv));
		  
		  for(Student student : datenliste){
			  for(String a: student.getAll()){   
				  bw.write(a + ";");
			  }
			  bw.write("\n");
		  }
		  bw.flush();
		  bw.close();
		
	  }catch(IOException e){
		  e.printStackTrace();
	  }finally {
		  if (bw != null) {
			  try {
				  bw.close();
				  System.out.println("Write done");
				  return true;
			  } catch (IOException e) {
				  e.printStackTrace();
			  }
		  }
	  }	
	  return false;  
  }
 //abgebrochen oder nicht abgebrochen ausgeben
 //die 2 vorhersagen aus bubblesort
 public boolean writeVorhersage(){
	 return true;
 }
  
  public boolean writeNetica(ArrayList<Student> datenliste){
	  String csv = Hochschulqualifikation.outputPath;
	  BufferedWriter bw = null;
	  int counter = 0;
	  
	  try{
		  bw = new BufferedWriter(new FileWriter(csv));
		 
		  bw.write("// ~->[CASE-1]->~");
		  bw.write("\n");
		  bw.write("\n");
		  
		  for(Student student : datenliste){
			  if(counter == 0){
				  bw.write("IDnum\t");
			  }else{
			  	   bw.write(counter+"\t");
			  }
			  counter++;
			  
			  for(String a: student.getAll()){   
				  bw.write(a + "\t");
			  }
			  bw.write("\n");
		  }
		  bw.flush();
		  bw.close();
		
	  }catch(IOException e){
		  e.printStackTrace();
	  }finally {
		  if (bw != null) {
			  try {
				  bw.close();
				  System.out.println("Write for Netica done");
				  return true;
			  } catch (IOException e) {
				  e.printStackTrace();
			  }
		  }
	  }	
	  return false;  
  }
 
}