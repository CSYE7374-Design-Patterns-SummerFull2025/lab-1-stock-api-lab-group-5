package edu.neu.csye7374;

public interface Tradable1 extends Tradable{

    @Override
    default double getMetric(){return 0;}
}

