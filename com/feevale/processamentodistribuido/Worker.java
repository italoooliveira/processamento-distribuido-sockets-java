package com.feevale.processamentodistribuido;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Worker {
    private Registry registry;
    private RMIServer rmiServer;

    public Worker() throws InterruptedException, RemoteException, NotBoundException{
        System.out.println("O Worker está online");   

        boolean hasArraysToSort = true;
        registry = LocateRegistry.getRegistry("localhost", 8090);
        rmiServer = (RMIServer) registry.lookup("servidor");
        
        Thread.sleep(1000);
        
        while(hasArraysToSort){
            int[] array = rmiServer.GetArray();
            
            if(array != null){
                int[] sortedArray = this.SortArray(array);
                rmiServer.AddSortedArray(sortedArray);
            }
            else {
                hasArraysToSort = false;
            }
        }
    }
    
    private int[] SortArray(int[] arrayToSort) {
        System.out.println("Ordenando um vetor");
        
        for(int i = 0; i < arrayToSort.length - 1; i++) {
            for(int j = 0; j < arrayToSort.length - 1 - i; j++) {
              if(arrayToSort[j] > arrayToSort[j + 1]) {
                int aux = arrayToSort[j];
                arrayToSort[j] = arrayToSort[j + 1];
                arrayToSort[j + 1] = aux;
              }
            }
        }
        
        System.out.println("Vetor ordenado");

        return arrayToSort;
    }

}
