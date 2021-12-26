package apfs.util;

import apfs.APFSElement;

import java.util.Comparator;

public class ReverseAlphabeticalComparator implements Comparator<APFSElement> {
    @Override
    public int compare(APFSElement o1, APFSElement o2) {
        return o2.getName().compareTo(o1.getName());
    }
}
