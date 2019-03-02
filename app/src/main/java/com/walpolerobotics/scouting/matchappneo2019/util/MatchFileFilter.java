package com.walpolerobotics.scouting.matchappneo2019.util;

import java.io.File;
import java.io.FileFilter;

public class MatchFileFilter implements FileFilter {

    private String mEventFilter;
    private String mTabletFilter;

    public MatchFileFilter() {

    }

    public MatchFileFilter(String eventFilter, String tabletFilter) {
        mEventFilter = eventFilter;
        mTabletFilter = tabletFilter;
    }

    @Override
    public boolean accept(File file) {
        String fullName = file.getName();
        String[] parts = fullName.split("\\.");
        String name = parts[0];
        String[] values = name.split("-");
        String extension = parts[1];

        try {
            Integer.valueOf(values[3]);
        } catch (NumberFormatException e) {
            return false;
        }

        if (mEventFilter != null && !values[1].equals(mEventFilter)) {
            return false;
        }

        if (mTabletFilter != null && !values[2].equals(mTabletFilter)) {
            return false;
        }

        return values[0].equals("nmd") && extension.equals("csv");
    }
}
