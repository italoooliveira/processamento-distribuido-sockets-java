package com.feevale.processamentodistribuido;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIServer extends Remote{
    int[] GetArray() throws RemoteException;
    void AddSortedArray(int[] sortedArray) throws RemoteException;
}
