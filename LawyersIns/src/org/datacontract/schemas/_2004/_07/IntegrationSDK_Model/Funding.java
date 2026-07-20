/**
 * Funding.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.datacontract.schemas._2004._07.IntegrationSDK_Model;

public class Funding  implements java.io.Serializable {
    private java.math.BigDecimal amount;

    private java.lang.String bankAcctNumber;

    private java.lang.String bankRoutingNumber;

    private java.util.Calendar fundingDate;

    private org.datacontract.schemas._2004._07.IntegrationSDK.EnumsPolicyFundingType fundingType;

    private java.lang.Boolean isCheckingAccount;

    private org.datacontract.schemas._2004._07.IntegrationSDK.EnumsPolicyPayTo payTo;

    private org.datacontract.schemas._2004._07.IntegrationSDK.EnumsPolicyPaymentMethod paymentMethod;

    public Funding() {
    }

    public Funding(
           java.math.BigDecimal amount,
           java.lang.String bankAcctNumber,
           java.lang.String bankRoutingNumber,
           java.util.Calendar fundingDate,
           org.datacontract.schemas._2004._07.IntegrationSDK.EnumsPolicyFundingType fundingType,
           java.lang.Boolean isCheckingAccount,
           org.datacontract.schemas._2004._07.IntegrationSDK.EnumsPolicyPayTo payTo,
           org.datacontract.schemas._2004._07.IntegrationSDK.EnumsPolicyPaymentMethod paymentMethod) {
           this.amount = amount;
           this.bankAcctNumber = bankAcctNumber;
           this.bankRoutingNumber = bankRoutingNumber;
           this.fundingDate = fundingDate;
           this.fundingType = fundingType;
           this.isCheckingAccount = isCheckingAccount;
           this.payTo = payTo;
           this.paymentMethod = paymentMethod;
    }


    /**
     * Gets the amount value for this Funding.
     * 
     * @return amount
     */
    public java.math.BigDecimal getAmount() {
        return amount;
    }


    /**
     * Sets the amount value for this Funding.
     * 
     * @param amount
     */
    public void setAmount(java.math.BigDecimal amount) {
        this.amount = amount;
    }


    /**
     * Gets the bankAcctNumber value for this Funding.
     * 
     * @return bankAcctNumber
     */
    public java.lang.String getBankAcctNumber() {
        return bankAcctNumber;
    }


    /**
     * Sets the bankAcctNumber value for this Funding.
     * 
     * @param bankAcctNumber
     */
    public void setBankAcctNumber(java.lang.String bankAcctNumber) {
        this.bankAcctNumber = bankAcctNumber;
    }


    /**
     * Gets the bankRoutingNumber value for this Funding.
     * 
     * @return bankRoutingNumber
     */
    public java.lang.String getBankRoutingNumber() {
        return bankRoutingNumber;
    }


    /**
     * Sets the bankRoutingNumber value for this Funding.
     * 
     * @param bankRoutingNumber
     */
    public void setBankRoutingNumber(java.lang.String bankRoutingNumber) {
        this.bankRoutingNumber = bankRoutingNumber;
    }


    /**
     * Gets the fundingDate value for this Funding.
     * 
     * @return fundingDate
     */
    public java.util.Calendar getFundingDate() {
        return fundingDate;
    }


    /**
     * Sets the fundingDate value for this Funding.
     * 
     * @param fundingDate
     */
    public void setFundingDate(java.util.Calendar fundingDate) {
        this.fundingDate = fundingDate;
    }


    /**
     * Gets the fundingType value for this Funding.
     * 
     * @return fundingType
     */
    public org.datacontract.schemas._2004._07.IntegrationSDK.EnumsPolicyFundingType getFundingType() {
        return fundingType;
    }


    /**
     * Sets the fundingType value for this Funding.
     * 
     * @param fundingType
     */
    public void setFundingType(org.datacontract.schemas._2004._07.IntegrationSDK.EnumsPolicyFundingType fundingType) {
        this.fundingType = fundingType;
    }


    /**
     * Gets the isCheckingAccount value for this Funding.
     * 
     * @return isCheckingAccount
     */
    public java.lang.Boolean getIsCheckingAccount() {
        return isCheckingAccount;
    }


    /**
     * Sets the isCheckingAccount value for this Funding.
     * 
     * @param isCheckingAccount
     */
    public void setIsCheckingAccount(java.lang.Boolean isCheckingAccount) {
        this.isCheckingAccount = isCheckingAccount;
    }


    /**
     * Gets the payTo value for this Funding.
     * 
     * @return payTo
     */
    public org.datacontract.schemas._2004._07.IntegrationSDK.EnumsPolicyPayTo getPayTo() {
        return payTo;
    }


    /**
     * Sets the payTo value for this Funding.
     * 
     * @param payTo
     */
    public void setPayTo(org.datacontract.schemas._2004._07.IntegrationSDK.EnumsPolicyPayTo payTo) {
        this.payTo = payTo;
    }


    /**
     * Gets the paymentMethod value for this Funding.
     * 
     * @return paymentMethod
     */
    public org.datacontract.schemas._2004._07.IntegrationSDK.EnumsPolicyPaymentMethod getPaymentMethod() {
        return paymentMethod;
    }


    /**
     * Sets the paymentMethod value for this Funding.
     * 
     * @param paymentMethod
     */
    public void setPaymentMethod(org.datacontract.schemas._2004._07.IntegrationSDK.EnumsPolicyPaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Funding)) return false;
        Funding other = (Funding) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.amount==null && other.getAmount()==null) || 
             (this.amount!=null &&
              this.amount.equals(other.getAmount()))) &&
            ((this.bankAcctNumber==null && other.getBankAcctNumber()==null) || 
             (this.bankAcctNumber!=null &&
              this.bankAcctNumber.equals(other.getBankAcctNumber()))) &&
            ((this.bankRoutingNumber==null && other.getBankRoutingNumber()==null) || 
             (this.bankRoutingNumber!=null &&
              this.bankRoutingNumber.equals(other.getBankRoutingNumber()))) &&
            ((this.fundingDate==null && other.getFundingDate()==null) || 
             (this.fundingDate!=null &&
              this.fundingDate.equals(other.getFundingDate()))) &&
            ((this.fundingType==null && other.getFundingType()==null) || 
             (this.fundingType!=null &&
              this.fundingType.equals(other.getFundingType()))) &&
            ((this.isCheckingAccount==null && other.getIsCheckingAccount()==null) || 
             (this.isCheckingAccount!=null &&
              this.isCheckingAccount.equals(other.getIsCheckingAccount()))) &&
            ((this.payTo==null && other.getPayTo()==null) || 
             (this.payTo!=null &&
              this.payTo.equals(other.getPayTo()))) &&
            ((this.paymentMethod==null && other.getPaymentMethod()==null) || 
             (this.paymentMethod!=null &&
              this.paymentMethod.equals(other.getPaymentMethod())));
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
        if (getAmount() != null) {
            _hashCode += getAmount().hashCode();
        }
        if (getBankAcctNumber() != null) {
            _hashCode += getBankAcctNumber().hashCode();
        }
        if (getBankRoutingNumber() != null) {
            _hashCode += getBankRoutingNumber().hashCode();
        }
        if (getFundingDate() != null) {
            _hashCode += getFundingDate().hashCode();
        }
        if (getFundingType() != null) {
            _hashCode += getFundingType().hashCode();
        }
        if (getIsCheckingAccount() != null) {
            _hashCode += getIsCheckingAccount().hashCode();
        }
        if (getPayTo() != null) {
            _hashCode += getPayTo().hashCode();
        }
        if (getPaymentMethod() != null) {
            _hashCode += getPaymentMethod().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Funding.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IntegrationSDK.Model", "Funding"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("amount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IntegrationSDK.Model", "Amount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
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
        elemField.setFieldName("fundingDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IntegrationSDK.Model", "FundingDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fundingType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IntegrationSDK.Model", "FundingType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IntegrationSDK", "Enums.PolicyFundingType"));
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
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("payTo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IntegrationSDK.Model", "PayTo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IntegrationSDK", "Enums.PolicyPayTo"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("paymentMethod");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IntegrationSDK.Model", "PaymentMethod"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IntegrationSDK", "Enums.PolicyPaymentMethod"));
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
