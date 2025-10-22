package org.firstinspires.ftc.teamcode.Components;

import android.util.Size;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;


import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

public class Outake {
    private Telemetry telemetry;
    private DcMotorEx shoot,rotate;
    private WebcamName webcam1;
    public AprilTagProcessor tagProcessor;
    private VisionPortal vPortal;
    private AprilTagDetection tag;
    double fx,fy,cx,cy;

    public Outake(DcMotorEx shoot, DcMotorEx rotate) {
        this.shoot = shoot;
        this.rotate = rotate;

        rotate.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rotate.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


    }

    public void autoShooter() {
            if (!tagProcessor.getDetections().isEmpty()) {
                AprilTagDetection tag = tagProcessor.getDetections().get(0);
                if (tag.ftcPose != null && tag.id==22) {
                    double x = Math.toDegrees(tag.ftcPose.bearing);
                    double y = 360.0 / 537.6;
                    int target = (int) (x * y);

                    rotate.setTargetPosition(target);
                    rotate.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    rotate.setPower(1);

                    telemetry.addData("Motor Target", target);
                    telemetry.addData("Motor pos", rotate.getCurrentPosition());
                    telemetry.addData("Distance", tag.ftcPose.range);
                    telemetry.addData("Bearing (deg)", x);
                    telemetry.addData("Tag ID", tag.id);
                    telemetry.update();
                }

            }
        }

        public void Shooter(Gamepad Gamepad1){
            boolean x = Gamepad1.square;

            int y = x? 1:0;
                shoot.setPower(-y);


        }

    }
