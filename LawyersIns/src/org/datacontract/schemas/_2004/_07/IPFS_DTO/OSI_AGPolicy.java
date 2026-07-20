/**
 * OSI_AGPolicy.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.datacontract.schemas._2004._07.IPFS_DTO;

public class OSI_AGPolicy  implements java.io.Serializable {
    private java.lang.String broker;

    private java.lang.String companyCancelDate;

    private java.lang.String companyName;

    private java.lang.String coverageType;

    private java.lang.String effectiveDate;

    private java.lang.String fees;

    private java.lang.String generalAgent;

    private java.lang.String polNum;

    private java.lang.String premiumAmt;

    private java.lang.String taxes;

    private java.lang.String term;

    public OSI_AGPolicy() {
    }

    public OSI_AGPolicy(
           java.lang.String broker,
           java.lang.String companyCancelDate,
           java.lang.String companyName,
           java.lang.String coverageType,
           java.lang.String effectiveDate,
           java.lang.String fees,
           java.lang.String generalAgent,
           java.lang.String polNum,
           java.lang.String premiumAmt,
           java.lang.String taxes,
           java.lang.String term) {
           this.broker = broker;
           this.companyCancelDate = companyCancelDate;
           this.companyName = companyName;
           this.coverageType = coverageType;
           this.effectiveDate = effectiveDate;
           this.fees = fees;
           this.generalAgent = generalAgent;
           this.polNum = polNum;
           this.premiumAmt = premiumAmt;
           this.taxes = taxes;
           this.term = term;
    }


    /**
     * Gets the broker value for this OSI_AGPolicy.
     * 
     * @return broker
     */
    public java.lang.String getBroker() {
        return broker;
    }


    /**
     * Sets the broker value for this OSI_AGPolicy.
     * 
     * @param broker
     */
    public void setBroker(java.lang.String broker) {
        this.broker = broker;
    }


    /**
     * Gets the companyCancelDate value for this OSI_AGPolicy.
     * 
     * @return companyCancelDate
     */
    public java.lang.String getCompanyCancelDate() {
        return companyCancelDate;
    }


    /**
     * Sets the companyCancelDate value for this OSI_AGPolicy.
     * 
     * @param companyCancelDate
     */
    public void setCompanyCancelDate(java.lang.String companyCancelDate) {
        this.companyCancelDate = companyCancelDate;
    }


    /**
     * Gets the companyName value for this OSI_AGPolicy.
     * 
     * @return companyName
     */
    public java.lang.String getCompanyName() {
        return companyName;
    }


    /**
     * Sets the companyName value for this OSI_AGPolicy.
     * 
     * @param companyName
     */
    public void setCompanyName(java.lang.String companyName) {
        this.companyName = companyName;
    }


    /**
     * Gets the coverageType value for this OSI_AGPolicy.
     * 
     * @return coverageType
     */
    public java.lang.String getCoverageType() {
        return coverageType;
    }


    /**
     * Sets the coverageType value for this OSI_AGPolicy.
     * 
     * @param coverageType
     */
    public void setCoverageType(java.lang.String coverageType) {
        this.coverageType = coverageType;
    }


    /**
     * Gets the effectiveDate value for this OSI_AGPolicy.
     * 
     * @return effectiveDate
     */
    public java.lang.String getEffectiveDate() {
        return effectiveDate;
    }


    /**
     * Sets the effectiveDate value for this OSI_AGPolicy.
     * 
     * @param effectiveDate
     */
    public void setEffectiveDate(java.lang.String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }


    /**
     * Gets the fees value for this OSI_AGPolicy.
     * 
     * @return fees
     */
    public java.lang.String getFees() {
        return fees;
    }


    /**
     * Sets the fees value for this OSI_AGPolicy.
     * 
     * @param fees
     */
    public void setFees(java.lang.String fees) {
        this.fees = fees;
    }


    /**
     * Gets the generalAgent value for this OSI_AGPolicy.
     * 
     * @return generalAgent
     */
    public java.lang.String getGeneralAgent() {
        return generalAgent;
    }


    /**
     * Sets the generalAgent value for this OSI_AGPolicy.
     * 
     * @param generalAgent
     */
    public void setGeneralAgent(java.lang.String generalAgent) {
        this.generalAgent = generalAgent;
    }


    /**
     * Gets the polNum value for this OSI_AGPolicy.
     * 
     * @return polNum
     */
    public java.lang.String getPolNum() {
        return polNum;
    }


    /**
     * Sets the polNum value for this OSI_AGPolicy.
     * 
     * @param polNum
     */
    public void setPolNum(java.lang.String polNum) {
        this.polNum = polNum;
    }


    /**
     * Gets the premiumAmt value for this OSI_AGPolicy.
     * 
     * @return premiumAmt
     */
    public java.lang.String getPremiumAmt() {
        return premiumAmt;
    }


    /**
     * Sets the premiumAmt value for this OSI_AGPolicy.
     * 
     * @param premiumAmt
     */
    public void setPremiumAmt(java.lang.String premiumAmt) {
        this.premiumAmt = premiumAmt;
    }


    /**
     * Gets the taxes value for this OSI_AGPolicy.
     * 
     * @return taxes
     */
    public java.lang.String getTaxes() {
        return taxes;
    }


    /**
     * Sets the taxes value for this OSI_AGPolicy.
     * 
     * @param taxes
     */
    public void setTaxes(java.lang.String taxes) {
        this.taxes = taxes;
    }


    /**
     * Gets the term value for this OSI_AGPolicy.
     * 
     * @return term
     */
    public java.lang.String getTerm() {
        return term;
    }


    /**
     * Sets the term value for this OSI_AGPolicy.
     * 
     * @param term
     */
    public void setTerm(java.lang.String term) {
        this.term = term;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof OSI_AGPolicy)) return false;
        OSI_AGPolicy other = (OSI_AGPolicy) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.broker==null && other.getBroker()==null) || 
             (this.broker!=null &&
              this.broker.equals(other.getBroker()))) &&
            ((this.companyCancelDate==null && other.getCompanyCancelDate()==null) || 
             (this.companyCancelDate!=null &&
              this.companyCancelDate.equals(other.getCompanyCancelDate()))) &&
            ((this.companyName==null && other.getCompanyName()==null) || 
             (this.companyName!=null &&
              this.companyName.equals(other.getCompanyName()))) &&
            ((this.coverageType==null && other.getCoverageType()==null) || 
             (this.coverageType!=null &&
              this.coverageType.equals(other.getCoverageType()))) &&
            ((this.effectiveDate==null && other.getEffectiveDate()==null) || 
             (this.effectiveDate!=null &&
              this.effectiveDate.equals(other.getEffectiveDate()))) &&
            ((this.fees==null && other.getFees()==null) || 
             (this.fees!=null &&
              this.fees.equals(other.getFees()))) &&
            ((this.generalAgent==null && other.getGeneralAgent()==null) || 
             (this.generalAgent!=null &&
              this.generalAgent.equals(other.getGeneralAgent()))) &&
            ((this.polNum==null && other.getPolNum()==null) || 
             (this.polNum!=null &&
              this.polNum.equals(other.getPolNum()))) &&
            ((this.premiumAmt==null && other.getPremiumAmt()==null) || 
             (this.premiumAmt!=null &&
              this.premiumAmt.equals(other.getPremiumAmt()))) &&
            ((this.taxes==null && other.getTaxes()==null) || 
             (this.taxes!=null &&
              this.taxes.equals(other.getTaxes()))) &&
            ((this.term==null && other.getTerm()==null) || 
             (this.term!=null &&
              this.term.equals(other.getTerm())));
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
        if (getBroker() != null) {
            _hashCode += getBroker().hashCode();
        }
        if (getCompanyCancelDate() != null) {
            _hashCode += getCompanyCancelDate().hashCode();
        }
        if (getCompanyName() != null) {
            _hashCode += getCompanyName().hashCode();
        }
        if (getCoverageType() != null) {
            _hashCode += getCoverageType().hashCode();
        }
        if (getEffectiveDate() != null) {
            _hashCode += getEffectiveDate().hashCode();
        }
        if (getFees() != null) {
            _hashCode += getFees().hashCode();
        }
        if (getGeneralAgent() != null) {
            _hashCode += getGeneralAgent().hashCode();
        }
        if (getPolNum() != null) {
            _hashCode += getPolNum().hashCode();
        }
        if (getPremiumAmt() != null) {
            _hashCode += getPremiumAmt().hashCode();
        }
        if (getTaxes() != null) {
            _hashCode += getTaxes().hashCode();
        }
        if (getTerm() != null) {
            _hashCode += getTerm().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(OSI_AGPolicy.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IPFS.DTO", "OSI_AGPolicy"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("broker");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IPFS.DTO", "Broker"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("companyCancelDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IPFS.DTO", "CompanyCancelDate"));
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
        elemField.setFieldName("coverageType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IPFS.DTO", "CoverageType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("effectiveDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IPFS.DTO", "EffectiveDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fees");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IPFS.DTO", "Fees"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("generalAgent");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IPFS.DTO", "GeneralAgent"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("polNum");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IPFS.DTO", "PolNum"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("premiumAmt");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IPFS.DTO", "PremiumAmt"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("taxes");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IPFS.DTO", "Taxes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("term");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IPFS.DTO", "Term"));
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
