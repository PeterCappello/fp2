package fp2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Peter Cappello
 */
public class Fp2 {

    /**
     * @param args the command line arguments - unused
     */
    public static void main(String[] args) 
    {
        List<Complex> numbers = Arrays.asList(
                new Complex( 0, 0),
                new Complex( 0, 1),
                new Complex( 1, 0),
                new Complex( 1, 1),
                new Complex( 0, Math.PI)
        );
        
        // ITERATING THROUGH A LIST
        
        // version 1:  The old way
        for ( Complex number : numbers )
        {
            System.out.println( number );
        }
        
         // version 2: Use streams
        numbers.stream()
               .forEach( (Complex number) -> { System.out.println( number ); } 
                       );
        
        // version 3: type specification is unnecessary
        numbers.stream()
               .forEach((number) -> { System.out.println( number ); }
                       );
        
         // version 4: when only 1 arg, parens are unnecessary
        numbers.stream()
               .forEach( number -> { System.out.println( number ); }
                       );
        
        // version 4: when function body has only 1 statement, braces are unnecessary
        numbers.stream()
               .forEach( number -> System.out.println( number ) );
        
        // version 5: Use method reference replacing function body with method name
        numbers.stream()
               .forEach( System.out::println );
        
        // USE LAMBDA EXPRESSIONS
        
        // version 1: Use map: transform 1 stream into another stream
        numbers.stream()
               .map( number -> number.size() )
               .forEach( size -> System.out.print( size + " " ) );
        System.out.println("");
        
        numbers.forEach( number -> System.out.print( number.size() + " " ) );
        System.out.println("");
        
        // USING METHOD REFERENCES
        /** Java compiler accepts either a lambda expression of a method reference
         *  where a functional interface is expected.
         */
        numbers.stream()
               .map( Complex::size )
               .forEach( size -> System.out.print( size + " " ) );
        System.out.println("");
        
        // FINDING ELEMENTS
        
        // version 1: The old way
        final List<Complex> smallElements = new ArrayList<>();
        for ( Complex number : numbers )
        {
            if ( number.size() < 2 )
            {
                smallElements.add( number );
            }
        }
        System.out.println( smallElements );
        
        // version 2: Use a filter
        final List<Complex> smallElements2 = new ArrayList<>();
        numbers.stream()
               .filter((number) -> ( number.size() < 2 ))
               .forEach(smallElements2::add );
        System.out.println( smallElements2 );
        
        // version 3: Collect functionally into a List of Complex objects.
        final List<Complex> smallElements3 = 
                numbers.stream()
                       .filter( number -> number.size() < 2 )
                       .collect( Collectors.toList() );
        System.out.println( smallElements3 );
        
         // version 4: Prefer expressions to statements.
        System.out.println( numbers.stream()
                                   .filter( number -> number.size() < 2 )
                                   .collect( Collectors.toList() ) 
        );
    }
}
