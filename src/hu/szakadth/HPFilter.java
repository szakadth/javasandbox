package hu.szakadth;

/*
 * Hodrick-Prescott-Filter
 * copyright: 2004 Kurt Annen
 * Version:   1.0
 *
 * Ein sehr schneller HP-Filter, der die Struktur der BadMatrix ausnutzt
*/
import java.io.*;
import java.lang.*;
import java.util.*;
class HPFilter {
    public static void main( String args[] ) {
        int i;
        boolean los = true;
        String str = new String();
        double lambda=1600;
        String datei=new String();
        String dateineu=new String();
        dateineu="";
        String separator=new String();
        separator="\n";
        if (args.length==0)
        {
            los =false;
            System.out.println("JAVA - HODRICK PRESCOTT FILTER \t Version 1.0 \t Copyright (c) Kurt Annen 2004\n");
            System.out.println("java hp [/schalter] [/input] [/output]");
            System.out.println(" /i \t - Laedt Daten aus einer Datei ");
            System.out.println(" /o \t - Speichert Daten in eine Datei ");
            System.out.println(" /s \t - Laedt Daten aus einem Stream ");
            System.out.println(" /l \t - Lambda (Standard 1600) ");
            System.out.println(" /h \t - Zeigt diesen Hilfetext an ");
            System.out.println(" /s:x \t - Trennzeichen (Standard Zeilenumbruch) ");
            System.out.println("\t   n - Zeilenumbruch");
            System.out.println("\t   f - Leerzeichen");
            System.out.println("\t   c - Komma");
            System.out.println("\t   s - Semikolon\n");
            System.out.println("Beispiele:");
            System.out.println("java hp /l 14400 /s:c /i data.txt /o hpdata.txt");
            System.out.println("java hp /l 14400 /s:n /s 1.3 2.1 3.7 4.7 5.3 /o hpdata.txt\n");
            System.out.println("Hinweis: Ohne Schalter /o koennen die Daten mit \">>\" umgelenkt werden.");
            System.out.println("Bei Fragen oder Bugs koennen Sie mir eine Mail an kannen74@aol.com schreiben.");
        }

        for (i=0;i<args.length;i++)
        {

            if (args[i].equals("/h") || args[i].equals("/help"))
            {
                los =false;
                System.out.println("JAVA - HODRICK PRESCOTT FILTER \t Version 1.0 \t Copyright (c) Kurt Annen 2004\n");
                System.out.println("java hp [/schalter] [/input] [/output]");
                System.out.println(" /i \t - Laedt Daten aus einer Datei ");
                System.out.println(" /o \t - Speichert Daten in eine Datei ");
                System.out.println(" /s \t - Laedt Daten aus einem Stream ");
                System.out.println(" /l \t - Lambda (Standard 1600) ");
                System.out.println(" /h \t - Zeigt diesen Hilfetext an ");
                System.out.println(" /s:x \t - Trennzeichen (Standard Zeilenumbruch) ");
                System.out.println("\t   n - Zeilenumbruch");
                System.out.println("\t   f - Leerzeichen");
                System.out.println("\t   c - Komma");
                System.out.println("\t   s - Semikolon\n");
                System.out.println("Beispiele:");
                System.out.println("java hp /l 14400 /s:c /i data.txt /o hpdata.txt");
                System.out.println("java hp /l 14400 /s:n /s 1.3 2.1 3.7 4.7 5.3 /o hpdata.txt\n");
                System.out.println("Hinweis: Ohne Schalter /o koennen die Daten mit \">>\" umgelenkt werden.");
                System.out.println("Bei Fragen oder Bugs koennen Sie mir eine Mail an kannen74@aol.com schreiben.");
            }
        }
        for (i=0;i<args.length;i++)
        {
            if (args[i].equals("/i"))
            {
                datei = args[i+1];
            }

            if (args[i].equals("/l"))
            {
                lambda=Double.valueOf(args[i+1]).doubleValue();;

            }

            if (args[i].equals("/s:f"))
            {
                separator = " ";
            }
            if (args[i].equals("/s:n"))
            {
                separator = "\n";
            }

            if (args[i].equals("/s:c"))
            {
                separator = ",";
            }
            if (args[i].equals("/s:s"))
            {
                separator = ";";
            }

            if (args[i].equals("/s"))
            {   int j;
                int r=0;
                datei="";


                if (args[args.length-2].equals("/o"))
                {
                    r=args.length-3;
                    dateineu=args[args.length-1];
                }
                else
                {
                    r=args.length-1;
                }
                for (j=i+1;j<r+1;j++)
                {
                    str=str+args[j]+separator;

                }
            }
            if (args[i].equals("/o"))
            {
                dateineu = args[i+1];
            }

        }
        byte buffer[] = new byte[400000];
        if (los==true)
        {
            try {
                if (datei != "")
                {
                    System.out.println(datei);
                    FileInputStream in;
                    in = new FileInputStream( datei );

                    int len = in.read( buffer, 0, 400000 );
                    String stri = new String( buffer, 0, len );
                    str=stri;
                    in.close();
                }



                String  datame[]=split(str, separator);
                int N=datame.length;
                int K;
                double[] data=new double[N];

                for (K=0;K<N;K++) {
                    data[K]=Double.parseDouble(datame[K]);

                }


                double[] a=new double[N];
                double[] b=new double[N];
                double[] c=new double[N];








                // Elemente der Haupt- und Nebendiagonalen

                a[0]=1+lambda;
                b[0]=-2*lambda;
                c[0]=lambda;
                for (K=1;K<N-2;K++)
                {
                    a[K]=6*lambda+1;
                    b[K]=-4*lambda;
                    c[K]=lambda;
                }
                a[1]=5*lambda+1;
                a[N-1]=1+lambda;
                a[N-2]=5*lambda+1;
                b[0]=-2*lambda;
                b[N-2]=-2*lambda;
                b[N-1]=0;
                c[N-2]=0;
                c[N-1]=0;

                data= pentas(a,b,c,data,N);

                if (dateineu == "")
                {
                    for (K=0;K<N;K++)
                    {



                        System.out.print(data[K]+separator );


                    }}
                else
                {
                    //
                    FileWriter out = new FileWriter(dateineu);


                    for (K=0;K<N;K++)
                    {

                        out.write(data[K]+separator );
                    }


                    out.close();
                    //
                }

            }














            catch ( Exception e ) {
                System.out.println( e.toString() );
            }
        }

    }

