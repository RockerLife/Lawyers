/**
 * IPFSServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.ipfs.service;

public class IPFSServiceLocator extends org.apache.axis.client.Service implements com.ipfs.service.IPFSService {

    public IPFSServiceLocator() {
    }


    public IPFSServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public IPFSServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for BasicHttpBinding_IIPFSService
    private java.lang.String BasicHttpBinding_IIPFSService_address = "http://172.30.30.20/IPFS_Service/IPFSService.svc";

    public java.lang.String getBasicHttpBinding_IIPFSServiceAddress() {
        return BasicHttpBinding_IIPFSService_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String BasicHttpBinding_IIPFSServiceWSDDServiceName = "BasicHttpBinding_IIPFSService";

    public java.lang.String getBasicHttpBinding_IIPFSServiceWSDDServiceName() {
        return BasicHttpBinding_IIPFSServiceWSDDServiceName;
    }

    public void setBasicHttpBinding_IIPFSServiceWSDDServiceName(java.lang.String name) {
        BasicHttpBinding_IIPFSServiceWSDDServiceName = name;
    }

    public com.ipfs.service.IIPFSService getBasicHttpBinding_IIPFSService() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(BasicHttpBinding_IIPFSService_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getBasicHttpBinding_IIPFSService(endpoint);
    }

    public com.ipfs.service.IIPFSService getBasicHttpBinding_IIPFSService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.ipfs.service.BasicHttpBinding_IIPFSServiceStub _stub = new com.ipfs.service.BasicHttpBinding_IIPFSServiceStub(portAddress, this);
            _stub.setPortName(getBasicHttpBinding_IIPFSServiceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setBasicHttpBinding_IIPFSServiceEndpointAddress(java.lang.String address) {
        BasicHttpBinding_IIPFSService_address = address;
    }


    // Use to get a proxy class for BasicHttpsBinding_IIPFSService
    private java.lang.String BasicHttpsBinding_IIPFSService_address = "https://sws-dsr-004.in.osspl.com/IPFS_Service/IPFSService.svc";

    public java.lang.String getBasicHttpsBinding_IIPFSServiceAddress() {
        return BasicHttpsBinding_IIPFSService_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String BasicHttpsBinding_IIPFSServiceWSDDServiceName = "BasicHttpsBinding_IIPFSService";

    public java.lang.String getBasicHttpsBinding_IIPFSServiceWSDDServiceName() {
        return BasicHttpsBinding_IIPFSServiceWSDDServiceName;
    }

    public void setBasicHttpsBinding_IIPFSServiceWSDDServiceName(java.lang.String name) {
        BasicHttpsBinding_IIPFSServiceWSDDServiceName = name;
    }

    public com.ipfs.service.IIPFSService getBasicHttpsBinding_IIPFSService() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(BasicHttpsBinding_IIPFSService_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getBasicHttpsBinding_IIPFSService(endpoint);
    }

    public com.ipfs.service.IIPFSService getBasicHttpsBinding_IIPFSService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.ipfs.service.BasicHttpsBinding_IIPFSServiceStub _stub = new com.ipfs.service.BasicHttpsBinding_IIPFSServiceStub(portAddress, this);
            _stub.setPortName(getBasicHttpsBinding_IIPFSServiceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setBasicHttpsBinding_IIPFSServiceEndpointAddress(java.lang.String address) {
        BasicHttpsBinding_IIPFSService_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     * This service has multiple ports for a given interface;
     * the proxy implementation returned may be indeterminate.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.ipfs.service.IIPFSService.class.isAssignableFrom(serviceEndpointInterface)) {
                com.ipfs.service.BasicHttpBinding_IIPFSServiceStub _stub = new com.ipfs.service.BasicHttpBinding_IIPFSServiceStub(new java.net.URL(BasicHttpBinding_IIPFSService_address), this);
                _stub.setPortName(getBasicHttpBinding_IIPFSServiceWSDDServiceName());
                return _stub;
            }
            if (com.ipfs.service.IIPFSService.class.isAssignableFrom(serviceEndpointInterface)) {
                com.ipfs.service.BasicHttpsBinding_IIPFSServiceStub _stub = new com.ipfs.service.BasicHttpsBinding_IIPFSServiceStub(new java.net.URL(BasicHttpsBinding_IIPFSService_address), this);
                _stub.setPortName(getBasicHttpsBinding_IIPFSServiceWSDDServiceName());
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
        if ("BasicHttpBinding_IIPFSService".equals(inputPortName)) {
            return getBasicHttpBinding_IIPFSService();
        }
        else if ("BasicHttpsBinding_IIPFSService".equals(inputPortName)) {
            return getBasicHttpsBinding_IIPFSService();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://tempuri.org/", "IPFSService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://tempuri.org/", "BasicHttpBinding_IIPFSService"));
            ports.add(new javax.xml.namespace.QName("http://tempuri.org/", "BasicHttpsBinding_IIPFSService"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("BasicHttpBinding_IIPFSService".equals(portName)) {
            setBasicHttpBinding_IIPFSServiceEndpointAddress(address);
        }
        else 
if ("BasicHttpsBinding_IIPFSService".equals(portName)) {
            setBasicHttpsBinding_IIPFSServiceEndpointAddress(address);
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
