package com.example.doanandroid.Class;

import android.util.Log;

import java.util.Random;

public class CauHoi {
    private int CauHoiID;
    private String NoiDung;
    private String PhuongAnA;
    private String PhuongAnB;
    private String PhuongAnC;
    private String PhuongAnD;
    private String PhuongAnDung;

    public String getNoiDung() {
        return NoiDung;
    }

    public void setNoiDung(String noiDung) {
        NoiDung = noiDung;
    }

    public String getPhuongAnA() {
        return PhuongAnA;
    }

    public void setPhuongAnA(String phuongAnA) {
        PhuongAnA = phuongAnA;
    }

    public String getPhuongAnB() {
        return PhuongAnB;
    }

    public void setPhuongAnB(String phuongAnB) {
        PhuongAnB = phuongAnB;
    }

    public String getPhuongAnC() {
        return PhuongAnC;
    }

    public void setPhuongAnC(String phuongAnC) {
        PhuongAnC = phuongAnC;
    }

    public String getPhuongAnD() {
        return PhuongAnD;
    }

    public void setPhuongAnD(String phuongAnD) {
        PhuongAnD = phuongAnD;
    }

    public String getPhuongAnDung() {
        return PhuongAnDung;
    }

    public void setPhuongAnDung(String phuongAnDung) {
        PhuongAnDung = phuongAnDung;
    }

    public int getCauHoiID() {
        return CauHoiID;
    }

    public void setCauHoiID(int cauHoiID) {
        CauHoiID = cauHoiID;
    }

    public void TronDapAn()
    {
        Random r=new Random();
        int a,b,c,d;
        String CauA,CauB,CauC,CauD;
        CauA=this.PhuongAnA;
        CauB=this.PhuongAnB;
        CauC=this.PhuongAnC;
        CauD=this.PhuongAnD;
        a=r.nextInt(4);
        Tron(a,CauA);
        do {
            b=r.nextInt(4);
        }while (b==a);
        Tron(b,CauB);
        do{
            c=r.nextInt(4);
        }while (c==a||c==b);
        Tron(c,CauC);
        do{
            d=r.nextInt(4);
        }while (d==a||d==b||d==c);
        Tron(d,CauD);

        TimDapAn(a,b,c,d);
        Log.d("da",PhuongAnDung);
    }

    private void Tron(int vt,String CauTraLoi)
    {
        if(vt==0)
        {
            this.PhuongAnA=CauTraLoi;
        }
        else if(vt==1)
        {
            this.PhuongAnB=CauTraLoi;
        }
        else if(vt==2)
        {
            this.PhuongAnC=CauTraLoi;
        }
        else if(vt==3)
        {
            this.PhuongAnD=CauTraLoi;
        }
    }

    private void TimDapAn(int a,int b,int c,int d)
    {
        if(this.PhuongAnDung.equals("A"))
        {
            LayDA(a);
        }
        else if(this.PhuongAnDung.equals("B"))
        {
            LayDA(b);
        }
        else if(this.PhuongAnDung.equals("C"))
        {
            LayDA(c);
        }
        else if(this.PhuongAnDung.equals("D"))
        {
            LayDA(d);
        }
    }

    private void LayDA(int vt)
    {
        if(vt==0)
        {
            this.PhuongAnDung="A";
        }
        else if(vt==1)
        {
            this.PhuongAnDung="B";
        }
        else if(vt==2)
        {
            this.PhuongAnDung="C";
        }
        else if(vt==3)
        {
            this.PhuongAnDung="D";
        }
    }
}
