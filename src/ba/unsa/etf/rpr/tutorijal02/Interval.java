package ba.unsa.etf.rpr.tutorijal02;

public class Interval {
private double pocetnaTacka, krajnjaTacka;
private boolean pocetnaTackaUIntervalu, krajnjaTackaUIntervalu;
private static final double eps = 0.001;
private static boolean jesuLiJednaki(double x, double y){
    if(x < 0 && y > 0 || x > 0 && y < 0) return false;
    return Math.abs(x-y)<=(1e-10)*(Math.abs(x)+Math.abs(y));

}

    Interval(double pTacka, double kTacka, boolean pUIntervalu, boolean kUIntervalu){
    if((kTacka-pTacka)<eps) throw new IllegalArgumentException("Pocetna tacka veca od krajnje!");
    pocetnaTacka = pTacka; krajnjaTacka = kTacka; pocetnaTackaUIntervalu = pUIntervalu; krajnjaTackaUIntervalu = kUIntervalu;
}
Interval(){ pocetnaTacka = 0; krajnjaTacka = 0; pocetnaTackaUIntervalu = false; krajnjaTackaUIntervalu = false;}
public boolean isNull() {
        //if((pocetnaTacka-0.)<eps && (krajnjaTacka-0.)<eps && !pocetnaTackaUIntervalu && !krajnjaTackaUIntervalu)
    if(pocetnaTacka==0 && krajnjaTacka==0 && !pocetnaTackaUIntervalu && !krajnjaTackaUIntervalu)
        return true;
        return false;
    }
    public boolean isIn(double tacka){
        boolean uOdnosuNaPocetak = false, uOdnosuNaKraj = false;
        if (pocetnaTackaUIntervalu) uOdnosuNaPocetak = (tacka >= pocetnaTacka);
        else uOdnosuNaPocetak = (tacka > pocetnaTacka);
        if (krajnjaTackaUIntervalu) uOdnosuNaKraj = (tacka <= krajnjaTacka);
        else uOdnosuNaKraj = (tacka < krajnjaTacka);
        return uOdnosuNaPocetak && uOdnosuNaKraj;
    }
    public Interval intersect(Interval i){
        double pocetna = pocetnaTacka;
        double krajnja = krajnjaTacka;
        boolean pocetnaUInt = pocetnaTackaUIntervalu, krajnjaUInt = krajnjaTackaUIntervalu;
        if (i.pocetnaTacka > pocetna) {
            pocetna = i.pocetnaTacka;
            pocetnaUInt = i.pocetnaTackaUIntervalu;
        }
        if (krajnja > i.krajnjaTacka) {
            krajnja = i.krajnjaTacka;
            krajnjaUInt = i.krajnjaTackaUIntervalu;
        }
        if(krajnjaTacka==i.pocetnaTacka && !pocetnaUInt && !krajnjaUInt) return new Interval();
        Interval presjek = new Interval(pocetna, krajnja, pocetnaUInt, krajnjaUInt);
        return presjek;
    }
    /*public Interval intersect(Interval i){
      Interval presjek = new Interval();
      if((pocetnaTacka>i.pocetnaTacka && krajnjaTacka>i.krajnjaTacka) && krajnjaTacka==i.pocetnaTacka ){
          if(krajnjaTackaUIntervalu && i.pocetnaTackaUIntervalu){
              presjek.pocetnaTacka = presjek.krajnjaTacka = krajnjaTacka;
              presjek.pocetnaTackaUIntervalu = presjek.krajnjaTackaUIntervalu = true;
              return presjek;
          } else return presjek;
      }else if((i.pocetnaTacka>pocetnaTacka && i.krajnjaTacka>krajnjaTacka) && i.krajnjaTacka==pocetnaTacka ){
            if(i.krajnjaTackaUIntervalu && pocetnaTackaUIntervalu){
                presjek.pocetnaTacka = presjek.krajnjaTacka = i.krajnjaTacka;
                presjek.pocetnaTackaUIntervalu = presjek.krajnjaTackaUIntervalu = true;
                return presjek;
            } else return presjek;
        }
      else if(pocetnaTacka<i.pocetnaTacka && krajnjaTacka<i.krajnjaTacka) return presjek;
      else{
          presjek = i;
          if(pocetnaTacka>i.pocetnaTacka){
              presjek.pocetnaTacka = pocetnaTacka;
              presjek.pocetnaTackaUIntervalu = pocetnaTackaUIntervalu;
          }
          if(krajnjaTacka<i.krajnjaTacka){
              presjek.krajnjaTacka = krajnjaTacka;
              presjek.krajnjaTackaUIntervalu = krajnjaTackaUIntervalu;
          }


      }
      return presjek;
    }*/
    public static Interval intersect(Interval i1, Interval i2){
         return i1.intersect(i2);
    }
    @Override
    public String toString(){
        if(isNull()) return "()";
        String s = new String();
        if(pocetnaTackaUIntervalu)
        s = s + "[";
        else s = s + "(";
        s = s + pocetnaTacka + "," + krajnjaTacka;
        if(krajnjaTackaUIntervalu)
            s = s + "]";
        else s = s + ")";
        return s;
    }
    @Override
    public boolean equals(Object o){
        Interval i = (Interval) o;
        /*String s = i.toString();
        String s1=this.toString();
        return s1.equals(s);*/
        return (pocetnaTackaUIntervalu==i.pocetnaTackaUIntervalu && krajnjaTackaUIntervalu==i.krajnjaTackaUIntervalu
                && pocetnaTacka==i.pocetnaTacka && krajnjaTacka==i.krajnjaTacka);
    }


}
