/**
 * APGA
 * 优点：1、相比GAVaPS而言，运行时间大大减少，与简单遗传算法的时间相近
 * 优点：2、相比GAVaPS而言，种群规模确实不会疯长了
 * 缺点：1、可就是因为种群规模变化太小，或者种群个体淘汰率过高，导致最终的实验结果很差
 */
package com.gyy.lifetime_APGA;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;

/**
 * @author Gloria
 *
 */
public class GeneticAlgorithms {
    public static double crossoverRate;//交叉概率  
    public static double mutateRate;//变异概率  
    public static int populationSize;//群体大小  
    public static int maxGeneration;
    public static double reproductionRatio;
    public static int chromLen;
    public static int popSize;
      
    static {  
        popSize = 20;
        chromLen = 20;
        maxGeneration  =50;  
        populationSize = 20;  
        crossoverRate = 0.65;  
        mutateRate = 0.015;
        reproductionRatio = 0.4;
    } 
  
    public static void run() throws IOException{  

    	Evolve.resetGeneration();
        Population pop = new Population(populationSize);  
        pop.initPopulation();
        

//        System.out.println(pop.toString());
        //pw.println("初始种群:\n" + pop);  
        DecimalFormat df = new DecimalFormat("######0.00000"); 
        
        long startTime = System.currentTimeMillis();
        while(!Evolve.isEvolutionDone()&&(!Evolve.isPopSizeZero(pop))){
            Evolve.evolve(pop);
           // pw.println("generation "+Evolve.getGeneration()+":current popsize  "+pop.getPopSize());
            //pw.print("current bestIndividual: fitness" + df.format(pop.getBestFitness())); 
            
           // System.out.println("current bestFitness： "+ df.format(pop.getBestFitness()));
            
            //System.out.println("current best individual: "+ pop.findBestIndividual());
            //pw.print("    bestIndvidual: fitness" + df.format(pop.currentBest.getFitness()) );
            
           // System.out.println("bestFitness: "+df.format(pop.currentBest.getFitness()));
            
           // pw.println(""); 
           // pw.flush();
        }  
        long endTime = System.currentTimeMillis();
        //System.out.println("the total evolve time: " + (endTime - startTime));
        FileWriter fw = new FileWriter("data_txt/APGA_SphereModel_40.txt", true);  
        BufferedWriter bw = new BufferedWriter(fw);  
        PrintWriter pw = new PrintWriter(bw); 
        pw.println("bestFitness: "+df.format(-pop.currentBest.getFitness())); 
        pw.println("the total evolve time: " + (endTime - startTime));
        pw.flush();
        pw.close(); 
        fw.close();
    }  
    
    public static void main(String[] args)throws IOException{
    	File f = new File("data_txt/APGA_SphereModel_40.txt");
    	if (!f.exists())
    	{
    		f.createNewFile();
    	}
    	FileWriter fw =  new FileWriter(f);
    	fw.write("");
    	fw.close();
    	for(int i = 0;i<10;i++){
    		run();
    		
    	}
    }

}
