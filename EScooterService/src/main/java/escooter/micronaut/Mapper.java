package escooter.micronaut;

public interface Mapper<A,B> {

    A revert(B b);
    B convert(A a);

}
