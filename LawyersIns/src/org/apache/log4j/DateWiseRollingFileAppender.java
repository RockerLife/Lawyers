package org.apache.log4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.helpers.CountingQuietWriter;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.helpers.OptionConverter;
import org.apache.log4j.spi.LoggingEvent;

public class DateWiseRollingFileAppender extends FileAppender{
	String actualFileName = null;
	protected long maxFileSize = 10485760L;
	protected int maxBackupIndex = 1;
  
	public DateWiseRollingFileAppender() {}
  
	public DateWiseRollingFileAppender(Layout layout, String filename, boolean append) throws IOException{
		super(layout, filename, append);
	}
  
	public DateWiseRollingFileAppender(Layout layout, String filename)throws IOException{
		super(layout, filename);
	}
  
	public int getMaxBackupIndex(){
		return this.maxBackupIndex;
	}
  
	public long getMaximumFileSize(){
		return this.maxFileSize;
	}
  
	public void rollOver(){
		
		File target1 = null;
		File target = null;
		File file = null;
		
		try{
		LogLog.debug("rolling over count=" + ((CountingQuietWriter)this.qw).getCount());
		LogLog.debug("maxBackupIndex=" + this.maxBackupIndex);
		if (this.maxBackupIndex > 0){
			file = new File(this.fileName + '.' + this.maxBackupIndex);
			if (file.exists()) {
				file.delete();
			}
			
			for (int i = this.maxBackupIndex - 1; i >= 1; i--){
				file = new File(this.fileName + "." + i);
				if (file.exists()){
					target = new File(this.fileName + '.' + (i + 1));
					LogLog.debug("Renaming file " + file + " to " + target);
					//file.renameTo(target);
					renameTo(file, target);
					
				}
			}
      
			target1 = new File(this.fileName + "." + 1);
      
			closeFile();
      
			file = new File(this.fileName);
			LogLog.debug("Renaming file " + file + " to " + target1);
			//file.renameTo(target);
			renameTo(file, target1);
			
			
		}
		
			//setFile(this.fileName.substring(0, this.fileName.lastIndexOf(File.separator))+File.separator, false, this.bufferedIO, this.bufferSize);
			setFile(actualFileName, false, this.bufferedIO, this.bufferSize);
		}catch (IOException e){
			LogLog.error("setFile(" + this.fileName + ", false) call failed.", e);
		}finally {
			/*code by sukhi 26/09/2018*/
			target=null;
			target1=null;
			file=null;
		}
	}
  
	public synchronized void setFile(String fileName, boolean append, boolean bufferedIO, int bufferSize)throws IOException{
		if(actualFileName == null)
			actualFileName = fileName;
		File f = null;
	  try{
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		f = new File(fileName+sd.format(new Date()));
		if(!f.exists() && !f.mkdirs())
			throw new IOException("Unable to create log directory " + f.getAbsolutePath());
		
		super.setFile(f.getAbsolutePath()+File.separator+"logs.txt", append, bufferedIO, bufferSize);
		if (append){
			f = new File(f.getAbsolutePath()+File.separator+"logs.txt");
			((CountingQuietWriter)this.qw).setCount(f.length());
		}
		
	  }catch(Exception ex){
		  LogLog.error("setFile(" + fileName + ") call failed.", ex);
		  if(ex instanceof IOException)
			  throw (IOException)ex;
	  }finally {
		  /*code by sukhi 26/09/2018*/
			f=null;
	  }
	}
  
	public void setMaxBackupIndex(int maxBackups){
		this.maxBackupIndex = maxBackups;
	}
  
	public void setMaximumFileSize(long maxFileSize){
		this.maxFileSize = maxFileSize;
	}
  
	public void setMaxFileSize(String value){
		this.maxFileSize = OptionConverter.toFileSize(value, this.maxFileSize + 1L);
	}
  
	protected void setQWForFiles(Writer writer){
		this.qw = new CountingQuietWriter(writer, this.errorHandler);
	}
  
	protected void subAppend(LoggingEvent event){
		super.subAppend(event);
		if ((this.fileName != null) && (((CountingQuietWriter)this.qw).getCount() >= this.maxFileSize)) {
			rollOver();
		}
	}
  
	protected void closeFile(){
		if (this.qw != null) {
			try{
				this.qw.close();
			}catch (IOException e){
				LogLog.error("Could not close " + this.qw, e);
			}
		}
	}
  
	private void renameTo(File file, File target){
		try{
			byte[] rb;
			try(FileInputStream fin = new FileInputStream(file)){
				rb = new byte[fin.available()];
				fin.read(rb);
			}
			file.delete();
	    	  
			try(FileOutputStream fout = new FileOutputStream(target.getAbsoluteFile())){
				fout.write(rb);
			}
		}catch(Exception e1){
			LogLog.error("Unable to roll log file to " + target, e1);
		}
	}
}
