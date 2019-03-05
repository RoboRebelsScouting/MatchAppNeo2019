package com.walpolerobotics.scouting.matchappneo2019.util;

public class Match {

    public int matchNumber;
    public int robotNumber;
    public String eventName;
    public String scouterName;

    public static final String[] POSITION_DESCRIPTORS = new String[] {"BLUE1", "BLUE2", "BLUE3",
            "RED1", "RED2", "RED3"};

    public static final int BLUE_1 = 0;
    public static final int BLUE_2 = 1;
    public static final int BLUE_3 = 2;
    public static final int RED_1 = 3;
    public static final int RED_2 = 4;
    public static final int RED_3 = 5;

    public int startingPosition;

    public static final int HAB_NONE = 0;
    public static final int HAB_L1 = 1;
    public static final int HAB_L2 = 2;

    public int crossHAB;

    public int sandstormCargoShipCargo;
    public int scr1;
    public int scr2;
    public int scr3;
    public int sandstormCargoShipHatches;
    public int shr1;
    public int shr2;
    public int shr3;
    public int cargoPickedUp;
    public int cargoDropped;
    public int hPickedUp;
    public int hDropped;
    public int teleopCargoShipCargo;
    public int tcr1;
    public int tcr2;
    public int tcr3;
    public int teleopCargoShipHatches;
    public int thr1;
    public int thr2;
    public int thr3;

    public static final int CLIMB_NONE = 0;
    public static final int CLIMB_L1 = 1;
    public static final int CLIMB_L2 = 2;
    public static final int CLIMB_L3 = 3;

    public int climb;

    public int numFouls;
    public boolean playedDefense;
    public boolean brokenHatchCollector;
    public boolean brokenCargoCollector;
    public boolean brokenDrivetrain;
    public boolean brokenClimber;

    public static int getStartingPosition(int alliance, int position) {
        return (alliance * 3) + position;
    }

    public static int[] getStartingPosition(int position) {
        return new int[]{position / 3, position % 3};
    }

    public static String getPositionDescriptor(int position) {
        return POSITION_DESCRIPTORS[position];
    }

}
