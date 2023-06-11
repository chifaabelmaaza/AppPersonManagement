package ma.emsi.applicationgestionpersonne.service;

import java.io.FileInputStream;
import java.util.List;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

public class ReadDocxFileParagraphByParagraph {
	public static void main(String[] args) {
	     try {
	       FileInputStream fis = new FileInputStream("src/main/resources/inputData.docx");
	       XWPFDocument xdoc=new XWPFDocument(OPCPackage.open(fis));
	       
	       List<XWPFParagraph> paragraphList =  xdoc.getParagraphs();
	       
	       for (XWPFParagraph paragraph: paragraphList){
	    	   
		         System.out.println(paragraph.getText()+"\n");
	            }
	       
	     } catch(Exception ex) {
		   ex.printStackTrace();
	     } 
	   }

}
