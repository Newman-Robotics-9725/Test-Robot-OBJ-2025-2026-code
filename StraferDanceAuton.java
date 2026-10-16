package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "Strafer Dance Auton", group = "Fun")
public class StraferDanceAuton extends LinearOpMode {

    // Update these names to match your Robot Configuration in FTC Driver Station.
    private static final String FRONT_LEFT_NAME = "drivefl";
    private static final String FRONT_RIGHT_NAME = "drivefr";
    private static final String BACK_LEFT_NAME = "drivebl";
    private static final String BACK_RIGHT_NAME = "drivebr";

    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor backLeft;
    private DcMotor backRight;

    @Override
    public void runOpMode() throws InterruptedException {
        mapDriveMotors();
        configureDriveMotors();

        telemetry.addLine("Strafer dance ready.");
        telemetry.addLine("Press Play to start.");
        telemetry.update();

        waitForStart();
        if (isStopRequested()) {
            return;
        }

        // Intro: quicker strafe taps to "wake up" the routine.
        pulse(0.0, 0.65, 0.0, 260, 6, 110);

        // Spin combo
        drive(0.0, 0.0, 0.65, 700);
        drive(0.0, 0.0, -0.65, 700);
        drive(0.0, 0.0, 0.80, 500);

        // Forward and back bounce
        pulse(0.65, 0.0, 0.0, 300, 3, 120);
        pulse(-0.65, 0.0, 0.0, 300, 3, 120);

        // Diagonal groove: strafe + slight turn
        drive(0.25, 0.55, 0.20, 600);
        drive(-0.25, -0.55, -0.20, 600);
        drive(0.25, -0.55, -0.20, 600);
        drive(-0.25, 0.55, 0.20, 600);

        // Box-step pattern
        drive(0.45, 0.25, 0.0, 650);
        drive(-0.45, 0.25, 0.0, 650);
        drive(-0.45, -0.25, 0.0, 650);
        drive(0.45, -0.25, 0.0, 650);

        // Shimmy section with a little twist
        pulse(0.10, 0.70, 0.18, 220, 4, 90);
        pulse(-0.10, -0.70, -0.18, 220, 4, 90);

        // Slow spotlight spins
        drive(0.0, 0.0, 0.45, 1100);
        drive(0.0, 0.0, -0.45, 1100);

        // Finale: arc, reverse arc, then fast spin and freeze
        drive(0.30, 0.0, 0.70, 900);
        drive(-0.30, 0.0, -0.70, 900);
        drive(0.0, 0.0, 0.95, 1400);
        stopDrive();

        telemetry.addLine("Dance complete.");
        telemetry.update();
        sleep(500);
    }

    private void mapDriveMotors() {
        frontLeft = hardwareMap.get(DcMotor.class, FRONT_LEFT_NAME);
        frontRight = hardwareMap.get(DcMotor.class, FRONT_RIGHT_NAME);
        backLeft = hardwareMap.get(DcMotor.class, BACK_LEFT_NAME);
        backRight = hardwareMap.get(DcMotor.class, BACK_RIGHT_NAME);
    }

    private void configureDriveMotors() {
        // Typical mecanum direction setup (left side reversed).
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.FORWARD);

        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    private void pulse(double forward, double strafe, double turn,
                       long moveMs, int reps, long restMs) throws InterruptedException {
        for (int i = 0; i < reps && opModeIsActive(); i++) {
            drive(forward, strafe, turn, moveMs);
            stopDrive();
            sleep(restMs);
        }
    }

    private void drive(double forward, double strafe, double turn,
                       long durationMs) throws InterruptedException {
        if (!opModeIsActive()) {
            return;
        }

        double frontLeftPower = forward + strafe + turn;
        double frontRightPower = forward - strafe - turn;
        double backLeftPower = forward - strafe + turn;
        double backRightPower = forward + strafe - turn;

        double max = Math.max(
                1.0,
                Math.max(
                        Math.abs(frontLeftPower),
                        Math.max(
                                Math.abs(frontRightPower),
                                Math.max(Math.abs(backLeftPower), Math.abs(backRightPower))
                        )
                )
        );

        frontLeft.setPower(frontLeftPower / max);
        frontRight.setPower(frontRightPower / max);
        backLeft.setPower(backLeftPower / max);
        backRight.setPower(backRightPower / max);

        sleep(durationMs);
        stopDrive();
    }

    private void stopDrive() {
        frontLeft.setPower(0.0);
        frontRight.setPower(0.0);
        backLeft.setPower(0.0);
        backRight.setPower(0.0);
    }
}
