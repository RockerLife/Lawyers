/**
 * Communication.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.datacontract.schemas._2004._07.IntegrationSDK_Model;

public class Communication  implements java.io.Serializable {
    private java.lang.String insuredESignCompletionURL;

    private java.lang.String paymentPortalCancelRedirectURL;

    private java.lang.String paymentPortalDeclineOfferRedirectURL;

    private java.lang.Boolean return_InsuredESignURL;

    private java.lang.Boolean suppressInsuredESignEmail;

    private java.lang.Boolean eSignAutoStart;

    private java.lang.Boolean eSignStartPickOptions;

    public Communication() {
    }

    public Communication(
           java.lang.String insuredESignCompletionURL,
           java.lang.String paymentPortalCancelRedirectURL,
           java.lang.String paymentPortalDeclineOfferRedirectURL,
           java.lang.Boolean return_InsuredESignURL,
           java.lang.Boolean suppressInsuredESignEmail,
           java.lang.Boolean eSignAutoStart,
           java.lang.Boolean eSignStartPickOptions) {
           this.insuredESignCompletionURL = insuredESignCompletionURL;
           this.paymentPortalCancelRedirectURL = paymentPortalCancelRedirectURL;
           this.paymentPortalDeclineOfferRedirectURL = paymentPortalDeclineOfferRedirectURL;
           this.return_InsuredESignURL = return_InsuredESignURL;
           this.suppressInsuredESignEmail = suppressInsuredESignEmail;
           this.eSignAutoStart = eSignAutoStart;
           this.eSignStartPickOptions = eSignStartPickOptions;
    }


    /**
     * Gets the insuredESignCompletionURL value for this Communication.
     * 
     * @return insuredESignCompletionURL
     */
    public java.lang.String getInsuredESignCompletionURL() {
        return insuredESignCompletionURL;
    }


    /**
     * Sets the insuredESignCompletionURL value for this Communication.
     * 
     * @param insuredESignCompletionURL
     */
    public void setInsuredESignCompletionURL(java.lang.String insuredESignCompletionURL) {
        this.insuredESignCompletionURL = insuredESignCompletionURL;
    }


    /**
     * Gets the paymentPortalCancelRedirectURL value for this Communication.
     * 
     * @return paymentPortalCancelRedirectURL
     */
    public java.lang.String getPaymentPortalCancelRedirectURL() {
        return paymentPortalCancelRedirectURL;
    }


    /**
     * Sets the paymentPortalCancelRedirectURL value for this Communication.
     * 
     * @param paymentPortalCancelRedirectURL
     */
    public void setPaymentPortalCancelRedirectURL(java.lang.String paymentPortalCancelRedirectURL) {
        this.paymentPortalCancelRedirectURL = paymentPortalCancelRedirectURL;
    }


    /**
     * Gets the paymentPortalDeclineOfferRedirectURL value for this Communication.
     * 
     * @return paymentPortalDeclineOfferRedirectURL
     */
    public java.lang.String getPaymentPortalDeclineOfferRedirectURL() {
        return paymentPortalDeclineOfferRedirectURL;
    }


    /**
     * Sets the paymentPortalDeclineOfferRedirectURL value for this Communication.
     * 
     * @param paymentPortalDeclineOfferRedirectURL
     */
    public void setPaymentPortalDeclineOfferRedirectURL(java.lang.String paymentPortalDeclineOfferRedirectURL) {
        this.paymentPortalDeclineOfferRedirectURL = paymentPortalDeclineOfferRedirectURL;
    }


    /**
     * Gets the return_InsuredESignURL value for this Communication.
     * 
     * @return return_InsuredESignURL
     */
    public java.lang.Boolean getReturn_InsuredESignURL() {
        return return_InsuredESignURL;
    }


    /**
     * Sets the return_InsuredESignURL value for this Communication.
     * 
     * @param return_InsuredESignURL
     */
    public void setReturn_InsuredESignURL(java.lang.Boolean return_InsuredESignURL) {
        this.return_InsuredESignURL = return_InsuredESignURL;
    }


    /**
     * Gets the suppressInsuredESignEmail value for this Communication.
     * 
     * @return suppressInsuredESignEmail
     */
    public java.lang.Boolean getSuppressInsuredESignEmail() {
        return suppressInsuredESignEmail;
    }


    /**
     * Sets the suppressInsuredESignEmail value for this Communication.
     * 
     * @param suppressInsuredESignEmail
     */
    public void setSuppressInsuredESignEmail(java.lang.Boolean suppressInsuredESignEmail) {
        this.suppressInsuredESignEmail = suppressInsuredESignEmail;
    }


    /**
     * Gets the eSignAutoStart value for this Communication.
     * 
     * @return eSignAutoStart
     */
    public java.lang.Boolean getESignAutoStart() {
        return eSignAutoStart;
    }


    /**
     * Sets the eSignAutoStart value for this Communication.
     * 
     * @param eSignAutoStart
     */
    public void setESignAutoStart(java.lang.Boolean eSignAutoStart) {
        this.eSignAutoStart = eSignAutoStart;
    }


    /**
     * Gets the eSignStartPickOptions value for this Communication.
     * 
     * @return eSignStartPickOptions
     */
    public java.lang.Boolean getESignStartPickOptions() {
        return eSignStartPickOptions;
    }


    /**
     * Sets the eSignStartPickOptions value for this Communication.
     * 
     * @param eSignStartPickOptions
     */
    public void setESignStartPickOptions(java.lang.Boolean eSignStartPickOptions) {
        this.eSignStartPickOptions = eSignStartPickOptions;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Communication)) return false;
        Communication other = (Communication) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.insuredESignCompletionURL==null && other.getInsuredESignCompletionURL()==null) || 
             (this.insuredESignCompletionURL!=null &&
              this.insuredESignCompletionURL.equals(other.getInsuredESignCompletionURL()))) &&
            ((this.paymentPortalCancelRedirectURL==null && other.getPaymentPortalCancelRedirectURL()==null) || 
             (this.paymentPortalCancelRedirectURL!=null &&
              this.paymentPortalCancelRedirectURL.equals(other.getPaymentPortalCancelRedirectURL()))) &&
            ((this.paymentPortalDeclineOfferRedirectURL==null && other.getPaymentPortalDeclineOfferRedirectURL()==null) || 
             (this.paymentPortalDeclineOfferRedirectURL!=null &&
              this.paymentPortalDeclineOfferRedirectURL.equals(other.getPaymentPortalDeclineOfferRedirectURL()))) &&
            ((this.return_InsuredESignURL==null && other.getReturn_InsuredESignURL()==null) || 
             (this.return_InsuredESignURL!=null &&
              this.return_InsuredESignURL.equals(other.getReturn_InsuredESignURL()))) &&
            ((this.suppressInsuredESignEmail==null && other.getSuppressInsuredESignEmail()==null) || 
             (this.suppressInsuredESignEmail!=null &&
              this.suppressInsuredESignEmail.equals(other.getSuppressInsuredESignEmail()))) &&
            ((this.eSignAutoStart==null && other.getESignAutoStart()==null) || 
             (this.eSignAutoStart!=null &&
              this.eSignAutoStart.equals(other.getESignAutoStart()))) &&
            ((this.eSignStartPickOptions==null && other.getESignStartPickOptions()==null) || 
             (this.eSignStartPickOptions!=null &&
              this.eSignStartPickOptions.equals(other.getESignStartPickOptions())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getInsuredESignCompletionURL() != null) {
            _hashCode += getInsuredESignCompletionURL().hashCode();
        }
        if (getPaymentPortalCancelRedirectURL() != null) {
            _hashCode += getPaymentPortalCancelRedirectURL().hashCode();
        }
        if (getPaymentPortalDeclineOfferRedirectURL() != null) {
            _hashCode += getPaymentPortalDeclineOfferRedirectURL().hashCode();
        }
        if (getReturn_InsuredESignURL() != null) {
            _hashCode += getReturn_InsuredESignURL().hashCode();
        }
        if (getSuppressInsuredESignEmail() != null) {
            _hashCode += getSuppressInsuredESignEmail().hashCode();
        }
        if (getESignAutoStart() != null) {
            _hashCode += getESignAutoStart().hashCode();
        }
        if (getESignStartPickOptions() != null) {
            _hashCode += getESignStartPickOptions().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Communication.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IntegrationSDK.Model", "Communication"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("insuredESignCompletionURL");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IntegrationSDK.Model", "InsuredESignCompletionURL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("paymentPortalCancelRedirectURL");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IntegrationSDK.Model", "PaymentPortalCancelRedirectURL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("paymentPortalDeclineOfferRedirectURL");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IntegrationSDK.Model", "PaymentPortalDeclineOfferRedirectURL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("return_InsuredESignURL");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IntegrationSDK.Model", "Return_InsuredESignURL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("suppressInsuredESignEmail");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IntegrationSDK.Model", "SuppressInsuredESignEmail"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ESignAutoStart");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IntegrationSDK.Model", "eSignAutoStart"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ESignStartPickOptions");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IntegrationSDK.Model", "eSignStartPickOptions"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
