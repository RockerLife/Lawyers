/**
 * Company.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.datacontract.schemas._2004._07.IntegrationSDK_Model;

public class Company  extends org.datacontract.schemas._2004._07.IntegrationSDK_Model.Entity  implements java.io.Serializable {
    private java.lang.String bestNum;

    public Company() {
    }

    public Company(
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
           java.lang.String bestNum) {
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
        this.bestNum = bestNum;
    }


    /**
     * Gets the bestNum value for this Company.
     * 
     * @return bestNum
     */
    public java.lang.String getBestNum() {
        return bestNum;
    }


    /**
     * Sets the bestNum value for this Company.
     * 
     * @param bestNum
     */
    public void setBestNum(java.lang.String bestNum) {
        this.bestNum = bestNum;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Company)) return false;
        Company other = (Company) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.bestNum==null && other.getBestNum()==null) || 
             (this.bestNum!=null &&
              this.bestNum.equals(other.getBestNum())));
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
        if (getBestNum() != null) {
            _hashCode += getBestNum().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Company.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IntegrationSDK.Model", "Company"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bestNum");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IntegrationSDK.Model", "BestNum"));
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
