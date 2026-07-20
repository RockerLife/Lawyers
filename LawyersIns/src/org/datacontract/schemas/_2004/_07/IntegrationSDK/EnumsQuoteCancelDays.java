/**
 * EnumsQuoteCancelDays.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.datacontract.schemas._2004._07.IntegrationSDK;

public class EnumsQuoteCancelDays implements java.io.Serializable {
    private java.lang.String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected EnumsQuoteCancelDays(java.lang.String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final java.lang.String _Item = "Item";
    public static final java.lang.String _Item0 = "Item0";
    public static final java.lang.String _Item10 = "Item10";
    public static final java.lang.String _Item15 = "Item15";
    public static final java.lang.String _Item20 = "Item20";
    public static final java.lang.String _Item25 = "Item25";
    public static final java.lang.String _Item30 = "Item30";
    public static final java.lang.String _Item35 = "Item35";
    public static final java.lang.String _Item60 = "Item60";
    public static final EnumsQuoteCancelDays Item = new EnumsQuoteCancelDays(_Item);
    public static final EnumsQuoteCancelDays Item0 = new EnumsQuoteCancelDays(_Item0);
    public static final EnumsQuoteCancelDays Item10 = new EnumsQuoteCancelDays(_Item10);
    public static final EnumsQuoteCancelDays Item15 = new EnumsQuoteCancelDays(_Item15);
    public static final EnumsQuoteCancelDays Item20 = new EnumsQuoteCancelDays(_Item20);
    public static final EnumsQuoteCancelDays Item25 = new EnumsQuoteCancelDays(_Item25);
    public static final EnumsQuoteCancelDays Item30 = new EnumsQuoteCancelDays(_Item30);
    public static final EnumsQuoteCancelDays Item35 = new EnumsQuoteCancelDays(_Item35);
    public static final EnumsQuoteCancelDays Item60 = new EnumsQuoteCancelDays(_Item60);
    public java.lang.String getValue() { return _value_;}
    public static EnumsQuoteCancelDays fromValue(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        EnumsQuoteCancelDays enumeration = (EnumsQuoteCancelDays)
            _table_.get(value);
        if (enumeration==null) throw new java.lang.IllegalArgumentException();
        return enumeration;
    }
    public static EnumsQuoteCancelDays fromString(java.lang.String value)
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
        new org.apache.axis.description.TypeDesc(EnumsQuoteCancelDays.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IntegrationSDK", "Enums.QuoteCancelDays"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
