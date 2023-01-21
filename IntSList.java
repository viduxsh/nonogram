public class IntSList{
  public static final IntSList NULL_INTLIST = new IntSList();
  
  private final boolean empty;
  private final int first;
  private final IntSList rest;
  
  public IntSList(){
    empty = true;
    first = 0;
    rest = null;
  }
  
  public IntSList(int e, IntSList il){
    empty = false;
    first = e;
    rest = il;
  }
  
  public boolean isNull(){
    return empty;
  }

  public int car(){
    return first;
  }
  
  public IntSList cdr(){
    return rest;
  }
  
  public IntSList cons(int e){
    return new IntSList(e, this);
  }
  
  public int length(){
    if(isNull()){
      return 0;
    }
    else{
      return (1+cdr().length());
    }
  }
  
  public int listRef(int k){
    if(k == 0){
      return car();
    }
    else{
      return (cdr().listRef(k-1));
    }
  }
  
  public boolean equals(IntSList il){
    if(isNull()||il.isNull()){
      return (isNull() && il.isNull());
    }
    else if(car() == il.car()){
      return cdr().equals(il.cdr());
    }
    else{
      return false;
    }
  }
  
  public IntSList append(IntSList il){
    if(isNull()){
      return il;
    }
    else{
      return (cdr().append(il)).cons(car());
    }
  }
  
  public IntSList reverse(){
    return reverseRec(new IntSList());
  }
  
  private IntSList reverseRec(IntSList re){
    if(isNull()){
      return re;
    }
    else{
      return cdr().reverseRec(re.cons(car()));
    }
  }

  public String toString(){
    if(isNull()){
      return "()";
    }
    else{
      String rep = "(" + car();
      IntSList r = cdr();
      while(!r.isNull()){
        rep = rep + ", " + r.car();
        r = r.cdr();
      }
      return (rep+")");
    }
  }
}
