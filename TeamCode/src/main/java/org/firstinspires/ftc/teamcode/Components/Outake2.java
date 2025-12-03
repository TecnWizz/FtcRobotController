package org.firstinspires.ftc.teamcode.Components;

import static org.firstinspires.ftc.teamcode.OpModes.Teleop.dashboard;

import android.util.Size;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Func;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.CameraName;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;

import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagGameDatabase;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
import org.openftc.easyopencv.OpenCvCamera;

import java.util.ArrayList;
import java.util.List;

public class Outake2 {

    private DcMotorEx shoot1, shoot2, rotate;
    private VisionPortal visionPortal;
    private AprilTagProcessor tagProcessor;
    double fx=752.848, fy=752.848, cx=314.441, cy=219.647;
    double mt=112; int maxP=700;
    double gr=13.7;  int minP=-350; private Servo transfer;
    double kp = 0;
    double ki = 0; private WebcamName webcam;
    double kd = 0;
    double tpr=(mt*gr)/360;
    public Outake2(DcMotorEx shoot1, DcMotorEx shoot2, DcMotorEx rotate, WebcamName webcam, Servo transfer) {
        this.shoot1 = shoot1;
        this.shoot2 = shoot2; this.transfer=transfer;
        this.rotate = rotate;
        shoot1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        shoot2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        rotate.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rotate.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rotate.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        tagProcessor = new AprilTagProcessor.Builder()
                .setDrawAxes(true)
                .setDrawTagOutline(true)
                .setDrawTagID(true)
                .setDrawCubeProjection(true)
                .setTagFamily(AprilTagProcessor.TagFamily.TAG_36h11)
                .setTagLibrary(AprilTagGameDatabase.getIntoTheDeepTagLibrary())
                .setLensIntrinsics(fx,fy,cx,cy)
                .build();

        visionPortal = new VisionPortal.Builder()
                .addProcessor(tagProcessor)
                .setCamera(webcam)
                .setCameraResolution(new Size(640, 480))
                .setStreamFormat(VisionPortal.StreamFormat.MJPEG)
                .enableLiveView(true)
                .build();
        FtcDashboard.getInstance().startCameraStream(visionPortal,10);
    }

    public void shooter(Gamepad Gamepad1) {
        int sp=Gamepad1.square ? 1 : 0;
        shoot1.setPower(-sp);
        shoot2.setPower(sp);
        rotate.setPower(Gamepad1.right_stick_x * 0.25);
    }
    public void aimbot() {
        ArrayList<AprilTagDetection> detections = tagProcessor.getDetections();
        if (detections.isEmpty()) {
            dashboard.addLine("No tag detected.");
        }
        else {
            AprilTagDetection tag = detections.get(0);
            dashboard.addData("deg", tag.robotPose);
            if (tag.ftcPose == null) {
                dashboard.addData("Tag id ",tag.id);
                dashboard.addLine("tag not valid");
                return;
            }
            double a = tag.ftcPose.bearing;
            int target = (int) (a * tpr);
        /*rotate.setTargetPosition(target);
        rotate.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rotate.setPower(0.25); if (rotate.getMode()!= DcMotor.RunMode.RUN_TO_POSITION){
            rotate.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
        */
            dashboard.addData("deg", tag.ftcPose.bearing);

            dashboard.addData("pos", rotate.getCurrentPosition());
        }
    }
    public void test1(Gamepad gamepad1){
        int x = gamepad1.square? 1:0;
        transfer.setPosition(x);
    }
    public void test2(){
        rotate.setTargetPosition(90);
        rotate.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rotate.setPower(0.1);
    }
}

