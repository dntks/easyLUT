package hu.don.easylut;

import hu.don.easylut.filter.Filter;
import hu.don.easylut.filter.FilterNon;
import hu.don.easylut.filter.LutFilterFromBitmap;
import hu.don.easylut.filter.LutFilterFromResource;

public class EasyLUT {

    public static LutFilterFromResource.Builder fromhResourceId() {
        return new LutFilterFromResource.Builder();
    }

    public static LutFilterFromBitmap.Builder fromBitmap() {
        return new LutFilterFromBitmap.Builder();
    }

    public static Filter createNonFilter() {
        return new FilterNon();
    }

}
