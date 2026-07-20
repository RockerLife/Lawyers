package com.util;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;
/**
 * @author Nilesh
 *
 * TODO don't forget to add the javadoc!
 */
/**
 * @author satyendras
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public abstract class IOUtils
{
	private static InetLogger logger = InetLogger.getInetLogger(IOUtils.class);
	
	/**
	 * e.g.
	 * baseFolder - C:\OutlineSys\PowerRAD\src\
	 * directoryStructure - com\metaapp\ormapping\domain
	 */
	public static void createDirectoryStructure(String baseFolder, String directorySructure){
		
		File f = null;
		
		try {
		    if(baseFolder==null || directorySructure==null)
		        return;
		    
		    if(directorySructure.contains("."))
		    	directorySructure = directorySructure.substring(0,directorySructure.lastIndexOf("\\") + 1);
		    
		    StringTokenizer strTokenizer = new StringTokenizer(directorySructure, "\\");
		    
		    while(strTokenizer.hasMoreTokens()){
		        String folder = strTokenizer.nextToken(); 
		        baseFolder = baseFolder + folder;
		        f = new File(baseFolder);
				if( !f.exists() )
					f.mkdir();
				
				baseFolder = baseFolder + "\\";
			}
		}catch(Exception ex) {
			logger.error("Unexpected error", ex);
		}finally {
			/*code by sukhi 26/09/2018*/
			f=null;
		}
	}
	
	public static void createDirectory(String baseFolder, String directorySructure){
		
		File f = null;
		
		try {
		    if(baseFolder==null || directorySructure==null)
		        return;
		    
		    String token = "";
		    
		   if(directorySructure.contains("//"))
		    	token = "//";
		    else if(directorySructure.contains("\\"))
		    	token = "\\";
		    else if(directorySructure.contains("/"))
		    	token = "/";
				
			 if(directorySructure.contains("."))
		    	directorySructure = directorySructure.substring(0,directorySructure.lastIndexOf(token)+token.length() );
		    
		    StringTokenizer strTokenizer = new StringTokenizer(directorySructure, token);
		    
		    while(strTokenizer.hasMoreTokens()){
		        String folder = strTokenizer.nextToken(); 
		        baseFolder = baseFolder + folder;
		        f = new File(baseFolder);
				if( !f.exists() )
					f.mkdir();
				
				baseFolder = baseFolder + token;
			 }
		}catch(Exception ex) {
			logger.error("Unexpected error", ex);
		}finally {
			/*code by sukhi 26/09/2018*/
			f=null;
		}
	}

	/**
	 * This method is used to get all the files within a specified directory.
	 * @param rootDirPath
	 * @return List
	 */
	public static List getFiles(String rootDirPath)
	{
		return getFiles(rootDirPath,null);
	}
	
	/**
	 * This method is used to get all the files within a specified directory and with specified extensions.
	 * @param rootDirPath
	 * @param extensions
	 * @return List
	 */
	public static List getFilesEx(String rootDirPath, String[] extensions)
	{
		List fileList = new ArrayList();
		
		if(extensions == null)
			return getFiles(rootDirPath,null);
		
		for( int i = 0; i < extensions.length; i++)
			fileList.addAll(getFiles(rootDirPath,extensions[i]));
		
		return fileList;
	}
	
	/**
	 * This method is used to get all the files within a specified directory and with specified extension.
	 * @param rootDirPath
	 * @param extension
	 * @return List
	 */
	public static List getFiles(String rootDirPath, String extension)
	{
		File tempFl = null;
		File[] files = null;
		File rootDir = null;
		List fileList = new ArrayList();
		try {
		
			rootDir = new File(rootDirPath);
			
			if(rootDir == null || !rootDir.isDirectory())
				return fileList;
			
			files = rootDir.listFiles();
			
			for( int i=0; i < files.length; i++ )
			{
				tempFl = files[i];
				if( tempFl.isFile() )
					if( extension != null ){
						if(tempFl.getAbsolutePath().contains(extension))
							fileList.add(tempFl);
					}else
						fileList.add(tempFl);
			}
		
		}catch(Exception ex) {
			logger.error("Unexpected error", ex);
		}finally {
		
			/*code by sukhi 26/09/2018*/
			tempFl=null;
			files=null;
			rootDir=null;
		}
		return fileList;
	}

	
	public static void main(String args[])
	{
//	    createDirectoryStructure("C:\\OutlineSys\\PowerRAD\\src\\", "com\\test\\test\\testaa\\testsbgsg");
//		System.out.println(getFilesEx("C:/OutlineSys/PowerRAD/src/XML/crm/ibatis/maps",new String[]{".scc"}));
		try {
//			deleteFile("C://NewTextDocument.txt");
			createDirectory("","C://new1//new2//new.txt");
			
		} catch (Exception e) {
			logger.error("Unexpected error", e);
		}
		
	}
	
	public static Properties readResourceAsProperties(String resource) throws Exception
	{
		InputStream in = IOUtils.class.getResourceAsStream(resource);
		try
		{
			Properties props = new Properties();
			props.load(in);
			return props;
		}finally
		{
			if(in!=null)
				in.close();
		}
	}
	
	public static boolean fileExists(String filename) throws Exception
	{
		return new File(filename).isFile();
	}

    public static void writeToFile(String fileName, String strbuf) throws Exception
    {
        FileOutputStream fout = null;
        
        try
        { 
             
            fout = new FileOutputStream(fileName);
            fout.write(strbuf.toString().getBytes());
//          File f = new File(fileName);
            
        } catch (Exception e)
        {
            logger.error(e.getMessage());
            return;
        } finally
        {
            if (fout != null)
                fout.close();
        }
    }
	public static void writeToFile(String fileName, StringBuffer strbuf) throws Exception
	{
	    FileOutputStream fout = null;
	    //logger.debug("Test..."+strbuf.length());
		try
		{ 
		     
			fout = new FileOutputStream(fileName);
			fout.write(strbuf.toString().getBytes());
//			File f = new File(fileName);
		    
		} catch (Exception e)
		{
			logger.error(e.getMessage());
			logger.error("Unexpected error", e);
			return;
		} finally
		{
			if (fout != null)
				fout.close();
		}
	}
	
	public static void appendToFile(String fileName, StringBuffer strbuf) throws Exception
	{
		FileOutputStream fout = null;
		
		try
		{
			fout = new FileOutputStream(fileName, true);
			fout.write(strbuf.toString().getBytes());
		} catch (Exception e)
		{
			logger.trace(e);
			return;
		} finally
		{
			if (fout != null)
				fout.close();
		}
	}
	
	public static void deleteFile(String fileName) throws Exception
	{
		File f = null;
		try
		{
			f = new File(fileName);
			if (f != null)
				f.delete();
		} catch (Exception e) {
			logger.trace(e);
		}finally {
			/*code by sukhi 26/09/2018*/
			f=null;
		}
	}

	public static String readFile(String filename) throws Exception
	{
		FileInputStream fileStream = null;
		try
		{
			fileStream = new FileInputStream(filename);
			return readFile(fileStream);
		}finally
		{
			if (fileStream != null)
				fileStream.close();
		}
	}
	
	public static String readFile(InputStream fileStream) throws Exception
	{
		BufferedReader reader = null;
		StringBuffer strbuf = null;
		try
		{
			reader = new BufferedReader(new InputStreamReader(fileStream));
			boolean endFlag = true;
			String line = null;
			strbuf = new StringBuffer(300);
			while (endFlag)
			{
				line = reader.readLine();
				if (line != null)
					strbuf.append(line).append("\n");
				if (line == null)
					endFlag = false;
			}
		} finally
		{
			if (reader != null)
				reader.close();
		}
		return strbuf.toString();
	}
	
	public static byte[] readFileInBinary(String fileName) throws Exception
	{
		try (DataInputStream din = new DataInputStream(new FileInputStream(fileName)))
		{
			// available stream to be read
	        int length = din.available();
	         
	         // create buffer
	        byte[] fileBytes = new byte[length];
			
			
			din.readFully(fileBytes);
			
			return fileBytes;
			
			/*List bytes = new ArrayList();
			while (din.available() !=0)
			{
				bytes.add(new Byte(din.readByte()));
			} 
			byte fileBytes[] = new byte[bytes.size()];
			for(int i=0; i<bytes.size(); i++)
			{
				Byte b = (Byte) bytes.get(i);
				fileBytes[i] = b.byteValue();
			}
			return fileBytes;*/
		}
	}
	
	public static void seralizeObjectToFile(Object obj, String fileName)
	throws Exception
	{
		File f = null;
		try
		{
			f = new File(fileName);
			if(f != null)
				f.delete();
			
		} catch (Exception e)
		{
			logger.error("Error delete file " + fileName);
			logger.trace(e);
		}finally {
			/*code by sukhi 26/09/2018*/
			f=null;
		}
		try (FileOutputStream fout = new FileOutputStream(fileName);
				ObjectOutputStream oout = new ObjectOutputStream(fout))
		{
			oout.writeObject(obj);
		}
	}
	
	public static Object deseralizeObjectFromFile(String fileName) throws Exception
	{
		try (FileInputStream fin = new FileInputStream(fileName);
				ObjectInputStream oin = new ObjectInputStream(fin))
		{
			return oin.readObject();
		}
	}
	
	public static void copy(String src, String destination) {
		File f1 = null;
		File f2 = null;
		
		try {
			if (src == null || destination == null)
				return;
			
			createDirectory("",destination);
			
			f1 = new File(src);
			f2 = new File(destination);
	
			if( f1.isDirectory() && f2.isDirectory() )		
				copyDirectory(f1,f2);
			else if( f1.isFile()  && !f2.isDirectory())	
				writeToFile(destination,new StringBuffer(readFile(src)));
			else
				logger.warn("Can not copy [" + src + " ] to [" + destination + " ] ........." );			
		}catch(Exception ex){
			logger.error("Unexpected error", ex);
			logger.error("error in copy in io utils:::"+ex.getMessage());
		}finally {
			/*code by sukhi 26/09/2018*/
			f2=null;
			f1=null;
		}
	}
	
	//*******************  Methods Added by ZUBAIR on 07th April 2006  ********************  ///
	
	public static void copyDirectory(File srcDir, File dstDir) throws IOException {
        if (srcDir.isDirectory()) {
            if (!dstDir.exists()) {
                dstDir.mkdir();
            }
    
            String[] children = srcDir.list();
            for (int i=0; i<children.length; i++) {
                copyDirectory(new File(srcDir, children[i]),
                                     new File(dstDir, children[i]));
            }
        } else {
           
            copy(srcDir, dstDir);
        }
    }

    public static void copy(File src, File dst) throws IOException {
		try (InputStream in = new FileInputStream(src);
				OutputStream out = new FileOutputStream(dst)) {
			byte[] buf = new byte[1024];
			int len;
			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
		}
    }
   
    // ***********************      Ended by ZUBAIR        ***********************************// 
    
    public static List getFoldersList(String rootDirPath){
    	
    	File rootDir = null;
    	File tempFl = null;
    	File[] files = null;
    	List fileList = new ArrayList();
    	
    	try {
			
			rootDir = new File(rootDirPath);
			
			if(rootDir == null || !rootDir.isDirectory())
				return fileList;
			
			files = rootDir.listFiles();
			
			for(int i=0; i < files.length; i++ ){
				tempFl = files[i];
				if( tempFl.isDirectory())
					fileList.add(tempFl.getName());
			}	
    	}catch(Exception ex){
    		logger.error("Unexpected error", ex);
    	}finally {
			/*code by sukhi 26/09/2018*/
			tempFl=null;
			files=null;
			rootDir=null;
    	}
		
		return fileList;
	}
    
}
