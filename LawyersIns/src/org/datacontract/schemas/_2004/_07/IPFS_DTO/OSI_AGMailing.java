/**
 * OSI_AGMailing.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.datacontract.schemas._2004._07.IPFS_DTO;

public class OSI_AGMailing  implements java.io.Serializable {
    private java.lang.String mailingName;

    private java.lang.String printDate;

    private java.lang.String recipientName;

    private java.lang.String sentDate;

    private java.lang.String sentTo;

    public OSI_AGMailing() {
    }

    public OSI_AGMailing(
           java.lang.String mailingName,
           java.lang.String printDate,
           java.lang.String recipientName,
           java.lang.String sentDate,
           java.lang.String sentTo) {
           this.mailingName = mailingName;
           this.printDate = printDate;
           this.recipientName = recipientName;
           this.sentDate = sentDate;
           this.sentTo = sentTo;
    }


    /**
     * Gets the mailingName value for this OSI_AGMailing.
     * 
     * @return mailingName
     */
    public java.lang.String getMailingName() {
        return mailingName;
    }


    /**
     * Sets the mailingName value for this OSI_AGMailing.
     * 
     * @param mailingName
     */
    public void setMailingName(java.lang.String mailingName) {
        this.mailingName = mailingName;
    }


    /**
     * Gets the printDate value for this OSI_AGMailing.
     * 
     * @return printDate
     */
    public java.lang.String getPrintDate() {
        return printDate;
    }


    /**
     * Sets the printDate value for this OSI_AGMailing.
     * 
     * @param printDate
     */
    public void setPrintDate(java.lang.String printDate) {
        this.printDate = printDate;
    }


    /**
     * Gets the recipientName value for this OSI_AGMailing.
     * 
     * @return recipientName
     */
    public java.lang.String getRecipientName() {
        return recipientName;
    }


    /**
     * Sets the recipientName value for this OSI_AGMailing.
     * 
     * @param recipientName
     */
    public void setRecipientName(java.lang.String recipientName) {
        this.recipientName = recipientName;
    }


    /**
     * Gets the sentDate value for this OSI_AGMailing.
     * 
     * @return sentDate
     */
    public java.lang.String getSentDate() {
        return sentDate;
    }


    /**
     * Sets the sentDate value for this OSI_AGMailing.
     * 
     * @param sentDate
     */
    public void setSentDate(java.lang.String sentDate) {
        this.sentDate = sentDate;
    }


    /**
     * Gets the sentTo value for this OSI_AGMailing.
     * 
     * @return sentTo
     */
    public java.lang.String getSentTo() {
        return sentTo;
    }


    /**
     * Sets the sentTo value for this OSI_AGMailing.
     * 
     * @param sentTo
     */
    public void setSentTo(java.lang.String sentTo) {
        this.sentTo = sentTo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof OSI_AGMailing)) return false;
        OSI_AGMailing other = (OSI_AGMailing) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.mailingName==null && other.getMailingName()==null) || 
             (this.mailingName!=null &&
              this.mailingName.equals(other.getMailingName()))) &&
            ((this.printDate==null && other.getPrintDate()==null) || 
             (this.printDate!=null &&
              this.printDate.equals(other.getPrintDate()))) &&
            ((this.recipientName==null && other.getRecipientName()==null) || 
             (this.recipientName!=null &&
              this.recipientName.equals(other.getRecipientName()))) &&
            ((this.sentDate==null && other.getSentDate()==null) || 
             (this.sentDate!=null &&
              this.sentDate.equals(other.getSentDate()))) &&
            ((this.sentTo==null && other.getSentTo()==null) || 
             (this.sentTo!=null &&
              this.sentTo.equals(other.getSentTo())));
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
        if (getMailingName() != null) {
            _hashCode += getMailingName().hashCode();
        }
        if (getPrintDate() != null) {
            _hashCode += getPrintDate().hashCode();
        }
        if (getRecipientName() != null) {
            _hashCode += getRecipientName().hashCode();
        }
        if (getSentDate() != null) {
            _hashCode += getSentDate().hashCode();
        }
        if (getSentTo() != null) {
            _hashCode += getSentTo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(OSI_AGMailing.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IPFS.DTO", "OSI_AGMailing"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mailingName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IPFS.DTO", "MailingName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("printDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IPFS.DTO", "PrintDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("recipientName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IPFS.DTO", "RecipientName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sentDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IPFS.DTO", "SentDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sentTo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IPFS.DTO", "SentTo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
