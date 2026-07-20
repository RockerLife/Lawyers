/**
 * FinanceAgreementOptionServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.ipfs.accountant.service;

public class FinanceAgreementOptionServiceLocator extends org.apache.axis.client.Service implements com.ipfs.accountant.service.FinanceAgreementOptionService {

    public FinanceAgreementOptionServiceLocator() {
    }


    public FinanceAgreementOptionServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public FinanceAgreementOptionServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for FinanceAgreementOptionPort
    private java.lang.String FinanceAgreementOptionPort_address = "http://192.168.43.141:8080/AccountantIns/FinanceAgreementOptionPort";

    public java.lang.String getFinanceAgreementOptionPortAddress() {
        return FinanceAgreementOptionPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String FinanceAgreementOptionPortWSDDServiceName = "FinanceAgreementOptionPort";

    public java.lang.String getFinanceAgreementOptionPortWSDDServiceName() {
        return FinanceAgreementOptionPortWSDDServiceName;
    }

    public void setFinanceAgreementOptionPortWSDDServiceName(java.lang.String name) {
        FinanceAgreementOptionPortWSDDServiceName = name;
    }

    public com.ipfs.accountant.service.FinanceAgreementOptionDelegate getFinanceAgreementOptionPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(FinanceAgreementOptionPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getFinanceAgreementOptionPort(endpoint);
    }

    public com.ipfs.accountant.service.FinanceAgreementOptionDelegate getFinanceAgreementOptionPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.ipfs.accountant.service.FinanceAgreementOptionPortBindingStub _stub = new com.ipfs.accountant.service.FinanceAgreementOptionPortBindingStub(portAddress, this);
            _stub.setPortName(getFinanceAgreementOptionPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setFinanceAgreementOptionPortEndpointAddress(java.lang.String address) {
        FinanceAgreementOptionPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.ipfs.accountant.service.FinanceAgreementOptionDelegate.class.isAssignableFrom(serviceEndpointInterface)) {
                com.ipfs.accountant.service.FinanceAgreementOptionPortBindingStub _stub = new com.ipfs.accountant.service.FinanceAgreementOptionPortBindingStub(new java.net.URL(FinanceAgreementOptionPort_address), this);
                _stub.setPortName(getFinanceAgreementOptionPortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("FinanceAgreementOptionPort".equals(inputPortName)) {
            return getFinanceAgreementOptionPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://userbo.com/", "FinanceAgreementOptionService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://userbo.com/", "FinanceAgreementOptionPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("FinanceAgreementOptionPort".equals(portName)) {
            setFinanceAgreementOptionPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
