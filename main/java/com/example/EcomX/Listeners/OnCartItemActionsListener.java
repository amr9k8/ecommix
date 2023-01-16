package com.example.EcomX.Listeners;

public interface OnCartItemActionsListener {
    public  void onRemove(int itemNum,int productPosition);
    public  void onInc(int productPosition);
    public  void onDec(int productPosition);
    public void onUpdate(int itemNum,int productPosition);
}
