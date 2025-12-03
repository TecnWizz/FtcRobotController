package org.firstinspires.ftc.teamcode.Components;

import android.util.Size;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;


import org.firstinspires.ftc.robotcore.external.Func;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.OpModes.Teleop;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
import org.opencv.core.TermCriteria;

public class Outake {
    private Telemetry telemetry;
    private DcMotorEx shoot1,shoot2,rotate;
    private WebcamName webcam1;

    double fx= 752.848,fy=752.848,cx=314.441,cy=219.647;

    public Outake(DcMotorEx shoot1, DcMotorEx shoot2, DcMotorEx rotate,WebcamName webcam1) {
        this.shoot1 = shoot1;
        this.rotate = rotate;
        this.shoot2 =shoot2;
        this.webcam1=webcam1;
        shoot1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rotate.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        shoot2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rotate.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rotate.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


    }

    public void autoShooter() {
        AprilTagProcessor tagProcessor = new AprilTagProcessor.Builder()
                .setDrawAxes(true)
                .setDrawCubeProjection(true)
                .setDrawTagID(true)
                .setDrawTagOutline(true)
                .setTagFamily(AprilTagProcessor.TagFamily.TAG_36h11)
                .setLensIntrinsics(fx, fy, cx, cy)
                .build();

        VisionPortal vPortal = new VisionPortal.Builder()
                .addProcessor(tagProcessor)
                .setCamera(webcam1)
                .setCameraResolution(new Size(640, 480))
                .setStreamFormat(VisionPortal.StreamFormat.MJPEG)
                .enableLiveView(true)
                .build();

        vPortal.setActiveCamera(webcam1);

        if (tagProcessor.getDetections().size() >0) {
            AprilTagDetection tag = tagProcessor.getDetections().get(0);

            if (tag.ftcPose != null && tag.id == 20) {
                int target = (int) ((tag.ftcPose.bearing / 360) * 537.6);
                rotate.setTargetPosition(target);
                rotate.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                rotate.setPower(1);

                telemetry.addData("FPS",vPortal.getFps());
                telemetry.addData("Pos",tag.ftcPose.bearing);
                telemetry.addData("ID",tag.id);

            }

        }
    }

        public void shooter(Gamepad Gamepad1) {
            int x = Gamepad1.square ? 1 : 0;
            double y = Gamepad1.right_stick_x * 0.25;
            shoot1.setPower(-x);
            shoot2.setPower(x);
            rotate.setPower(y);

        }

    }
