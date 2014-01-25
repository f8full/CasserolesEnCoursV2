/*------------------------------------------------------------------------------
 **     Ident: Fabrice V.
 **    Author: Fabrice V.
 ** Copyright: (c) January 24, 2014 Fabrice Veniard All Rights Reserved.
 **------------------------------------------------------------------------------
 ** Fabrice V.                       |  No part of this file may be reproduced
 ** @f8full                          |  or transmitted in any form or by any
 **                                  |  means, electronic or mechanical, for the
 ** H2J Montréal                     |  purpose, without the express written
 ** Québec                           |  permission of the copyright holder.
 *------------------------------------------------------------------------------
 *
 *   This file is part of casserolesencours.
 *
 *   casserolesencours is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   casserolesencours is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with casserolesencours.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package com.f8full.casserolesencours;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;


public class CasserolesEnCoursV2ViewFragment extends MapFragment {

    MapView mView;
    GoogleMap mMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        RelativeLayout layout = (RelativeLayout) inflater.inflate(R.layout.fragment_map,
                container, false);
        mView = (MapView) layout.findViewById(R.id.map);
        mView.onCreate(savedInstanceState);

        if(checkMap())
        {
            mMap.setMyLocationEnabled(true);
        }

        return layout;
    }

    @Override
    public void onResume() {
        super.onResume();
        mView.onResume();

        checkMap();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mView.onDestroy();
    }

    @Override
    public void onPause() {
        super.onPause();
        mView.onPause();
    }

    private boolean checkMap() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null)
        {
            mMap = mView.getMap();
        }

        return mMap != null;
    }



    /*@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.map_activity);

        // Get a handle to the Map Fragment
        GoogleMap map = ((MapFragment) getFragmentManager()
                .findFragmentById(R.id.map)).getMap();


        LatLng sydney = new LatLng(-33.867, 151.206);

        map.setMyLocationEnabled(true);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 13));

        map.addMarker(new MarkerOptions()
                .title("Sydney")
                .snippet("The most populous city in Australia.")
                .position(sydney));
    }*/


}
