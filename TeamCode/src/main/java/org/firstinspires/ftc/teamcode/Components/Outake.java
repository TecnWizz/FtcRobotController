package org.firstinspires.ftc.teamcode.Components;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.State;
import org.openftc.easyopencv.OpenCvCamera;
import org.opencv.calib3d.Calib3d;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Outake {

    private DcMotorEx rotate,outakeMotor;
    private Servo push;

    public Outake(DcMotorEx rotate, DcMotorEx outakeMotor,Servo push){
        this.rotate=rotate;
        this.outakeMotor=outakeMotor;
        this.push=push;

        rotate.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rotate.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }
    public void Shooter(Gamepad gamepad){

        outakeMotor.setPower(gamepad.left_trigger);
        push.setPosition(gamepad.right_trigger);
        rotate.setPower(gamepad.right_stick_x);

    }
}
