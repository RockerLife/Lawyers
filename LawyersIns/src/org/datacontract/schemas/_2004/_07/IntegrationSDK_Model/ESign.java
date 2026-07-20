/**
 * ESign.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.datacontract.schemas._2004._07.IntegrationSDK_Model;

public class ESign  implements java.io.Serializable {
    private org.datacontract.schemas._2004._07.IntegrationSDK.EnumsQuoteDownPaymentMethod downPaymentMethod;

    private org.datacontract.schemas._2004._07.IntegrationSDK.EnumsQuoteDownPaymentPaidBy downPaymentPaidBy;

    private java.lang.Boolean processDownPayment;

    private org.datacontract.schemas._2004._07.IntegrationSDK.EnumsQuoteDownPaymentNoReason processDownPaymentNoReason;

    private org.datacontract.schemas._2004._07.IntegrationSDK.EnumsQuoteESignOption eSignOpton;

    public ESign() {
    }

    public ESign(
           org.datacontract.schemas._2004._07.IntegrationSDK.EnumsQuoteDownPaymentMethod downPaymentMethod,
           org.datacontract.schemas._2004._07.IntegrationSDK.EnumsQuoteDownPaymentPaidBy downPaymentPaidBy,
           java.lang.Boolean processDownPayment,
           org.datacontract.schemas._2004._07.IntegrationSDK.EnumsQuoteDownPaymentNoReason processDownPaymentNoReason,
           org.datacontract.schemas._2004._07.IntegrationSDK.EnumsQuoteESignOption eSignOpton) {
           this.downPaymentMethod = downPaymentMethod;
           this.downPaymentPaidBy = downPaymentPaidBy;
           this.processDownPayment = processDownPayment;
           this.processDownPaymentNoReason = processDownPaymentNoReason;
           this.eSignOpton = eSignOpton;
    }


    /**
     * Gets the downPaymentMethod value for this ESign.
     * 
     * @return downPaymentMethod
     */
    public org.datacontract.schemas._2004._07.IntegrationSDK.EnumsQuoteDownPaymentMethod getDownPaymentMethod() {
        return downPaymentMethod;
    }


    /**
     * Sets the downPaymentMethod value for this ESign.
     * 
     * @param downPaymentMethod
     */
    public void setDownPaymentMethod(org.datacontract.schemas._2004._07.IntegrationSDK.EnumsQuoteDownPaymentMethod downPaymentMethod) {
        this.downPaymentMethod = downPaymentMethod;
    }


    /**
     * Gets the downPaymentPaidBy value for this ESign.
     * 
     * @return downPaymentPaidBy
     */
    public org.datacontract.schemas._2004._07.IntegrationSDK.EnumsQuoteDownPaymentPaidBy getDownPaymentPaidBy() {
        return downPaymentPaidBy;
    }


    /**
     * Sets the downPaymentPaidBy value for this ESign.
     * 
     * @param downPaymentPaidBy
     */
    public void setDownPaymentPaidBy(org.datacontract.schemas._2004._07.IntegrationSDK.EnumsQuoteDownPaymentPaidBy downPaymentPaidBy) {
        this.downPaymentPaidBy = downPaymentPaidBy;
    }


    /**
     * Gets the processDownPayment value for this ESign.
     * 
     * @return processDownPayment
     */
    public java.lang.Boolean getProcessDownPayment() {
        return processDownPayment;
    }


    /**
     * Sets the processDownPayment value for this ESign.
     * 
     * @param processDownPayment
     */
    public void setProcessDownPayment(java.lang.Boolean processDownPayment) {
        this.processDownPayment = processDownPayment;
    }


    /**
     * Gets the processDownPaymentNoReason value for this ESign.
     * 
     * @return processDownPaymentNoReason
     */
    public org.datacontract.schemas._2004._07.IntegrationSDK.EnumsQuoteDownPaymentNoReason getProcessDownPaymentNoReason() {
        return processDownPaymentNoReason;
    }


    /**
     * Sets the processDownPaymentNoReason value for this ESign.
     * 
     * @param processDownPaymentNoReason
     */
    public void setProcessDownPaymentNoReason(org.datacontract.schemas._2004._07.IntegrationSDK.EnumsQuoteDownPaymentNoReason processDownPaymentNoReason) {
        this.processDownPaymentNoReason = processDownPaymentNoReason;
    }


    /**
     * Gets the eSignOpton value for this ESign.
     * 
     * @return eSignOpton
     */
    public org.datacontract.schemas._2004._07.IntegrationSDK.EnumsQuoteESignOption getESignOpton() {
        return eSignOpton;
    }


    /**
     * Sets the eSignOpton value for this ESign.
     * 
     * @param eSignOpton
     */
    public void setESignOpton(org.datacontract.schemas._2004._07.IntegrationSDK.EnumsQuoteESignOption eSignOpton) {
        this.eSignOpton = eSignOpton;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ESign)) return false;
        ESign other = (ESign) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.downPaymentMethod==null && other.getDownPaymentMethod()==null) || 
             (this.downPaymentMethod!=null &&
              this.downPaymentMethod.equals(other.getDownPaymentMethod()))) &&
            ((this.downPaymentPaidBy==null && other.getDownPaymentPaidBy()==null) || 
             (this.downPaymentPaidBy!=null &&
              this.downPaymentPaidBy.equals(other.getDownPaymentPaidBy()))) &&
            ((this.processDownPayment==null && other.getProcessDownPayment()==null) || 
             (this.processDownPayment!=null &&
              this.processDownPayment.equals(other.getProcessDownPayment()))) &&
            ((this.processDownPaymentNoReason==null && other.getProcessDownPaymentNoReason()==null) || 
             (this.processDownPaymentNoReason!=null &&
              this.processDownPaymentNoReason.equals(other.getProcessDownPaymentNoReason()))) &&
            ((this.eSignOpton==null && other.getESignOpton()==null) || 
             (this.eSignOpton!=null &&
              this.eSignOpton.equals(other.getESignOpton())));
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
        if (getDownPaymentMethod() != null) {
            _hashCode += getDownPaymentMethod().hashCode();
        }
        if (getDownPaymentPaidBy() != null) {
            _hashCode += getDownPaymentPaidBy().hashCode();
        }
        if (getProcessDownPayment() != null) {
            _hashCode += getProcessDownPayment().hashCode();
        }
        if (getProcessDownPaymentNoReason() != null) {
            _hashCode += getProcessDownPaymentNoReason().hashCode();
        }
        if (getESignOpton() != null) {
            _hashCode += getESignOpton().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ESign.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IntegrationSDK.Model", "ESign"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("downPaymentMethod");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IntegrationSDK.Model", "DownPaymentMethod"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IntegrationSDK", "Enums.QuoteDownPaymentMethod"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("downPaymentPaidBy");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IntegrationSDK.Model", "DownPaymentPaidBy"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IntegrationSDK", "Enums.QuoteDownPaymentPaidBy"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("processDownPayment");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IntegrationSDK.Model", "ProcessDownPayment"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("processDownPaymentNoReason");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IntegrationSDK.Model", "ProcessDownPaymentNoReason"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IntegrationSDK", "Enums.QuoteDownPaymentNoReason"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ESignOpton");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IntegrationSDK.Model", "eSignOpton"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IntegrationSDK", "Enums.QuoteESignOption"));
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