    //Stream einlesen
    public static String[] split(String aString, String delimiter) {
        StringTokenizer st = new StringTokenizer(aString, delimiter);
        String[] result = new String[st.countTokens()];
        int i = 0;
        while (st.hasMoreTokens()) {
            result[i++] = st.nextToken();
        }    return result;
    }




    static double[] pentas(double a[],double b[], double c[], double data[], int N)
    {
        /*
         *  Schneller Algorithmus zur Lösung des LGS BxX=Y
         * unter Berücksichtigung, dass B eine pentadiagonale Matrix ist
         * siehe z.B. Helmut Späth "Numerik, Eine Einführung für
         * Mathematiker und Informatiker", S. 110 ff, Braunschweig &
         * Wiesbaden, 1994
         *
        */

        int K;
        double H1=0;
        double H2=0;
        double H3=0;
        double H4=0;
        double H5=0;
        double HH1=0;
        double HH2=0;
        double HH3=0;
        double HH4=0;
        double HH5=0;
        double Z;
        double HB;
        double HC;

        // Vorwärts
        for (K=0;K<N;K++)
        {
            Z=a[K]-H4*H1-HH5*HH2;
            HB=b[K];
            HH1=H1;
            H1=(HB-H4*H2)/Z;
            b[K]=H1;
            HC=c[K];
            HH2=H2;
            H2=HC/Z;
            c[K]=H2;
            a[K]=(data[K]-HH3*HH5-H3*H4)/Z;
            HH3=H3;
            H3=a[K];
            H4=HB-H5*HH1;
            HH5=H5;
            H5=HC;
        }


        // Rückwärts
        H2=0;
        H1=a[N-1];
        data[N-1]=H1;


        for (K=N-2;K>-1;K--)
        {

            data[K]=a[K]-b[K]*H1-c[K]*H2;
            H2=H1;
            H1=data[K];
        }

        return data;

    }



}