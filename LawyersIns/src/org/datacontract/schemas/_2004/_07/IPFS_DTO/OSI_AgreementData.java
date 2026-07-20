/**
 * OSI_AgreementData.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.datacontract.schemas._2004._07.IPFS_DTO;

public class OSI_AgreementData  implements java.io.Serializable {
    private org.datacontract.schemas._2004._07.IPFS_DTO.OSI_AGDisbursement[] disbursements;

    private org.datacontract.schemas._2004._07.IPFS_DTO.OSI_AGGeneralDetails generalDetails;

    private org.datacontract.schemas._2004._07.IPFS_DTO.OSI_AGMailing[] mailingsHistory;

    private org.datacontract.schemas._2004._07.IPFS_DTO.OSI_AGPaymentSchedule[] paymentSchedules;

    private org.datacontract.schemas._2004._07.IPFS_DTO.OSI_AGPolicy[] policies;

    private org.datacontract.schemas._2004._07.IPFS_DTO.OSI_AGTransaction[] transactionHistory;

    public OSI_AgreementData() {
    }

    public OSI_AgreementData(
           org.datacontract.schemas._2004._07.IPFS_DTO.OSI_AGDisbursement[] disbursements,
           org.datacontract.schemas._2004._07.IPFS_DTO.OSI_AGGeneralDetails generalDetails,
           org.datacontract.schemas._2004._07.IPFS_DTO.OSI_AGMailing[] mailingsHistory,
           org.datacontract.schemas._2004._07.IPFS_DTO.OSI_AGPaymentSchedule[] paymentSchedules,
           org.datacontract.schemas._2004._07.IPFS_DTO.OSI_AGPolicy[] policies,
           org.datacontract.schemas._2004._07.IPFS_DTO.OSI_AGTransaction[] transactionHistory) {
           this.disbursements = disbursements;
           this.generalDetails = generalDetails;
           this.mailingsHistory = mailingsHistory;
           this.paymentSchedules = paymentSchedules;
           this.policies = policies;
           this.transactionHistory = transactionHistory;
    }


    /**
     * Gets the disbursements value for this OSI_AgreementData.
     * 
     * @return disbursements
     */
    public org.datacontract.schemas._2004._07.IPFS_DTO.OSI_AGDisbursement[] getDisbursements() {
        return disbursements;
    }


    /**
     * Sets the disbursements value for this OSI_AgreementData.
     * 
     * @param disbursements
     */
    public void setDisbursements(org.datacontract.schemas._2004._07.IPFS_DTO.OSI_AGDisbursement[] disbursements) {
        this.disbursements = disbursements;
    }


    /**
     * Gets the generalDetails value for this OSI_AgreementData.
     * 
     * @return generalDetails
     */
    public org.datacontract.schemas._2004._07.IPFS_DTO.OSI_AGGeneralDetails getGeneralDetails() {
        return generalDetails;
    }


    /**
     * Sets the generalDetails value for this OSI_AgreementData.
     * 
     * @param generalDetails
     */
    public void setGeneralDetails(org.datacontract.schemas._2004._07.IPFS_DTO.OSI_AGGeneralDetails generalDetails) {
        this.generalDetails = generalDetails;
    }


    /**
     * Gets the mailingsHistory value for this OSI_AgreementData.
     * 
     * @return mailingsHistory
     */
    public org.datacontract.schemas._2004._07.IPFS_DTO.OSI_AGMailing[] getMailingsHistory() {
        return mailingsHistory;
    }


    /**
     * Sets the mailingsHistory value for this OSI_AgreementData.
     * 
     * @param mailingsHistory
     */
    public void setMailingsHistory(org.datacontract.schemas._2004._07.IPFS_DTO.OSI_AGMailing[] mailingsHistory) {
        this.mailingsHistory = mailingsHistory;
    }


    /**
     * Gets the paymentSchedules value for this OSI_AgreementData.
     * 
     * @return paymentSchedules
     */
    public org.datacontract.schemas._2004._07.IPFS_DTO.OSI_AGPaymentSchedule[] getPaymentSchedules() {
        return paymentSchedules;
    }


    /**
     * Sets the paymentSchedules value for this OSI_AgreementData.
     * 
     * @param paymentSchedules
     */
    public void setPaymentSchedules(org.datacontract.schemas._2004._07.IPFS_DTO.OSI_AGPaymentSchedule[] paymentSchedules) {
        this.paymentSchedules = paymentSchedules;
    }


    /**
     * Gets the policies value for this OSI_AgreementData.
     * 
     * @return policies
     */
    public org.datacontract.schemas._2004._07.IPFS_DTO.OSI_AGPolicy[] getPolicies() {
        return policies;
    }


    /**
     * Sets the policies value for this OSI_AgreementData.
     * 
     * @param policies
     */
    public void setPolicies(org.datacontract.schemas._2004._07.IPFS_DTO.OSI_AGPolicy[] policies) {
        this.policies = policies;
    }


    /**
     * Gets the transactionHistory value for this OSI_AgreementData.
     * 
     * @return transactionHistory
     */
    public org.datacontract.schemas._2004._07.IPFS_DTO.OSI_AGTransaction[] getTransactionHistory() {
        return transactionHistory;
    }


    /**
     * Sets the transactionHistory value for this OSI_AgreementData.
     * 
     * @param transactionHistory
     */
    public void setTransactionHistory(org.datacontract.schemas._2004._07.IPFS_DTO.OSI_AGTransaction[] transactionHistory) {
        this.transactionHistory = transactionHistory;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof OSI_AgreementData)) return false;
        OSI_AgreementData other = (OSI_AgreementData) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.disbursements==null && other.getDisbursements()==null) || 
             (this.disbursements!=null &&
              java.util.Arrays.equals(this.disbursements, other.getDisbursements()))) &&
            ((this.generalDetails==null && other.getGeneralDetails()==null) || 
             (this.generalDetails!=null &&
              this.generalDetails.equals(other.getGeneralDetails()))) &&
            ((this.mailingsHistory==null && other.getMailingsHistory()==null) || 
             (this.mailingsHistory!=null &&
              java.util.Arrays.equals(this.mailingsHistory, other.getMailingsHistory()))) &&
            ((this.paymentSchedules==null && other.getPaymentSchedules()==null) || 
             (this.paymentSchedules!=null &&
              java.util.Arrays.equals(this.paymentSchedules, other.getPaymentSchedules()))) &&
            ((this.policies==null && other.getPolicies()==null) || 
             (this.policies!=null &&
              java.util.Arrays.equals(this.policies, other.getPolicies()))) &&
            ((this.transactionHistory==null && other.getTransactionHistory()==null) || 
             (this.transactionHistory!=null &&
              java.util.Arrays.equals(this.transactionHistory, other.getTransactionHistory())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getDisbursements() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDisbursements());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDisbursements(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getGeneralDetails() != null) {
            _hashCode += getGeneralDetails().hashCode();
        }
        if (getMailingsHistory() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getMailingsHistory());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getMailingsHistory(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getPaymentSchedules() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPaymentSchedules());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPaymentSchedules(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getPolicies() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPolicies());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPolicies(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getTransactionHistory() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTransactionHistory());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTransactionHistory(), i);
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
        new org.apache.axis.description.TypeDesc(OSI_AgreementData.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IPFS.DTO", "OSI_AgreementData"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("disbursements");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IPFS.DTO", "Disbursements"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IPFS.DTO", "OSI_AGDisbursement"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IPFS.DTO", "OSI_AGDisbursement"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("generalDetails");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IPFS.DTO", "GeneralDetails"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IPFS.DTO", "OSI_AGGeneralDetails"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mailingsHistory");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IPFS.DTO", "MailingsHistory"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IPFS.DTO", "OSI_AGMailing"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IPFS.DTO", "OSI_AGMailing"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("paymentSchedules");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IPFS.DTO", "PaymentSchedules"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IPFS.DTO", "OSI_AGPaymentSchedule"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IPFS.DTO", "OSI_AGPaymentSchedule"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("policies");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IPFS.DTO", "Policies"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IPFS.DTO", "OSI_AGPolicy"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IPFS.DTO", "OSI_AGPolicy"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("transactionHistory");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IPFS.DTO", "TransactionHistory"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IPFS.DTO", "OSI_AGTransaction"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IPFS.DTO", "OSI_AGTransaction"));
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
