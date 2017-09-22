package com.example.myothiha09.coursehelper.adapter;


/**
 * Created by Myo on 7/18/2017.
 */

public interface ItemClickedListener<T> {
  void itemChosen(T item);
  void delItem(int position);
  void editItem(int position);
}
