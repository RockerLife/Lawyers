/**
 * OSI_AGDisbursement.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.datacontract.schemas._2004._07.IPFS_DTO;

public class OSI_AGDisbursement  implements java.io.Serializable {
    private java.lang.String checkDraftNumber;

    private java.lang.String fundingType;

    private java.lang.String method;

    private java.lang.String paidDate;

    private java.lang.String payTo;

    private java.lang.String payableAmt;

    private java.lang.String payableOnDate;

    private java.lang.String release;

    public OSI_AGDisbursement() {
    }

    public OSI_AGDisbursement(
           java.lang.String checkDraftNumber,
           java.lang.String fundingType,
           java.lang.String method,
           java.lang.String paidDate,
           java.lang.String payTo,
           java.lang.String payableAmt,
           java.lang.String payableOnDate,
           java.lang.String release) {
           this.checkDraftNumber = checkDraftNumber;
           this.fundingType = fundingType;
           this.method = method;
           this.paidDate = paidDate;
           this.payTo = payTo;
           this.payableAmt = payableAmt;
           this.payableOnDate = payableOnDate;
           this.release = release;
    }


    /**
     * Gets the checkDraftNumber value for this OSI_AGDisbursement.
     * 
     * @return checkDraftNumber
     */
    public java.lang.String getCheckDraftNumber() {
        return checkDraftNumber;
    }


    /**
     * Sets the checkDraftNumber value for this OSI_AGDisbursement.
     * 
     * @param checkDraftNumber
     */
    public void setCheckDraftNumber(java.lang.String checkDraftNumber) {
        this.checkDraftNumber = checkDraftNumber;
    }


    /**
     * Gets the fundingType value for this OSI_AGDisbursement.
     * 
     * @return fundingType
     */
    public java.lang.String getFundingType() {
        return fundingType;
    }


    /**
     * Sets the fundingType value for this OSI_AGDisbursement.
     * 
     * @param fundingType
     */
    public void setFundingType(java.lang.String fundingType) {
        this.fundingType = fundingType;
    }


    /**
     * Gets the method value for this OSI_AGDisbursement.
     * 
     * @return method
     */
    public java.lang.String getMethod() {
        return method;
    }


    /**
     * Sets the method value for this OSI_AGDisbursement.
     * 
     * @param method
     */
    public void setMethod(java.lang.String method) {
        this.method = method;
    }


    /**
     * Gets the paidDate value for this OSI_AGDisbursement.
     * 
     * @return paidDate
     */
    public java.lang.String getPaidDate() {
        return paidDate;
    }


    /**
     * Sets the paidDate value for this OSI_AGDisbursement.
     * 
     * @param paidDate
     */
    public void setPaidDate(java.lang.String paidDate) {
        this.paidDate = paidDate;
    }


    /**
     * Gets the payTo value for this OSI_AGDisbursement.
     * 
     * @return payTo
     */
    public java.lang.String getPayTo() {
        return payTo;
    }


    /**
     * Sets the payTo value for this OSI_AGDisbursement.
     * 
     * @param payTo
     */
    public void setPayTo(java.lang.String payTo) {
        this.payTo = payTo;
    }


    /**
     * Gets the payableAmt value for this OSI_AGDisbursement.
     * 
     * @return payableAmt
     */
    public java.lang.String getPayableAmt() {
        return payableAmt;
    }


    /**
     * Sets the payableAmt value for this OSI_AGDisbursement.
     * 
     * @param payableAmt
     */
    public void setPayableAmt(java.lang.String payableAmt) {
        this.payableAmt = payableAmt;
    }


    /**
     * Gets the payableOnDate value for this OSI_AGDisbursement.
     * 
     * @return payableOnDate
     */
    public java.lang.String getPayableOnDate() {
        return payableOnDate;
    }


    /**
     * Sets the payableOnDate value for this OSI_AGDisbursement.
     * 
     * @param payableOnDate
     */
    public void setPayableOnDate(java.lang.String payableOnDate) {
        this.payableOnDate = payableOnDate;
    }


    /**
     * Gets the release value for this OSI_AGDisbursement.
     * 
     * @return release
     */
    public java.lang.String getRelease() {
        return release;
    }


    /**
     * Sets the release value for this OSI_AGDisbursement.
     * 
     * @param release
     */
    public void setRelease(java.lang.String release) {
        this.release = release;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof OSI_AGDisbursement)) return false;
        OSI_AGDisbursement other = (OSI_AGDisbursement) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.checkDraftNumber==null && other.getCheckDraftNumber()==null) || 
             (this.checkDraftNumber!=null &&
              this.checkDraftNumber.equals(other.getCheckDraftNumber()))) &&
            ((this.fundingType==null && other.getFundingType()==null) || 
             (this.fundingType!=null &&
              this.fundingType.equals(other.getFundingType()))) &&
            ((this.method==null && other.getMethod()==null) || 
             (this.method!=null &&
              this.method.equals(other.getMethod()))) &&
            ((this.paidDate==null && other.getPaidDate()==null) || 
             (this.paidDate!=null &&
              this.paidDate.equals(other.getPaidDate()))) &&
            ((this.payTo==null && other.getPayTo()==null) || 
             (this.payTo!=null &&
              this.payTo.equals(other.getPayTo()))) &&
            ((this.payableAmt==null && other.getPayableAmt()==null) || 
             (this.payableAmt!=null &&
              this.payableAmt.equals(other.getPayableAmt()))) &&
            ((this.payableOnDate==null && other.getPayableOnDate()==null) || 
             (this.payableOnDate!=null &&
              this.payableOnDate.equals(other.getPayableOnDate()))) &&
            ((this.release==null && other.getRelease()==null) || 
             (this.release!=null &&
              this.release.equals(other.getRelease())));
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
        if (getCheckDraftNumber() != null) {
            _hashCode += getCheckDraftNumber().hashCode();
        }
        if (getFundingType() != null) {
            _hashCode += getFundingType().hashCode();
        }
        if (getMethod() != null) {
            _hashCode += getMethod().hashCode();
        }
        if (getPaidDate() != null) {
            _hashCode += getPaidDate().hashCode();
        }
        if (getPayTo() != null) {
            _hashCode += getPayTo().hashCode();
        }
        if (getPayableAmt() != null) {
            _hashCode += getPayableAmt().hashCode();
        }
        if (getPayableOnDate() != null) {
            _hashCode += getPayableOnDate().hashCode();
        }
        if (getRelease() != null) {
            _hashCode += getRelease().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(OSI_AGDisbursement.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IPFS.DTO", "OSI_AGDisbursement"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("checkDraftNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IPFS.DTO", "CheckDraftNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fundingType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IPFS.DTO", "FundingType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("method");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IPFS.DTO", "Method"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("paidDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IPFS.DTO", "PaidDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("payTo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IPFS.DTO", "PayTo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("payableAmt");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IPFS.DTO", "PayableAmt"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("payableOnDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IPFS.DTO", "PayableOnDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("release");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IPFS.DTO", "Release"));
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
