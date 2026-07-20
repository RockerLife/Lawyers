/**
 * EnumsQuoteESignOption.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.datacontract.schemas._2004._07.IntegrationSDK;

public class EnumsQuoteESignOption implements java.io.Serializable {
    private java.lang.String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected EnumsQuoteESignOption(java.lang.String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final java.lang.String _Item = "Item";
    public static final java.lang.String _Item2 = "Item2";
    public static final java.lang.String _Item2a = "Item2a";
    public static final java.lang.String _Item3 = "Item3";
    public static final java.lang.String _Item4 = "Item4";
    public static final java.lang.String _Item5 = "Item5";
    public static final EnumsQuoteESignOption Item = new EnumsQuoteESignOption(_Item);
    public static final EnumsQuoteESignOption Item2 = new EnumsQuoteESignOption(_Item2);
    public static final EnumsQuoteESignOption Item2a = new EnumsQuoteESignOption(_Item2a);
    public static final EnumsQuoteESignOption Item3 = new EnumsQuoteESignOption(_Item3);
    public static final EnumsQuoteESignOption Item4 = new EnumsQuoteESignOption(_Item4);
    public static final EnumsQuoteESignOption Item5 = new EnumsQuoteESignOption(_Item5);
    public java.lang.String getValue() { return _value_;}
    public static EnumsQuoteESignOption fromValue(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        EnumsQuoteESignOption enumeration = (EnumsQuoteESignOption)
            _table_.get(value);
        if (enumeration==null) throw new java.lang.IllegalArgumentException();
        return enumeration;
    }
    public static EnumsQuoteESignOption fromString(java.lang.String value)
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
        new org.apache.axis.description.TypeDesc(EnumsQuoteESignOption.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IntegrationSDK", "Enums.QuoteESignOption"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
