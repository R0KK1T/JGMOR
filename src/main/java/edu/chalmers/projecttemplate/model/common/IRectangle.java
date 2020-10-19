package edu.chalmers.projecttemplate.model.common;

public interface IRectangle extends IPositionableInt{

    boolean intersect(IRectangle rect);

    void incX(int i);

    void incY(int i);

    void decX(int i);

    void decY(int i);

    void setX(int i);

    void setY(int i);

}
