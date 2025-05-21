package edu.neu.csye7374;

public interface Tradable0 extends Tradable{

    @Override
    default void setBid(double bid){}

    @Override
    default double getMetric(){return 0;}
}

