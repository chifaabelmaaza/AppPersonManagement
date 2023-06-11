package ma.emsi.applicationgestionpersonne.service;

import ma.emsi.applicationgestionpersonne.entities.Person;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;


public class ReadWriteTxtFile {
	 public static void main(String[] args)throws Exception {
	        BufferedReader br = new BufferedReader(new FileReader("src/main/resources/personInputData.txt"));

	        ArrayList<Person> list = new ArrayList<Person>();
	        Person p = null;
	        String readLine = br.readLine();

	        while(readLine != null){
	        	
	        	String [] person  = readLine.split("\\,");
	             
//	        	name + "," + age + "," + gender + "," + address + ","+ email + "," + phoneNumber + "," + dateNaiss + "," + cin
	            p = new Person();
	            p.setName(person[0].trim());
	            p.setAge(Integer.parseInt(person[1].trim()));
	            p.setGender(person[2].trim());
	            p.setAddress(person[3].trim());
	            p.setEmail(person[4].trim());
	            p.setPhoneNumber(person[5].trim());
	            p.setDateNaiss(new Date(person[6].trim()));
	            p.setCin(person[7].trim());

             list.add(p);
             readLine = br.readLine();
	        }


	       try( FileOutputStream fout = new FileOutputStream("src/main/resources/personOutputData.txt"))
	       {

	        for(Person prsn : list){
	            fout.write(prsn.toString().getBytes());
	            fout.write('\n');
	            
	            System.out.println("Person :"+prsn.toString());
	        }
	       }
	       catch (IOException e) {
	    	   System.out.println(e.getStackTrace());
		}

	    }

	
}
