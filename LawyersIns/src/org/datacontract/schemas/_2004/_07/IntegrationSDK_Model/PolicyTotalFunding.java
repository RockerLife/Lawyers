/**
 * PolicyTotalFunding.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.datacontract.schemas._2004._07.IntegrationSDK_Model;

public class PolicyTotalFunding  extends org.datacontract.schemas._2004._07.IntegrationSDK_Model.Funding  implements java.io.Serializable {
    public PolicyTotalFunding() {
    }

    public PolicyTotalFunding(
           java.math.BigDecimal amount,
           java.lang.String bankAcctNumber,
           java.lang.String bankRoutingNumber,
           java.util.Calendar fundingDate,
           org.datacontract.schemas._2004._07.IntegrationSDK.EnumsPolicyFundingType fundingType,
           java.lang.Boolean isCheckingAccount,
           org.datacontract.schemas._2004._07.IntegrationSDK.EnumsPolicyPayTo payTo,
           org.datacontract.schemas._2004._07.IntegrationSDK.EnumsPolicyPaymentMethod paymentMethod) {
        super(
            amount,
            bankAcctNumber,
            bankRoutingNumber,
            fundingDate,
            fundingType,
            isCheckingAccount,
            payTo,
            paymentMethod);
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PolicyTotalFunding)) return false;
        PolicyTotalFunding other = (PolicyTotalFunding) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj);
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
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PolicyTotalFunding.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IntegrationSDK.Model", "PolicyTotalFunding"));
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
