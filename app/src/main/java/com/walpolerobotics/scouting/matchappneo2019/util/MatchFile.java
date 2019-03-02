package com.walpolerobotics.scouting.matchappneo2019.util;

import android.os.Environment;

import java.io.File;
import java.io.IOException;

public class MatchFile {

    private static final String MATCH_FILE_DIRECTORY_NAME = "MatchAppNeo";
    private static final String MATCH_FILE_LEADING_FLAG = "nmd";
    private static final String MATCH_FILE_EXTENSION = ".csv";

    /**
     * Formulates a filename using the general conventions for Match data files
     *
     * @param event Event Text Code to be placed in filename
     * @param tablet Tablet Text Code to be placed in filename
     * @param num Match File Number to be placed in filename
     * @return A properly formatted Match data filename
     */
    public static String getMatchFileName(String event, String tablet, int num) {
        return MATCH_FILE_LEADING_FLAG + "-" + event + "-" + tablet + "-" + String.valueOf(num)
                + MATCH_FILE_EXTENSION;
    }

    /**
     * Retrieves the current Match file and creates a new one if one does not currently exist
     *
     * @param eventFilter Event Text Code to filter by
     * @param tabletFilter Tablet Text Code to filter by
     * @return The Match file currently in use or null if one did not exist and could not be created
     */
    public static File getCurrentMatchFile(String eventFilter, String tabletFilter) {
        File dir = new File(Environment.getExternalStorageDirectory(), MATCH_FILE_DIRECTORY_NAME);
        dir.mkdirs();
        File[] files = dir.listFiles(new MatchFileFilter(eventFilter, tabletFilter));

        File target = null;
        int targetNum = 0;

        for (File f : files) {
            String name = f.getName().split("\\.")[0];
            String[] values = name.split("-");

            int num = Integer.valueOf(values[3]);

            if (num >= targetNum) {
                target = f;
            }
        }

        if (target == null) {
            target = new File(dir, getMatchFileName(eventFilter, tabletFilter, 1));
            try {
                target.createNewFile();
            } catch (IOException e) {
                return null;
            }
        }

        return target;
    }
}
