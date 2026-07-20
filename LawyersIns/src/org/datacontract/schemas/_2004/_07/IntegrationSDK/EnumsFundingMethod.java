/**
 * EnumsFundingMethod.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.datacontract.schemas._2004._07.IntegrationSDK;

public class EnumsFundingMethod implements java.io.Serializable {
    private java.lang.String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected EnumsFundingMethod(java.lang.String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final java.lang.String _CHK = "CHK";
    public static final java.lang.String _ACH = "ACH";
    public static final java.lang.String _DFT = "DFT";
    public static final java.lang.String _WIR = "WIR";
    public static final java.lang.String _All = "All";
    public static final EnumsFundingMethod CHK = new EnumsFundingMethod(_CHK);
    public static final EnumsFundingMethod ACH = new EnumsFundingMethod(_ACH);
    public static final EnumsFundingMethod DFT = new EnumsFundingMethod(_DFT);
    public static final EnumsFundingMethod WIR = new EnumsFundingMethod(_WIR);
    public static final EnumsFundingMethod All = new EnumsFundingMethod(_All);
    public java.lang.String getValue() { return _value_;}
    public static EnumsFundingMethod fromValue(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        EnumsFundingMethod enumeration = (EnumsFundingMethod)
            _table_.get(value);
        if (enumeration==null) throw new java.lang.IllegalArgumentException();
        return enumeration;
    }
    public static EnumsFundingMethod fromString(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        return fromValue(value);
    }
    public boolean equals(java.lang.Object obj) {return (obj == this);}
    public int hashCode() { return toString().hashCode();}
    public java.lang.String toString() { return _value_;}
    public java.lang.Object readResolve() throws java.io.ObjectStreamException { return fromValue(_value_);}
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new org.apache.axis.encoding.ser.EnumSerializer(
            _javaType, _xmlType);
    }
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new org.apache.axis.encoding.ser.EnumDeserializer(
            _javaType, _xmlType);
    }
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EnumsFundingMethod.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IntegrationSDK", "Enums.FundingMethod"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
