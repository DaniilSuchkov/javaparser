package me.tomassetti.symbolsolver.model;

/**
 * Reference to a type. A TypeRef could be a primitive type or a reference type (enum, class, interface).
 * In the later case it could take type parameters (other TypeRefs). It could also be a TypeVariable, like in:
 *
 * class A<B> { }
 *
 * where B is a TypeVariable. It could also be Wildcard Type, possibly with constraints.
 */
public interface TypeReference {

    boolean isArray();
    boolean isPrimitive();

    /**
     * Class, interface and enum are reference-type
     * @return
     */
    boolean isReferenceType();

    String getTypeName();

    TypeReference getBaseType();

    /* Represent the position of the reference, it is used when solving symbols
     * because a reference to a class A could be related to different classes depending on the position
     * of the reference
     */
    Context getContext();

}
