/**
 * Details.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.datacontract.schemas._2004._07.IntegrationSDK_Model;

public class Details  implements java.io.Serializable {
    private java.lang.String batchID;

    private java.math.BigDecimal brokerFee;

    private java.lang.Integer CRD;

    private org.datacontract.schemas._2004._07.IntegrationSDK.EnumsQuoteCommercial commercial;

    private org.datacontract.schemas._2004._07.IntegrationSDK.EnumsQuoteCoupon coupon;

    private java.lang.Boolean couponInvoicePrinted;

    private java.lang.Boolean displayPaymentPortalAddendum;

    private org.datacontract.schemas._2004._07.IntegrationSDK.EnumsQuoteDownPaymentMethod downPaymentMethod;

    private org.datacontract.schemas._2004._07.IntegrationSDK.EnumsQuoteDownPaymentPaidBy downPaymentPaidBy;

    private org.datacontract.schemas._2004._07.IntegrationSDK.EnumsEqualPaymentCalculationType equalPayments;

    private java.lang.Integer installments;

    private org.datacontract.schemas._2004._07.IntegrationSDK.EnumsQuoteInterval interval;

    private java.lang.Boolean processDownPayment;

    private org.datacontract.schemas._2004._07.IntegrationSDK.EnumsQuoteDownPaymentNoReason processDownPaymentNoReason;

    private java.lang.Integer programID;

    private org.datacontract.schemas._2004._07.IntegrationSDK_Model.RecurringACH recurringACH;

    private java.lang.Boolean returnPFA;

    private org.datacontract.schemas._2004._07.IntegrationSDK.EnumsQuoteESignOption eSignOpton;

    public Details() {
    }

    public Details(
           java.lang.String batchID,
           java.math.BigDecimal brokerFee,
           java.lang.Integer CRD,
           org.datacontract.schemas._2004._07.IntegrationSDK.EnumsQuoteCommercial commercial,
           org.datacontract.schemas._2004._07.IntegrationSDK.EnumsQuoteCoupon coupon,
           java.lang.Boolean couponInvoicePrinted,
           java.lang.Boolean displayPaymentPortalAddendum,
           org.datacontract.schemas._2004._07.IntegrationSDK.EnumsQuoteDownPaymentMethod downPaymentMethod,
           org.datacontract.schemas._2004._07.IntegrationSDK.EnumsQuoteDownPaymentPaidBy downPaymentPaidBy,
           org.datacontract.schemas._2004._07.IntegrationSDK.EnumsEqualPaymentCalculationType equalPayments,
           java.lang.Integer installments,
           org.datacontract.schemas._2004._07.IntegrationSDK.EnumsQuoteInterval interval,
           java.lang.Boolean processDownPayment,
           org.datacontract.schemas._2004._07.IntegrationSDK.EnumsQuoteDownPaymentNoReason processDownPaymentNoReason,
           java.lang.Integer programID,
           org.datacontract.schemas._2004._07.IntegrationSDK_Model.RecurringACH recurringACH,
           java.lang.Boolean returnPFA,
           org.datacontract.schemas._2004._07.IntegrationSDK.EnumsQuoteESignOption eSignOpton) {
           this.batchID = batchID;
           this.brokerFee = brokerFee;
           this.CRD = CRD;
           this.commercial = commercial;
           this.coupon = coupon;
           this.couponInvoicePrinted = couponInvoicePrinted;
           this.displayPaymentPortalAddendum = displayPaymentPortalAddendum;
           this.downPaymentMethod = downPaymentMethod;
           this.downPaymentPaidBy = downPaymentPaidBy;
           this.equalPayments = equalPayments;
           this.installments = installments;
           this.interval = interval;
           this.processDownPayment = processDownPayment;
           this.processDownPaymentNoReason = processDownPaymentNoReason;
           this.programID = programID;
           this.recurringACH = recurringACH;
           this.returnPFA = returnPFA;
           this.eSignOpton = eSignOpton;
    }


    /**
     * Gets the batchID value for this Details.
     * 
     * @return batchID
     */
    public java.lang.String getBatchID() {
        return batchID;
    }


    /**
     * Sets the batchID value for this Details.
     * 
     * @param batchID
     */
    public void setBatchID(java.lang.String batchID) {
        this.batchID = batchID;
    }


    /**
     * Gets the brokerFee value for this Details.
     * 
     * @return brokerFee
     */
    public java.math.BigDecimal getBrokerFee() {
        return brokerFee;
    }


    /**
     * Sets the brokerFee value for this Details.
     * 
     * @param brokerFee
     */
    public void setBrokerFee(java.math.BigDecimal brokerFee) {
        this.brokerFee = brokerFee;
    }


    /**
     * Gets the CRD value for this Details.
     * 
     * @return CRD
     */
    public java.lang.Integer getCRD() {
        return CRD;
    }


    /**
     * Sets the CRD value for this Details.
     * 
     * @param CRD
     */
    public void setCRD(java.lang.Integer CRD) {
        this.CRD = CRD;
    }


    /**
     * Gets the commercial value for this Details.
     * 
     * @return commercial
     */
    public org.datacontract.schemas._2004._07.IntegrationSDK.EnumsQuoteCommercial getCommercial() {
        return commercial;
    }


    /**
     * Sets the commercial value for this Details.
     * 
     * @param commercial
     */
    public void setCommercial(org.datacontract.schemas._2004._07.IntegrationSDK.EnumsQuoteCommercial commercial) {
        this.commercial = commercial;
    }


    /**
     * Gets the coupon value for this Details.
     * 
     * @return coupon
     */
    public org.datacontract.schemas._2004._07.IntegrationSDK.EnumsQuoteCoupon getCoupon() {
        return coupon;
    }


    /**
     * Sets the coupon value for this Details.
     * 
     * @param coupon
     */
    public void setCoupon(org.datacontract.schemas._2004._07.IntegrationSDK.EnumsQuoteCoupon coupon) {
        this.coupon = coupon;
    }


    /**
     * Gets the couponInvoicePrinted value for this Details.
     * 
     * @return couponInvoicePrinted
     */
    public java.lang.Boolean getCouponInvoicePrinted() {
        return couponInvoicePrinted;
    }


    /**
     * Sets the couponInvoicePrinted value for this Details.
     * 
     * @param couponInvoicePrinted
     */
    public void setCouponInvoicePrinted(java.lang.Boolean couponInvoicePrinted) {
        this.couponInvoicePrinted = couponInvoicePrinted;
    }


    /**
     * Gets the displayPaymentPortalAddendum value for this Details.
     * 
     * @return displayPaymentPortalAddendum
     */
    public java.lang.Boolean getDisplayPaymentPortalAddendum() {
        return displayPaymentPortalAddendum;
    }


    /**
     * Sets the displayPaymentPortalAddendum value for this Details.
     * 
     * @param displayPaymentPortalAddendum
     */
    public void setDisplayPaymentPortalAddendum(java.lang.Boolean displayPaymentPortalAddendum) {
        this.displayPaymentPortalAddendum = displayPaymentPortalAddendum;
    }


    /**
     * Gets the downPaymentMethod value for this Details.
     * 
     * @return downPaymentMethod
     */
    public org.datacontract.schemas._2004._07.IntegrationSDK.EnumsQuoteDownPaymentMethod getDownPaymentMethod() {
        return downPaymentMethod;
    }


    /**
     * Sets the downPaymentMethod value for this Details.
     * 
     * @param downPaymentMethod
     */
    public void setDownPaymentMethod(org.datacontract.schemas._2004._07.IntegrationSDK.EnumsQuoteDownPaymentMethod downPaymentMethod) {
        this.downPaymentMethod = downPaymentMethod;
    }


    /**
     * Gets the downPaymentPaidBy value for this Details.
     * 
     * @return downPaymentPaidBy
     */
    public org.datacontract.schemas._2004._07.IntegrationSDK.EnumsQuoteDownPaymentPaidBy getDownPaymentPaidBy() {
        return downPaymentPaidBy;
    }


    /**
     * Sets the downPaymentPaidBy value for this Details.
     * 
     * @param downPaymentPaidBy
     */
    public void setDownPaymentPaidBy(org.datacontract.schemas._2004._07.IntegrationSDK.EnumsQuoteDownPaymentPaidBy downPaymentPaidBy) {
        this.downPaymentPaidBy = downPaymentPaidBy;
    }


    /**
     * Gets the equalPayments value for this Details.
     * 
     * @return equalPayments
     */
    public org.datacontract.schemas._2004._07.IntegrationSDK.EnumsEqualPaymentCalculationType getEqualPayments() {
        return equalPayments;
    }


    /**
     * Sets the equalPayments value for this Details.
     * 
     * @param equalPayments
     */
    public void setEqualPayments(org.datacontract.schemas._2004._07.IntegrationSDK.EnumsEqualPaymentCalculationType equalPayments) {
        this.equalPayments = equalPayments;
    }


    /**
     * Gets the installments value for this Details.
     * 
     * @return installments
     */
    public java.lang.Integer getInstallments() {
        return installments;
    }


    /**
     * Sets the installments value for this Details.
     * 
     * @param installments
     */
    public void setInstallments(java.lang.Integer installments) {
        this.installments = installments;
    }


    /**
     * Gets the interval value for this Details.
     * 
     * @return interval
     */
    public org.datacontract.schemas._2004._07.IntegrationSDK.EnumsQuoteInterval getInterval() {
        return interval;
    }


    /**
     * Sets the interval value for this Details.
     * 
     * @param interval
     */
    public void setInterval(org.datacontract.schemas._2004._07.IntegrationSDK.EnumsQuoteInterval interval) {
        this.interval = interval;
    }


    /**
     * Gets the processDownPayment value for this Details.
     * 
     * @return processDownPayment
     */
    public java.lang.Boolean getProcessDownPayment() {
        return processDownPayment;
    }


    /**
     * Sets the processDownPayment value for this Details.
     * 
     * @param processDownPayment
     */
    public void setProcessDownPayment(java.lang.Boolean processDownPayment) {
        this.processDownPayment = processDownPayment;
    }


    /**
     * Gets the processDownPaymentNoReason value for this Details.
     * 
     * @return processDownPaymentNoReason
     */
    public org.datacontract.schemas._2004._07.IntegrationSDK.EnumsQuoteDownPaymentNoReason getProcessDownPaymentNoReason() {
        return processDownPaymentNoReason;
    }


    /**
     * Sets the processDownPaymentNoReason value for this Details.
     * 
     * @param processDownPaymentNoReason
     */
    public void setProcessDownPaymentNoReason(org.datacontract.schemas._2004._07.IntegrationSDK.EnumsQuoteDownPaymentNoReason processDownPaymentNoReason) {
        this.processDownPaymentNoReason = processDownPaymentNoReason;
    }


    /**
     * Gets the programID value for this Details.
     * 
     * @return programID
     */
    public java.lang.Integer getProgramID() {
        return programID;
    }


    /**
     * Sets the programID value for this Details.
     * 
     * @param programID
     */
    public void setProgramID(java.lang.Integer programID) {
        this.programID = programID;
    }


    /**
     * Gets the recurringACH value for this Details.
     * 
     * @return recurringACH
     */
    public org.datacontract.schemas._2004._07.IntegrationSDK_Model.RecurringACH getRecurringACH() {
        return recurringACH;
    }


    /**
     * Sets the recurringACH value for this Details.
     * 
     * @param recurringACH
     */
    public void setRecurringACH(org.datacontract.schemas._2004._07.IntegrationSDK_Model.RecurringACH recurringACH) {
        this.recurringACH = recurringACH;
    }


    /**
     * Gets the returnPFA value for this Details.
     * 
     * @return returnPFA
     */
    public java.lang.Boolean getReturnPFA() {
        return returnPFA;
    }


    /**
     * Sets the returnPFA value for this Details.
     * 
     * @param returnPFA
     */
    public void setReturnPFA(java.lang.Boolean returnPFA) {
        this.returnPFA = returnPFA;
    }


    /**
     * Gets the eSignOpton value for this Details.
     * 
     * @return eSignOpton
     */
    public org.datacontract.schemas._2004._07.IntegrationSDK.EnumsQuoteESignOption getESignOpton() {
        return eSignOpton;
    }


    /**
     * Sets the eSignOpton value for this Details.
     * 
     * @param eSignOpton
     */
    public void setESignOpton(org.datacontract.schemas._2004._07.IntegrationSDK.EnumsQuoteESignOption eSignOpton) {
        this.eSignOpton = eSignOpton;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Details)) return false;
        Details other = (Details) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.batchID==null && other.getBatchID()==null) || 
             (this.batchID!=null &&
              this.batchID.equals(other.getBatchID()))) &&
            ((this.brokerFee==null && other.getBrokerFee()==null) || 
             (this.brokerFee!=null &&
              this.brokerFee.equals(other.getBrokerFee()))) &&
            ((this.CRD==null && other.getCRD()==null) || 
             (this.CRD!=null &&
              this.CRD.equals(other.getCRD()))) &&
            ((this.commercial==null && other.getCommercial()==null) || 
             (this.commercial!=null &&
              this.commercial.equals(other.getCommercial()))) &&
            ((this.coupon==null && other.getCoupon()==null) || 
             (this.coupon!=null &&
              this.coupon.equals(other.getCoupon()))) &&
            ((this.couponInvoicePrinted==null && other.getCouponInvoicePrinted()==null) || 
             (this.couponInvoicePrinted!=null &&
              this.couponInvoicePrinted.equals(other.getCouponInvoicePrinted()))) &&
            ((this.displayPaymentPortalAddendum==null && other.getDisplayPaymentPortalAddendum()==null) || 
             (this.displayPaymentPortalAddendum!=null &&
              this.displayPaymentPortalAddendum.equals(other.getDisplayPaymentPortalAddendum()))) &&
            ((this.downPaymentMethod==null && other.getDownPaymentMethod()==null) || 
             (this.downPaymentMethod!=null &&
              this.downPaymentMethod.equals(other.getDownPaymentMethod()))) &&
            ((this.downPaymentPaidBy==null && other.getDownPaymentPaidBy()==null) || 
             (this.downPaymentPaidBy!=null &&
              this.downPaymentPaidBy.equals(other.getDownPaymentPaidBy()))) &&
            ((this.equalPayments==null && other.getEqualPayments()==null) || 
             (this.equalPayments!=null &&
              this.equalPayments.equals(other.getEqualPayments()))) &&
            ((this.installments==null && other.getInstallments()==null) || 
             (this.installments!=null &&
              this.installments.equals(other.getInstallments()))) &&
            ((this.interval==null && other.getInterval()==null) || 
             (this.interval!=null &&
              this.interval.equals(other.getInterval()))) &&
            ((this.processDownPayment==null && other.getProcessDownPayment()==null) || 
             (this.processDownPayment!=null &&
              this.processDownPayment.equals(other.getProcessDownPayment()))) &&
            ((this.processDownPaymentNoReason==null && other.getProcessDownPaymentNoReason()==null) || 
             (this.processDownPaymentNoReason!=null &&
              this.processDownPaymentNoReason.equals(other.getProcessDownPaymentNoReason()))) &&
            ((this.programID==null && other.getProgramID()==null) || 
             (this.programID!=null &&
              this.programID.equals(other.getProgramID()))) &&
            ((this.recurringACH==null && other.getRecurringACH()==null) || 
             (this.recurringACH!=null &&
              this.recurringACH.equals(other.getRecurringACH()))) &&
            ((this.returnPFA==null && other.getReturnPFA()==null) || 
             (this.returnPFA!=null &&
              this.returnPFA.equals(other.getReturnPFA()))) &&
            ((this.eSignOpton==null && other.getESignOpton()==null) || 
             (this.eSignOpton!=null &&
              this.eSignOpton.equals(other.getESignOpton())));
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
        if (getBatchID() != null) {
            _hashCode += getBatchID().hashCode();
        }
        if (getBrokerFee() != null) {
            _hashCode += getBrokerFee().hashCode();
        }
        if (getCRD() != null) {
            _hashCode += getCRD().hashCode();
        }
        if (getCommercial() != null) {
            _hashCode += getCommercial().hashCode();
        }
        if (getCoupon() != null) {
            _hashCode += getCoupon().hashCode();
        }
        if (getCouponInvoicePrinted() != null) {
            _hashCode += getCouponInvoicePrinted().hashCode();
        }
        if (getDisplayPaymentPortalAddendum() != null) {
            _hashCode += getDisplayPaymentPortalAddendum().hashCode();
        }
        if (getDownPaymentMethod() != null) {
            _hashCode += getDownPaymentMethod().hashCode();
        }
        if (getDownPaymentPaidBy() != null) {
            _hashCode += getDownPaymentPaidBy().hashCode();
        }
        if (getEqualPayments() != null) {
            _hashCode += getEqualPayments().hashCode();
        }
        if (getInstallments() != null) {
            _hashCode += getInstallments().hashCode();
        }
        if (getInterval() != null) {
            _hashCode += getInterval().hashCode();
        }
        if (getProcessDownPayment() != null) {
            _hashCode += getProcessDownPayment().hashCode();
        }
        if (getProcessDownPaymentNoReason() != null) {
            _hashCode += getProcessDownPaymentNoReason().hashCode();
        }
        if (getProgramID() != null) {
            _hashCode += getProgramID().hashCode();
        }
        if (getRecurringACH() != null) {
            _hashCode += getRecurringACH().hashCode();
        }
        if (getReturnPFA() != null) {
            _hashCode += getReturnPFA().hashCode();
        }
        if (getESignOpton() != null) {
            _hashCode += getESignOpton().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Details.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IntegrationSDK.Model", "Details"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("batchID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IntegrationSDK.Model", "BatchID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("brokerFee");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IntegrationSDK.Model", "BrokerFee"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CRD");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IntegrationSDK.Model", "CRD"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("commercial");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IntegrationSDK.Model", "Commercial"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IntegrationSDK", "Enums.QuoteCommercial"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("coupon");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IntegrationSDK.Model", "Coupon"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IntegrationSDK", "Enums.QuoteCoupon"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("couponInvoicePrinted");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IntegrationSDK.Model", "CouponInvoicePrinted"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("displayPaymentPortalAddendum");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IntegrationSDK.Model", "DisplayPaymentPortalAddendum"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("downPaymentMethod");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IntegrationSDK.Model", "DownPaymentMethod"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IntegrationSDK", "Enums.QuoteDownPaymentMethod"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("downPaymentPaidBy");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IntegrationSDK.Model", "DownPaymentPaidBy"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IntegrationSDK", "Enums.QuoteDownPaymentPaidBy"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("equalPayments");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IntegrationSDK.Model", "EqualPayments"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IntegrationSDK", "Enums.EqualPaymentCalculationType"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("installments");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IntegrationSDK.Model", "Installments"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("interval");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IntegrationSDK.Model", "Interval"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IntegrationSDK", "Enums.QuoteInterval"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("processDownPayment");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IntegrationSDK.Model", "ProcessDownPayment"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("processDownPaymentNoReason");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IntegrationSDK.Model", "ProcessDownPaymentNoReason"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IntegrationSDK", "Enums.QuoteDownPaymentNoReason"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("programID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IntegrationSDK.Model", "ProgramID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("recurringACH");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IntegrationSDK.Model", "RecurringACH"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IntegrationSDK.Model", "RecurringACH"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("returnPFA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IntegrationSDK.Model", "ReturnPFA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ESignOpton");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IntegrationSDK.Model", "eSignOpton"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IntegrationSDK", "Enums.QuoteESignOption"));
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
