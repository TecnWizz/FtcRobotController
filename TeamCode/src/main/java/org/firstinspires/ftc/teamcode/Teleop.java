package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "avocado teleop")
public class Teleop extends LinearOpMode {
    private static final double TICKS_PER_ROTATION = 2786.2;
    private static final double ROTATION_DIVISOR = 2;
    private static final double DEFAULT_POWER_MODE = 1.5;
    private static final double ALTERNATE_POWER_MODE = 3;

    private DcMotor frontLeftMotor, frontRightMotor, backLeftMotor, backRightMotor;
    private DcMotor intakeMotor, slideShiftMotor, extendMotor;
    private Servo rotateServo, clawServo;

    @Override
    public void runOpMode() throws InterruptedException {
        initializeHardware();
        double powerMode = DEFAULT_POWER_MODE;
        double targetPosition = TICKS_PER_ROTATION / ROTATION_DIVISOR;

        waitForStart();

        while (opModeIsActive()) {
            handleDriving(powerMode);
            handleExtendMotorControl(targetPosition);
            handleSlideShiftMotorControl(targetPosition);
            handleServoControl();
            handleIntakeMotorControl();
        }
    }

    private void initializeHardware() {
        rotateServo = hardwareMap.servo.get("rotateServo");
        clawServo = hardwareMap.servo.get("clawServo");
        intakeMotor = hardwareMap.dcMotor.get("intakeMotor");
        slideShiftMotor = hardwareMap.dcMotor.get("slideShiftMotor");
        extendMotor = hardwareMap.dcMotor.get("extendMotor");

        frontLeftMotor = hardwareMap.dcMotor.get("frontLeftMotor");
        backLeftMotor = hardwareMap.dcMotor.get("backLeftMotor");
        frontRightMotor = hardwareMap.dcMotor.get("frontRightMotor");
        backRightMotor = hardwareMap.dcMotor.get("backRightMotor");

        frontLeftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        setMotorZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE, frontLeftMotor, frontRightMotor, backLeftMotor, backRightMotor, slideShiftMotor, extendMotor);

        resetEncoders(slideShiftMotor, extendMotor);

        rotateServo.setPosition(1);
        clawServo.setPosition(1);
    }

    private void setMotorZeroPowerBehavior(DcMotor.ZeroPowerBehavior behavior, DcMotor... motors) {
        for (DcMotor motor : motors) {
            motor.setZeroPowerBehavior(behavior);
        }
    }

    private void resetEncoders(DcMotor... motors) {
        for (DcMotor motor : motors) {
            motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }

    private void handleDriving(double powerMode) {
        double drive = -gamepad1.left_stick_y;
        double strafe = gamepad1.left_stick_x;
        double rotate = gamepad1.right_stick_x;

        double frontLeftPower = (drive + strafe + rotate) / powerMode;
        double frontRightPower = (drive - strafe - rotate) / powerMode;
        double backLeftPower = (drive - strafe + rotate) / powerMode;
        double backRightPower = (drive + strafe - rotate) / powerMode;

        setMotorPowers(frontLeftPower, frontRightPower, backLeftPower, backRightPower);

        if (gamepad1.dpad_up) {
            powerMode = DEFAULT_POWER_MODE;
        } else if (gamepad1.dpad_down) {
            powerMode = ALTERNATE_POWER_MODE;
        }

        telemetry.addData("PowerMode", powerMode);
        telemetry.update();
    }

    private void setMotorPowers(double frontLeft, double frontRight, double backLeft, double backRight) {
        frontLeftMotor.setPower(frontLeft);
        frontRightMotor.setPower(frontRight);
        backLeftMotor.setPower(backLeft);
        backRightMotor.setPower(backRight);
    }

    private void handleExtendMotorControl(double targetPosition) {
        if (gamepad2.left_stick_y != 0) {
            extendMotor.setTargetPosition((int) (gamepad2.left_stick_y > 0 ? targetPosition : -targetPosition));
            extendMotor.setPower(gamepad2.left_stick_y > 0 ? 0.3 : -0.3);
            extendMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            extendMotor.setPower(0);
        }
    }

    private void handleSlideShiftMotorControl(double targetPosition) {
        if (gamepad2.right_stick_y != 0) {
            slideShiftMotor.setTargetPosition((int) (gamepad2.right_stick_y > 0 ? targetPosition : -targetPosition));
            slideShiftMotor.setPower(gamepad2.right_stick_y > 0 ? 1 : -1);
            slideShiftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            slideShiftMotor.setPower(0);
        }
    }

    private void handleServoControl() {
        if (gamepad2.square) {
            rotateServo.setPosition(0);
        } else if (gamepad2.circle) {
            rotateServo.setPosition(0.98);
        }

        if (gamepad2.right_bumper) {
            clawServo.setPosition(1);
        } else if (gamepad2.left_bumper) {
            clawServo.setPosition(0);
        }
    }

    private void handleIntakeMotorControl() {
        if (gamepad2.right_trigger > 0) {
            intakeMotor.setPower(gamepad2.right_trigger / 1.5);
        } else if (gamepad2.left_trigger > 0) {
            intakeMotor.setPower(-gamepad2.left_trigger / 1.5);
        } else {
            intakeMotor.setPower(0);
        }
    }
}
