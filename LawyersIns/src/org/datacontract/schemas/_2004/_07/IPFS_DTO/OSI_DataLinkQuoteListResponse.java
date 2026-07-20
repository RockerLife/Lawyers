/**
 * OSI_DataLinkQuoteListResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.datacontract.schemas._2004._07.IPFS_DTO;

public class OSI_DataLinkQuoteListResponse  extends org.datacontract.schemas._2004._07.IPFS_DTO.OSI_IntegrationResponse  implements java.io.Serializable {
    private org.datacontract.schemas._2004._07.IPFS_DTO.OSI_Error[] errors;

    private org.datacontract.schemas._2004._07.IPFS_DTO.OSI_QuoteData[] quoteList;

    public OSI_DataLinkQuoteListResponse() {
    }

    public OSI_DataLinkQuoteListResponse(
           java.lang.String message,
           java.lang.String request,
           java.lang.String response,
           java.lang.Boolean success,
           org.datacontract.schemas._2004._07.IPFS_DTO.OSI_Error[] errors,
           org.datacontract.schemas._2004._07.IPFS_DTO.OSI_QuoteData[] quoteList) {
        super(
            message,
            request,
            response,
            success);
        this.errors = errors;
        this.quoteList = quoteList;
    }


    /**
     * Gets the errors value for this OSI_DataLinkQuoteListResponse.
     * 
     * @return errors
     */
    public org.datacontract.schemas._2004._07.IPFS_DTO.OSI_Error[] getErrors() {
        return errors;
    }


    /**
     * Sets the errors value for this OSI_DataLinkQuoteListResponse.
     * 
     * @param errors
     */
    public void setErrors(org.datacontract.schemas._2004._07.IPFS_DTO.OSI_Error[] errors) {
        this.errors = errors;
    }


    /**
     * Gets the quoteList value for this OSI_DataLinkQuoteListResponse.
     * 
     * @return quoteList
     */
    public org.datacontract.schemas._2004._07.IPFS_DTO.OSI_QuoteData[] getQuoteList() {
        return quoteList;
    }


    /**
     * Sets the quoteList value for this OSI_DataLinkQuoteListResponse.
     * 
     * @param quoteList
     */
    public void setQuoteList(org.datacontract.schemas._2004._07.IPFS_DTO.OSI_QuoteData[] quoteList) {
        this.quoteList = quoteList;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof OSI_DataLinkQuoteListResponse)) return false;
        OSI_DataLinkQuoteListResponse other = (OSI_DataLinkQuoteListResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.errors==null && other.getErrors()==null) || 
             (this.errors!=null &&
              java.util.Arrays.equals(this.errors, other.getErrors()))) &&
            ((this.quoteList==null && other.getQuoteList()==null) || 
             (this.quoteList!=null &&
              java.util.Arrays.equals(this.quoteList, other.getQuoteList())));
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
        if (getErrors() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getErrors());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getErrors(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getQuoteList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getQuoteList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getQuoteList(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(OSI_DataLinkQuoteListResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IPFS.DTO", "OSI_DataLinkQuoteListResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errors");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IPFS.DTO", "Errors"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IPFS.DTO", "OSI_Error"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IPFS.DTO", "OSI_Error"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("quoteList");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IPFS.DTO", "QuoteList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IPFS.DTO", "OSI_QuoteData"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IPFS.DTO", "OSI_QuoteData"));
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
