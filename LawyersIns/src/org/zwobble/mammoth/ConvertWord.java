package org.zwobble.mammoth;

import com.util.InetLogger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ConvertWord {
	private static final InetLogger logger = InetLogger.getInetLogger(ConvertWord.class);
	private static final String docName = "FL rate change 2020 renewal soliciation.docx";
	private static final String outputlFolderPath = "C:/Data/Vivek Jindal/PROJECT/Email_Solicitaion/Renewal Mails/";
	//C:\Data\Vivek Jindal\PROJECT\Email_Solicitaion\Renewal Mails


	public static void main(String[] args) throws IOException {
		DocumentConverter doc = new DocumentConverter();
		InputStream file = new FileInputStream(new File(outputlFolderPath + docName));
		Result<String> r = doc.convertToHtml(file);
		
		String html = r.getValue();
		html = html.replace("{Go to video}", "<a href=\"{VideoURL}\">Go to video</a>");
		html = html.replace("{Renew Here}", "<a href=\"{AppURL}\">RENEW HERE</a>");
		html = html.replaceAll("<p>\\s+Social\\s+Engineering\\s+losses", "<p style=\"color:#ff0000\">Social Engineering losses");
		html = html.replaceAll("<p>Social\\s+Engineering\\s+losses", "<p style=\"color:#ff0000\">Social Engineering losses");
	}
}
