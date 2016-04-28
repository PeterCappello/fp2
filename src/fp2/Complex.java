package fp2;

import java.util.ArrayList;
import java.util.List;

/**
 * Students: Can this class be changed to a class that is mutable but whose 
 * methods can be chained as in the main method below?
 * 
 * @author Peter Cappello
 */
public class Complex {
    private final double real;
    private final double imag;

    Complex( double real, double imag ) {
        this.real = real;
        this.imag = imag;
    }

    Complex add( Complex operand ) {
        return new Complex( real + operand.real,  imag + operand.imag );
    }

    Complex multiply( Complex operand ) {
        return new Complex( real * operand.real - imag * operand.imag, 
                            imag * operand.real + real * operand.imag );
    }

    double size() { return Math.sqrt( real * real + imag * imag ); }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append( " (").append( real ).append( ',' ).append( imag ).append( ") ");
        return new String( stringBuilder );
    }
        
    public static void main( String[] args ) {
        List<Complex> complexList = new ArrayList<>();
        complexList.add( new Complex( 6, 3 ) );
        complexList.add( new Complex( 2, 2 ) );
        complexList.add( new Complex( 5, 8 ) );
        complexList.add( new Complex( 1, 5 ) );
        complexList.add( new Complex( 1, 6 ) );
        complexList.add( new Complex( 2, 7 ) );
        complexList.add( new Complex( 2, 8 ) );
        complexList.add( new Complex( 6, 5 ) );
        complexList.add( new Complex( 1, 3 ) );
        complexList.add( new Complex( 6, 6 ) );
        System.out.println( complexList );
        
        // Sum the sizes of the magnified and translated complex numbers whose original size is < 3.0.
        final Complex translate = new Complex( 1, 1 );
        final Complex magnify   = new Complex( 2, 2 );
        
        // imperatively
        double sum = 0.0;
        for ( Complex num : complexList )
        {
            if ( num.size() < 3.0 ) {
                sum += num.multiply( magnify ).add( translate ).size();
            }
        }
        System.out.println( "sum: " + sum );
        
        // declaratively
        final double declarativeSum = 
              complexList.stream()
                         .filter( num -> num.size() < 3 )
                         .mapToDouble( num -> num.multiply( magnify ).add( translate ).size() )
                         .sum();
        System.out.println( "declarativeSum: " + declarativeSum );                
    }
}
