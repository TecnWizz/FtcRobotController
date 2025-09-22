package org.firstinspires.ftc.teamcode.Components;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import static java.lang.Math.abs;

import android.util.Size;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.State;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
import org.openftc.easyopencv.OpenCvCamera;
import org.opencv.calib3d.Calib3d;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Outake {
    Telemetry telemetry;

    AprilTagProcessor tagProcessor = new AprilTagProcessor.Builder()
            .setDrawAxes(true)
            .setDrawCubeProjection(true)
            .setDrawTagID(true)
            .setDrawTagOutline(true)
            .build();

    VisionPortal vPortal = new VisionPortal.Builder()
            .addProcessor(tagProcessor)
            .setCamera(hardwareMap.get(WebcamName.class,"Webcam 1"))
            .setCameraResolution(new Size(640,480))
            .setStreamFormat(VisionPortal.StreamFormat.MJPEG)
            .build();
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
        if (tagProcessor.getDetections().size()>0){

            AprilTagDetection tag =tagProcessor.getDetections().get(0);

            double ticks = 537.6;
            int target = (int)((tag.ftcPose.bearing/ (2 * Math.PI)) * ticks);

            rotate.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            rotate.setTargetPosition(target);
            rotate.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rotate.setPower(0.5);

            telemetry.addData("Motor pos",rotate.getCurrentPosition());
            telemetry.addData("x",tag.ftcPose.x);
            telemetry.addData("y",tag.ftcPose.y);
            telemetry.addData("z",tag.ftcPose.z);
            telemetry.addData("bearing",tag.ftcPose.bearing);

        }

        outakeMotor.setPower(gamepad.left_trigger);
        push.setPosition(gamepad.right_trigger);


        telemetry.update();
    }
}
