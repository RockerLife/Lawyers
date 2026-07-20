
package com.userbo.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "receiveIssuePolicy", namespace = "http://userbo.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "receiveIssuePolicy", namespace = "http://userbo.com/")
public class ReceiveIssuePolicy {

    @XmlElement(name = "context", namespace = "")
    private String context;

    
    
    /**
	 * @return the context
	 */
	public String getContext() {
		return context;
	}

	/**
	 * @param context the context to set
	 */
	public void setContext(String context) {
		this.context = context;
	}

	@XmlElement(name = "paymentType", namespace = "")
    private String paymentType;

    /**
     * 
     * @return
     *     returns String
     */
    public String getPaymentType() {
        return this.paymentType;
    }

    /**
     * 
     * @param paymentType
     *     the value for the paymentType property
     */
    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

}
