package model.drawing;


/**
* model/drawing/TranslateFigureCommandOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from api.idl
* Tuesday, May 6, 2014 12:57:50 AM CEST
*/

public interface TranslateFigureCommandOperations  extends model.drawing.CommandOperations
{
  model.figures.FigureToken figure ();
  model.figures.Point[] getTranslation ();
} // interface TranslateFigureCommandOperations
