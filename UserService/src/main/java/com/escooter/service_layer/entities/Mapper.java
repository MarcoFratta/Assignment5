package com.escooter.service_layer.entities;

public interface Mapper<A,B> {

    A revert(B b);
    B convert(A a);

}
