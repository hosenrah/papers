  public boolean writeNetica(ArrayList<Student> datenliste){
	  String csv = "Ausgabe.cas";
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