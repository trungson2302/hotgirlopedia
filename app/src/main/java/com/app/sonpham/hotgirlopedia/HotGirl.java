package com.app.sonpham.hotgirlopedia;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Son Pham on 7/24/2017.
 */

public class HotGirl implements Parcelable{
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public HotGirl createFromParcel(Parcel in) {
            return new HotGirl(in);
        }

        public HotGirl[] newArray(int size) {
            return new HotGirl[size];
        }
    };

    private int mID;
    private String mName;
    private String mFB;
    private String mInsta;
    private String mPhoto1;
    private String mPhoto2;
    private String mPhoto3;
    private String mPhoto4;
    private String mPhoto5;

    public HotGirl(Parcel in){
        this.mID = in.readInt();
        this.mName = in.readString();
        this.mFB =  in.readString();
        this.mInsta=in.readString();
        this.mPhoto1=in.readString();
        this.mPhoto2=in.readString();
        this.mPhoto3=in.readString();
        this.mPhoto4=in.readString();
        this.mPhoto5=in.readString();
    }
    public HotGirl() {
        this.mID = 0;
        this.mName = "none";
        this.mFB = "none";
        this.mInsta = "none";
        this.mPhoto1 = "none";
        this.mPhoto2 = "none";
        this.mPhoto3 = "none";
        this.mPhoto4 = "none";
        this.mPhoto5 = "none";
    }
    public HotGirl(int mID, String mName, String mFB, String mInsta, String mPhoto1, String mPhoto2, String mPhoto3, String mPhoto4, String mPhoto5) {
        this.mID = mID;
        this.mName = mName;
        this.mFB = mFB;
        this.mInsta = mInsta;
        this.mPhoto1 = mPhoto1;
        this.mPhoto2 = mPhoto2;
        this.mPhoto3 = mPhoto3;
        this.mPhoto4 = mPhoto4;
        this.mPhoto5 = mPhoto5;
    }

    public int getmID() {
        return mID;
    }

    public void setmID(int mID) {
        this.mID = mID;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmFB() {
        return mFB;
    }

    public void setmFB(String mFB) {
        this.mFB = mFB;
    }

    public String getmInsta() {
        return mInsta;
    }

    public void setmInsta(String mInsta) {
        this.mInsta = mInsta;
    }

    public String getmPhoto1() {
        return mPhoto1;
    }

    public void setmPhoto1(String mPhoto1) {
        this.mPhoto1 = mPhoto1;
    }

    public String getmPhoto2() {
        return mPhoto2;
    }

    public void setmPhoto2(String mPhoto2) {
        this.mPhoto2 = mPhoto2;
    }

    public String getmPhoto3() {
        return mPhoto3;
    }

    public void setmPhoto3(String mPhoto3) {
        this.mPhoto3 = mPhoto3;
    }

    public String getmPhoto4() {
        return mPhoto4;
    }

    public void setmPhoto4(String mPhoto4) {
        this.mPhoto4 = mPhoto4;
    }

    public String getmPhoto5() {
        return mPhoto5;
    }

    public void setmPhoto5(String mPhoto5) {
        this.mPhoto5 = mPhoto5;
    }
    public String toString(){
        return mName;
    }
    @Override
    public int describeContents() {
        return hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mID);
        dest.writeString(mName);
        dest.writeString(mFB);
        dest.writeString(mInsta);
        dest.writeString(mPhoto1);
        dest.writeString(mPhoto2);
        dest.writeString(mPhoto3);
        dest.writeString(mPhoto4);
        dest.writeString(mPhoto5);
    }
}
