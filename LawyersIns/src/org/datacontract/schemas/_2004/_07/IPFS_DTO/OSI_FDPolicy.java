/**
 * OSI_FDPolicy.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.datacontract.schemas._2004._07.IPFS_DTO;

public class OSI_FDPolicy  implements java.io.Serializable {
    private java.lang.String accountNumber;

    private java.lang.String companyName;

    private java.lang.String fundsID;

    private java.lang.String policyEffectiveDate;

    private java.lang.String policyFeesTaxes;

    private java.lang.String policyNumber;

    private java.lang.String policyPremium;

    public OSI_FDPolicy() {
    }

    public OSI_FDPolicy(
           java.lang.String accountNumber,
           java.lang.String companyName,
           java.lang.String fundsID,
           java.lang.String policyEffectiveDate,
           java.lang.String policyFeesTaxes,
           java.lang.String policyNumber,
           java.lang.String policyPremium) {
           this.accountNumber = accountNumber;
           this.companyName = companyName;
           this.fundsID = fundsID;
           this.policyEffectiveDate = policyEffectiveDate;
           this.policyFeesTaxes = policyFeesTaxes;
           this.policyNumber = policyNumber;
           this.policyPremium = policyPremium;
    }


    /**
     * Gets the accountNumber value for this OSI_FDPolicy.
     * 
     * @return accountNumber
     */
    public java.lang.String getAccountNumber() {
        return accountNumber;
    }


    /**
     * Sets the accountNumber value for this OSI_FDPolicy.
     * 
     * @param accountNumber
     */
    public void setAccountNumber(java.lang.String accountNumber) {
        this.accountNumber = accountNumber;
    }


    /**
     * Gets the companyName value for this OSI_FDPolicy.
     * 
     * @return companyName
     */
    public java.lang.String getCompanyName() {
        return companyName;
    }


    /**
     * Sets the companyName value for this OSI_FDPolicy.
     * 
     * @param companyName
     */
    public void setCompanyName(java.lang.String companyName) {
        this.companyName = companyName;
    }


    /**
     * Gets the fundsID value for this OSI_FDPolicy.
     * 
     * @return fundsID
     */
    public java.lang.String getFundsID() {
        return fundsID;
    }


    /**
     * Sets the fundsID value for this OSI_FDPolicy.
     * 
     * @param fundsID
     */
    public void setFundsID(java.lang.String fundsID) {
        this.fundsID = fundsID;
    }


    /**
     * Gets the policyEffectiveDate value for this OSI_FDPolicy.
     * 
     * @return policyEffectiveDate
     */
    public java.lang.String getPolicyEffectiveDate() {
        return policyEffectiveDate;
    }


    /**
     * Sets the policyEffectiveDate value for this OSI_FDPolicy.
     * 
     * @param policyEffectiveDate
     */
    public void setPolicyEffectiveDate(java.lang.String policyEffectiveDate) {
        this.policyEffectiveDate = policyEffectiveDate;
    }


    /**
     * Gets the policyFeesTaxes value for this OSI_FDPolicy.
     * 
     * @return policyFeesTaxes
     */
    public java.lang.String getPolicyFeesTaxes() {
        return policyFeesTaxes;
    }


    /**
     * Sets the policyFeesTaxes value for this OSI_FDPolicy.
     * 
     * @param policyFeesTaxes
     */
    public void setPolicyFeesTaxes(java.lang.String policyFeesTaxes) {
        this.policyFeesTaxes = policyFeesTaxes;
    }


    /**
     * Gets the policyNumber value for this OSI_FDPolicy.
     * 
     * @return policyNumber
     */
    public java.lang.String getPolicyNumber() {
        return policyNumber;
    }


    /**
     * Sets the policyNumber value for this OSI_FDPolicy.
     * 
     * @param policyNumber
     */
    public void setPolicyNumber(java.lang.String policyNumber) {
        this.policyNumber = policyNumber;
    }


    /**
     * Gets the policyPremium value for this OSI_FDPolicy.
     * 
     * @return policyPremium
     */
    public java.lang.String getPolicyPremium() {
        return policyPremium;
    }


    /**
     * Sets the policyPremium value for this OSI_FDPolicy.
     * 
     * @param policyPremium
     */
    public void setPolicyPremium(java.lang.String policyPremium) {
        this.policyPremium = policyPremium;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof OSI_FDPolicy)) return false;
        OSI_FDPolicy other = (OSI_FDPolicy) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.accountNumber==null && other.getAccountNumber()==null) || 
             (this.accountNumber!=null &&
              this.accountNumber.equals(other.getAccountNumber()))) &&
            ((this.companyName==null && other.getCompanyName()==null) || 
             (this.companyName!=null &&
              this.companyName.equals(other.getCompanyName()))) &&
            ((this.fundsID==null && other.getFundsID()==null) || 
             (this.fundsID!=null &&
              this.fundsID.equals(other.getFundsID()))) &&
            ((this.policyEffectiveDate==null && other.getPolicyEffectiveDate()==null) || 
             (this.policyEffectiveDate!=null &&
              this.policyEffectiveDate.equals(other.getPolicyEffectiveDate()))) &&
            ((this.policyFeesTaxes==null && other.getPolicyFeesTaxes()==null) || 
             (this.policyFeesTaxes!=null &&
              this.policyFeesTaxes.equals(other.getPolicyFeesTaxes()))) &&
            ((this.policyNumber==null && other.getPolicyNumber()==null) || 
             (this.policyNumber!=null &&
              this.policyNumber.equals(other.getPolicyNumber()))) &&
            ((this.policyPremium==null && other.getPolicyPremium()==null) || 
             (this.policyPremium!=null &&
              this.policyPremium.equals(other.getPolicyPremium())));
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
        if (getAccountNumber() != null) {
            _hashCode += getAccountNumber().hashCode();
        }
        if (getCompanyName() != null) {
            _hashCode += getCompanyName().hashCode();
        }
        if (getFundsID() != null) {
            _hashCode += getFundsID().hashCode();
        }
        if (getPolicyEffectiveDate() != null) {
            _hashCode += getPolicyEffectiveDate().hashCode();
        }
        if (getPolicyFeesTaxes() != null) {
            _hashCode += getPolicyFeesTaxes().hashCode();
        }
        if (getPolicyNumber() != null) {
            _hashCode += getPolicyNumber().hashCode();
        }
        if (getPolicyPremium() != null) {
            _hashCode += getPolicyPremium().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(OSI_FDPolicy.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IPFS.DTO", "OSI_FDPolicy"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IPFS.DTO", "AccountNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("companyName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IPFS.DTO", "CompanyName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fundsID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IPFS.DTO", "FundsID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("policyEffectiveDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IPFS.DTO", "PolicyEffectiveDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("policyFeesTaxes");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IPFS.DTO", "PolicyFeesTaxes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("policyNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IPFS.DTO", "PolicyNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("policyPremium");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IPFS.DTO", "PolicyPremium"));
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
