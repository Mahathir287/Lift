package com.company;
import java.util.*;
public class Myclass{
    static void restrictedmode(List<lift>l){
        Scanner sc=new Scanner(System.in);
        //creating diff list for restricted mode
        //fands 1st and 2nd lift
        List<lift>fands=new ArrayList<>();
        //tandf for 3rd and 4th lift
        List<lift>tandf=new ArrayList<>();
        //f for fifth lift
        List<lift>f=new ArrayList<>();
        for(lift a:l){
            if(a.liftno==1 || a.liftno==2){
                fands.add(a);
            }
            if(a.liftno==3 || a.liftno==4){
                tandf.add(a);
            }
            if(a.liftno==5){
                f.add(a);
            }
        }
        System.out.print("Enter the floor:");
        int which=sc.nextInt();
        System.out.print("Enter the floor to go:");
        int togo= sc.nextInt();
        if(which<0 || which>10){
            System.out.println("Invalid floor number");
            return;
        }
        //Always assign lift with least stops
        if(which>=6 && togo==0){
            Assign(tandf,which,togo);
        }
        //Assign lifts 1 and 2
        else if(which<=5 && togo<=5){
            Assign(fands,which,togo);
        }
        //Assign lifts 3 and 4
        else if( which>=6 && (togo==0 || togo>=6)){
            Assign(tandf,which,togo);
        }
        //Assign lifts 5
        else{
            Assign(f,which,togo);
        }
    }
    static List<lift> createl(int n){
        //creating n number of lifts and added to the list
        ArrayList<lift>t=new ArrayList<>();
        for(int i=0;i<n;i++){
            lift a=new lift();
            t.add(a);
        }
        return t;
    }
    static void Assign(List<lift>l,int a,int togo){
        int go;
        //calculating the users direction for the lift
        if(togo>a){
            go=1;
        }
        else{
            go=-1;
        }
        int min=1000;
        lift Assigned=null;
        int dir=0;
        //assign the lift based on users direction
        for(lift li:l){
            //finding the lift with close distance to the user
            int mindis=Math.abs(li.curfloor-a);
            //always checking the lift condition while assigning
            if(mindis<min && li.condition && go==li.direction){
                Assigned=li;
                min=mindis;
            }
        }
        //if no lift is moving on users direction assign other lift
        if(Assigned==null){
            for(lift li:l){
                int mindis=Math.abs(li.curfloor-a);
                if(mindis<min && li.condition ){
                    Assigned=li;
                    min=mindis;
                }
            }
        }
        //printing the Result
        System.out.println("L"+Assigned.liftno+" is Assigned");
        if(a>Assigned.curfloor){
            dir=1;
            System.out.println("L"+Assigned.liftno+" is going UP");
        }
        else if(a<Assigned.curfloor){
            dir=-1;
            System.out.println("L"+Assigned.liftno+" is going DOWN");
        }
        Assigned.setdet(togo,dir);

    }
    static void setfloor(List<lift>l,int ln,int fn,int d){
        //explicitly set the lift to user entered floor
        String dir;
        if(d==1){
            dir="upward";
        }
        else{
            dir="downward";
        }
        for(lift a:l){
            if(a.liftno==ln){
                a.curfloor=fn;
                a.direction=d;
            }
        }
        System.out.println("Lift "+ln+" is set to floor "+fn+" with "+dir+" direction");
    }
    static void setundermaintain(lift first,int ln){
        //set the lift to maintanence it will not assign at any cost
        first.curfloor=-1;
        first.condition=false;
        System.out.println("lift "+ln+" is undermaintanence");
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=5;
        List<lift> l=createl(n);
        boolean loop = true;
        while(loop){
            System.out.println("1.display\n2.Assign\n3.Set list undermaintanence\n4.Set lift floor\n5.Restricted Mode\n6.Exit");
            int ch= sc.nextInt();
            switch(ch){
                case 1:
                    System.out.print("LIFT :");
                    for(int i=1;i<=n;i++){
                        System.out.print("L"+i+" ");
                    }
                    System.out.print("\nFLOOR:");
                    for(lift a:l){
                        System.out.print(a.curfloor+"  ");
                    }
                    System.out.println();
                    break;
                case 2:
                    System.out.print("Enter the floor:");
                    int whichfloor=sc.nextInt();
                    System.out.print("Enter the floor to go:");
                    int togo= sc.nextInt();
                    if(whichfloor<0 || whichfloor>10){
                        System.out.println("Invalid floor number");
                        loop=false;
                        break;
                    }

                    Assign(l,whichfloor,togo);
                    break;
                case 3:
                    System.out.print("Enter lift no to set undermaintanence:");
                    int un=sc.nextInt();
                    if(un<1 || un>5){
                        System.out.println("Invalid lift number");
                        break;
                    }
                    for(lift a:l){
                        if(un==a.liftno){
                            setundermaintain(a,un);
                            break;
                        }
                    }
                    break;
                case 4:
                    System.out.print("Enter the liftno:");
                    int ln= sc.nextInt();
                    System.out.print("Enter the floor:");
                    int f=sc.nextInt();
                    System.out.print("Direction(-1 for down and 1 for up):");
                    int d= sc.nextInt();
                    if(d!=0 && d>1 || d<-1 || ln<1 || ln>5 || f<0 || f>10){
                        System.out.println("invalid lift no or floor no or direction");
                        break;
                    }
                    setfloor(l,ln,f,d);
                    break;
                case 5:
                    System.out.println("Restricted Mode On");
                    System.out.println("Lift 1 and 2 will be operated between 0 to 5 floors");
                    System.out.println("Lift 3 and 4 will be operated between 6 to 10 floors");
                    System.out.println("Lift 5 will be operated between 0 to 10 floors");
                    restrictedmode(l);
                    break;
                case 6:
                    loop=false;
                    System.out.println("EXITTING...");
                    break;
                default:
                    System.out.println("Enter valid option");
            }
        }
    }

}
  
