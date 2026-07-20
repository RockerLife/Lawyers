/**
 * OSI_AGPaymentSchedule.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.datacontract.schemas._2004._07.IPFS_DTO;

public class OSI_AGPaymentSchedule  implements java.io.Serializable {
    private java.lang.String datePaid;

    private java.lang.String dueDate;

    private java.lang.String installmentAmt;

    private java.lang.String principalBal;

    public OSI_AGPaymentSchedule() {
    }

    public OSI_AGPaymentSchedule(
           java.lang.String datePaid,
           java.lang.String dueDate,
           java.lang.String installmentAmt,
           java.lang.String principalBal) {
           this.datePaid = datePaid;
           this.dueDate = dueDate;
           this.installmentAmt = installmentAmt;
           this.principalBal = principalBal;
    }


    /**
     * Gets the datePaid value for this OSI_AGPaymentSchedule.
     * 
     * @return datePaid
     */
    public java.lang.String getDatePaid() {
        return datePaid;
    }


    /**
     * Sets the datePaid value for this OSI_AGPaymentSchedule.
     * 
     * @param datePaid
     */
    public void setDatePaid(java.lang.String datePaid) {
        this.datePaid = datePaid;
    }


    /**
     * Gets the dueDate value for this OSI_AGPaymentSchedule.
     * 
     * @return dueDate
     */
    public java.lang.String getDueDate() {
        return dueDate;
    }


    /**
     * Sets the dueDate value for this OSI_AGPaymentSchedule.
     * 
     * @param dueDate
     */
    public void setDueDate(java.lang.String dueDate) {
        this.dueDate = dueDate;
    }


    /**
     * Gets the installmentAmt value for this OSI_AGPaymentSchedule.
     * 
     * @return installmentAmt
     */
    public java.lang.String getInstallmentAmt() {
        return installmentAmt;
    }


    /**
     * Sets the installmentAmt value for this OSI_AGPaymentSchedule.
     * 
     * @param installmentAmt
     */
    public void setInstallmentAmt(java.lang.String installmentAmt) {
        this.installmentAmt = installmentAmt;
    }


    /**
     * Gets the principalBal value for this OSI_AGPaymentSchedule.
     * 
     * @return principalBal
     */
    public java.lang.String getPrincipalBal() {
        return principalBal;
    }


    /**
     * Sets the principalBal value for this OSI_AGPaymentSchedule.
     * 
     * @param principalBal
     */
    public void setPrincipalBal(java.lang.String principalBal) {
        this.principalBal = principalBal;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof OSI_AGPaymentSchedule)) return false;
        OSI_AGPaymentSchedule other = (OSI_AGPaymentSchedule) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.datePaid==null && other.getDatePaid()==null) || 
             (this.datePaid!=null &&
              this.datePaid.equals(other.getDatePaid()))) &&
            ((this.dueDate==null && other.getDueDate()==null) || 
             (this.dueDate!=null &&
              this.dueDate.equals(other.getDueDate()))) &&
            ((this.installmentAmt==null && other.getInstallmentAmt()==null) || 
             (this.installmentAmt!=null &&
              this.installmentAmt.equals(other.getInstallmentAmt()))) &&
            ((this.principalBal==null && other.getPrincipalBal()==null) || 
             (this.principalBal!=null &&
              this.principalBal.equals(other.getPrincipalBal())));
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
        if (getDatePaid() != null) {
            _hashCode += getDatePaid().hashCode();
        }
        if (getDueDate() != null) {
            _hashCode += getDueDate().hashCode();
        }
        if (getInstallmentAmt() != null) {
            _hashCode += getInstallmentAmt().hashCode();
        }
        if (getPrincipalBal() != null) {
            _hashCode += getPrincipalBal().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(OSI_AGPaymentSchedule.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IPFS.DTO", "OSI_AGPaymentSchedule"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("datePaid");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IPFS.DTO", "DatePaid"));
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
        elemField.setFieldName("installmentAmt");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IPFS.DTO", "InstallmentAmt"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("principalBal");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IPFS.DTO", "PrincipalBal"));
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
