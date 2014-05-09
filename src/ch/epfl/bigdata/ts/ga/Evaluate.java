package ch.epfl.bigdata.ts.ga;

import ch.epfl.bigdata.ts.ga.crossover.CrossoverMethod;
import ch.epfl.bigdata.ts.ga.crossover.SinglePointCrossover;
import ch.epfl.bigdata.ts.ga.mutation.MutationMethod;
import ch.epfl.bigdata.ts.ga.mutation.UniformMutation;
import ch.epfl.bigdata.ts.ga.selection.RouletteWheelSelection;
import ch.epfl.bigdata.ts.ga.selection.SelectionMethod;
import ch.epfl.bigdata.ts.ga.util.Util;
import ch.epfl.bigdata.ts.pattern.fitness.DoubleBottom;
import ch.epfl.bigdata.ts.pattern.fitness.DoubleTop;
import ch.epfl.bigdata.ts.pattern.fitness.Rectangle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Evaluate {

    public static int NUM_OF_CHROMOSOMES = 50;

    public static int DOUBLE_BOT_GENE_BOT1_RANGE = 2;
    public static double val2[] = new double[4];

    public static void main(String[] args) {


        long startTime = System.currentTimeMillis();
        //        double [] values = {0.025632261337161677, 0.1900228970807991, 0.46556808380738546, 0.2946468966796436};
        double[] values = val2;
        int evalNumOfDays = 12;
        int evalStartMoney = 3000;
        int evalGenerationWindow = 12;
        int evalStartData = 30;
        Chromosome best = evaluateChromosome(values, evalNumOfDays, evalStartMoney, evalGenerationWindow, evalStartData);

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        System.out.println(best);
        System.out.println("Fitness: " + best.getFitness());
        System.out.println("Number of transactions: " + best.getNumberOfTransactions());
        System.out.println("This took " + duration + " milliseconds");
    }

    public static Chromosome evaluateChromosome(double[] values, int evalNumOfDays, int evalStartMoney, int evalGenerationWindow, int evalStartData) {
        List<Chromosome> chromosomes = new ArrayList<Chromosome>();

        addChromosome(chromosomes, values);

        DoubleBottom doubleBottom = new DoubleBottom(evalNumOfDays, evalStartMoney, evalGenerationWindow, evalStartData);
//        DoubleTop doubleBottom = new DoubleTop(7, 100, 7, 25);

//        Rectangle doubleBottom = new Rectangle(11, 3000, 11, 31);

        doubleBottom.calcFitness(chromosomes.get(0));

        return chromosomes.get(0);
    }

    private static void addChromosome(List<Chromosome> chromosomes, double[] values) {
        List<Chromosome.Gene> genes = new ArrayList<Chromosome.Gene>();
        Chromosome chr = new Chromosome(genes);

        genes.add(chr.new Gene("a", values[0]));
        genes.add(chr.new Gene("b", values[1]));
        genes.add(chr.new Gene("c", values[2]));
        genes.add(chr.new Gene("d", values[3]));

        chromosomes.add(chr);
    }
}
