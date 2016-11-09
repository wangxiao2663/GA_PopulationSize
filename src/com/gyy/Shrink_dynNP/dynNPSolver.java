package com.gyy.Shrink_dynNP;

import java.util.List;

public class dynNPSolver  {

    static List<Population> m_List = null;
    public static int m_nInitalSize;
    public static int maxnfeval = 100000;
    public static int pmax = 4;
    static{
       m_nInitalSize = 200;
    }

 /*   public static void evolve(Population pop)
    {
        Crossover cros = new UniformCrossover(0.65, 0.5);
        Mutation muta = new Mutation(0.015);  
        Individual[] inv = cros.cross(pop);
        for (int i = 0; i < pop.getPopSize(); i++){
            pop.setIndividual(i, inv[i], 0);
        }
        muta.mutate(pop.getIndividuals());
    }*/
    
    public static Population evolve(Population pop){
        int oldPopSize = pop.getPopSize();
        System.out.println("current population size is: "+oldPopSize);
        int gen = maxnfeval/pmax/(oldPopSize);
        for(int i=0;i<gen;i++){
            Crossover cros = new UniformCrossover(0.65, 0.5);
            Mutation muta = new Mutation(0.015);
            Individual[] inv = cros.cross(pop);
            for (int j = 0; j < pop.getPopSize(); j++){
                pop.setIndividual(j, inv[j], 0);
            }
            muta.mutate(pop.getIndividuals());
        }
        int newPopSize = oldPopSize/2;
        Population newPop = new Population(newPopSize);
        Reduction rect = new Reduction();
        rect.reduction(pop, newPop);
        System.out.println("new population size is: "+newPop.getPopSize());
        return newPop;
    }
    
    public static void run(){
        int i=0;
        Population pop = new IndivPopulation(m_nInitalSize);
        while (i < pmax){
            System.out.println("Generation: "+(i+1));
            Population newPop = evolve(pop);
            newPop.dumpMyself();
            for(int j=0;j<newPop.getPopSize();j++){
                System.out.println(newPop.fitness[j]);
            }
            pop = newPop;
            i++;
        }
    }
    
    public static void main(String args[]){
        run();
    }
}
