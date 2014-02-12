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

import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.http.HttpTransport;
import com.google.api.services.fusiontables.Fusiontables;
import com.google.api.services.fusiontables.model.Table;

import java.io.IOException;


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

    public void setupData(Table tableData){

    }

    private boolean checkMap() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null)
        {
            mMap = mView.getMap();
        }

        return mMap != null;
    }

//    private void retrieveData()
//    {
//        String fromTableID = "1cmlx9aChHUYTWwYivaZucr7NHNsP_ulvEPX1FoM";
//
//        final String SqlQuery = "SELECT Date, Location, Description FROM "
//                + fromTableID + " "
//                //+ whereClause
//                + "ORDER BY Date DESC";// LIMIT "
//                //+ ((EditText)findViewById(R.id.nbIconsMax)).getText();
//
//        new Thread(new Runnable() {
//
//            public void run() {
//                try {
//
//                    ////////////////////////////////////////////////////////////////////////////
//
//                    String encodedQuery = URLEncoder.encode(SqlQuery, "UTF-8");
//
//
//                    AbstractGoogleJsonClient truc = G
//
//                    GoogleUrl GUrl = new GoogleUrl(SERVICE_URL + "?sql=" + encodedQuery + "&encid=true");
//
//
//
//                    HttpRequest request = mGOOGClient.getRequestFactory().buildGetRequest(GUrl);
//                    HttpHeaders headers = new HttpHeaders();
//
//                    headers.setContentLength("0");//Required so that Fusion Table API considers request
//                    request.setHeaders(headers);
//
//                    HttpResponse response = request.execute();
//
//                    if(response.getStatusCode() == 200)
//                    {
//                        //Here I have my data
//                        InputStreamReader inputStreamReader = new InputStreamReader(response.getContent());
//                        BufferedReader bufferedStreamReader = new BufferedReader(inputStreamReader);
//                        CSVReader reader = new CSVReader(bufferedStreamReader);
//                        // The first line is the column names, and the remaining lines are the rows.
//                        List<String[]> csvLines = reader.readAll();
//
//                        List<String> columns = Arrays.asList(csvLines.get(0));
//                        List<String[]> rows = csvLines.subList(1, csvLines.size());
//
//                        if(rows.size() == 0)
//                        {
//                            toastMessage("Table vide !");
//                            return;
//                        }
//
//
//                        ArrayList<String> rowData = new ArrayList<String>();
//
//                        for(String[] row : rows)
//                        {
//                            String toAdd = "";
//                            for(String cell : row)
//                            {
//                                //No , in data, or things are gonna go horribly wrong here
//                                toAdd += cell + "|";
//                            }
//
//                            //I have this last pesky separator ,it will give me an empty String on the other side
//                            rowData.add(toAdd);
//                        }
//
//                        Intent mapIntent = new Intent(getApplicationContext(), CasserolesEnCoursViewerActivity.class);
//                        mapIntent.putStringArrayListExtra("rowsData", rowData);
//                        mapIntent.putStringArrayListExtra("CUTVRows", CUTVRowData);
//                        mapIntent.putExtra("timeColored", timeColored);
//                        mapIntent.putExtra("myLocation", myLocationEnabled);
//                        mapIntent.putExtra("CUTVOnly", cutvOnly);
//
//                        if(myLocationEnabled)
//                            toastMessage(getString(R.string.lastContributionTapHintLocal));
//                        else
//                            toastMessage(getString(R.string.lastContributionTapHintWorld));
//                        //mapIntent.putExtra("relativeTime", ((CheckBox)findViewById(R.id.relativeTimeCheckbox)).isChecked());
//                        startActivity(mapIntent);
//
//
//                    }
//
//
//                }
//                catch (HttpResponseException e)
//                {
//                    if (e.getStatusCode() == 401)
//                    {
//                        mGOOGCredential.setAccessToken(null);
//
//                        SharedPreferences.Editor editor2 = mPrefs.edit();
//                        editor2.remove(PREF_REFRESH_TOKEN);
//                        editor2.commit();
//
//
//                        toastMessage("OAuth login required, redirecting...");
//
//                        //This last Constant is weird
//                        startActivityForResult(new Intent().setClass(getApplicationContext(),OAuth2AccessTokenActivity.class), REQUEST_OAUTH2_AUTHENTICATE);
//
//                    }
//
//                } catch (IOException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
//
//                mProviderIDMapCustomViewIdsSemaphore = true;
//            }
//        }).start();
//    }



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
