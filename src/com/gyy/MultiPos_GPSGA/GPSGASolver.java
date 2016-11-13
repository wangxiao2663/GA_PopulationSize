package com.gyy.MultiPos_GPSGA;

import java.util.ArrayList;
import java.util.List;

public class GPSGASolver  {

    static List<Population> m_List = null;
    public static int m_nInitalSize;
    static{
       m_nInitalSize = 4;
    }

    public static void evolve(Population pop)
    {
        Crossover cros = new UniformCrossover(0.65, 0.5);
        Mutation muta = new Mutation(0.015);  
        Individual[] inv = cros.cross(pop);
        for (int i = 0; i < pop.getPopSize(); i++){
            pop.setIndividual(i, inv[i], 0);
        }
        muta.mutate(pop.getIndividuals());
    }
    
    public static void evolve(Population pop, int M){
        M -= pop.calFitnessValues();
        while(M > 0){
            Crossover cros = new UniformCrossover(0.65, 0.5);
            Mutation muta = new Mutation(0.015);
            Individual[] inv = cros.cross(pop);
            for (int i = 0; i < pop.getPopSize(); i++){
                pop.setIndividual(i, inv[i], 0);
            }
//            pop.setIndividual(cros.cross(pop));
            muta.mutate(pop.getIndividuals());
            int nCalc = pop.calFitnessValues();
            M -= nCalc;
        }

    }
    
    public static void run(){
        m_List = new ArrayList<Population>();
        int i = 0;
        int j = 0;
        int nPopSize = m_nInitalSize;
        int M = 2 * nPopSize;
        Population pop1 = new Population(nPopSize);
        Population pop2 = new Population(nPopSize * 2);
        m_List.add(i, pop1);
        m_List.add(i+1, pop2);
        
        while (i < 7){
            evolve(pop1, M);
            evolve(pop2, M);
            if (pop1.expired(pop2)){
                System.out.println(i);
                i++;
                pop1 = pop2;
                pop2 = new Population(pop1.getPopSize() * 2, pop1);
                M = pop2.getPopSize();
                m_List.add(i+1, pop2);
                evolve(pop2, pop1.getF());
            }
        }
        
        /*
        int nF1 = pop1.calFitnessValues();
        int nF2 = pop2.calFitnessValues();
        
        while (i<5){
            //System.out.println("i = "+i);
            if (nF1 <= nF2){
                evolve(pop1);
                nF1 += pop1.calFitnessValues();
            }
            else{
                evolve(pop2);
                nF2 += pop2.calFitnessValues();
            }
            if (pop1.expired(pop2)){
                i = i + 1;
                pop1 = m_List.get(i);
                nF1 = nF2;
                pop2 = new Population(pop1.getPopSize() * 2);
                m_List.add(i+1, pop2);
                nF2 = pop2.calFitnessValues();
                while (nF2 < nF1){
                    evolve(pop2);
                    nF2 += pop2.calFitnessValues();
                }
            }
        }
        */
        for (j = 0; j < m_List.size(); j++){
            Population pop = m_List.get(j);
            System.out.print(j+":");
            pop.dumpMyself();
        }
  
    }
    
    public static void main(String args[]){
        run();
    }
}