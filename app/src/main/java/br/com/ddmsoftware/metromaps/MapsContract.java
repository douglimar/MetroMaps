package br.com.ddmsoftware.metromaps;

import android.provider.BaseColumns;

class MapsContract {

    private MapsContract(){


    }


    public static final class MapsEntry implements BaseColumns {

        public static final String TABLE_NAME = "favoriteMaps";
        public static final String COLUMN_MAPS_ID = "maps_Id";
        public static final String COLUMN_MAPS_NAME = "maps_Name";

    }

}
