
package com.userbo.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "receiveAgreementQuoteNumber", namespace = "http://userbo.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "receiveAgreementQuoteNumber", namespace = "http://userbo.com/")
public class ReceiveAgreementQuoteNumber {

    @XmlElement(name = "ipfsQuoteNumber", namespace = "")
    private String ipfsQuoteNumber;

    /**
     * 
     * @return
     *     returns String
     */
    public String getIpfsQuoteNumber() {
        return this.ipfsQuoteNumber;
    }

    /**
     * 
     * @param ipfsQuoteNumber
     *     the value for the ipfsQuoteNumber property
     */
    public void setIpfsQuoteNumber(String ipfsQuoteNumber) {
        this.ipfsQuoteNumber = ipfsQuoteNumber;
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
