/**
 * OSI_DataLinkMailingImageResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.datacontract.schemas._2004._07.IPFS_DTO;

public class OSI_DataLinkMailingImageResponse  extends org.datacontract.schemas._2004._07.IPFS_DTO.OSI_IntegrationResponse  implements java.io.Serializable {
    private org.datacontract.schemas._2004._07.IPFS_DTO.OSI_Error[] errors;

    private java.lang.String imageString;

    public OSI_DataLinkMailingImageResponse() {
    }

    public OSI_DataLinkMailingImageResponse(
           java.lang.String message,
           java.lang.String request,
           java.lang.String response,
           java.lang.Boolean success,
           org.datacontract.schemas._2004._07.IPFS_DTO.OSI_Error[] errors,
           java.lang.String imageString) {
        super(
            message,
            request,
            response,
            success);
        this.errors = errors;
        this.imageString = imageString;
    }


    /**
     * Gets the errors value for this OSI_DataLinkMailingImageResponse.
     * 
     * @return errors
     */
    public org.datacontract.schemas._2004._07.IPFS_DTO.OSI_Error[] getErrors() {
        return errors;
    }


    /**
     * Sets the errors value for this OSI_DataLinkMailingImageResponse.
     * 
     * @param errors
     */
    public void setErrors(org.datacontract.schemas._2004._07.IPFS_DTO.OSI_Error[] errors) {
        this.errors = errors;
    }


    /**
     * Gets the imageString value for this OSI_DataLinkMailingImageResponse.
     * 
     * @return imageString
     */
    public java.lang.String getImageString() {
        return imageString;
    }


    /**
     * Sets the imageString value for this OSI_DataLinkMailingImageResponse.
     * 
     * @param imageString
     */
    public void setImageString(java.lang.String imageString) {
        this.imageString = imageString;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof OSI_DataLinkMailingImageResponse)) return false;
        OSI_DataLinkMailingImageResponse other = (OSI_DataLinkMailingImageResponse) obj;
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
            ((this.imageString==null && other.getImageString()==null) || 
             (this.imageString!=null &&
              this.imageString.equals(other.getImageString())));
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
        if (getImageString() != null) {
            _hashCode += getImageString().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(OSI_DataLinkMailingImageResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IPFS.DTO", "OSI_DataLinkMailingImageResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errors");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IPFS.DTO", "Errors"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IPFS.DTO", "OSI_Error"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IPFS.DTO", "OSI_Error"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("imageString");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IPFS.DTO", "ImageString"));
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
