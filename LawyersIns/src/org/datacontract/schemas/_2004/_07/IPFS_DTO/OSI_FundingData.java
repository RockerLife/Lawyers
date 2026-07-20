/**
 * OSI_FundingData.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.datacontract.schemas._2004._07.IPFS_DTO;

public class OSI_FundingData  implements java.io.Serializable {
    private java.lang.String accountNumber;

    private java.lang.String amountFinanced;

    private java.lang.String clientName;

    private java.lang.String cutDate;

    private java.lang.String fundingAmount;

    private java.lang.String fundingType;

    private java.lang.String fundsID;

    private java.lang.String payDate;

    private java.lang.String payTo;

    private java.lang.String payee;

    private java.lang.String payeeAddress1;

    private java.lang.String payeeAddress2;

    private java.lang.String payeeCity;

    private java.lang.String payeeFax;

    private java.lang.String payeeState;

    private java.lang.String payeeZip;

    private org.datacontract.schemas._2004._07.IPFS_DTO.OSI_FDPolicy policy;

    public OSI_FundingData() {
    }

    public OSI_FundingData(
           java.lang.String accountNumber,
           java.lang.String amountFinanced,
           java.lang.String clientName,
           java.lang.String cutDate,
           java.lang.String fundingAmount,
           java.lang.String fundingType,
           java.lang.String fundsID,
           java.lang.String payDate,
           java.lang.String payTo,
           java.lang.String payee,
           java.lang.String payeeAddress1,
           java.lang.String payeeAddress2,
           java.lang.String payeeCity,
           java.lang.String payeeFax,
           java.lang.String payeeState,
           java.lang.String payeeZip,
           org.datacontract.schemas._2004._07.IPFS_DTO.OSI_FDPolicy policy) {
           this.accountNumber = accountNumber;
           this.amountFinanced = amountFinanced;
           this.clientName = clientName;
           this.cutDate = cutDate;
           this.fundingAmount = fundingAmount;
           this.fundingType = fundingType;
           this.fundsID = fundsID;
           this.payDate = payDate;
           this.payTo = payTo;
           this.payee = payee;
           this.payeeAddress1 = payeeAddress1;
           this.payeeAddress2 = payeeAddress2;
           this.payeeCity = payeeCity;
           this.payeeFax = payeeFax;
           this.payeeState = payeeState;
           this.payeeZip = payeeZip;
           this.policy = policy;
    }


    /**
     * Gets the accountNumber value for this OSI_FundingData.
     * 
     * @return accountNumber
     */
    public java.lang.String getAccountNumber() {
        return accountNumber;
    }


    /**
     * Sets the accountNumber value for this OSI_FundingData.
     * 
     * @param accountNumber
     */
    public void setAccountNumber(java.lang.String accountNumber) {
        this.accountNumber = accountNumber;
    }


    /**
     * Gets the amountFinanced value for this OSI_FundingData.
     * 
     * @return amountFinanced
     */
    public java.lang.String getAmountFinanced() {
        return amountFinanced;
    }


    /**
     * Sets the amountFinanced value for this OSI_FundingData.
     * 
     * @param amountFinanced
     */
    public void setAmountFinanced(java.lang.String amountFinanced) {
        this.amountFinanced = amountFinanced;
    }


    /**
     * Gets the clientName value for this OSI_FundingData.
     * 
     * @return clientName
     */
    public java.lang.String getClientName() {
        return clientName;
    }


    /**
     * Sets the clientName value for this OSI_FundingData.
     * 
     * @param clientName
     */
    public void setClientName(java.lang.String clientName) {
        this.clientName = clientName;
    }


    /**
     * Gets the cutDate value for this OSI_FundingData.
     * 
     * @return cutDate
     */
    public java.lang.String getCutDate() {
        return cutDate;
    }


    /**
     * Sets the cutDate value for this OSI_FundingData.
     * 
     * @param cutDate
     */
    public void setCutDate(java.lang.String cutDate) {
        this.cutDate = cutDate;
    }


    /**
     * Gets the fundingAmount value for this OSI_FundingData.
     * 
     * @return fundingAmount
     */
    public java.lang.String getFundingAmount() {
        return fundingAmount;
    }


    /**
     * Sets the fundingAmount value for this OSI_FundingData.
     * 
     * @param fundingAmount
     */
    public void setFundingAmount(java.lang.String fundingAmount) {
        this.fundingAmount = fundingAmount;
    }


    /**
     * Gets the fundingType value for this OSI_FundingData.
     * 
     * @return fundingType
     */
    public java.lang.String getFundingType() {
        return fundingType;
    }


    /**
     * Sets the fundingType value for this OSI_FundingData.
     * 
     * @param fundingType
     */
    public void setFundingType(java.lang.String fundingType) {
        this.fundingType = fundingType;
    }


    /**
     * Gets the fundsID value for this OSI_FundingData.
     * 
     * @return fundsID
     */
    public java.lang.String getFundsID() {
        return fundsID;
    }


    /**
     * Sets the fundsID value for this OSI_FundingData.
     * 
     * @param fundsID
     */
    public void setFundsID(java.lang.String fundsID) {
        this.fundsID = fundsID;
    }


    /**
     * Gets the payDate value for this OSI_FundingData.
     * 
     * @return payDate
     */
    public java.lang.String getPayDate() {
        return payDate;
    }


    /**
     * Sets the payDate value for this OSI_FundingData.
     * 
     * @param payDate
     */
    public void setPayDate(java.lang.String payDate) {
        this.payDate = payDate;
    }


    /**
     * Gets the payTo value for this OSI_FundingData.
     * 
     * @return payTo
     */
    public java.lang.String getPayTo() {
        return payTo;
    }


    /**
     * Sets the payTo value for this OSI_FundingData.
     * 
     * @param payTo
     */
    public void setPayTo(java.lang.String payTo) {
        this.payTo = payTo;
    }


    /**
     * Gets the payee value for this OSI_FundingData.
     * 
     * @return payee
     */
    public java.lang.String getPayee() {
        return payee;
    }


    /**
     * Sets the payee value for this OSI_FundingData.
     * 
     * @param payee
     */
    public void setPayee(java.lang.String payee) {
        this.payee = payee;
    }


    /**
     * Gets the payeeAddress1 value for this OSI_FundingData.
     * 
     * @return payeeAddress1
     */
    public java.lang.String getPayeeAddress1() {
        return payeeAddress1;
    }


    /**
     * Sets the payeeAddress1 value for this OSI_FundingData.
     * 
     * @param payeeAddress1
     */
    public void setPayeeAddress1(java.lang.String payeeAddress1) {
        this.payeeAddress1 = payeeAddress1;
    }


    /**
     * Gets the payeeAddress2 value for this OSI_FundingData.
     * 
     * @return payeeAddress2
     */
    public java.lang.String getPayeeAddress2() {
        return payeeAddress2;
    }


    /**
     * Sets the payeeAddress2 value for this OSI_FundingData.
     * 
     * @param payeeAddress2
     */
    public void setPayeeAddress2(java.lang.String payeeAddress2) {
        this.payeeAddress2 = payeeAddress2;
    }


    /**
     * Gets the payeeCity value for this OSI_FundingData.
     * 
     * @return payeeCity
     */
    public java.lang.String getPayeeCity() {
        return payeeCity;
    }


    /**
     * Sets the payeeCity value for this OSI_FundingData.
     * 
     * @param payeeCity
     */
    public void setPayeeCity(java.lang.String payeeCity) {
        this.payeeCity = payeeCity;
    }


    /**
     * Gets the payeeFax value for this OSI_FundingData.
     * 
     * @return payeeFax
     */
    public java.lang.String getPayeeFax() {
        return payeeFax;
    }


    /**
     * Sets the payeeFax value for this OSI_FundingData.
     * 
     * @param payeeFax
     */
    public void setPayeeFax(java.lang.String payeeFax) {
        this.payeeFax = payeeFax;
    }


    /**
     * Gets the payeeState value for this OSI_FundingData.
     * 
     * @return payeeState
     */
    public java.lang.String getPayeeState() {
        return payeeState;
    }


    /**
     * Sets the payeeState value for this OSI_FundingData.
     * 
     * @param payeeState
     */
    public void setPayeeState(java.lang.String payeeState) {
        this.payeeState = payeeState;
    }


    /**
     * Gets the payeeZip value for this OSI_FundingData.
     * 
     * @return payeeZip
     */
    public java.lang.String getPayeeZip() {
        return payeeZip;
    }


    /**
     * Sets the payeeZip value for this OSI_FundingData.
     * 
     * @param payeeZip
     */
    public void setPayeeZip(java.lang.String payeeZip) {
        this.payeeZip = payeeZip;
    }


    /**
     * Gets the policy value for this OSI_FundingData.
     * 
     * @return policy
     */
    public org.datacontract.schemas._2004._07.IPFS_DTO.OSI_FDPolicy getPolicy() {
        return policy;
    }


    /**
     * Sets the policy value for this OSI_FundingData.
     * 
     * @param policy
     */
    public void setPolicy(org.datacontract.schemas._2004._07.IPFS_DTO.OSI_FDPolicy policy) {
        this.policy = policy;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof OSI_FundingData)) return false;
        OSI_FundingData other = (OSI_FundingData) obj;
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
            ((this.amountFinanced==null && other.getAmountFinanced()==null) || 
             (this.amountFinanced!=null &&
              this.amountFinanced.equals(other.getAmountFinanced()))) &&
            ((this.clientName==null && other.getClientName()==null) || 
             (this.clientName!=null &&
              this.clientName.equals(other.getClientName()))) &&
            ((this.cutDate==null && other.getCutDate()==null) || 
             (this.cutDate!=null &&
              this.cutDate.equals(other.getCutDate()))) &&
            ((this.fundingAmount==null && other.getFundingAmount()==null) || 
             (this.fundingAmount!=null &&
              this.fundingAmount.equals(other.getFundingAmount()))) &&
            ((this.fundingType==null && other.getFundingType()==null) || 
             (this.fundingType!=null &&
              this.fundingType.equals(other.getFundingType()))) &&
            ((this.fundsID==null && other.getFundsID()==null) || 
             (this.fundsID!=null &&
              this.fundsID.equals(other.getFundsID()))) &&
            ((this.payDate==null && other.getPayDate()==null) || 
             (this.payDate!=null &&
              this.payDate.equals(other.getPayDate()))) &&
            ((this.payTo==null && other.getPayTo()==null) || 
             (this.payTo!=null &&
              this.payTo.equals(other.getPayTo()))) &&
            ((this.payee==null && other.getPayee()==null) || 
             (this.payee!=null &&
              this.payee.equals(other.getPayee()))) &&
            ((this.payeeAddress1==null && other.getPayeeAddress1()==null) || 
             (this.payeeAddress1!=null &&
              this.payeeAddress1.equals(other.getPayeeAddress1()))) &&
            ((this.payeeAddress2==null && other.getPayeeAddress2()==null) || 
             (this.payeeAddress2!=null &&
              this.payeeAddress2.equals(other.getPayeeAddress2()))) &&
            ((this.payeeCity==null && other.getPayeeCity()==null) || 
             (this.payeeCity!=null &&
              this.payeeCity.equals(other.getPayeeCity()))) &&
            ((this.payeeFax==null && other.getPayeeFax()==null) || 
             (this.payeeFax!=null &&
              this.payeeFax.equals(other.getPayeeFax()))) &&
            ((this.payeeState==null && other.getPayeeState()==null) || 
             (this.payeeState!=null &&
              this.payeeState.equals(other.getPayeeState()))) &&
            ((this.payeeZip==null && other.getPayeeZip()==null) || 
             (this.payeeZip!=null &&
              this.payeeZip.equals(other.getPayeeZip()))) &&
            ((this.policy==null && other.getPolicy()==null) || 
             (this.policy!=null &&
              this.policy.equals(other.getPolicy())));
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
        if (getAmountFinanced() != null) {
            _hashCode += getAmountFinanced().hashCode();
        }
        if (getClientName() != null) {
            _hashCode += getClientName().hashCode();
        }
        if (getCutDate() != null) {
            _hashCode += getCutDate().hashCode();
        }
        if (getFundingAmount() != null) {
            _hashCode += getFundingAmount().hashCode();
        }
        if (getFundingType() != null) {
            _hashCode += getFundingType().hashCode();
        }
        if (getFundsID() != null) {
            _hashCode += getFundsID().hashCode();
        }
        if (getPayDate() != null) {
            _hashCode += getPayDate().hashCode();
        }
        if (getPayTo() != null) {
            _hashCode += getPayTo().hashCode();
        }
        if (getPayee() != null) {
            _hashCode += getPayee().hashCode();
        }
        if (getPayeeAddress1() != null) {
            _hashCode += getPayeeAddress1().hashCode();
        }
        if (getPayeeAddress2() != null) {
            _hashCode += getPayeeAddress2().hashCode();
        }
        if (getPayeeCity() != null) {
            _hashCode += getPayeeCity().hashCode();
        }
        if (getPayeeFax() != null) {
            _hashCode += getPayeeFax().hashCode();
        }
        if (getPayeeState() != null) {
            _hashCode += getPayeeState().hashCode();
        }
        if (getPayeeZip() != null) {
            _hashCode += getPayeeZip().hashCode();
        }
        if (getPolicy() != null) {
            _hashCode += getPolicy().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(OSI_FundingData.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IPFS.DTO", "OSI_FundingData"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IPFS.DTO", "AccountNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("amountFinanced");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IPFS.DTO", "AmountFinanced"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("clientName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IPFS.DTO", "ClientName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cutDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IPFS.DTO", "CutDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fundingAmount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IPFS.DTO", "FundingAmount"));
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
        elemField.setFieldName("fundsID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IPFS.DTO", "FundsID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("payDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IPFS.DTO", "PayDate"));
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
        elemField.setFieldName("payee");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IPFS.DTO", "Payee"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("payeeAddress1");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IPFS.DTO", "PayeeAddress1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("payeeAddress2");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IPFS.DTO", "PayeeAddress2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("payeeCity");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IPFS.DTO", "PayeeCity"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("payeeFax");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IPFS.DTO", "PayeeFax"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("payeeState");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IPFS.DTO", "PayeeState"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("payeeZip");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IPFS.DTO", "PayeeZip"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("policy");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IPFS.DTO", "Policy"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IPFS.DTO", "OSI_FDPolicy"));
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
