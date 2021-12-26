package apfs.util;

import apfs.APFSElement;

import java.util.Comparator;

public class TimeStampComparator implements Comparator<APFSElement> {
    @Override
    public int compare(APFSElement o1, APFSElement o2) {
        return o2.getLastModified().compareTo(o1.getLastModified());
    }
}
