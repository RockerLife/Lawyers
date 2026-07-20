package com.pdf;

import com.util.InetLogger;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.apache.pdfbox.util.TextPosition;

import com.userbo.LawyersUtils;

public class PrintTextLocations extends PDFTextStripper {
	private static final InetLogger logger = InetLogger.getInetLogger(PrintTextLocations.class);
	
	public static StringBuilder tWord = new StringBuilder();
	public static String seek;
	public static String[] seekA;
	public static List wordList = new ArrayList();
	public static boolean is1stChar = true;
	public static boolean lineMatch;
	public static int pageNo = 1;
	public static double lastYVal;
	static List<String> words = new ArrayList<String>();

    public PrintTextLocations() throws IOException {
        super.setSortByPosition(true);
    }

    public static void main(String[] args) throws Exception {

        PDDocument document = null;
        File input = null;
        try {
            input = new File("D:\\Tomcat\\webapps\\LawyersIns\\data\\Lawyers_Application_QN-0056480.pdf");
        	//File input = new File("D:\\SvnProtexureWorking\\LawyersIns\\web\\data\\Policy Form_QN-0066246.pdf");
            document = PDDocument.load(input);    
            new PrintTextLocations().printSubwords(document, "Policy term", 1); 
                       
            /*PrintTextLocations stripper = new PrintTextLocations();
            stripper.setSortByPosition( true );
            stripper.setStartPage(1);
            stripper.setEndPage(1);
 
            Writer dummy = new OutputStreamWriter(new ByteArrayOutputStream());
            stripper.writeText(document, dummy);
            
            // print words
            int k = 0;
            for(int i=0; i<words.size(); i++){
            	if("Number:".equals(words.get(i))){
            		k = i;
            		break;
            	}              
            }
            
            System.out.println("Policy No..." + words.get(k+2));*/
            
        } finally {
        	document.close();
        	/*code by sukhi 26/09/2018*/
            input=null;
        }
    }    
    /*@Override
    protected void writeString(String str, List<TextPosition> textPositions) throws IOException {
        String[] wordsInStream = str.split(getWordSeparator());
        if(wordsInStream!=null){
            for(String word :wordsInStream){
                words.add(word);
            }
        }
    }*/
    
    public Map<String, Double> printSubwords(String filePath, String searchTerm, int pageNo) throws IOException
    {
    	Map <String, Double> mapXY = new HashMap <String, Double>();        
    	PDDocument document = null;
    	File input = null;
        try {
            input = new File(filePath);
            document = PDDocument.load(input);    
            mapXY = new PrintTextLocations().printSubwords(document, searchTerm, pageNo);  
            
        }  finally {
        	document.close();
        	/*code by sukhi 26/09/2018*/
            input=null;
        }        
        return mapXY;
    }
    
    public Map<String, Double> printSubwords(PDDocument document, String searchTerm, int pageNo) throws IOException
    {
    	Map <String, Double> mapXY = new HashMap <String, Double>();
        Double yPos = 600.00;
        Double xPos = 300.00;
        List<TextPositionSequence> hits = findSubwords(document, pageNo, searchTerm);
        for (TextPositionSequence hit : hits)
        {
            //TextPosition lastPosition = hit.textPositionAt(hit.length() - 1);
            yPos = new Double(hit.getY()); //1122.519685039
            //yPos = yPos/1010;
            yPos = (yPos-13.0)/871.718;
            xPos = new Double(hit.getX());
            //xPos = xPos/880;
            xPos = xPos/616;
            
            yPos = Double.parseDouble((LawyersUtils.roundCeiling(yPos, 5)).toString());
            xPos = Double.parseDouble((LawyersUtils.roundCeiling(xPos, 5)).toString());
            
            mapXY.put("yPos", yPos);
            mapXY.put("xPos", xPos);
            logger.debug("hit.getX() " + hit.getX() + " hit.getY() " + hit.getY() );
        }
        
        return mapXY;
    }
    
    public void printSubwords(PDDocument document, String searchTerm) throws IOException
    {
        //System.out.printf("* Looking for '%s'\n", searchTerm);
        for (int page = 1; page <= document.getNumberOfPages(); page++)
        {
            List<TextPositionSequence> hits = findSubwords(document, page, searchTerm);
            for (TextPositionSequence hit : hits)
            {
                TextPosition lastPosition = hit.textPositionAt(hit.length() - 1);
                logger.debug("hit.getX() " + hit.getX() +
                		" hit.getY() " + hit.getY() + 
                		" lastPosition.getXDirAdj() " + lastPosition.getXDirAdj() +
                		" lastPosition.getYDirAdj() " + lastPosition.getYDirAdj());
            }
        }
    }
    
    
    List<TextPositionSequence> findSubwords(PDDocument document, int page, final String searchTerm) throws IOException
    {
        final List<TextPositionSequence> hits = new ArrayList<TextPositionSequence>();
        PDFTextStripper stripper = new PDFTextStripper()
        {
            @Override
            protected void writeString(String text, List<TextPosition> textPositions) throws IOException
            {
                TextPositionSequence word = new TextPositionSequence(textPositions);
                String string = word.toString();

                int fromIndex = 0;
                int index;
                while ((index = string.indexOf(searchTerm, fromIndex)) > -1)
                {
                    hits.add(word.subSequence(index, index + searchTerm.length()));
                    fromIndex = index + 1;
                }
                super.writeString(text, textPositions);
            }
        };

        stripper.setSortByPosition(true);
        stripper.setStartPage(page);
        stripper.setEndPage(page);
        stripper.getText(document);
        return hits;
    }   
    
    class TextPositionSequence implements CharSequence
    {

        final List<TextPosition> textPositions;
        final int start, end;
        
        public TextPositionSequence(List<TextPosition> textPositions)
        {
            this(textPositions, 0, textPositions.size());
        }

        public TextPositionSequence(List<TextPosition> textPositions, int start, int end)
        {
            this.textPositions = textPositions;
            this.start = start;
            this.end = end;
        }

        public int length()
        {
            return end - start;
        }

        public char charAt(int index)
        {
            TextPosition textPosition = textPositionAt(index);
            String text = textPosition.getCharacter();
            return text.charAt(0);
        }

        public TextPositionSequence subSequence(int start, int end)
        {
            return new TextPositionSequence(textPositions, this.start + start, this.start + end);
        }

        @Override
        public String toString()
        {
            StringBuilder builder = new StringBuilder(length());
            for (int i = 0; i < length(); i++)
            {
                builder.append(charAt(i));
            }
            return builder.toString();
        }

        public TextPosition textPositionAt(int index)
        {
            return textPositions.get(start + index);
        }

        public float getX()
        {
            return textPositions.get(start).getXDirAdj();
        }

        public float getY()
        {
            return textPositions.get(start).getYDirAdj();
        }

        public float getWidth()
        {
            TextPosition first = textPositions.get(start);
            TextPosition last = textPositions.get(end);
            return last.getWidthDirAdj() + last.getXDirAdj() - first.getXDirAdj();
        }

    }


  }
