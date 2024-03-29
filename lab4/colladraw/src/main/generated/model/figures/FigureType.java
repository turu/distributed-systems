package model.figures;


/**
* model/figures/FigureType.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from api.idl
* Tuesday, May 6, 2014 12:57:50 AM CEST
*/

public class FigureType implements org.omg.CORBA.portable.IDLEntity
{
  private        int __value;
  private static int __size = 3;
  private static model.figures.FigureType[] __array = new model.figures.FigureType [__size];

  public static final int _CIRCLE = 0;
  public static final model.figures.FigureType CIRCLE = new model.figures.FigureType(_CIRCLE);
  public static final int _RECTANGLE = 1;
  public static final model.figures.FigureType RECTANGLE = new model.figures.FigureType(_RECTANGLE);
  public static final int _LINE = 2;
  public static final model.figures.FigureType LINE = new model.figures.FigureType(_LINE);

  public int value ()
  {
    return __value;
  }

  public static model.figures.FigureType from_int (int value)
  {
    if (value >= 0 && value < __size)
      return __array[value];
    else
      throw new org.omg.CORBA.BAD_PARAM ();
  }

  protected FigureType (int value)
  {
    __value = value;
    __array[__value] = this;
  }
} // class FigureType
