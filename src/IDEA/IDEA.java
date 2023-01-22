package IDEA;
public class IDEA {
    public static Word round (SubKey K ,Word X, int r ){
        Binary b1 = Binary.mul(X.X1,K.S1);
        Binary b2 = Binary.add(X.X2,K.S2);
        Binary b3 = Binary.add(X.X3,K.S3);
        Binary b4 = Binary.mul(X.X4,K.S4);
        /////////////////////////////////
        Binary b5 = Binary.xor(b1, b3);
        Binary b6 = Binary.xor(b2, b4);
        Binary b7 = Binary.mul(b5, K.S5);
        Binary b8 = Binary.add(b6, b7);
        Binary b9 = Binary.mul(b8, K.S6);
        Binary b10 = Binary.add(b7, b9);
        /////////////////////////////////
        Binary b11 = Binary.xor(b1, b9);
        Binary b12 = Binary.xor(b3, b9);
        Binary b13 = Binary.xor(b2, b10);
        Binary b14 = Binary.xor(b4, b10);
        /////////////////////////////////
        if (r ==8 ) 
            return  new Word (b11,b13,b12,b14);           
        else        
            return  new Word (b11,b12,b13,b14);               
    }
    public static Word halfround (SubKey K ,Word X){
        Binary b1 = Binary.mul(X.X1,K.S1);
        Binary b2 = Binary.add(X.X2,K.S2);
        Binary b3 = Binary.add(X.X3,K.S3);
        Binary b4 = Binary.mul(X.X4,K.S4);
          return  new Word (b1,b2,b3,b4);     
    }
    public static String encrypt (String text , String key ){
         Word [] Words = Word.generate(text);
         int n = Words.length;
         Key K = new Key(key);
         //initialize output 
         Binary [] Out = new Binary [n*4];
         for (int i=0; i<n*4 ; i++)
             Out[i]= new Binary (16);
         ////////////////
         int j = 0;
         for (int i=0; i <n; i++){
             Word X = Words[i];
             int s =1;
             // rounds from 1 -----> 8 
             for (int r = 1; r<=8; r++){    
                 SubKey SK = new SubKey(K.ENC[s],K.ENC[s+1],K.ENC[s+2],K.ENC[s+3],K.ENC[s+4],K.ENC[s+5]);
                 s+=6;
                 X= round(SK,X,r);
             }
             // round 9
             Binary T5 = new Binary (16); //nothing 
             Binary T6 = new Binary (16); //nothing 
             SubKey SK = new SubKey(K.ENC[s],K.ENC[s+1],K.ENC[s+2],K.ENC[s+3],T5,T6);
             X=halfround(SK, X); 
             Out[j]=X.X1; j++; 
             Out[j]=X.X2; j++; 
             Out[j]=X.X3; j++; 
             Out[j]=X.X4; j++; 
         }//end of for 
       String result = Convert.binaryToString(Out);
        return result;
    }
    public static String decrypt (String text , String key ){
        
         Word [] Words = Word.generate(text);
         int n = Words.length;
         Key K = new Key(key);
         //initialize output 
         Binary [] Out = new Binary [n*4];
         for (int i=0; i<n*4 ; i++)
             Out[i]= new Binary (16);
         ////////////////
         int j = 0;
         for (int i=0; i <n; i++){
             Word X = Words[i];
             int s =1;
             // rounds from 1 -----> 8 
             for (int r = 1; r<=8; r++){    
                 SubKey SK = new SubKey(K.DEC[s],K.DEC[s+1],K.DEC[s+2],K.DEC[s+3],K.DEC[s+4],K.DEC[s+5]);
                 s+=6;
                 X= round(SK,X,r);
             }
             // round 9
             Binary T5 = new Binary (16); //nothing 
             Binary T6 = new Binary (16); //nothing 
             SubKey SK = new SubKey(K.DEC[s],K.DEC[s+1],K.DEC[s+2],K.DEC[s+3],T5,T6);
             X=halfround(SK, X); 
             // 4 words = 8 binaries
             Out[j]=X.X1; j++; 
             Out[j]=X.X2; j++; 
             Out[j]=X.X3; j++; 
             Out[j]=X.X4; j++; 
         }//end of for 
         String result = Convert.binaryToString(Out);
        return result;
    }   
}