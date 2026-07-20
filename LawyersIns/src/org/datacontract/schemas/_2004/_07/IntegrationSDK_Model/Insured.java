/**
 * Insured.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.datacontract.schemas._2004._07.IntegrationSDK_Model;

public class Insured  extends org.datacontract.schemas._2004._07.IntegrationSDK_Model.Entity  implements java.io.Serializable {
    private java.lang.String agentCustomerNumber;

    private java.lang.String email;

    private java.lang.Boolean enrollEforms;

    public Insured() {
    }

    public Insured(
           java.lang.String address1,
           java.lang.String address2,
           java.lang.String careOf,
           java.lang.String city,
           java.lang.String name,
           java.lang.String phone,
           java.lang.String state,
           java.lang.String uniqueID,
           java.lang.String zip,
           java.lang.String _address1,
           java.lang.String _address2,
           java.lang.String _careOf,
           java.lang.String _city,
           java.lang.String _name,
           java.lang.String _phone,
           java.lang.String _state,
           java.lang.String _zip,
           java.lang.String agentCustomerNumber,
           java.lang.String email,
           java.lang.Boolean enrollEforms) {
        super(
            address1,
            address2,
            careOf,
            city,
            name,
            phone,
            state,
            uniqueID,
            zip,
            _address1,
            _address2,
            _careOf,
            _city,
            _name,
            _phone,
            _state,
            _zip);
        this.agentCustomerNumber = agentCustomerNumber;
        this.email = email;
        this.enrollEforms = enrollEforms;
    }


    /**
     * Gets the agentCustomerNumber value for this Insured.
     * 
     * @return agentCustomerNumber
     */
    public java.lang.String getAgentCustomerNumber() {
        return agentCustomerNumber;
    }


    /**
     * Sets the agentCustomerNumber value for this Insured.
     * 
     * @param agentCustomerNumber
     */
    public void setAgentCustomerNumber(java.lang.String agentCustomerNumber) {
        this.agentCustomerNumber = agentCustomerNumber;
    }


    /**
     * Gets the email value for this Insured.
     * 
     * @return email
     */
    public java.lang.String getEmail() {
        return email;
    }


    /**
     * Sets the email value for this Insured.
     * 
     * @param email
     */
    public void setEmail(java.lang.String email) {
        this.email = email;
    }


    /**
     * Gets the enrollEforms value for this Insured.
     * 
     * @return enrollEforms
     */
    public java.lang.Boolean getEnrollEforms() {
        return enrollEforms;
    }


    /**
     * Sets the enrollEforms value for this Insured.
     * 
     * @param enrollEforms
     */
    public void setEnrollEforms(java.lang.Boolean enrollEforms) {
        this.enrollEforms = enrollEforms;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Insured)) return false;
        Insured other = (Insured) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.agentCustomerNumber==null && other.getAgentCustomerNumber()==null) || 
             (this.agentCustomerNumber!=null &&
              this.agentCustomerNumber.equals(other.getAgentCustomerNumber()))) &&
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
        int _hashCode = super.hashCode();
        if (getAgentCustomerNumber() != null) {
            _hashCode += getAgentCustomerNumber().hashCode();
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
        new org.apache.axis.description.TypeDesc(Insured.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IntegrationSDK.Model", "Insured"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("agentCustomerNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IntegrationSDK.Model", "AgentCustomerNumber"));
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
