/**
 * OSI_CustomLinkSubmitStartEsignResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.datacontract.schemas._2004._07.IPFS_DTO;

public class OSI_CustomLinkSubmitStartEsignResponse  extends org.datacontract.schemas._2004._07.IPFS_DTO.OSI_IntegrationResponse  implements java.io.Serializable {
    private java.lang.String agentURL;

    private java.lang.String batchID;

    private org.datacontract.schemas._2004._07.IPFS_DTO.OSI_Error[] errors;

    private java.lang.String insuredUrl;

    public OSI_CustomLinkSubmitStartEsignResponse() {
    }

    public OSI_CustomLinkSubmitStartEsignResponse(
           java.lang.String message,
           java.lang.String request,
           java.lang.String response,
           java.lang.Boolean success,
           java.lang.String agentURL,
           java.lang.String batchID,
           org.datacontract.schemas._2004._07.IPFS_DTO.OSI_Error[] errors,
           java.lang.String insuredUrl) {
        super(
            message,
            request,
            response,
            success);
        this.agentURL = agentURL;
        this.batchID = batchID;
        this.errors = errors;
        this.insuredUrl = insuredUrl;
    }


    /**
     * Gets the agentURL value for this OSI_CustomLinkSubmitStartEsignResponse.
     * 
     * @return agentURL
     */
    public java.lang.String getAgentURL() {
        return agentURL;
    }


    /**
     * Sets the agentURL value for this OSI_CustomLinkSubmitStartEsignResponse.
     * 
     * @param agentURL
     */
    public void setAgentURL(java.lang.String agentURL) {
        this.agentURL = agentURL;
    }


    /**
     * Gets the batchID value for this OSI_CustomLinkSubmitStartEsignResponse.
     * 
     * @return batchID
     */
    public java.lang.String getBatchID() {
        return batchID;
    }


    /**
     * Sets the batchID value for this OSI_CustomLinkSubmitStartEsignResponse.
     * 
     * @param batchID
     */
    public void setBatchID(java.lang.String batchID) {
        this.batchID = batchID;
    }


    /**
     * Gets the errors value for this OSI_CustomLinkSubmitStartEsignResponse.
     * 
     * @return errors
     */
    public org.datacontract.schemas._2004._07.IPFS_DTO.OSI_Error[] getErrors() {
        return errors;
    }


    /**
     * Sets the errors value for this OSI_CustomLinkSubmitStartEsignResponse.
     * 
     * @param errors
     */
    public void setErrors(org.datacontract.schemas._2004._07.IPFS_DTO.OSI_Error[] errors) {
        this.errors = errors;
    }


    /**
     * Gets the insuredUrl value for this OSI_CustomLinkSubmitStartEsignResponse.
     * 
     * @return insuredUrl
     */
    public java.lang.String getInsuredUrl() {
        return insuredUrl;
    }


    /**
     * Sets the insuredUrl value for this OSI_CustomLinkSubmitStartEsignResponse.
     * 
     * @param insuredUrl
     */
    public void setInsuredUrl(java.lang.String insuredUrl) {
        this.insuredUrl = insuredUrl;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof OSI_CustomLinkSubmitStartEsignResponse)) return false;
        OSI_CustomLinkSubmitStartEsignResponse other = (OSI_CustomLinkSubmitStartEsignResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.agentURL==null && other.getAgentURL()==null) || 
             (this.agentURL!=null &&
              this.agentURL.equals(other.getAgentURL()))) &&
            ((this.batchID==null && other.getBatchID()==null) || 
             (this.batchID!=null &&
              this.batchID.equals(other.getBatchID()))) &&
            ((this.errors==null && other.getErrors()==null) || 
             (this.errors!=null &&
              java.util.Arrays.equals(this.errors, other.getErrors()))) &&
            ((this.insuredUrl==null && other.getInsuredUrl()==null) || 
             (this.insuredUrl!=null &&
              this.insuredUrl.equals(other.getInsuredUrl())));
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
        if (getAgentURL() != null) {
            _hashCode += getAgentURL().hashCode();
        }
        if (getBatchID() != null) {
            _hashCode += getBatchID().hashCode();
        }
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
        if (getInsuredUrl() != null) {
            _hashCode += getInsuredUrl().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(OSI_CustomLinkSubmitStartEsignResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IPFS.DTO", "OSI_CustomLinkSubmitStartEsignResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("agentURL");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IPFS.DTO", "AgentURL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("batchID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IPFS.DTO", "BatchID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errors");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IPFS.DTO", "Errors"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IPFS.DTO", "OSI_Error"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IPFS.DTO", "OSI_Error"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("insuredUrl");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IPFS.DTO", "InsuredUrl"));
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
