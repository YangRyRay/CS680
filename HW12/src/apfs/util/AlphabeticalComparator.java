package apfs.util;

import apfs.APFSElement;

import java.util.Comparator;

public class AlphabeticalComparator implements Comparator<APFSElement> {

    @Override
    public int compare(APFSElement o1, APFSElement o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
