package com.feevale.processamentodistribuido;

public class WorkerMain {
    public static void main(String[] args){
        try{
            Worker worker = new Worker();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
