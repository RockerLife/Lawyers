/**
 * RecurringACH.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.datacontract.schemas._2004._07.IntegrationSDK_Model;

public class RecurringACH  implements java.io.Serializable {
    private java.lang.String achDisclosure;

    private java.lang.String bankAcctNumber;

    private java.lang.String bankRoutingNumber;

    private java.lang.Boolean enrollRecurringAch;

    private java.lang.Boolean isCheckingAccount;

    public RecurringACH() {
    }

    public RecurringACH(
           java.lang.String achDisclosure,
           java.lang.String bankAcctNumber,
           java.lang.String bankRoutingNumber,
           java.lang.Boolean enrollRecurringAch,
           java.lang.Boolean isCheckingAccount) {
           this.achDisclosure = achDisclosure;
           this.bankAcctNumber = bankAcctNumber;
           this.bankRoutingNumber = bankRoutingNumber;
           this.enrollRecurringAch = enrollRecurringAch;
           this.isCheckingAccount = isCheckingAccount;
    }


    /**
     * Gets the achDisclosure value for this RecurringACH.
     * 
     * @return achDisclosure
     */
    public java.lang.String getAchDisclosure() {
        return achDisclosure;
    }


    /**
     * Sets the achDisclosure value for this RecurringACH.
     * 
     * @param achDisclosure
     */
    public void setAchDisclosure(java.lang.String achDisclosure) {
        this.achDisclosure = achDisclosure;
    }


    /**
     * Gets the bankAcctNumber value for this RecurringACH.
     * 
     * @return bankAcctNumber
     */
    public java.lang.String getBankAcctNumber() {
        return bankAcctNumber;
    }


    /**
     * Sets the bankAcctNumber value for this RecurringACH.
     * 
     * @param bankAcctNumber
     */
    public void setBankAcctNumber(java.lang.String bankAcctNumber) {
        this.bankAcctNumber = bankAcctNumber;
    }


    /**
     * Gets the bankRoutingNumber value for this RecurringACH.
     * 
     * @return bankRoutingNumber
     */
    public java.lang.String getBankRoutingNumber() {
        return bankRoutingNumber;
    }


    /**
     * Sets the bankRoutingNumber value for this RecurringACH.
     * 
     * @param bankRoutingNumber
     */
    public void setBankRoutingNumber(java.lang.String bankRoutingNumber) {
        this.bankRoutingNumber = bankRoutingNumber;
    }


    /**
     * Gets the enrollRecurringAch value for this RecurringACH.
     * 
     * @return enrollRecurringAch
     */
    public java.lang.Boolean getEnrollRecurringAch() {
        return enrollRecurringAch;
    }


    /**
     * Sets the enrollRecurringAch value for this RecurringACH.
     * 
     * @param enrollRecurringAch
     */
    public void setEnrollRecurringAch(java.lang.Boolean enrollRecurringAch) {
        this.enrollRecurringAch = enrollRecurringAch;
    }


    /**
     * Gets the isCheckingAccount value for this RecurringACH.
     * 
     * @return isCheckingAccount
     */
    public java.lang.Boolean getIsCheckingAccount() {
        return isCheckingAccount;
    }


    /**
     * Sets the isCheckingAccount value for this RecurringACH.
     * 
     * @param isCheckingAccount
     */
    public void setIsCheckingAccount(java.lang.Boolean isCheckingAccount) {
        this.isCheckingAccount = isCheckingAccount;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RecurringACH)) return false;
        RecurringACH other = (RecurringACH) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.achDisclosure==null && other.getAchDisclosure()==null) || 
             (this.achDisclosure!=null &&
              this.achDisclosure.equals(other.getAchDisclosure()))) &&
            ((this.bankAcctNumber==null && other.getBankAcctNumber()==null) || 
             (this.bankAcctNumber!=null &&
              this.bankAcctNumber.equals(other.getBankAcctNumber()))) &&
            ((this.bankRoutingNumber==null && other.getBankRoutingNumber()==null) || 
             (this.bankRoutingNumber!=null &&
              this.bankRoutingNumber.equals(other.getBankRoutingNumber()))) &&
            ((this.enrollRecurringAch==null && other.getEnrollRecurringAch()==null) || 
             (this.enrollRecurringAch!=null &&
              this.enrollRecurringAch.equals(other.getEnrollRecurringAch()))) &&
            ((this.isCheckingAccount==null && other.getIsCheckingAccount()==null) || 
             (this.isCheckingAccount!=null &&
              this.isCheckingAccount.equals(other.getIsCheckingAccount())));
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
        if (getAchDisclosure() != null) {
            _hashCode += getAchDisclosure().hashCode();
        }
        if (getBankAcctNumber() != null) {
            _hashCode += getBankAcctNumber().hashCode();
        }
        if (getBankRoutingNumber() != null) {
            _hashCode += getBankRoutingNumber().hashCode();
        }
        if (getEnrollRecurringAch() != null) {
            _hashCode += getEnrollRecurringAch().hashCode();
        }
        if (getIsCheckingAccount() != null) {
            _hashCode += getIsCheckingAccount().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RecurringACH.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IntegrationSDK.Model", "RecurringACH"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("achDisclosure");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IntegrationSDK.Model", "AchDisclosure"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bankAcctNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IntegrationSDK.Model", "BankAcctNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bankRoutingNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IntegrationSDK.Model", "BankRoutingNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("enrollRecurringAch");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IntegrationSDK.Model", "EnrollRecurringAch"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isCheckingAccount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IntegrationSDK.Model", "IsCheckingAccount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
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
