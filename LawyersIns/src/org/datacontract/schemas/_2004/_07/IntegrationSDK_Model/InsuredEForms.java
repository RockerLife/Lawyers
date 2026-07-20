/**
 * InsuredEForms.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.datacontract.schemas._2004._07.IntegrationSDK_Model;

public class InsuredEForms  implements java.io.Serializable {
    private java.lang.String eformsDisclosure;

    private java.lang.String email;

    private java.lang.Boolean enrollEforms;

    public InsuredEForms() {
    }

    public InsuredEForms(
           java.lang.String eformsDisclosure,
           java.lang.String email,
           java.lang.Boolean enrollEforms) {
           this.eformsDisclosure = eformsDisclosure;
           this.email = email;
           this.enrollEforms = enrollEforms;
    }


    /**
     * Gets the eformsDisclosure value for this InsuredEForms.
     * 
     * @return eformsDisclosure
     */
    public java.lang.String getEformsDisclosure() {
        return eformsDisclosure;
    }


    /**
     * Sets the eformsDisclosure value for this InsuredEForms.
     * 
     * @param eformsDisclosure
     */
    public void setEformsDisclosure(java.lang.String eformsDisclosure) {
        this.eformsDisclosure = eformsDisclosure;
    }


    /**
     * Gets the email value for this InsuredEForms.
     * 
     * @return email
     */
    public java.lang.String getEmail() {
        return email;
    }


    /**
     * Sets the email value for this InsuredEForms.
     * 
     * @param email
     */
    public void setEmail(java.lang.String email) {
        this.email = email;
    }


    /**
     * Gets the enrollEforms value for this InsuredEForms.
     * 
     * @return enrollEforms
     */
    public java.lang.Boolean getEnrollEforms() {
        return enrollEforms;
    }


    /**
     * Sets the enrollEforms value for this InsuredEForms.
     * 
     * @param enrollEforms
     */
    public void setEnrollEforms(java.lang.Boolean enrollEforms) {
        this.enrollEforms = enrollEforms;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof InsuredEForms)) return false;
        InsuredEForms other = (InsuredEForms) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.eformsDisclosure==null && other.getEformsDisclosure()==null) || 
             (this.eformsDisclosure!=null &&
              this.eformsDisclosure.equals(other.getEformsDisclosure()))) &&
            ((this.email==null && other.getEmail()==null) || 
             (this.email!=null &&
              this.email.equals(other.getEmail()))) &&
            ((this.enrollEforms==null && other.getEnrollEforms()==null) || 
             (this.enrollEforms!=null &&
              this.enrollEforms.equals(other.getEnrollEforms())));
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
        if (getEformsDisclosure() != null) {
            _hashCode += getEformsDisclosure().hashCode();
        }
        if (getEmail() != null) {
            _hashCode += getEmail().hashCode();
        }
        if (getEnrollEforms() != null) {
            _hashCode += getEnrollEforms().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(InsuredEForms.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IntegrationSDK.Model", "InsuredEForms"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("eformsDisclosure");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IntegrationSDK.Model", "EformsDisclosure"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("email");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IntegrationSDK.Model", "Email"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("enrollEforms");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IntegrationSDK.Model", "EnrollEforms"));
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
