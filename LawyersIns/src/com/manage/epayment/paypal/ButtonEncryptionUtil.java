package com.manage.epayment.paypal;

/*
 *
 */
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertStoreException;
import java.security.cert.CertificateException;

import org.bouncycastle.cms.CMSException;

import com.util.InetLogger;

/**
 * @author Amit Jain
 * Created on 16 Sep, 2010
 */
public class ButtonEncryptionUtil 
{
	
	private static InetLogger logger = InetLogger.getInetLogger(ButtonEncryptionUtil.class);
	public static String getEncryptedButton(String cert_id,String certPath,String keyPath,String paypalCertPath,
			String keyPass,String business_id,String item_name,double amount,
			String currency_code,String cancel_url,String return_url,String notify_URL,String customVar,String textForReturnButton){
		
		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());	
		String result = "";

		StringBuffer buttonText = new StringBuffer();
		buttonText.append("cert_id=").append(cert_id);
		buttonText.append(",cmd=_xclick");
		buttonText.append(",business=").append(business_id);
		buttonText.append(",item_name=").append(item_name);
		buttonText.append(",amount=").append(amount);
		buttonText.append(",currency_code=").append(currency_code);
		if(cancel_url != null && !"".equals(cancel_url))
			buttonText.append(",cancel_return=").append(cancel_url);
		if(return_url != null && !"".equals(return_url))
			buttonText.append(",return=").append(return_url);
		
		if(textForReturnButton != null && !"".equals(textForReturnButton))
			buttonText.append(",cbt=").append(textForReturnButton);
		
		if(customVar != null && !"".equals(customVar))
			buttonText.append(",custom=").append(customVar);
		
		if(notify_URL != null && !"".equals(notify_URL))
			buttonText.append(",notify_url=").append(notify_URL);
		
		
		try 
		{
			GetEncryption getEncyp = new GetEncryption();
			
			result = getEncyp.getButtonEncryptionValue( buttonText.toString(), keyPath, certPath, paypalCertPath, keyPass );
			

			
		} 
		catch (NoSuchAlgorithmException e) 
		{
			// TODO Auto-generated catch block
			logger.error("Unexpected error", e);
		} 
		catch (NoSuchProviderException e) 
		{
			// TODO Auto-generated catch block
			logger.error("Unexpected error", e);
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			logger.error("Unexpected error", e);
		} 
		catch (CMSException e) 
		{
			// TODO Auto-generated catch block
			logger.error("Unexpected error", e);
		} 
		catch (CertificateException e) 
		{
			// TODO Auto-generated catch block
			logger.error("Unexpected error", e);
		} 
		catch (KeyStoreException e) 
		{
			// TODO Auto-generated catch block
			logger.error("Unexpected error", e);
		} 
		catch (UnrecoverableKeyException e) 
		{
			// TODO Auto-generated catch block
			logger.error("Unexpected error", e);
		} 
		catch (InvalidAlgorithmParameterException e) 
		{
			// TODO Auto-generated catch block
			logger.error("Unexpected error", e);
		} 
		catch (CertStoreException e) 
		{
			// TODO Auto-generated catch block
			logger.error("Unexpected error", e);
		}
		
		return result;
	}
	

	public static void main(String[] args) 
	{
		
		String cert_id = "MMMYFLH56SLEC";
		String certPath ="D:\\OLS\\certforpp\\my-pubcert.pem";
		String keyPath ="D:\\OLS\\certforpp\\my-prvkey.p12";
		String paypalCertPath ="D:\\OLS\\certforpp\\paypal_cert_pem.txt";
		String keyPass ="paypal12";
		String business_id ="sel_sb_1284041102_biz@yahoo.com";
		String item_name ="Insurance Policy";
		double amount = 560.00;
		String currency_code ="USD";
		String cancel_url ="http://in.rscube.com:8080/AccountPortal/index.html";
		String return_url ="http://in.rscube.com:8080/AccountPortal/index.html";
		String customVar ="";
		String notify_URL ="http://in.rscube.com:8080/AccountPortal/index.html";
		
		
		
		logger.debug(getEncryptedButton(cert_id, certPath, keyPath, paypalCertPath, keyPass, business_id, item_name, amount, currency_code, cancel_url, return_url,notify_URL,customVar,""));
	
	}
}
