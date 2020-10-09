package com.ssynhtn.contest;

public class ParkingSystem {
    private static final int TYPE_BIG = 1;
    private static final int TYPE_MEDIUM = 2;
    private static final int TYPE_SMALL = 3;

    int big;
    int medium;
    int small;

    public ParkingSystem(int big, int medium, int small) {
        this.big = big;
        this.medium = medium;
        this.small = small;
    }
    
    public boolean addCar(int carType) {
        switch (carType) {
            case TYPE_BIG: {
                if (big > 0) {
                    big--;
                    return true;
                } else {
                    return false;
                }
            }
            case TYPE_MEDIUM: {
                if (medium > 0) {
                    medium--;
                    return true;
                } else {
                    return false;
                }
            }
            case TYPE_SMALL:
                if (small > 0) {
                    small--;
                    return true;
                } else {
                    return false;
                }

            default: return false;
        }
    }
}