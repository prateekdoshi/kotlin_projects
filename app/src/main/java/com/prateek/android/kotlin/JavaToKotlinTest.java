package com.prateek.android.kotlin;

class JavaToKotlinTest {

    private void printMessage(int number) {
        switch (number) {
            case 1:
            case 2:
                System.out.println("first");
                break;
            case 3:
            case 4:
                System.out.println("second");
            case 5:
            default:
                System.out.println("default");

        }
    }
}
