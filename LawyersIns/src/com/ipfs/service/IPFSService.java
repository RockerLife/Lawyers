/**
 * IPFSService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.ipfs.service;

public interface IPFSService extends javax.xml.rpc.Service {
    public java.lang.String getBasicHttpBinding_IIPFSServiceAddress();

    public com.ipfs.service.IIPFSService getBasicHttpBinding_IIPFSService() throws javax.xml.rpc.ServiceException;

    public com.ipfs.service.IIPFSService getBasicHttpBinding_IIPFSService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
    public java.lang.String getBasicHttpsBinding_IIPFSServiceAddress();

    public com.ipfs.service.IIPFSService getBasicHttpsBinding_IIPFSService() throws javax.xml.rpc.ServiceException;

    public com.ipfs.service.IIPFSService getBasicHttpsBinding_IIPFSService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
