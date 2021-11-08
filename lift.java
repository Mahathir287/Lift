package com.company;
public class lift {
    static int id=1;
    int curfloor;
    int liftno;
    boolean condition;
    int direction;

    public lift(){
        //default constructor
        curfloor=0;
        liftno=id;
        condition=true;//lift is in good condition
        direction=1;//i assume 1 means up and -1 means down
        id++;
    }
    public void setdet(int a,int dir){
        //change the values when assigned
        curfloor=a;
        this.direction=dir;
    }

}
