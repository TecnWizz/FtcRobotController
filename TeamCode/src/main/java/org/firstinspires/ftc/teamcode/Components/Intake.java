package org.firstinspires.ftc.teamcode.Components;


import android.graphics.Color;

import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Intake {
    Telemetry telemetry;
    private DcMotorEx intakeMotor;
    private Servo servo1,servo2;
    private RevColorSensorV3 colorSensor1,colorSensor2;

    boolean ok1 = true;
    boolean ok2 = false;
    boolean ok3 = false;
    enum SortState{
        GPP,
        PGP,
        PPG,
    };
    enum ColorState{
        Neutral,
        Green,
        Purple1,
        Purple2,
    };

    public Intake (DcMotorEx intakeMotor,Servo servo1, Servo servo2,RevColorSensorV3 colorSensor1,RevColorSensorV3 colorSensor2) {

        this.intakeMotor=intakeMotor;
        this.servo1=servo1;
        this.servo2=servo2;
        this.colorSensor1=colorSensor1;
        this.colorSensor2=colorSensor2;

        colorSensor1.enableLed(true);
        colorSensor2.enableLed(true);
    }
    public void Fiorosul(Gamepad gamepad){

        boolean x = gamepad.square;
        boolean x1 = gamepad.circle;
        int y = x? 1:0;
        int y1= x1? 1:0;

        String Obelisk = "GPP";
        ColorState colorState = ColorState.Neutral;
        SortState sortState = SortState.GPP;
        if (ok1==true && gamepad.options){
            sortState=SortState.PGP;
            ok2=true;
            ok1=false;
            Obelisk = "PGP";
        }
        else if (ok2==true && gamepad.options){
            sortState=SortState.PPG;
            ok3=true;
            ok2=false;
            Obelisk = "PPG";
        }
        else if (ok3==true && gamepad.options){
            sortState=SortState.GPP;
            ok1=true;
            ok3=false;
            Obelisk = "GPP";
        }

        int red1 = colorSensor1.red();
        int green1 = colorSensor1.green();
        int blue1 = colorSensor1.blue();
        int red2 = colorSensor2.red();
        int green2 = colorSensor2.green();
        int blue2 = colorSensor2.blue();

        float[] hsvValues1 = new float[3];
        Color.RGBToHSV(red1 * 8, green1 * 8, blue1 * 8, hsvValues1);  float hue1 = hsvValues1[0];float saturation1 = hsvValues1[1];float value1 = hsvValues1[2];
        float[] hsvValues2 = new float[3];
        Color.RGBToHSV(red1 * 8, green1 * 8, blue1 * 8, hsvValues2);  float hue2 = hsvValues2[0];float saturation2 = hsvValues2[1];float value2 = hsvValues2[2];

        String colorDetected1 = "No data";
        String colorDetected2 = "No data";

        if (hue1 >= 100 && hue1 <= 160 && saturation1 > 0.4 && value1 < 0.5) {
            colorDetected1= "Green";
        }
        else if ((hue1 >= 270 && hue1 <= 300) && saturation1 > 0.4 && value1 < 0.5) {
            colorDetected1 = "Purple";
        }

        if (hue2 >= 100 && hue1 <= 160 && saturation2 > 0.4 && value2 < 0.5) {
            colorDetected2= "Green";
        }
        else if ((hue1 >= 270 && hue1 <= 300) && saturation1 > 0.4 && value2 < 0.5) {
            colorDetected2 = "Purple";
        }

        switch (sortState){
            case GPP:
                switch (colorState){
                    case Green:
                        if (colorDetected1=="Green"){
                            servo1.setPosition(1);
                            colorState=ColorState.Purple1;
                        }
                        else if (colorDetected2=="Green") {
                            servo2.setPosition(1);
                            colorState = ColorState.Purple1;
                        }
                        break;
                    case Purple1:
                        if(colorDetected1=="Purple"){
                            servo1.setPosition(1);
                            colorState=ColorState.Purple2;
                        }
                        else if (colorDetected2=="Purple"){
                            servo2.setPosition(1);
                            colorState=ColorState.Purple2;
                        }
                        break;
                    case Purple2:
                        if (colorDetected1=="Purple"){
                            servo1.setPosition(1);
                            colorState=ColorState.Green;
                        }
                        else if (colorDetected2=="Purple"){
                            servo2.setPosition(1);
                            colorState=ColorState.Green;
                        }
                        break;
                }
                break;
            case PGP:
                switch (colorState){
                    case Purple1:
                        if(colorDetected1=="Purple"){
                            servo1.setPosition(1);
                            colorState=ColorState.Green;
                        }
                        else if (colorDetected2=="Purple"){
                            servo2.setPosition(1);
                            colorState=ColorState.Green;
                        }
                        break;
                    case Green:
                        if (colorDetected1=="Green"){
                            servo1.setPosition(1);
                            colorState=ColorState.Purple2;
                        }
                        else if (colorDetected2=="Green"){
                            servo2.setPosition(1);
                            colorState=ColorState.Purple2;
                        }
                        break;
                    case Purple2:
                        if (colorDetected1=="Purple"){
                            servo1.setPosition(1);
                            colorState=ColorState.Purple1;
                        }
                        else if (colorDetected2=="Purple"){
                            servo2.setPosition(1);
                            colorState=ColorState.Purple1;
                        }
                        break;

                }
                break;
            case PPG:
                switch (colorState){
                    case Purple1:
                        if(colorDetected1=="Purple"){
                            servo1.setPosition(1);
                            colorState=ColorState.Purple2;
                        }
                        else if (colorDetected2=="Purple"){
                            servo2.setPosition(1);
                            colorState=ColorState.Purple2;
                        }
                        break;
                    case Purple2:
                        if (colorDetected1=="Purple"){
                            servo1.setPosition(1);
                            colorState=ColorState.Green;
                        }
                        else if (colorDetected2=="Purple"){
                            servo2.setPosition(1);
                            colorState=ColorState.Green;

                        }
                        break;
                    case Green:
                        if (colorDetected1=="Green"){
                            servo1.setPosition(1);
                            colorState=ColorState.Purple1;
                        }
                        else if (colorDetected2=="Green"){
                            servo2.setPosition(1);
                            colorState=ColorState.Purple1;
                        }
                        break;
                }
        }

        intakeMotor.setPower(y);
        intakeMotor.setPower(-y1);

        telemetry.addData("Obelisk sort:",Obelisk);
        telemetry.addData("RGB", "%d / %d / %d", red1, green1, blue1);
        telemetry.addData("HSV", "H: %.1f  S: %.2f  V: %.2f", hue1, saturation1, value1);
        telemetry.addData("ColorDetected",colorDetected1);
        telemetry.addData("RGB", "%d / %d / %d", red2, green2, blue2);
        telemetry.addData("HSV", "H: %.1f  S: %.2f  V: %.2f", hue2, saturation2, value2);
        telemetry.addData("ColorDetected",colorDetected2);
    }
}


