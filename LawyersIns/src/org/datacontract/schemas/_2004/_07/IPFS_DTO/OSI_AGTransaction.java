/**
 * OSI_AGTransaction.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.datacontract.schemas._2004._07.IPFS_DTO;

public class OSI_AGTransaction  implements java.io.Serializable {
    private java.lang.String amt;

    private java.lang.String checkFee;

    private java.lang.String credit;

    private java.lang.String defaultFee;

    private java.lang.String dueDate;

    private java.lang.String interest;

    private java.lang.String lateFee;

    private java.lang.String method;

    private java.lang.String NSFFee;

    private java.lang.String postedReceivedDate;

    private java.lang.String principal;

    private java.lang.String transType;

    public OSI_AGTransaction() {
    }

    public OSI_AGTransaction(
           java.lang.String amt,
           java.lang.String checkFee,
           java.lang.String credit,
           java.lang.String defaultFee,
           java.lang.String dueDate,
           java.lang.String interest,
           java.lang.String lateFee,
           java.lang.String method,
           java.lang.String NSFFee,
           java.lang.String postedReceivedDate,
           java.lang.String principal,
           java.lang.String transType) {
           this.amt = amt;
           this.checkFee = checkFee;
           this.credit = credit;
           this.defaultFee = defaultFee;
           this.dueDate = dueDate;
           this.interest = interest;
           this.lateFee = lateFee;
           this.method = method;
           this.NSFFee = NSFFee;
           this.postedReceivedDate = postedReceivedDate;
           this.principal = principal;
           this.transType = transType;
    }


    /**
     * Gets the amt value for this OSI_AGTransaction.
     * 
     * @return amt
     */
    public java.lang.String getAmt() {
        return amt;
    }


    /**
     * Sets the amt value for this OSI_AGTransaction.
     * 
     * @param amt
     */
    public void setAmt(java.lang.String amt) {
        this.amt = amt;
    }


    /**
     * Gets the checkFee value for this OSI_AGTransaction.
     * 
     * @return checkFee
     */
    public java.lang.String getCheckFee() {
        return checkFee;
    }


    /**
     * Sets the checkFee value for this OSI_AGTransaction.
     * 
     * @param checkFee
     */
    public void setCheckFee(java.lang.String checkFee) {
        this.checkFee = checkFee;
    }


    /**
     * Gets the credit value for this OSI_AGTransaction.
     * 
     * @return credit
     */
    public java.lang.String getCredit() {
        return credit;
    }


    /**
     * Sets the credit value for this OSI_AGTransaction.
     * 
     * @param credit
     */
    public void setCredit(java.lang.String credit) {
        this.credit = credit;
    }


    /**
     * Gets the defaultFee value for this OSI_AGTransaction.
     * 
     * @return defaultFee
     */
    public java.lang.String getDefaultFee() {
        return defaultFee;
    }


    /**
     * Sets the defaultFee value for this OSI_AGTransaction.
     * 
     * @param defaultFee
     */
    public void setDefaultFee(java.lang.String defaultFee) {
        this.defaultFee = defaultFee;
    }


    /**
     * Gets the dueDate value for this OSI_AGTransaction.
     * 
     * @return dueDate
     */
    public java.lang.String getDueDate() {
        return dueDate;
    }


    /**
     * Sets the dueDate value for this OSI_AGTransaction.
     * 
     * @param dueDate
     */
    public void setDueDate(java.lang.String dueDate) {
        this.dueDate = dueDate;
    }


    /**
     * Gets the interest value for this OSI_AGTransaction.
     * 
     * @return interest
     */
    public java.lang.String getInterest() {
        return interest;
    }


    /**
     * Sets the interest value for this OSI_AGTransaction.
     * 
     * @param interest
     */
    public void setInterest(java.lang.String interest) {
        this.interest = interest;
    }


    /**
     * Gets the lateFee value for this OSI_AGTransaction.
     * 
     * @return lateFee
     */
    public java.lang.String getLateFee() {
        return lateFee;
    }


    /**
     * Sets the lateFee value for this OSI_AGTransaction.
     * 
     * @param lateFee
     */
    public void setLateFee(java.lang.String lateFee) {
        this.lateFee = lateFee;
    }


    /**
     * Gets the method value for this OSI_AGTransaction.
     * 
     * @return method
     */
    public java.lang.String getMethod() {
        return method;
    }


    /**
     * Sets the method value for this OSI_AGTransaction.
     * 
     * @param method
     */
    public void setMethod(java.lang.String method) {
        this.method = method;
    }


    /**
     * Gets the NSFFee value for this OSI_AGTransaction.
     * 
     * @return NSFFee
     */
    public java.lang.String getNSFFee() {
        return NSFFee;
    }


    /**
     * Sets the NSFFee value for this OSI_AGTransaction.
     * 
     * @param NSFFee
     */
    public void setNSFFee(java.lang.String NSFFee) {
        this.NSFFee = NSFFee;
    }


    /**
     * Gets the postedReceivedDate value for this OSI_AGTransaction.
     * 
     * @return postedReceivedDate
     */
    public java.lang.String getPostedReceivedDate() {
        return postedReceivedDate;
    }


    /**
     * Sets the postedReceivedDate value for this OSI_AGTransaction.
     * 
     * @param postedReceivedDate
     */
    public void setPostedReceivedDate(java.lang.String postedReceivedDate) {
        this.postedReceivedDate = postedReceivedDate;
    }


    /**
     * Gets the principal value for this OSI_AGTransaction.
     * 
     * @return principal
     */
    public java.lang.String getPrincipal() {
        return principal;
    }


    /**
     * Sets the principal value for this OSI_AGTransaction.
     * 
     * @param principal
     */
    public void setPrincipal(java.lang.String principal) {
        this.principal = principal;
    }


    /**
     * Gets the transType value for this OSI_AGTransaction.
     * 
     * @return transType
     */
    public java.lang.String getTransType() {
        return transType;
    }


    /**
     * Sets the transType value for this OSI_AGTransaction.
     * 
     * @param transType
     */
    public void setTransType(java.lang.String transType) {
        this.transType = transType;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof OSI_AGTransaction)) return false;
        OSI_AGTransaction other = (OSI_AGTransaction) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.amt==null && other.getAmt()==null) || 
             (this.amt!=null &&
              this.amt.equals(other.getAmt()))) &&
            ((this.checkFee==null && other.getCheckFee()==null) || 
             (this.checkFee!=null &&
              this.checkFee.equals(other.getCheckFee()))) &&
            ((this.credit==null && other.getCredit()==null) || 
             (this.credit!=null &&
              this.credit.equals(other.getCredit()))) &&
            ((this.defaultFee==null && other.getDefaultFee()==null) || 
             (this.defaultFee!=null &&
              this.defaultFee.equals(other.getDefaultFee()))) &&
            ((this.dueDate==null && other.getDueDate()==null) || 
             (this.dueDate!=null &&
              this.dueDate.equals(other.getDueDate()))) &&
            ((this.interest==null && other.getInterest()==null) || 
             (this.interest!=null &&
              this.interest.equals(other.getInterest()))) &&
            ((this.lateFee==null && other.getLateFee()==null) || 
             (this.lateFee!=null &&
              this.lateFee.equals(other.getLateFee()))) &&
            ((this.method==null && other.getMethod()==null) || 
             (this.method!=null &&
              this.method.equals(other.getMethod()))) &&
            ((this.NSFFee==null && other.getNSFFee()==null) || 
             (this.NSFFee!=null &&
              this.NSFFee.equals(other.getNSFFee()))) &&
            ((this.postedReceivedDate==null && other.getPostedReceivedDate()==null) || 
             (this.postedReceivedDate!=null &&
              this.postedReceivedDate.equals(other.getPostedReceivedDate()))) &&
            ((this.principal==null && other.getPrincipal()==null) || 
             (this.principal!=null &&
              this.principal.equals(other.getPrincipal()))) &&
            ((this.transType==null && other.getTransType()==null) || 
             (this.transType!=null &&
              this.transType.equals(other.getTransType())));
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
        if (getAmt() != null) {
            _hashCode += getAmt().hashCode();
        }
        if (getCheckFee() != null) {
            _hashCode += getCheckFee().hashCode();
        }
        if (getCredit() != null) {
            _hashCode += getCredit().hashCode();
        }
        if (getDefaultFee() != null) {
            _hashCode += getDefaultFee().hashCode();
        }
        if (getDueDate() != null) {
            _hashCode += getDueDate().hashCode();
        }
        if (getInterest() != null) {
            _hashCode += getInterest().hashCode();
        }
        if (getLateFee() != null) {
            _hashCode += getLateFee().hashCode();
        }
        if (getMethod() != null) {
            _hashCode += getMethod().hashCode();
        }
        if (getNSFFee() != null) {
            _hashCode += getNSFFee().hashCode();
        }
        if (getPostedReceivedDate() != null) {
            _hashCode += getPostedReceivedDate().hashCode();
        }
        if (getPrincipal() != null) {
            _hashCode += getPrincipal().hashCode();
        }
        if (getTransType() != null) {
            _hashCode += getTransType().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(OSI_AGTransaction.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IPFS.DTO", "OSI_AGTransaction"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("amt");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IPFS.DTO", "Amt"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("checkFee");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IPFS.DTO", "CheckFee"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("credit");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IPFS.DTO", "Credit"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("defaultFee");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IPFS.DTO", "DefaultFee"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dueDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IPFS.DTO", "DueDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("interest");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IPFS.DTO", "Interest"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lateFee");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IPFS.DTO", "LateFee"));
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
        elemField.setFieldName("NSFFee");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IPFS.DTO", "NSFFee"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("postedReceivedDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IPFS.DTO", "PostedReceivedDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("principal");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IPFS.DTO", "Principal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("transType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IPFS.DTO", "TransType"));
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
