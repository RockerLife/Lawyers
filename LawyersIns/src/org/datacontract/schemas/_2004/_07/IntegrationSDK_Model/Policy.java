/**
 * Policy.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.datacontract.schemas._2004._07.IntegrationSDK_Model;

public class Policy  implements java.io.Serializable {
    private java.lang.Boolean auditable;

    private org.datacontract.schemas._2004._07.IntegrationSDK_Model.Broker broker;

    private org.datacontract.schemas._2004._07.IntegrationSDK.EnumsQuoteCancelDays cancelDays;

    private org.datacontract.schemas._2004._07.IntegrationSDK_Model.Company company;

    private java.lang.String coverage;

    private java.math.BigDecimal down;

    private java.math.BigDecimal downPercent;

    private java.util.Calendar effectiveDate;

    private java.util.Calendar expirationDate;

    private java.math.BigDecimal fee;

    private org.datacontract.schemas._2004._07.IntegrationSDK_Model.GA GA;

    private java.lang.Boolean lossPayeeRequested;

    private java.math.BigDecimal maxLiability;

    private java.math.BigDecimal minLiability;

    private java.math.BigDecimal minimumEarned;

    private java.math.BigDecimal minimumEarnedPercent;

    private java.lang.String number;

    private java.math.BigDecimal premium;

    private java.math.BigDecimal tax;

    private java.lang.Integer term;

    private org.datacontract.schemas._2004._07.IntegrationSDK_Model.PolicyTotalFunding[] totalPayFunding;

    public Policy() {
    }

    public Policy(
           java.lang.Boolean auditable,
           org.datacontract.schemas._2004._07.IntegrationSDK_Model.Broker broker,
           org.datacontract.schemas._2004._07.IntegrationSDK.EnumsQuoteCancelDays cancelDays,
           org.datacontract.schemas._2004._07.IntegrationSDK_Model.Company company,
           java.lang.String coverage,
           java.math.BigDecimal down,
           java.math.BigDecimal downPercent,
           java.util.Calendar effectiveDate,
           java.util.Calendar expirationDate,
           java.math.BigDecimal fee,
           org.datacontract.schemas._2004._07.IntegrationSDK_Model.GA GA,
           java.lang.Boolean lossPayeeRequested,
           java.math.BigDecimal maxLiability,
           java.math.BigDecimal minLiability,
           java.math.BigDecimal minimumEarned,
           java.math.BigDecimal minimumEarnedPercent,
           java.lang.String number,
           java.math.BigDecimal premium,
           java.math.BigDecimal tax,
           java.lang.Integer term,
           org.datacontract.schemas._2004._07.IntegrationSDK_Model.PolicyTotalFunding[] totalPayFunding) {
           this.auditable = auditable;
           this.broker = broker;
           this.cancelDays = cancelDays;
           this.company = company;
           this.coverage = coverage;
           this.down = down;
           this.downPercent = downPercent;
           this.effectiveDate = effectiveDate;
           this.expirationDate = expirationDate;
           this.fee = fee;
           this.GA = GA;
           this.lossPayeeRequested = lossPayeeRequested;
           this.maxLiability = maxLiability;
           this.minLiability = minLiability;
           this.minimumEarned = minimumEarned;
           this.minimumEarnedPercent = minimumEarnedPercent;
           this.number = number;
           this.premium = premium;
           this.tax = tax;
           this.term = term;
           this.totalPayFunding = totalPayFunding;
    }


    /**
     * Gets the auditable value for this Policy.
     * 
     * @return auditable
     */
    public java.lang.Boolean getAuditable() {
        return auditable;
    }


    /**
     * Sets the auditable value for this Policy.
     * 
     * @param auditable
     */
    public void setAuditable(java.lang.Boolean auditable) {
        this.auditable = auditable;
    }


    /**
     * Gets the broker value for this Policy.
     * 
     * @return broker
     */
    public org.datacontract.schemas._2004._07.IntegrationSDK_Model.Broker getBroker() {
        return broker;
    }


    /**
     * Sets the broker value for this Policy.
     * 
     * @param broker
     */
    public void setBroker(org.datacontract.schemas._2004._07.IntegrationSDK_Model.Broker broker) {
        this.broker = broker;
    }


    /**
     * Gets the cancelDays value for this Policy.
     * 
     * @return cancelDays
     */
    public org.datacontract.schemas._2004._07.IntegrationSDK.EnumsQuoteCancelDays getCancelDays() {
        return cancelDays;
    }


    /**
     * Sets the cancelDays value for this Policy.
     * 
     * @param cancelDays
     */
    public void setCancelDays(org.datacontract.schemas._2004._07.IntegrationSDK.EnumsQuoteCancelDays cancelDays) {
        this.cancelDays = cancelDays;
    }


    /**
     * Gets the company value for this Policy.
     * 
     * @return company
     */
    public org.datacontract.schemas._2004._07.IntegrationSDK_Model.Company getCompany() {
        return company;
    }


    /**
     * Sets the company value for this Policy.
     * 
     * @param company
     */
    public void setCompany(org.datacontract.schemas._2004._07.IntegrationSDK_Model.Company company) {
        this.company = company;
    }


    /**
     * Gets the coverage value for this Policy.
     * 
     * @return coverage
     */
    public java.lang.String getCoverage() {
        return coverage;
    }


    /**
     * Sets the coverage value for this Policy.
     * 
     * @param coverage
     */
    public void setCoverage(java.lang.String coverage) {
        this.coverage = coverage;
    }


    /**
     * Gets the down value for this Policy.
     * 
     * @return down
     */
    public java.math.BigDecimal getDown() {
        return down;
    }


    /**
     * Sets the down value for this Policy.
     * 
     * @param down
     */
    public void setDown(java.math.BigDecimal down) {
        this.down = down;
    }


    /**
     * Gets the downPercent value for this Policy.
     * 
     * @return downPercent
     */
    public java.math.BigDecimal getDownPercent() {
        return downPercent;
    }


    /**
     * Sets the downPercent value for this Policy.
     * 
     * @param downPercent
     */
    public void setDownPercent(java.math.BigDecimal downPercent) {
        this.downPercent = downPercent;
    }


    /**
     * Gets the effectiveDate value for this Policy.
     * 
     * @return effectiveDate
     */
    public java.util.Calendar getEffectiveDate() {
        return effectiveDate;
    }


    /**
     * Sets the effectiveDate value for this Policy.
     * 
     * @param effectiveDate
     */
    public void setEffectiveDate(java.util.Calendar effectiveDate) {
        this.effectiveDate = effectiveDate;
    }


    /**
     * Gets the expirationDate value for this Policy.
     * 
     * @return expirationDate
     */
    public java.util.Calendar getExpirationDate() {
        return expirationDate;
    }


    /**
     * Sets the expirationDate value for this Policy.
     * 
     * @param expirationDate
     */
    public void setExpirationDate(java.util.Calendar expirationDate) {
        this.expirationDate = expirationDate;
    }


    /**
     * Gets the fee value for this Policy.
     * 
     * @return fee
     */
    public java.math.BigDecimal getFee() {
        return fee;
    }


    /**
     * Sets the fee value for this Policy.
     * 
     * @param fee
     */
    public void setFee(java.math.BigDecimal fee) {
        this.fee = fee;
    }


    /**
     * Gets the GA value for this Policy.
     * 
     * @return GA
     */
    public org.datacontract.schemas._2004._07.IntegrationSDK_Model.GA getGA() {
        return GA;
    }


    /**
     * Sets the GA value for this Policy.
     * 
     * @param GA
     */
    public void setGA(org.datacontract.schemas._2004._07.IntegrationSDK_Model.GA GA) {
        this.GA = GA;
    }


    /**
     * Gets the lossPayeeRequested value for this Policy.
     * 
     * @return lossPayeeRequested
     */
    public java.lang.Boolean getLossPayeeRequested() {
        return lossPayeeRequested;
    }


    /**
     * Sets the lossPayeeRequested value for this Policy.
     * 
     * @param lossPayeeRequested
     */
    public void setLossPayeeRequested(java.lang.Boolean lossPayeeRequested) {
        this.lossPayeeRequested = lossPayeeRequested;
    }


    /**
     * Gets the maxLiability value for this Policy.
     * 
     * @return maxLiability
     */
    public java.math.BigDecimal getMaxLiability() {
        return maxLiability;
    }


    /**
     * Sets the maxLiability value for this Policy.
     * 
     * @param maxLiability
     */
    public void setMaxLiability(java.math.BigDecimal maxLiability) {
        this.maxLiability = maxLiability;
    }


    /**
     * Gets the minLiability value for this Policy.
     * 
     * @return minLiability
     */
    public java.math.BigDecimal getMinLiability() {
        return minLiability;
    }


    /**
     * Sets the minLiability value for this Policy.
     * 
     * @param minLiability
     */
    public void setMinLiability(java.math.BigDecimal minLiability) {
        this.minLiability = minLiability;
    }


    /**
     * Gets the minimumEarned value for this Policy.
     * 
     * @return minimumEarned
     */
    public java.math.BigDecimal getMinimumEarned() {
        return minimumEarned;
    }


    /**
     * Sets the minimumEarned value for this Policy.
     * 
     * @param minimumEarned
     */
    public void setMinimumEarned(java.math.BigDecimal minimumEarned) {
        this.minimumEarned = minimumEarned;
    }


    /**
     * Gets the minimumEarnedPercent value for this Policy.
     * 
     * @return minimumEarnedPercent
     */
    public java.math.BigDecimal getMinimumEarnedPercent() {
        return minimumEarnedPercent;
    }


    /**
     * Sets the minimumEarnedPercent value for this Policy.
     * 
     * @param minimumEarnedPercent
     */
    public void setMinimumEarnedPercent(java.math.BigDecimal minimumEarnedPercent) {
        this.minimumEarnedPercent = minimumEarnedPercent;
    }


    /**
     * Gets the number value for this Policy.
     * 
     * @return number
     */
    public java.lang.String getNumber() {
        return number;
    }


    /**
     * Sets the number value for this Policy.
     * 
     * @param number
     */
    public void setNumber(java.lang.String number) {
        this.number = number;
    }


    /**
     * Gets the premium value for this Policy.
     * 
     * @return premium
     */
    public java.math.BigDecimal getPremium() {
        return premium;
    }


    /**
     * Sets the premium value for this Policy.
     * 
     * @param premium
     */
    public void setPremium(java.math.BigDecimal premium) {
        this.premium = premium;
    }


    /**
     * Gets the tax value for this Policy.
     * 
     * @return tax
     */
    public java.math.BigDecimal getTax() {
        return tax;
    }


    /**
     * Sets the tax value for this Policy.
     * 
     * @param tax
     */
    public void setTax(java.math.BigDecimal tax) {
        this.tax = tax;
    }


    /**
     * Gets the term value for this Policy.
     * 
     * @return term
     */
    public java.lang.Integer getTerm() {
        return term;
    }


    /**
     * Sets the term value for this Policy.
     * 
     * @param term
     */
    public void setTerm(java.lang.Integer term) {
        this.term = term;
    }


    /**
     * Gets the totalPayFunding value for this Policy.
     * 
     * @return totalPayFunding
     */
    public org.datacontract.schemas._2004._07.IntegrationSDK_Model.PolicyTotalFunding[] getTotalPayFunding() {
        return totalPayFunding;
    }


    /**
     * Sets the totalPayFunding value for this Policy.
     * 
     * @param totalPayFunding
     */
    public void setTotalPayFunding(org.datacontract.schemas._2004._07.IntegrationSDK_Model.PolicyTotalFunding[] totalPayFunding) {
        this.totalPayFunding = totalPayFunding;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Policy)) return false;
        Policy other = (Policy) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.auditable==null && other.getAuditable()==null) || 
             (this.auditable!=null &&
              this.auditable.equals(other.getAuditable()))) &&
            ((this.broker==null && other.getBroker()==null) || 
             (this.broker!=null &&
              this.broker.equals(other.getBroker()))) &&
            ((this.cancelDays==null && other.getCancelDays()==null) || 
             (this.cancelDays!=null &&
              this.cancelDays.equals(other.getCancelDays()))) &&
            ((this.company==null && other.getCompany()==null) || 
             (this.company!=null &&
              this.company.equals(other.getCompany()))) &&
            ((this.coverage==null && other.getCoverage()==null) || 
             (this.coverage!=null &&
              this.coverage.equals(other.getCoverage()))) &&
            ((this.down==null && other.getDown()==null) || 
             (this.down!=null &&
              this.down.equals(other.getDown()))) &&
            ((this.downPercent==null && other.getDownPercent()==null) || 
             (this.downPercent!=null &&
              this.downPercent.equals(other.getDownPercent()))) &&
            ((this.effectiveDate==null && other.getEffectiveDate()==null) || 
             (this.effectiveDate!=null &&
              this.effectiveDate.equals(other.getEffectiveDate()))) &&
            ((this.expirationDate==null && other.getExpirationDate()==null) || 
             (this.expirationDate!=null &&
              this.expirationDate.equals(other.getExpirationDate()))) &&
            ((this.fee==null && other.getFee()==null) || 
             (this.fee!=null &&
              this.fee.equals(other.getFee()))) &&
            ((this.GA==null && other.getGA()==null) || 
             (this.GA!=null &&
              this.GA.equals(other.getGA()))) &&
            ((this.lossPayeeRequested==null && other.getLossPayeeRequested()==null) || 
             (this.lossPayeeRequested!=null &&
              this.lossPayeeRequested.equals(other.getLossPayeeRequested()))) &&
            ((this.maxLiability==null && other.getMaxLiability()==null) || 
             (this.maxLiability!=null &&
              this.maxLiability.equals(other.getMaxLiability()))) &&
            ((this.minLiability==null && other.getMinLiability()==null) || 
             (this.minLiability!=null &&
              this.minLiability.equals(other.getMinLiability()))) &&
            ((this.minimumEarned==null && other.getMinimumEarned()==null) || 
             (this.minimumEarned!=null &&
              this.minimumEarned.equals(other.getMinimumEarned()))) &&
            ((this.minimumEarnedPercent==null && other.getMinimumEarnedPercent()==null) || 
             (this.minimumEarnedPercent!=null &&
              this.minimumEarnedPercent.equals(other.getMinimumEarnedPercent()))) &&
            ((this.number==null && other.getNumber()==null) || 
             (this.number!=null &&
              this.number.equals(other.getNumber()))) &&
            ((this.premium==null && other.getPremium()==null) || 
             (this.premium!=null &&
              this.premium.equals(other.getPremium()))) &&
            ((this.tax==null && other.getTax()==null) || 
             (this.tax!=null &&
              this.tax.equals(other.getTax()))) &&
            ((this.term==null && other.getTerm()==null) || 
             (this.term!=null &&
              this.term.equals(other.getTerm()))) &&
            ((this.totalPayFunding==null && other.getTotalPayFunding()==null) || 
             (this.totalPayFunding!=null &&
              java.util.Arrays.equals(this.totalPayFunding, other.getTotalPayFunding())));
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
        if (getAuditable() != null) {
            _hashCode += getAuditable().hashCode();
        }
        if (getBroker() != null) {
            _hashCode += getBroker().hashCode();
        }
        if (getCancelDays() != null) {
            _hashCode += getCancelDays().hashCode();
        }
        if (getCompany() != null) {
            _hashCode += getCompany().hashCode();
        }
        if (getCoverage() != null) {
            _hashCode += getCoverage().hashCode();
        }
        if (getDown() != null) {
            _hashCode += getDown().hashCode();
        }
        if (getDownPercent() != null) {
            _hashCode += getDownPercent().hashCode();
        }
        if (getEffectiveDate() != null) {
            _hashCode += getEffectiveDate().hashCode();
        }
        if (getExpirationDate() != null) {
            _hashCode += getExpirationDate().hashCode();
        }
        if (getFee() != null) {
            _hashCode += getFee().hashCode();
        }
        if (getGA() != null) {
            _hashCode += getGA().hashCode();
        }
        if (getLossPayeeRequested() != null) {
            _hashCode += getLossPayeeRequested().hashCode();
        }
        if (getMaxLiability() != null) {
            _hashCode += getMaxLiability().hashCode();
        }
        if (getMinLiability() != null) {
            _hashCode += getMinLiability().hashCode();
        }
        if (getMinimumEarned() != null) {
            _hashCode += getMinimumEarned().hashCode();
        }
        if (getMinimumEarnedPercent() != null) {
            _hashCode += getMinimumEarnedPercent().hashCode();
        }
        if (getNumber() != null) {
            _hashCode += getNumber().hashCode();
        }
        if (getPremium() != null) {
            _hashCode += getPremium().hashCode();
        }
        if (getTax() != null) {
            _hashCode += getTax().hashCode();
        }
        if (getTerm() != null) {
            _hashCode += getTerm().hashCode();
        }
        if (getTotalPayFunding() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTotalPayFunding());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTotalPayFunding(), i);
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
        new org.apache.axis.description.TypeDesc(Policy.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IntegrationSDK.Model", "Policy"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("auditable");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IntegrationSDK.Model", "Auditable"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("broker");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IntegrationSDK.Model", "Broker"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IntegrationSDK.Model", "Broker"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cancelDays");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IntegrationSDK.Model", "CancelDays"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IntegrationSDK", "Enums.QuoteCancelDays"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("company");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IntegrationSDK.Model", "Company"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IntegrationSDK.Model", "Company"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("coverage");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IntegrationSDK.Model", "Coverage"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("down");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IntegrationSDK.Model", "Down"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("downPercent");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IntegrationSDK.Model", "DownPercent"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("effectiveDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IntegrationSDK.Model", "EffectiveDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("expirationDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IntegrationSDK.Model", "ExpirationDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fee");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IntegrationSDK.Model", "Fee"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("GA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IntegrationSDK.Model", "GA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IntegrationSDK.Model", "GA"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lossPayeeRequested");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IntegrationSDK.Model", "LossPayeeRequested"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("maxLiability");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IntegrationSDK.Model", "MaxLiability"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("minLiability");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IntegrationSDK.Model", "MinLiability"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("minimumEarned");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IntegrationSDK.Model", "MinimumEarned"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("minimumEarnedPercent");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IntegrationSDK.Model", "MinimumEarnedPercent"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("number");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IntegrationSDK.Model", "Number"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("premium");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IntegrationSDK.Model", "Premium"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tax");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IntegrationSDK.Model", "Tax"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("term");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IntegrationSDK.Model", "Term"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("totalPayFunding");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IntegrationSDK.Model", "TotalPayFunding"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IntegrationSDK.Model", "PolicyTotalFunding"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IntegrationSDK.Model", "PolicyTotalFunding"));
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
