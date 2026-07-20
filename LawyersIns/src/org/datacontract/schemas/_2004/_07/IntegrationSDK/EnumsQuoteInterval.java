/**
 * EnumsQuoteInterval.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.datacontract.schemas._2004._07.IntegrationSDK;

public class EnumsQuoteInterval implements java.io.Serializable {
    private java.lang.String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected EnumsQuoteInterval(java.lang.String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final java.lang.String _M = "M";
    public static final java.lang.String _B = "B";
    public static final java.lang.String _Q = "Q";
    public static final java.lang.String _S = "S";
    public static final java.lang.String _A = "A";
    public static final java.lang.String _Z = "Z";
    public static final java.lang.String _Y = "Y";
    public static final java.lang.String _X = "X";
    public static final java.lang.String _W = "W";
    public static final EnumsQuoteInterval M = new EnumsQuoteInterval(_M);
    public static final EnumsQuoteInterval B = new EnumsQuoteInterval(_B);
    public static final EnumsQuoteInterval Q = new EnumsQuoteInterval(_Q);
    public static final EnumsQuoteInterval S = new EnumsQuoteInterval(_S);
    public static final EnumsQuoteInterval A = new EnumsQuoteInterval(_A);
    public static final EnumsQuoteInterval Z = new EnumsQuoteInterval(_Z);
    public static final EnumsQuoteInterval Y = new EnumsQuoteInterval(_Y);
    public static final EnumsQuoteInterval X = new EnumsQuoteInterval(_X);
    public static final EnumsQuoteInterval W = new EnumsQuoteInterval(_W);
    public java.lang.String getValue() { return _value_;}
    public static EnumsQuoteInterval fromValue(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        EnumsQuoteInterval enumeration = (EnumsQuoteInterval)
            _table_.get(value);
        if (enumeration==null) throw new java.lang.IllegalArgumentException();
        return enumeration;
    }
    public static EnumsQuoteInterval fromString(java.lang.String value)
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
        new org.apache.axis.description.TypeDesc(EnumsQuoteInterval.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IntegrationSDK", "Enums.QuoteInterval"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
