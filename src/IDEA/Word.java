package IDEA;
import static IDEA.Convert.stringToBinary;
//Word = 64 bit 
public class Word {
    Binary X1,X2,X3,X4 ; //item = 16 bit 
    void init () {
        X1 = new Binary (16);
        X2 = new Binary (16);
        X3 = new Binary (16);
        X4 = new Binary (16);
    }
    Word () {
        init();
    }
    Word (Binary X1, Binary X2 , Binary X3 , Binary X4 ){
        init() ;
        this.X1.set(X1.get());
        this.X2.set(X2.get());
        this.X3.set(X3.get());
        this.X4.set(X4.get());
    }
    public static Word[] generate (String s ){
        int m = s.length();
        Binary [] A16 = stringToBinary(s);
        if (m % 4 !=0){
            int k =4-  m % 4 ;
            Binary [] old = A16;
            A16 = new Binary[m  + k];
            for (int i =0; i <m; i++)
                A16[i]=old[i];
            for (int i =m; i <m+k; i++)
                A16[i]=new Binary (16);
                m = m  + k;
        }
           Word [] mat = new Word[m/4] ;
           int u = 0 ; 
           for (int i=0; i<m; i+=4){
            mat[u]=new Word (A16[i],A16[i+1],A16[i+2],A16[i+3]);
            u++;
        }
           return mat;
    }
    public String get () {
        return X1.get()+X2.get()+X3.get()+X4.get();
    }
}