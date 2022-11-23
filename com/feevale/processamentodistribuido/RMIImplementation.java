package com.feevale.processamentodistribuido;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class RMIImplementation extends UnicastRemoteObject implements RMIServer{
    Queue<int[]> arraysToSort = new LinkedList<int[]>();
    List<int[]> sortedArrays = new ArrayList<int[]>();
    
    public RMIImplementation(int quantityArrays) throws RemoteException{
        super();
        this.FillArrays(quantityArrays);
        this.RunLook();
    }
      
    @Override
    public int[] GetArray() throws RemoteException {
        if(!this.arraysToSort.isEmpty()) 
            return arraysToSort.poll();
        return null;
    }
    
    @Override
    public void AddSortedArray(int[] sortedArray) throws RemoteException {
        this.sortedArrays.add(sortedArray);
    }
    
    private void FillArrays(int quantityArrays) {
        for(int i=0; i<quantityArrays; i++){
            int array[] = new int[100000];
            
            for(int j=0; j<100000; j++){
                int number = (int)Math.floor(Math.random()*(10000-0+1)+0);
                array[j] = number;
            }
            
            this.arraysToSort.add(array);
        }
    }
        
    private void RunLook(){
        new Thread(){
            @Override
            public void run() {
                boolean shouldContinue = true;
                
                while(shouldContinue){
                    System.out.println("[...]");
                    if(arraysToSort.isEmpty() && sortedArrays.size() == 10) shouldContinue = false;
                }

                System.out.println("Todos os arrays foram ordenados!");
            }
        }.start();
    }  
}
