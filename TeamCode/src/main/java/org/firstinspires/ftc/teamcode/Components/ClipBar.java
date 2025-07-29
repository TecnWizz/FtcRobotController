package org.firstinspires.ftc.teamcode.Components;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;

public class ClipBar {

    private CRServo clipStealer, clipPusher;

    public ClipBar (Servo clipStealer, Servo clipPusher){
        this.clipStealer = clipStealer;
        this.clipPusher = clipPusher;
    }

}
