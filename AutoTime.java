package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.DriveL;

@Autonomous(name = "AUTON", group = "Auto")
public class AutoTime extends LinearOpMode {

    private DriveL drive;

    @Override
    public void runOpMode() {

        drive = new DriveL(hardwareMap);

        telemetry.update();

        waitForStart();
        if (isStopRequested()) return;

        drive.driveForTime(0.5, 0.5, 1200);
        sleep(300);

        drive.driveForTime(0.5, 0.5, 800);
        drive.driveForTime(-0.5, -0.5, 400);
        sleep(200);

        drive.driveForTime(-0.6, 0.6, 600);  
        drive.driveForTime(0.6, -0.6, 600);  
        sleep(200);

        for (int i = 0; i < 4; i++) {
            drive.driveForTime(0.4, -0.4, 250); 
            drive.driveForTime(-0.4, 0.4, 250); 
        }
        sleep(200);

        for (int i = 0; i < 3; i++) {
            drive.driveForTime(0.5, 0.5, 150);
            drive.driveForTime(-0.5, -0.5, 150);
        }
        sleep(200);

        for (int i = 0; i < 3; i++) {
            drive.driveForTime(0.5, 0.2, 400);  
            drive.driveForTime(0.5, -0.2, 400);  
        }
        sleep(200);

        drive.driveForTime(-0.4, 0.4, 1000);

        telemetry.update();
    }
}
