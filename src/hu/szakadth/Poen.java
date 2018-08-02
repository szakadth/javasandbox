package hu.szakadth;

public class Poen{
    static int i = 1;

    public static void main(String []args){
        _do_();
        _homework_();
        _yourself_();
    }

    static void one() { System.out.println(i++); }
    static void two() { one(); one(); }
    static void _yourself_() { two(); two(); }
    static void eight() { _yourself_(); _yourself_(); }
    static void sixteen() { eight(); eight(); }
    static void _homework_() { sixteen(); sixteen(); }
    static void _do_() { _homework_(); _homework_(); }
}